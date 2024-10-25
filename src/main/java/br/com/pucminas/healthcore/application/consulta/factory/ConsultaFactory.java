package br.com.pucminas.healthcore.application.consulta.factory;


import br.com.pucminas.healthcore.adapter.consulta.model.ConsultaRequest;
import br.com.pucminas.healthcore.domain.model.consulta.ConsultaModel;

public interface ConsultaFactory {

    ConsultaModel criar(ConsultaRequest consulta);


}
