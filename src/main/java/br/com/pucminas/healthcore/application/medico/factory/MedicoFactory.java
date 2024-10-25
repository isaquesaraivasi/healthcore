package br.com.pucminas.healthcore.application.medico.factory;

import br.com.pucminas.healthcore.adapter.medico.model.MedicoRequest;
import br.com.pucminas.healthcore.domain.model.medico.MedicoModel;

public interface MedicoFactory {
    MedicoModel criar(MedicoRequest medico);
}