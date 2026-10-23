package br.com.fiap.resource;

import br.com.fiap.bo.MissaoBO;
import br.com.fiap.to.MissaoTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missao")
public class MissaoResource implements IResource<MissaoTO> {
    MissaoBO MissaoBO = new MissaoBO();

    @GetMapping
    @Override
    public ResponseEntity<List<Object>> findAll() {
        try {
            List<Object> missoes = MissaoBO.findAll();
            if (missoes != null) {
                return ResponseEntity.status(HttpStatus.OK).body(missoes);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(missoes);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<?> findById(@PathVariable Object id) {
        try {
            MissaoTO missao = (MissaoTO) MissaoBO.findById(id);
            if (missao != null) {
                return ResponseEntity.status(HttpStatus.OK).body(missao);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(missao);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro na aplicação, busca não pode ser feita: " + e);
        }
    }

    @PostMapping
    @Override
    public ResponseEntity<?> save(@Valid @RequestBody MissaoTO body) {
        try {
            MissaoTO missao = (MissaoTO) MissaoBO.save(body);
            if (missao != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(missao);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(missao);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro na aplicação, missão não criada: " + e);
        }
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<?> update(@PathVariable Object id, @Valid @RequestBody MissaoTO body) {
        try {
            MissaoTO missao = (MissaoTO) MissaoBO.update(id, body);
            if (missao != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(missao);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(missao);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro na aplicação, missão não atualizada: " + e);
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<String> delete(@PathVariable Object id) {
        try {
            if (MissaoBO.delete(id)) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Missão deletada!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão não existe!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro na aplicação, missão não deletada: " + e);
        }
    }
}
