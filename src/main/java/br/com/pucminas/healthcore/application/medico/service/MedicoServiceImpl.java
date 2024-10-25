package br.com.pucminas.healthcore.application.medico.service;

import br.com.pucminas.healthcore.adapter.consulta.model.AgendaResponse;
import br.com.pucminas.healthcore.adapter.medico.model.MedicoRequest;
import br.com.pucminas.healthcore.adapter.medico.model.MedicoResponse;
import br.com.pucminas.healthcore.application.cep.service.CepService;
import br.com.pucminas.healthcore.application.exception.NotFoundException;
import br.com.pucminas.healthcore.application.medico.converter.AgendaConverter;
import br.com.pucminas.healthcore.application.medico.converter.MedicoConverter;
import br.com.pucminas.healthcore.application.medico.factory.MedicoFactory;
import br.com.pucminas.healthcore.adapter.consulta.model.AgendaRequest;

import br.com.pucminas.healthcore.commons.utils.AuthUtil;
import br.com.pucminas.healthcore.domain.enumerate.TipoEspecialidade;
import br.com.pucminas.healthcore.domain.model.medico.MedicoModel;
import br.com.pucminas.healthcore.domain.model.medico.AgendaModel;


import br.com.pucminas.healthcore.domain.model.paciente.PacienteModel;
import br.com.pucminas.healthcore.infrastructure.repository.medico.AgendaRepository;
import br.com.pucminas.healthcore.infrastructure.repository.medico.MedicoRepository;
import br.com.pucminas.healthcore.infrastructure.repository.paciente.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService {

    public static final String MEDICO_NAO_ENCONTRADO = "Médico não encontrado";
    private final MedicoRepository medicoRepository;
    private final AgendaRepository agendaRepository;
    private final AuthUtil authUtil;
    private final MedicoFactory medicoFactory;
    private final MedicoConverter medicoConverter;
    private final AgendaConverter agendaConverter;
    private final CepService cepService;
    private final PacienteRepository pacienteRepository;

    @Override
    public MedicoResponse salvar(MedicoRequest medicoRequest) {

        MedicoModel medico = medicoFactory.criar(medicoRequest);
        medico.setSenha(authUtil.encriptarSenha(medico.getSenha()));
        MedicoModel savedMedico = medicoRepository.save(medico);
        List<AgendaModel> agendas = criarAgendas(savedMedico);
        agendaRepository.saveAll(agendas);

        return medicoConverter.medicoModelToMedicoResponse(savedMedico);
    }

    @Override
    public MedicoResponse atualizar(String id, MedicoRequest medicoRequest) {
        MedicoModel medico = medicoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MEDICO_NAO_ENCONTRADO));
        medico.setNome(medicoRequest.getNome());
        medico.setCrm(medicoRequest.getCrm());
        medico.setEspecialidade(medicoRequest.getEspecialidade());
        if (!medicoRequest.getSenha().isEmpty()) {
            medico.setSenha(authUtil.encriptarSenha(medicoRequest.getSenha()));
        }
        MedicoModel updatedMedico = medicoRepository.save(medico);
        return medicoConverter.medicoModelToMedicoResponse(updatedMedico);
    }

    @Override
    public MedicoResponse buscarPorId(String id) {
        MedicoModel medico = medicoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MEDICO_NAO_ENCONTRADO));
        return medicoConverter.medicoModelToMedicoResponse(medico);
    }

    @Override
    public List<MedicoResponse> buscarTodos() {
        return medicoRepository.findAll().stream()
                .map(medicoConverter::medicoModelToMedicoResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deletar(String id) {
        MedicoModel medico = medicoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MEDICO_NAO_ENCONTRADO));
        medicoRepository.delete(medico);
    }

    @Override
    public boolean validarCredenciais(String crm, String senha) {
        MedicoModel medico = medicoRepository.findByCrm(crm)
                .orElseThrow(() -> new NotFoundException("Médico nagendas = {ArrayList@17552}  size = 99ão encontrado"));
        return authUtil.validarSenha(senha, medico.getSenha());
    }

    @Override
    public List<AgendaResponse> findAvailableAgendasByEspecialidadeAndPeriodo(TipoEspecialidade especialidade, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, String cpf) {

        PacienteModel paciente = pacienteRepository.findByCpf(cpf).orElseThrow(()->new RuntimeException("Paciente não localizado."));

        List<AgendaModel> agendas = agendaRepository.findAvailableAgendasByEspecialidadeAndSituacao(especialidade, dataHoraInicio, dataHoraFim);

        List<AgendaResponse> agendasResponse =

                agendas.stream().map(agenda -> {
                    AgendaResponse response = agendaConverter.agendaModelToAgendaResponse(agenda);
                    double distancia = cepService.calcularDistanciaEntreCeps(agenda.getMedico().getCep(), paciente.getCep());
                    response.setDistancia(String.valueOf(distancia));
                    return response;
                })
                .collect(Collectors.toList());

        return agendasResponse;

    }

    @Override
    public void cadastrarAgenda(String medicoId, List<AgendaRequest> agendas) {
        MedicoModel medico = medicoRepository.findByCrm(medicoId)
                .orElseThrow(() -> new NotFoundException(MEDICO_NAO_ENCONTRADO));
        
        List<AgendaModel> agendasSalvar = agendas.stream()
                       .map(agendaRequest -> {

                            LocalDateTime dataHoraInicio = agendaRequest.getDataHoraInicio();
                            LocalDateTime dataHoraFim = dataHoraInicio.plusMinutes(50);
                            
                             boolean exists = agendaRepository.existsByMedicoAndDataHoraInicio(medico, dataHoraInicio);
                             if (exists) {
                                return null;
                             }
                             AgendaModel agenda = agendaConverter.agendaRequestToAgendaModel(agendaRequest);
                                         agenda.setId(UUID.randomUUID().toString());
                                         agenda.setMedico(medico);
                                             agenda.setDataHoraFim(dataHoraFim);
                                             return agenda;
                             })
                              .filter(agenda -> agenda != null)
                              .collect(Collectors.toList());

        agendaRepository.saveAll(agendasSalvar);
    }

    private List<AgendaModel> criarAgendas(MedicoModel medico) {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime umMesDepois = agora.plusMonths(1);
        LocalTime horaInicio = LocalTime.of(9, 0);
        LocalTime horaFim = LocalTime.of(17, 0);

        return Stream.iterate(agora, date -> date.plusDays(1))
                .limit(umMesDepois.toLocalDate().toEpochDay() - agora.toLocalDate().toEpochDay())
                .filter(date -> date.getDayOfWeek().getValue() >= 1 && date.getDayOfWeek().getValue() <= 5)
                .flatMap(date -> {
                    LocalDateTime startTime = date.with(horaInicio);
                    LocalDateTime endTime = date.with(horaFim);
                    return Stream.iterate(startTime, time -> time.plusMinutes(50))
                            .limit((endTime.toLocalTime().toSecondOfDay() - startTime.toLocalTime().toSecondOfDay()) / 3000L)
                            .map(time -> AgendaModel.builder()
                                    .id(String.valueOf(UUID.randomUUID()))
                                    .medico(medico)
                                    .dataHoraInicio(time)
                                    .situacao(true)
                                    .dataHoraFim(time.plusMinutes(50))
                                    .build());
                })
                .collect(Collectors.toList());
    }


}
