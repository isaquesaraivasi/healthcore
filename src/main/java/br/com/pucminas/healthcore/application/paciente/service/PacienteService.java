package br.com.pucminas.healthcore.application.paciente.service;

import br.com.pucminas.healthcore.adapter.paciente.model.PacienteRequest;
import br.com.pucminas.healthcore.adapter.paciente.model.PacienteResponse;

import java.util.List;

public interface PacienteService {
    PacienteResponse salvar(PacienteRequest paciente);
    PacienteResponse atualizar(String id, PacienteRequest pacienteRequest);
    PacienteResponse buscarPorId(String id);
    void deletar(String id);
    boolean validarCredenciais(String cpf, String senha);

    List<PacienteResponse> buscarTodos();

}