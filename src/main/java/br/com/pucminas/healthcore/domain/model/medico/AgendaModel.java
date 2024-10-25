package br.com.pucminas.healthcore.domain.model.medico;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class AgendaModel {
    @Id
    String id;
    @ManyToOne
    @JoinColumn(name = "medico_id")
    @JsonBackReference
    private MedicoModel medico;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private boolean situacao;

}
