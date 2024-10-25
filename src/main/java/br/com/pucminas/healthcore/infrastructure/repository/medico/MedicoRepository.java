package br.com.pucminas.healthcore.infrastructure.repository.medico;

import br.com.pucminas.healthcore.domain.model.medico.MedicoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<MedicoModel, String> {
    Optional<MedicoModel> findByCrm(String crm);
}
