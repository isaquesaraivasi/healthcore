package br.com.pucminas.healthcore.application.medico.service;

import br.com.pucminas.healthcore.adapter.consulta.model.AgendaRequest;
import br.com.pucminas.healthcore.adapter.consulta.model.AgendaResponse;
import br.com.pucminas.healthcore.adapter.medico.model.MedicoRequest;
import br.com.pucminas.healthcore.adapter.medico.model.MedicoResponse;
import br.com.pucminas.healthcore.domain.enumerate.TipoEspecialidade;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicoService {
    MedicoResponse salvar(MedicoRequest medico);
    MedicoResponse atualizar(String id, MedicoRequest medicoRequest);
    MedicoResponse buscarPorId(String id);
    List<MedicoResponse> buscarTodos();
    void deletar(String id);
    boolean validarCredenciais(String crm, String senha);
    List<AgendaResponse> findAvailableAgendasByEspecialidadeAndPeriodo(TipoEspecialidade especialidade, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, String cpf);
    void cadastrarAgenda(String medicoId, List<AgendaRequest> agendas);

}