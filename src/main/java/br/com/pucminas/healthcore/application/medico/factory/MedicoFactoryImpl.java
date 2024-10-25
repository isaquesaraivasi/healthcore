package br.com.pucminas.healthcore.application.medico.factory;

import br.com.pucminas.healthcore.adapter.medico.model.MedicoRequest;
import br.com.pucminas.healthcore.domain.model.medico.MedicoModel;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MedicoFactoryImpl implements MedicoFactory {

    @Override
    public MedicoModel criar(MedicoRequest medico) {
        return MedicoModel.builder()
                .id(UUID.randomUUID().toString())
                .nome(medico.getNome())
                .crm(medico.getCrm())
                .senha(medico.getSenha())
                .valorConsulta(medico.getValorConsulta())
                .especialidade(medico.getEspecialidade())
                .cep(medico.getCep())
                .build();
    }
}