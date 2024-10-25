package br.com.pucminas.healthcore.application.medico.converter;

import br.com.pucminas.healthcore.adapter.consulta.model.AgendaRequest;
import br.com.pucminas.healthcore.adapter.consulta.model.AgendaResponse;
import br.com.pucminas.healthcore.domain.model.medico.AgendaModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AgendaConverter {
    private final ModelMapper modelMapper;
    public AgendaResponse agendaModelToAgendaResponse(AgendaModel agenda){
        return modelMapper.map(agenda, AgendaResponse.class);
    }

    public AgendaModel agendaRequestToAgendaModel(AgendaRequest agenda){
        return modelMapper.map(agenda, AgendaModel.class);
    }
}
