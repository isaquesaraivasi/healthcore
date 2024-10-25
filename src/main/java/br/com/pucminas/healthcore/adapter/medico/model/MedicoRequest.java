package br.com.pucminas.healthcore.adapter.medico.model;

import br.com.pucminas.healthcore.domain.enumerate.TipoEspecialidade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoRequest {
    private String nome;
    private String crm;
    private String senha;
    private TipoEspecialidade especialidade;
    private BigDecimal valorConsulta;
    private String cep;


}