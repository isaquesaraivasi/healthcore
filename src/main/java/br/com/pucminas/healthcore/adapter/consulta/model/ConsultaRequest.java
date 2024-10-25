package br.com.pucminas.healthcore.adapter.consulta.model;

import br.com.pucminas.healthcore.domain.enumerate.SituacaoConsulta;
import br.com.pucminas.healthcore.domain.model.medico.AgendaModel;
import br.com.pucminas.healthcore.domain.model.medico.MedicoModel;
import br.com.pucminas.healthcore.domain.model.paciente.PacienteModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConsultaRequest {
    private MedicoModel medico;
    private PacienteModel paciente;
    private SituacaoConsulta situacaoConsulta;
    private AgendaModel agenda;

}
