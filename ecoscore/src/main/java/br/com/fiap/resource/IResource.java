package br.com.fiap.resource;

import br.com.fiap.bo.IBO;
import br.com.fiap.dao.IDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Interface controladora genérica para requisição HTTP.
 * Define as operações padrão da API.
 * @author murilo-a-souza
 * @since Java 21
 * @see IBO
 * @see IDAO
 */
public interface IResource<T> {
/*
O T é de tipo genérico, estou usando apenas porque a interface precisa ter tipos flexiveis,
no desenvolvimento isso só se tornou um problema na validação com @Valid, se não fosse isso dava pra usar Object normal.
Tanto que somente o POST e PUT que usam
 */

    /**
     * Recupera uma lista contendo todos os registros disponíveis.
     * @return uma ResponseEntity contendo a lista de objetos e o status HTTP 200 (OK).
     * @see IDAO#findAll()
     * @see IBO#findAll()
     */
    @GetMapping
    ResponseEntity<List<Object>> findAll();

    /**
     * Busca um registro específico com base no seu identificador exclusivo.
     * @param id O identificador do registro a ser buscado.
     * @return uma ResponseEntity objeto encontrado e o status HTTP 200 (OK).
     * | status HTTP 404 (Not Found) caso não exista.
     * @see IDAO#findById(Object)
     * @see IBO#findById(Object)
     */
    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Object id);

    /**
     * Cria e salva um novo registro no sistema.
     * @param body O corpo da requisição contendo os dados do objeto a ser criado.
     * @return Um ResponseEntity contendo o objeto criado e o status HTTP 201 (Created).
     * | status HTTP 400 (Bad Request) se os dados forem inválidos.
     * @see IDAO#save(Object)
     * @see IBO#save(Object)
     */
    @PostMapping
    ResponseEntity<?> save(@RequestBody T body);

    /**
     * Atualiza os dados de um registro existente com base no ID fornecido.
     * @param id O identificador do registro que será atualizado (PK no banco).
     * @param body O corpo da requisição contendo os novos dados do objeto.
     * @return Um ResponseEntity contendo o objeto atualizado e o status HTTP 200 (OK).
     * | status HTTP 404 (Not Found) se o registro não for encontrado.
     * @see IDAO#update(Object, Object)
     * @see IBO#update(Object, Object)
     */
    @PutMapping("/{id}")
    ResponseEntity<?> update(@PathVariable Object id, @RequestBody T body);

    /**
     * Remove um registro do sistema com base no seu identificador.
     * @param id O identificador do registro a ser excluído.
     * @return Um ResponseEntity com corpo vazio e status HTTP 204 (No Content) após a exclusão.
     * | pode retornar HTTP 404 se o objeto não existir
     * @see IDAO#delete(Object)
     * @see IBO#delete(Object)
     */
    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Object id);

}
