package br.com.pucminas.healthcore.infrastructure.repository.paciente;

import br.com.pucminas.healthcore.domain.model.paciente.PacienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<PacienteModel, String> {
    Optional<PacienteModel> findByCpf(String cpf);

}
