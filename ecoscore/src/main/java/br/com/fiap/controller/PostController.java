package br.com.fiap.controller;

import br.com.fiap.dao.PostDAO;
import br.com.fiap.to.PostTO;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PostController {

    public String inserirPost(String idUsuario, String dsPost, LocalDateTime dtPost, int numUpVotes, int numDownVotes)
            throws ClassNotFoundException, SQLException {
        PostTO postTO = new PostTO();
        postTO.setIdUsuario(idUsuario);
        postTO.setDsPost(dsPost);
        postTO.setDtPost(dtPost);
        postTO.setNumUpVotes(numUpVotes);
        postTO.setNumDownVotes(numDownVotes);
        postTO.setNumSaldoVotes(); // Atualiza internamente o saldo com base nos up/down votes passados

        PostDAO postDAO = new PostDAO();
        PostTO resultado = postDAO.save(postTO);
        System.out.println("LOG: " + resultado);
        if (resultado != null) {
            return "Inserido com sucesso";
        } else {
            return "Erro a inserir";
        }
    }

    public String alterarPost(int idPost, String idUsuario, String dsPost, LocalDateTime dtPost, int numUpVotes, int numDownVotes)
            throws ClassNotFoundException, SQLException {
        PostTO postTO = new PostTO();
        postTO.setIdUsuario(idUsuario);
        postTO.setDsPost(dsPost);
        postTO.setDtPost(dtPost);
        postTO.setNumUpVotes(numUpVotes);
        postTO.setNumDownVotes(numDownVotes);
        postTO.setNumSaldoVotes(); // Atualiza internamente o saldo antes de persistir

        PostDAO postDAO = new PostDAO();
        PostTO resultado = postDAO.update(idPost, postTO);
        System.out.println("LOG: " + resultado);
        if (resultado != null) {
            return "Inserido com sucesso";
        } else {
            return "Erro a inserir";
        }
    }

    public String excluirPost(int idPost) throws ClassNotFoundException, SQLException {
        PostDAO postDAO = new PostDAO();
        boolean resultado = postDAO.delete(idPost);
        System.out.println("LOG: " + resultado);
        if (resultado) {
            return "Deletado com sucesso";
        } else {
            return "Erro ao deletar";
        }
    }

    public ArrayList<Object> listarTodosPost() throws ClassNotFoundException, SQLException {
        ArrayList<Object> resultado;
        PostDAO postDAO = new PostDAO();
        resultado = postDAO.findAll();
        return resultado;
    }

    public PostTO pegarUmPost(int idPost) throws ClassNotFoundException, SQLException {
        PostTO resultado;
        PostDAO postDAO = new PostDAO();
        resultado = postDAO.findById(idPost);
        return resultado;
    }
}
