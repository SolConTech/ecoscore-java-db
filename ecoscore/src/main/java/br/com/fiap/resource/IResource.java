package br.com.fiap.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public interface IResource {

    /**
     * Recupera uma lista contendo todos os registros disponíveis.
     * @return uma ResponseEntity contendo a lista de objetos e o status HTTP 200 (OK).
     */
    @GetMapping
    ResponseEntity<List<Object>> findAll();

    /**
     * Busca um registro específico com base no seu identificador exclusivo.
     * @param id O identificador do registro a ser buscado.
     * @return uma ResponseEntity objeto encontrado e o status HTTP 200 (OK).
     * | status HTTP 404 (Not Found) caso não exista.
     */
    @GetMapping("/{id}")
    ResponseEntity<Object> findById(@PathVariable Object id);

    /**
     * Cria e salva um novo registro no sistema.
     * @param body O corpo da requisição contendo os dados do objeto a ser criado.
     * @return Um ResponseEntity contendo o objeto criado e o status HTTP 201 (Created).
     * | status HTTP 400 (Bad Request) se os dados forem inválidos.
     */
    @PostMapping
    ResponseEntity<Object> save(@RequestBody Object body);

    /**
     * Atualiza os dados de um registro existente com base no ID fornecido.
     * @param id O identificador do registro que será atualizado (PK no banco).
     * @param body O corpo da requisição contendo os novos dados do objeto.
     * @return Um ResponseEntity contendo o objeto atualizado e o status HTTP 200 (OK).
     * | status HTTP 404 (Not Found) se o registro não for encontrado.
     */
    @PutMapping("/{id}")
    ResponseEntity<Object> update(@PathVariable Object id, @RequestBody Object body);

    /**
     * Remove um registro do sistema com base no seu identificador.
     * @param id O identificador do registro a ser excluído.
     * @return Um ResponseEntity com corpo vazio e status HTTP 204 (No Content) após a exclusão.
     * | pode retornar HTTP 404 se o objeto não existir
     */
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Object id);

}
