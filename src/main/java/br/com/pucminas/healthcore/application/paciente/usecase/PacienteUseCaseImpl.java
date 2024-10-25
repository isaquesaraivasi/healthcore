package br.com.pucminas.healthcore.application.paciente.usecase;

import br.com.pucminas.healthcore.adapter.paciente.model.PacienteRequest;
import br.com.pucminas.healthcore.adapter.paciente.model.PacienteResponse;
import br.com.pucminas.healthcore.application.paciente.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PacienteUseCaseImpl implements PacienteUseCase {

    private final PacienteService service;

    @Override
    public PacienteResponse salvar(PacienteRequest paciente) {
        return service.salvar(paciente);
    }

    @Override
    public PacienteResponse atualizar(String id, PacienteRequest paciente) {
        return service.atualizar(id, paciente);
    }

    @Override
    public PacienteResponse buscarPorId(String id) {
        return service.buscarPorId(id);
    }

    @Override
    public List<PacienteResponse> buscarTodos() {
        return service.buscarTodos();
    }

    @Override
    public void deletar(String id) {
        service.deletar(id);
    }
}
