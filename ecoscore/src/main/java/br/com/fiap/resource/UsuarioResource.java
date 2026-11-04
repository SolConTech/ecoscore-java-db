package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.to.UsuarioTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource implements IResource<String,UsuarioTO>{
    UsuarioBO usuarioBO = new UsuarioBO();

    @GetMapping
    @Override
    public ResponseEntity<List<Object>> findAll() {
        try {
            List<Object> acoes = usuarioBO.findAll();
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
    public ResponseEntity<?> findById(@PathVariable String id) {
        try {
            UsuarioTO usuarioTO = (UsuarioTO) usuarioBO.findById(id);
            if (usuarioTO != null) {
                return ResponseEntity.status(HttpStatus.OK).body(usuarioTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usuarioTO);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro na aplicação, busca não pode ser feita: " + e);
        }
    }

    @PostMapping
    @Override
    public ResponseEntity<?> save(@Valid @RequestBody UsuarioTO body) {
        try {
            UsuarioTO usuario = (UsuarioTO) usuarioBO.save(body);
            if (usuario != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(usuario);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro na aplicação, usuario não criado: " + e);
        }
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody UsuarioTO body) {
        try {
            UsuarioTO usuario = (UsuarioTO) usuarioBO.update(id, body);
            if (usuario != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usuario);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro na aplicação, usuario não atualizado: " + e);
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<String> delete(@PathVariable String id) {
        try {
            if (usuarioBO.delete(id)) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuario deletado!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não existe!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro na aplicação, usuario não deletado: " + e);
        }
    }
}
