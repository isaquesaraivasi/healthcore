package br.com.pucminas.healthcore.application.paciente.service;

import br.com.pucminas.healthcore.adapter.paciente.model.PacienteRequest;
import br.com.pucminas.healthcore.adapter.paciente.model.PacienteResponse;
import br.com.pucminas.healthcore.application.exception.NotFoundException;
import br.com.pucminas.healthcore.application.paciente.converter.PacienteConverter;
import br.com.pucminas.healthcore.application.paciente.factory.PacienteFactory;
import br.com.pucminas.healthcore.commons.utils.AuthUtil;
import br.com.pucminas.healthcore.domain.model.paciente.PacienteModel;
import br.com.pucminas.healthcore.infrastructure.repository.paciente.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    public static final String PACIENTE_NAO_ENCONTRADO = "Paciente não encontrado";
    private final PacienteRepository pacienteRepository;
    private final AuthUtil authUtil;
    private final PacienteFactory pacienteFactory;
    private final PacienteConverter pacienteConverter;

    @Override
    public PacienteResponse salvar(PacienteRequest pacienteRequest) {

        PacienteModel paciente = pacienteFactory.criar(pacienteRequest);
        paciente.setSenha(authUtil.encriptarSenha(paciente.getSenha()));
        PacienteModel savedPaciente = pacienteRepository.save(paciente);

        return pacienteConverter.pacienteModelToPacienteResponse(savedPaciente);
    }

    @Override
    public PacienteResponse atualizar(String id, PacienteRequest pacienteRequest) {

        PacienteModel paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PACIENTE_NAO_ENCONTRADO));
        paciente.setNome(pacienteRequest.getNome());
        paciente.setCpf(pacienteRequest.getCpf());
        paciente.setEmail(pacienteRequest.getEmail());
        if (!pacienteRequest.getSenha().isEmpty()) {
            paciente.setSenha(authUtil.encriptarSenha(pacienteRequest.getSenha()));
        }
        PacienteModel updatedPaciente = pacienteRepository.save(paciente);
        return pacienteConverter.pacienteModelToPacienteResponse(updatedPaciente);
    }

    @Override
    public PacienteResponse buscarPorId(String id) {
        PacienteModel paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PACIENTE_NAO_ENCONTRADO));
        return pacienteConverter.pacienteModelToPacienteResponse(paciente);
    }

    @Override
    public void deletar(String id) {
        PacienteModel paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PACIENTE_NAO_ENCONTRADO));
        pacienteRepository.delete(paciente);
    }

    @Override
    public boolean validarCredenciais(String cpf, String senha) {
        PacienteModel paciente = pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException(PACIENTE_NAO_ENCONTRADO));
        return authUtil.validarSenha(senha, paciente.getSenha());
    }

    @Override
    public List<PacienteResponse> buscarTodos() {
        return pacienteRepository.findAll().stream()
                .map(pacienteConverter::pacienteModelToPacienteResponse)
                .collect(Collectors.toList());
    }
}