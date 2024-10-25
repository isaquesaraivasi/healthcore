package br.com.pucminas.healthcore.application.consulta.converter;

import br.com.pucminas.healthcore.adapter.consulta.model.ConsultaResponse;
import br.com.pucminas.healthcore.domain.model.consulta.ConsultaModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ConsultaConverter {

    private final ModelMapper modelMapper;

    public ConsultaResponse consultaModelToConsultaResponse(ConsultaModel consulta){
        return modelMapper.map(consulta, ConsultaResponse.class);
    }


}
