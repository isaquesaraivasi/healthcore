package br.com.pucminas.healthcore.domain.model.paciente;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class PacienteModel {
    @Id
    private String id;

    private String nome;

    private String cpf;

    private String senha;

    private String email;

    private String cep;


}
