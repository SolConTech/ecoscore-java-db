package br.com.fiap.controller;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.PostDAO;
import br.com.fiap.to.PostTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PostController {

    public String inserirPost(int idPost, String idUsuario, String dsPost, LocalDateTime dtPost, int numUpVotes, int numDownVotes)
            throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        PostTO postTO = new PostTO();
        postTO.setIdPost(idPost);
        postTO.setIdUsuario(idUsuario);
        postTO.setDsPost(dsPost);
        postTO.setDtPost(dtPost);
        postTO.setNumUpVotes(numUpVotes);
        postTO.setNumDownVotes(numDownVotes);
        postTO.setNumSaldoVotes(); // Atualiza internamente o saldo com base nos up/down votes passados

        PostDAO postDAO = new PostDAO(con);
        resultado = postDAO.save(postTO);

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public String alterarPost(int idPost, String idUsuario, String dsPost, LocalDateTime dtPost, int numUpVotes, int numDownVotes)
            throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        PostTO postTO = new PostTO();
        postTO.setIdPost(idPost);
        postTO.setIdUsuario(idUsuario);
        postTO.setDsPost(dsPost);
        postTO.setDtPost(dtPost);
        postTO.setNumUpVotes(numUpVotes);
        postTO.setNumDownVotes(numDownVotes);
        postTO.setNumSaldoVotes(); // Atualiza internamente o saldo antes de persistir

        PostDAO postDAO = new PostDAO(con);
        resultado = postDAO.update(postTO.getIdPost(), postTO);

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public String excluirPost(int idPost) throws ClassNotFoundException, SQLException {

        String resultado = "";
        Connection con = ConnectionFactory.abrirConexao();

        PostTO postTO = new PostTO();
        postTO.setIdPost(idPost);

        PostDAO postDAO = new PostDAO(con);
        postDAO.delete(postTO.getIdPost());

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public ArrayList<Object> listarTodosPost() throws ClassNotFoundException, SQLException {

        ArrayList<Object> resultado;
        Connection con = ConnectionFactory.abrirConexao();

        PostDAO postDAO = new PostDAO(con);
        resultado = postDAO.findAll();

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public PostTO pegarUmPost(int idPost) throws ClassNotFoundException, SQLException {

        PostTO resultado;
        Connection con = ConnectionFactory.abrirConexao();

        PostDAO postDAO = new PostDAO(con);
        resultado = (PostTO) postDAO.findById(idPost);

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public int criarIdPost() throws ClassNotFoundException, SQLException {
        int resultado;
        Connection con = ConnectionFactory.abrirConexao();
        PostDAO postDAO = new PostDAO(con);
        resultado = postDAO.criarId();
        ConnectionFactory.fecharConexao();
        return resultado;
    }
}
