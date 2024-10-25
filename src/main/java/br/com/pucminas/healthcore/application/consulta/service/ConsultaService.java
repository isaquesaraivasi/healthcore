package br.com.pucminas.healthcore.application.consulta.service;

import br.com.pucminas.healthcore.adapter.consulta.model.ConsultaRequest;
import br.com.pucminas.healthcore.adapter.consulta.model.ConsultaResponse;

import java.util.List;

public interface ConsultaService {

    ConsultaResponse salvar(ConsultaRequest consulta);
    ConsultaResponse atualizar(String id, ConsultaRequest consulta);
    ConsultaResponse buscarPorId(String id);
    List<ConsultaResponse> buscarTodos();
    void deletar(String id);
    List<ConsultaResponse> obterConsultas(String crm);
    ConsultaResponse aprovarOuRejeitarConsulta(boolean aprovar, String idConsulta);
    List<ConsultaResponse> consultasConfirmadas(String cpf);


}
