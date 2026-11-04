package br.com.fiap.bo;

import br.com.fiap.dao.IDAO;
import br.com.fiap.resource.IResource;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Interface de negócios genérica para gerenciamento de Objetos de Transferência (TO).
 * Define as operações padrão de CRUD para classe Business Object (BO).
 * @author murilo-a-souza
 * @since Java 21
 * @see IDAO
 * @see br.com.fiap.resource.IResource
 */
public interface IBO {

    /**
     * Recupera todos os registros cadastrados daquele TO.
     * @return Uma lista contendo os objetos TO encontrados |
     * lista vazia se não houverem objetos correspondentes.
     * @see IDAO#findAll()
     * @see IResource#findAll() 
     */
    public ArrayList<Object> findAll() throws SQLException;

    /**
     * Busca um registro específico através do seu identificador único (PK).
     *
     * @param id O identificador (chave primária no banco) do registro.
     * @return O objeto TO correspondente ao ID informado.
     * @see IDAO#findById(Object)
     * @see IResource#findById(Object) 
     */
    public Object findById(Object id) throws SQLException;

    /**
     * Salva um novo registro na base de dados.
     * @param body O objeto TO contendo os dados a serem registrado.
     * @return O objeto salvo, com o ID gerado, se tudo deu certo.
     * @see IDAO#save(Object)
     * @see IResource#save(Object) 
     */
    public Object save(Object body) throws SQLException;

    /**
     * Atualiza os dados de um registro existente com base no ID (PK) fornecido.
     * @param id O identificador do registro que será atualizado (PK no banco).
     * @param body O objeto TO com os novos dados estruturados.
     * @return O objeto atualizado com sucesso.
     * @see IDAO#update(Object, Object)
     * @see IResource#update(Object, Object) 
     */
    public Object update(Object id, Object body) throws SQLException;

    /**
     * Remove um registro da base de dados.
     * @param id O identificador do registro a ser excluído (PK no banco).
     * @return O objeto TO que foi removido.
     * @see IDAO#delete(Object)
     * @see IResource#delete(Object)
     */
    public Boolean delete(Object id) throws SQLException;
}
