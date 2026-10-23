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

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        RankingUsuarioTO rankingUsuarioTO = new RankingUsuarioTO();
        rankingUsuarioTO.setIdRanking(idRanking);
        rankingUsuarioTO.setIdUsuario(idUsuario);
        rankingUsuarioTO.setQtVotos(qtVotos);

        RankingUsuarioDAO rankingUsuarioDAO = new RankingUsuarioDAO(con);
        resultado = rankingUsuarioDAO.inserir(rankingUsuarioTO);

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public String alterarRankingUsuario(int idRanking, String idUsuario, int qtVotos)
            throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        RankingUsuarioTO rankingUsuarioTO = new RankingUsuarioTO();
        rankingUsuarioTO.setIdRanking(idRanking);
        rankingUsuarioTO.setIdUsuario(idUsuario);
        rankingUsuarioTO.setQtVotos(qtVotos);

        RankingUsuarioDAO rankingUsuarioDAO = new RankingUsuarioDAO(con);
        resultado = rankingUsuarioDAO.alterar(rankingUsuarioTO.getIdUsuario(), rankingUsuarioTO);

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public String excluirRankingUsuario(int idRanking) throws ClassNotFoundException, SQLException {

        String resultado = "Erro";

        RankingUsuarioTO rankingUsuarioTO = new RankingUsuarioTO();
        rankingUsuarioTO.setIdRanking(idRanking);

        RankingUsuarioDAO rankingUsuarioDAO = new RankingUsuarioDAO();

        if (rankingUsuarioDAO.excluir(rankingUsuarioTO.getIdRanking())){
            resultado = "Excluido com sucesso";
        }
        return resultado;
    }

    public ArrayList<Object> listarTodosRankingUsuario() throws ClassNotFoundException, SQLException {

        ArrayList<Object> resultado;
        Connection con = ConnectionFactory.abrirConexao();

        RankingUsuarioDAO rankingUsuarioDAO = new RankingUsuarioDAO(con);
        resultado = rankingUsuarioDAO.listarTodos();

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public RankingUsuarioTO pegarUmRankingUsuario(String idUsuario) throws ClassNotFoundException, SQLException {

        RankingUsuarioTO resultado;
        Connection con = ConnectionFactory.abrirConexao();

        RankingUsuarioDAO rankingUsuarioDAO = new RankingUsuarioDAO(con);
        // Note que no seu DAO, a query filtra usando WHERE ID_USUARIO = ?, por isso o parâmetro aqui é String
        resultado = (RankingUsuarioTO) rankingUsuarioDAO.listarUm(idUsuario);

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public int criarIdRanking() throws ClassNotFoundException, SQLException {
        int resultado;
        Connection con = ConnectionFactory.abrirConexao();
        RankingUsuarioDAO rankingUsuarioDAO = new RankingUsuarioDAO(con);
        resultado = (int) rankingUsuarioDAO.criarId();
        ConnectionFactory.fecharConexao();
        return resultado;
    }
}
