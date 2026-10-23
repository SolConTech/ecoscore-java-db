package br.com.fiap.resource;

import br.com.fiap.bo.PostBO;
import br.com.fiap.to.PostTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostResource implements IResource<PostTO>{
    PostBO postBO = new PostBO();

    @GetMapping
    @Override
    public ResponseEntity<List<Object>> findAll() {
        try {
            List<Object> acoes = postBO.findAll();
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
            PostTO postTO = (PostTO) postBO.findById(id);
            if (postTO != null) {
                return ResponseEntity.status(HttpStatus.OK).body(postTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(postTO);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro na aplicação, busca não pode ser feita: " + e);
        }
    }

    @PostMapping
    @Override
    public ResponseEntity<?> save(@Valid @RequestBody PostTO body) {
        try {
            PostTO post = (PostTO) postBO.save(body);
            if (post != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(post);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(post);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro na aplicação, post não criado: " + e);
        }
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<?> update(@PathVariable Object id, @Valid @RequestBody PostTO body) {
        try {
            PostTO post = (PostTO) postBO.update(id, body);
            if (post != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(post);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(post);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro na aplicação, post não atualizado: " + e);
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<String> delete(@PathVariable Object id) {
        try {
            if (postBO.delete(id)) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Post deletado!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post não existe!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro na aplicação, post não deletado: " + e);
        }
    }
}
