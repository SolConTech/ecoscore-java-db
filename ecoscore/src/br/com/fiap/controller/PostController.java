package br.com.fiap.controller;

import br.com.fiap.model.dao.ConnectionFactory;
import br.com.fiap.model.dao.PostDAO;
import br.com.fiap.model.dto.Post;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PostController {

    public String inserirPost(int idPost, String idUsuario, String dsPost, LocalDateTime dtPost, int numUpVotes, int numDownVotes)
            throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        Post post = new Post();
        post.setIdPost(idPost);
        post.setIdUsuario(idUsuario);
        post.setDsPost(dsPost);
        post.setDtPost(dtPost);
        post.setNumUpVotes(numUpVotes);
        post.setNumDownVotes(numDownVotes);
        post.setNumSaldoVotes(); // Atualiza internamente o saldo com base nos up/down votes passados

        PostDAO postDAO = new PostDAO(con);
        resultado = postDAO.inserir(post);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String alterarPost(int idPost, String idUsuario, String dsPost, LocalDateTime dtPost, int numUpVotes, int numDownVotes)
            throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        Post post = new Post();
        post.setIdPost(idPost);
        post.setIdUsuario(idUsuario);
        post.setDsPost(dsPost);
        post.setDtPost(dtPost);
        post.setNumUpVotes(numUpVotes);
        post.setNumDownVotes(numDownVotes);
        post.setNumSaldoVotes(); // Atualiza internamente o saldo antes de persistir

        PostDAO postDAO = new PostDAO(con);
        resultado = postDAO.alterar(post);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String excluirPost(int idPost) throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        Post post = new Post();
        post.setIdPost(idPost);

        PostDAO postDAO = new PostDAO(con);
        resultado = postDAO.excluir(post);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public ArrayList<Object> listarTodosPost() throws ClassNotFoundException, SQLException {

        ArrayList<Object> resultado;
        Connection con = ConnectionFactory.abrirConexao();

        PostDAO postDAO = new PostDAO(con);
        resultado = postDAO.listarTodos();

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public Post pegarUmPost(int idPost) throws ClassNotFoundException, SQLException {

        Post resultado;
        Connection con = ConnectionFactory.abrirConexao();

        PostDAO postDAO = new PostDAO(con);
        resultado = (Post) postDAO.pegarUm(idPost);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public int criarIdPost() throws ClassNotFoundException, SQLException {
        int resultado;
        Connection con = ConnectionFactory.abrirConexao();
        PostDAO postDAO = new PostDAO(con);
        resultado = postDAO.criarId();
        ConnectionFactory.fecharConexao(con);
        return resultado;
    }
}
