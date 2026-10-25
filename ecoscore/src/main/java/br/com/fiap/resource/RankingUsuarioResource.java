package br.com.fiap.resource;

import br.com.fiap.bo.RankingUsuarioBO;
import br.com.fiap.to.RankingUsuarioTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingUsuarioResource implements IResource<Integer, RankingUsuarioTO>{
    RankingUsuarioBO rankingUsuarioBO = new RankingUsuarioBO();

    @GetMapping
    @Override
    public ResponseEntity<List<Object>> findAll() {
        try {
            List<Object> acoes = rankingUsuarioBO.findAll();
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
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            RankingUsuarioTO rankingUsuarioTO = (RankingUsuarioTO) rankingUsuarioBO.findById(id);
            if (rankingUsuarioTO != null) {
                return ResponseEntity.status(HttpStatus.OK).body(rankingUsuarioTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rankingUsuarioTO);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro na aplicação, busca não pode ser feita: " + e);
        }
    }

    @PostMapping
    @Override
    public ResponseEntity<?> save(@Valid @RequestBody RankingUsuarioTO body) {
        try {
            RankingUsuarioTO rankingUsuario = (RankingUsuarioTO) rankingUsuarioBO.save(body);
            if (rankingUsuario != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(rankingUsuario);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rankingUsuario);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro na aplicação, ranking não criado: " + e);
        }
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody RankingUsuarioTO body) {
        try {
            RankingUsuarioTO rankingUsuario = (RankingUsuarioTO) rankingUsuarioBO.update(id, body);
            if (rankingUsuario != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(rankingUsuario);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rankingUsuario);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro na aplicação, ranking não atualizado: " + e);
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            if (rankingUsuarioBO.delete(id)) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Ranking deletado!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ranking não existe!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro na aplicação, ranking não deletado: " + e);
        }
    }
}
