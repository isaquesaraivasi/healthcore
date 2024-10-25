package br.com.pucminas.healthcore.application.medico.converter;

import br.com.pucminas.healthcore.adapter.medico.model.MedicoResponse;
import br.com.pucminas.healthcore.domain.model.medico.MedicoModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MedicoConverter {

    private final ModelMapper modelMapper;

    public MedicoResponse medicoModelToMedicoResponse(MedicoModel medico){
        return modelMapper.map(medico, MedicoResponse.class);
    }
}