package br.com.pucminas.healthcore.domain.model.consulta;

import com.fasterxml.jackson.annotation.JsonBackReference;
import br.com.pucminas.healthcore.domain.enumerate.SituacaoConsulta;
import br.com.pucminas.healthcore.domain.model.medico.AgendaModel;
import br.com.pucminas.healthcore.domain.model.medico.MedicoModel;
import br.com.pucminas.healthcore.domain.model.paciente.PacienteModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class ConsultaModel {
    @Id
    private String id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "medico_id", nullable = false)
    @NotNull
    private MedicoModel medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    @NotNull
    private PacienteModel paciente;

    @ManyToOne
    @JoinColumn(name = "agenda_id", nullable = false)
    @NotNull
    private AgendaModel agenda;

    @Enumerated(EnumType.STRING)
    @NotNull
    private SituacaoConsulta situacaoConsulta;

    private String linkReuniao;

}
