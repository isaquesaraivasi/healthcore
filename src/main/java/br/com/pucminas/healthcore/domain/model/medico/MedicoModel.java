package br.com.pucminas.healthcore.domain.model.medico;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import br.com.pucminas.healthcore.domain.enumerate.TipoEspecialidade;
import br.com.pucminas.healthcore.domain.model.consulta.ConsultaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class MedicoModel {
    @Id
    private String id;

    private String nome;

    private String crm;

    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoEspecialidade especialidade;

    @JsonManagedReference
    @OneToMany(mappedBy = "medico")
    private List<ConsultaModel> consultas;

    @JsonManagedReference
    @OneToMany(mappedBy = "medico")
    private List<AgendaModel> agendas;

    private BigDecimal valorConsulta;

    private String cep;

}
