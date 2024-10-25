package br.com.pucminas.healthcore.adapter.medico;

import br.com.pucminas.healthcore.adapter.consulta.model.AgendaRequest;
import br.com.pucminas.healthcore.adapter.medico.model.MedicoRequest;
import br.com.pucminas.healthcore.adapter.medico.model.MedicoResponse;
import br.com.pucminas.healthcore.application.medico.usecase.MedicoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MedicoController {

    private final MedicoUseCase medicoUseCase;

    @PostMapping
    public ResponseEntity<MedicoResponse> criarMedico(@RequestBody MedicoRequest medicoRequest) {
        MedicoResponse response = medicoUseCase.salvar(medicoRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponse> atualizarMedico(@PathVariable String id, @RequestBody MedicoRequest medicoRequest, @RequestHeader("crm") String crm, @RequestHeader("senha") String senha) {
        MedicoResponse response = medicoUseCase.atualizar(id, medicoRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> buscarMedicoPorId(@PathVariable String id, @RequestHeader("crm") String crm, @RequestHeader("senha") String senha) {
        MedicoResponse response = medicoUseCase.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<MedicoResponse>> buscarTodosMedicos(@RequestHeader("crm") String crm, @RequestHeader("senha") String senha) {
        List<MedicoResponse> response = medicoUseCase.buscarTodos();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedico(@PathVariable String id, @RequestHeader("crm") String crm, @RequestHeader("senha") String senha) {
        medicoUseCase.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/agendas")
    public ResponseEntity<Void> cadastrarAgenda(@RequestHeader("crm") String crm, @RequestHeader("senha") String senha, @RequestBody List<AgendaRequest> agendaRequest) {
        medicoUseCase.cadastrarAgenda(crm, agendaRequest);

        return ResponseEntity.ok().build();
    }
}
