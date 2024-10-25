package br.com.pucminas.healthcore.application.paciente.factory;

import br.com.pucminas.healthcore.adapter.paciente.model.PacienteRequest;
import br.com.pucminas.healthcore.domain.model.paciente.PacienteModel;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PacienteFactoryImpl implements PacienteFactory {

    @Override
    public PacienteModel criar(PacienteRequest paciente) {
        return PacienteModel.builder()
                .id(UUID.randomUUID().toString())
                .nome(paciente.getNome())
                .cpf(paciente.getCpf())
                .senha(paciente.getSenha())
                .email(paciente.getEmail())
                .cep(paciente.getCep())
                .build();
    }
}