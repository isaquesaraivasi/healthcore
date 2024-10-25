package br.com.pucminas.healthcore.adapter.consulta.model;

import br.com.pucminas.healthcore.adapter.medico.model.MedicoResponse;
import br.com.pucminas.healthcore.adapter.paciente.model.PacienteResponse;
import br.com.pucminas.healthcore.domain.enumerate.SituacaoConsulta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConsultaResponse {

    private String id;
    private MedicoResponse medico;
    private PacienteResponse paciente;
    private AgendaResponse agenda;
    private  SituacaoConsulta situacaoConsulta;
    private String linkReuniao;

}
