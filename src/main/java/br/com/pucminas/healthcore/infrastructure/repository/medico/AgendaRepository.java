package br.com.pucminas.healthcore.infrastructure.repository.medico;

import br.com.pucminas.healthcore.domain.enumerate.TipoEspecialidade;
import br.com.pucminas.healthcore.domain.model.medico.AgendaModel;
import br.com.pucminas.healthcore.domain.model.medico.MedicoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendaRepository extends JpaRepository<AgendaModel, String> {

    @Query("SELECT a FROM AgendaModel a WHERE a.medico.especialidade = :especialidade AND a.situacao = true AND a.dataHoraInicio >= :dataHoraInicio AND a.dataHoraFim <= :dataHoraFim")
    List<AgendaModel> findAvailableAgendasByEspecialidadeAndSituacao(
            @Param("especialidade") TipoEspecialidade especialidade,
            @Param("dataHoraInicio") LocalDateTime dataHoraInicio,
            @Param("dataHoraFim") LocalDateTime dataHoraFim);

    boolean existsByMedicoAndDataHoraInicio(MedicoModel medico, LocalDateTime dataHoraInicio);
}