package br.com.fiap.controller;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.RankingUsuarioDAO;
import br.com.fiap.to.RankingUsuarioTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class RankingUsuarioController {

    public String inserirRankingUsuario(String idUsuario, int qtVotos)
            throws ClassNotFoundException, SQLException {
        RankingUsuarioTO rankingUsuarioTO = new RankingUsuarioTO();
        rankingUsuarioTO.setIdUsuario(idUsuario);
        rankingUsuarioTO.setQtVotos(qtVotos);

        RankingUsuarioDAO rankingUsuarioDAO = new RankingUsuarioDAO();
        RankingUsuarioTO resultado = rankingUsuarioDAO.save(rankingUsuarioTO);
        System.out.println("LOG: " + resultado);
        if (resultado != null) {
            return "Inserido com sucesso";
        } else {
            return "Erro a inserir";
        }
    }

    public String alterarRankingUsuario(Integer idRanking, String idUsuario, int qtVotos)
            throws ClassNotFoundException, SQLException {
        RankingUsuarioTO rankingUsuarioTO = new RankingUsuarioTO();
        rankingUsuarioTO.setIdUsuario(idUsuario);
        rankingUsuarioTO.setQtVotos(qtVotos);

        RankingUsuarioDAO rankingUsuarioDAO = new RankingUsuarioDAO();
        RankingUsuarioTO resultado = rankingUsuarioDAO.update(idRanking, rankingUsuarioTO);
        System.out.println("LOG: " + resultado);
        if (resultado != null) {
            return "Inserido com sucesso";
        } else {
            return "Erro a inserir";
        }
    }

    public String excluirRankingUsuario(Integer idRanking) throws ClassNotFoundException, SQLException {
        RankingUsuarioDAO rankingUsuarioDAO = new RankingUsuarioDAO();
        boolean resultado = rankingUsuarioDAO.delete(idRanking);
        System.out.println("LOG: " + resultado);
        if (resultado) {
            return "Deletado com sucesso";
        } else {
            return "Erro ao deletar";
        }
    }

    public ArrayList<Object> listarTodosRankingUsuario() throws ClassNotFoundException, SQLException {
        ArrayList<Object> resultado;
        RankingUsuarioDAO rankingUsuarioDAO = new RankingUsuarioDAO();
        resultado = rankingUsuarioDAO.findAll();
        return resultado;
    }

    public RankingUsuarioTO pegarUmRankingUsuario(String idUsuario) throws ClassNotFoundException, SQLException {
        RankingUsuarioTO resultado;
        RankingUsuarioDAO rankingUsuarioDAO = new RankingUsuarioDAO();
        resultado = rankingUsuarioDAO.findById(idUsuario);
        return resultado;
    }

}
