package br.com.pucminas.healthcore.application.medico.usecase;

import br.com.pucminas.healthcore.adapter.consulta.model.AgendaRequest;
import br.com.pucminas.healthcore.adapter.medico.model.MedicoRequest;
import br.com.pucminas.healthcore.adapter.medico.model.MedicoResponse;
import br.com.pucminas.healthcore.application.medico.service.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MedicoUseCaseImpl implements MedicoUseCase {

    private final MedicoService service;

    @Override
    public MedicoResponse salvar(MedicoRequest medico) {
        return service.salvar(medico);
    }

    @Override
    public MedicoResponse atualizar(String id, MedicoRequest medico) {
        return service.atualizar(id, medico);
    }

    @Override
    public MedicoResponse buscarPorId(String id) {
        return service.buscarPorId(id);
    }

    @Override
    public List<MedicoResponse> buscarTodos() {
        return service.buscarTodos();
    }

    @Override
    public void deletar(String id) {
        service.deletar(id);
    }

    @Override
    public void cadastrarAgenda(String crm, List<AgendaRequest> agendas) {
         service.cadastrarAgenda(crm,agendas);
    }
}