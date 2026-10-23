package br.com.fiap.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDAO {

    /**
     * Inseri um registro na tabela do banco de dados
     * @param objeto Um objeto de uma tabela correspondente
     * @return Mensagem de resultado da operação
     */
    public Object save(Object objeto) throws SQLException;

    /**
     * Altera um registro na tabela do banco de dados
     * @param id O identificador do objeto a ser alterado
     * @param objeto Um objeto de uma tabela correspondente
     * @return Mensagem de resultado da operação
     */
    public Object update(Object id, Object objeto) throws SQLException;

    /**
     * Exclui um registro na tabela do banco de dados
     * @param objeto Um objeto de uma tabela correspondente
     * @return Mensagem de resultado da operação
     */
    public Boolean delete(Object objeto) throws SQLException;

    /**
     * Lista todos os registros do banco
     * @return Uma lista com objetos dos registros
     */
    public ArrayList<Object> findAll() throws SQLException;

    /**
     * Atribui a um objeto o registro da tabela no banco
     * @param id O valor da chave primária
     * @return O objeto com registro da tabela correspondente à chave informada
     */
    public Object findById(Object id) throws SQLException;

}
