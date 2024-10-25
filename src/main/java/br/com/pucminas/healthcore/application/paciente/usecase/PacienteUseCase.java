package br.com.pucminas.healthcore.application.paciente.usecase;


import br.com.pucminas.healthcore.adapter.paciente.model.PacienteRequest;
import br.com.pucminas.healthcore.adapter.paciente.model.PacienteResponse;

import java.util.List;

public interface PacienteUseCase {
    PacienteResponse salvar(PacienteRequest paciente);
    PacienteResponse atualizar(String id, PacienteRequest paciente);
    PacienteResponse buscarPorId(String id);
    List<PacienteResponse> buscarTodos();
    void deletar(String id);
}
