package br.com.pucminas.healthcore.application.consulta.usecase;

import br.com.pucminas.healthcore.adapter.consulta.model.AgendaResponse;
import br.com.pucminas.healthcore.adapter.consulta.model.ConsultaRequest;
import br.com.pucminas.healthcore.adapter.consulta.model.ConsultaResponse;
import br.com.pucminas.healthcore.domain.enumerate.TipoEspecialidade;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaUseCase {
    ConsultaResponse salvar(ConsultaRequest consulta);
    List<ConsultaResponse> obterConsultas(String crm);
    ConsultaResponse aprovarOuRejeitarConsulta(boolean aprovar, String idConsulta);
    List<AgendaResponse> obterAgendaPorEspecilidade(TipoEspecialidade especialidade, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, String cpf);
    List<ConsultaResponse> consultasConfirmadas(String cpf);
}
