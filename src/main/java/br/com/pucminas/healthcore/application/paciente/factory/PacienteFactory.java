package br.com.pucminas.healthcore.application.paciente.factory;

import br.com.pucminas.healthcore.adapter.paciente.model.PacienteRequest;
import br.com.pucminas.healthcore.domain.model.paciente.PacienteModel;

public interface PacienteFactory {
    PacienteModel criar(PacienteRequest paciente);
}