package br.com.pucminas.healthcore.application.paciente.converter;

import br.com.pucminas.healthcore.adapter.paciente.model.PacienteResponse;
import br.com.pucminas.healthcore.domain.model.paciente.PacienteModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PacienteConverter {

    private final ModelMapper modelMapper;

    public PacienteResponse pacienteModelToPacienteResponse(PacienteModel paciente){
        return modelMapper.map(paciente, PacienteResponse.class);
    }
}