package br.com.fiap.controller;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.RankingUsuarioDAO;
import br.com.fiap.to.RankingUsuarioTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class RankingUsuarioController {

    public String inserirRankingUsuario(int idRanking, String idUsuario, int qtVotos)
            throws ClassNotFoundException, SQLException {
        RankingUsuarioTO rankingUsuarioTO = new RankingUsuarioTO();
        rankingUsuarioTO.setIdRanking(idRanking);
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

    public String alterarRankingUsuario(int idRanking, String idUsuario, int qtVotos)
            throws ClassNotFoundException, SQLException {
        RankingUsuarioTO rankingUsuarioTO = new RankingUsuarioTO();
        rankingUsuarioTO.setIdRanking(idRanking);
        rankingUsuarioTO.setIdUsuario(idUsuario);
        rankingUsuarioTO.setQtVotos(qtVotos);

        RankingUsuarioDAO rankingUsuarioDAO = new RankingUsuarioDAO();
        RankingUsuarioTO resultado = rankingUsuarioDAO.update(rankingUsuarioTO.getIdUsuario(), rankingUsuarioTO);
        System.out.println("LOG: " + resultado);
        if (resultado != null) {
            return "Inserido com sucesso";
        } else {
            return "Erro a inserir";
        }
    }

    public String excluirRankingUsuario(int idRanking) throws ClassNotFoundException, SQLException {

        String resultado = "Erro";

        RankingUsuarioTO rankingUsuarioTO = new RankingUsuarioTO();
        rankingUsuarioTO.setIdRanking(idRanking);

        RankingUsuarioDAO rankingUsuarioDAO = new RankingUsuarioDAO();

        if (rankingUsuarioDAO.delete(rankingUsuarioTO.getIdRanking())){
            resultado = "Excluido com sucesso";
        }
        return resultado;
    }

    public ArrayList<Object> listarTodosRankingUsuario() throws ClassNotFoundException, SQLException {

        ArrayList<Object> resultado;
        Connection con = ConnectionFactory.abrirConexao();

        RankingUsuarioDAO rankingUsuarioDAO = new RankingUsuarioDAO(con);
        resultado = rankingUsuarioDAO.findAll();

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public RankingUsuarioTO pegarUmRankingUsuario(String idUsuario) throws ClassNotFoundException, SQLException {

        RankingUsuarioTO resultado;
        Connection con = ConnectionFactory.abrirConexao();

        RankingUsuarioDAO rankingUsuarioDAO = new RankingUsuarioDAO(con);
        // Note que no seu DAO, a query filtra usando WHERE ID_USUARIO = ?, por isso o parâmetro aqui é String
        resultado = (RankingUsuarioTO) rankingUsuarioDAO.findById(idUsuario);

        ConnectionFactory.fecharConexao();
        return resultado;
    }

}
