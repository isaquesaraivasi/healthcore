package br.com.pucminas.healthcore.application.consulta.usecase;

import br.com.pucminas.healthcore.adapter.consulta.model.AgendaResponse;
import br.com.pucminas.healthcore.adapter.consulta.model.ConsultaRequest;
import br.com.pucminas.healthcore.adapter.consulta.model.ConsultaResponse;
import br.com.pucminas.healthcore.application.consulta.service.ConsultaService;
import br.com.pucminas.healthcore.application.medico.service.MedicoService;
import br.com.pucminas.healthcore.domain.enumerate.TipoEspecialidade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ConsultaUseCaseImpl implements ConsultaUseCase {

    private final ConsultaService service;

    private final MedicoService medicoService;

    @Override
    public ConsultaResponse salvar(ConsultaRequest consulta) {
        return service.salvar(consulta);
    }
    @Override
    public List<ConsultaResponse> obterConsultas(String crm) {
        return service.obterConsultas(crm);
    }
    @Override
    public ConsultaResponse aprovarOuRejeitarConsulta(boolean aprovar, String idConsulta) {
        return service.aprovarOuRejeitarConsulta(aprovar, idConsulta);
    }
    @Override
    public List<AgendaResponse> obterAgendaPorEspecilidade(TipoEspecialidade especialidade, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, String cpf) {
        return medicoService.findAvailableAgendasByEspecialidadeAndPeriodo(especialidade, dataHoraInicio, dataHoraFim, cpf);
    }
    @Override
    public List<ConsultaResponse> consultasConfirmadas(String cpf) {
        return service.consultasConfirmadas(cpf);
    }

}
