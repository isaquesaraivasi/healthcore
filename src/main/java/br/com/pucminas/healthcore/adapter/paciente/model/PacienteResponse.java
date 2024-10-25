package br.com.pucminas.healthcore.adapter.paciente.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteResponse {
    private UUID id;
    private String nome;
    private String cpf;
    private String email;
    private String cep;
}