package br.com.fiap.resource;

import br.com.fiap.bo.AcaoBO;
import br.com.fiap.to.AcaoTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acao")
public class AcaoResource implements IResource<AcaoTO>{
    AcaoBO acaoBO = new AcaoBO();

    @GetMapping
    @Override
    public ResponseEntity<List<Object>> findAll() {
        try {
            List<Object> acoes = acaoBO.findAll();
            if (acoes != null) {
                return ResponseEntity.status(HttpStatus.OK).body(acoes);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(acoes);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<?> findById(@PathVariable Object id) {
        try {
            AcaoTO acaoTO = (AcaoTO) acaoBO.findById(id);
            if (acaoTO != null) {
                return ResponseEntity.status(HttpStatus.OK).body(acaoTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(acaoTO);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro na aplicação, busca não pode ser feita: " + e);
        }
    }

    @PostMapping
    @Override
    public ResponseEntity<?> save(@Valid @RequestBody AcaoTO body) {
        try {
            AcaoTO acao = (AcaoTO) acaoBO.save(body);
            if (acao != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(acao);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(acao);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro na aplicação, ação não criada: " + e);
        }
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<?> update(@PathVariable Object id, @Valid @RequestBody AcaoTO body) {
        try {
            AcaoTO acao = (AcaoTO) acaoBO.update(id, body);
            if (acao != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(acao);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(acao);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro na aplicação, ação não atualizada: " + e);
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<String> delete(@PathVariable Object id) {
        try {
            if (acaoBO.delete(id)) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Ação deletada!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ação não existe!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro na aplicação, ação não deletada: " + e);
        }
    }
}
