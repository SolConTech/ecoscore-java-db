package br.com.fiap.resource;

import br.com.fiap.bo.QuizBO;
import br.com.fiap.to.QuizTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizResource implements IResource<Integer,QuizTO>{
    QuizBO quizBO = new QuizBO();

    @GetMapping
    @Override
    public ResponseEntity<List<Object>> findAll() {
        try {
            List<Object> acoes = quizBO.findAll();
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
            QuizTO quizTO = (QuizTO) quizBO.findById(id);
            if (quizTO != null) {
                return ResponseEntity.status(HttpStatus.OK).body(quizTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(quizTO);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro na aplicação, busca não pode ser feita: " + e);
        }
    }

    @PostMapping
    @Override
    public ResponseEntity<?> save(@Valid @RequestBody QuizTO body) {
        try {
            QuizTO quiz = (QuizTO) quizBO.save(body);
            if (quiz != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(quiz);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(quiz);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro na aplicação, quiz não criado: " + e);
        }
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody QuizTO body) {
        try {
            QuizTO quiz = (QuizTO) quizBO.update(id, body);
            if (quiz != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(quiz);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(quiz);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro na aplicação, quiz não atualizado: " + e);
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            if (quizBO.delete(id)) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Quiz deletado!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz não existe!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro na aplicação, quiz não deletado: " + e);
        }
    }
}

