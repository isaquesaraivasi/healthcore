package br.com.pucminas.healthcore.infrastructure.repository.consulta;

import br.com.pucminas.healthcore.domain.model.consulta.ConsultaModel;
import br.com.pucminas.healthcore.domain.model.medico.MedicoModel;
import br.com.pucminas.healthcore.domain.model.paciente.PacienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ConsultaRepository extends JpaRepository<ConsultaModel, String> {
    List<ConsultaModel> findAllByMedico(MedicoModel medico);

    List<ConsultaModel> findAllByPaciente(PacienteModel paciente);

}
