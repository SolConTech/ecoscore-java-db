package br.com.fiap.dao;

import java.sql.Connection;
import java.util.ArrayList;

public interface IDAO {

    /**
     * Usa a conexão
     * @return a conexão do objeto DAO
     */
    public Connection getCon();

    /**
     * Inseri um registro na tabela do banco de dados
     * @param objeto Um objeto de uma tabela correspondente
     * @return Mensagem de resultado da operação
     */
    public String inserir(Object objeto);

    /**
     * Altera um registro na tabela do banco de dados
     * @param objeto Um objeto de uma tabela correspondente
     * @return Mensagem de resultado da operação
     */
    public String alterar(Object objeto);

    /**
     * Exclui um registro na tabela do banco de dados
     * @param objeto Um objeto de uma tabela correspondente
     * @return Mensagem de resultado da operação
     */
    public String excluir(Object objeto);

    /**
     * Lista todos os registros do banco
     * @return Uma lista com objetos dos registros
     */
    public ArrayList<Object> listarTodos();

    /**
     * Atribui a um objeto o registro da tabela no banco
     * @param object O valor da chave primária
     * @return O objeto com registro da tabela correspondente à chave informada
     */
    public Object pegarUm(Object object);

}
