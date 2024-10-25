package br.com.pucminas.healthcore.application.medico.usecase;

import br.com.pucminas.healthcore.adapter.consulta.model.AgendaRequest;
import br.com.pucminas.healthcore.adapter.medico.model.MedicoRequest;
import br.com.pucminas.healthcore.adapter.medico.model.MedicoResponse;

import java.util.List;

public interface MedicoUseCase {
    MedicoResponse salvar(MedicoRequest medico);
    MedicoResponse atualizar(String id, MedicoRequest medico);
    MedicoResponse buscarPorId(String id);
    List<MedicoResponse> buscarTodos();
    void deletar(String id);
    void cadastrarAgenda(String crm, List<AgendaRequest> agendas);

}
