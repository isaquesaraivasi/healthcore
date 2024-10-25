package br.com.pucminas.healthcore.application.consulta.factory;

import br.com.pucminas.healthcore.adapter.consulta.model.ConsultaRequest;
import br.com.pucminas.healthcore.commons.utils.DataProvider;
import br.com.pucminas.healthcore.domain.enumerate.SituacaoConsulta;
import br.com.pucminas.healthcore.domain.model.consulta.ConsultaModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ConsultaFactoryImpl implements ConsultaFactory {
    private final DataProvider dataProvider;
    @Override
    public ConsultaModel criar(ConsultaRequest consulta) {

        return ConsultaModel.builder()
                .id(UUID.randomUUID().toString())
                .agenda(consulta.getAgenda())
                .medico(consulta.getMedico())
                .agenda(consulta.getAgenda())
                .paciente(consulta.getPaciente())
                .situacaoConsulta(SituacaoConsulta.SOLICITADA)
                .build();
    }
}
