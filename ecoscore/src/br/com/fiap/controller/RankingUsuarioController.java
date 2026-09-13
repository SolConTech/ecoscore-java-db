package br.com.fiap.controller;

import br.com.fiap.model.dao.ConnectionFactory;
import br.com.fiap.model.dao.RankingUsuarioDAO;
import br.com.fiap.model.dto.RankingUsuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class RankingUsuarioController {

    public String inserirRankingUsuario(int idRanking, String idUsuario, int qtVotos)
            throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        RankingUsuario rankingUsuario = new RankingUsuario();
        rankingUsuario.setIdRanking(idRanking);
        rankingUsuario.setIdUsuario(idUsuario);
        rankingUsuario.setQtVotos(qtVotos);

        RankingUsuarioDAO rankingUsuarioDAO = new RankingUsuarioDAO(con);
        resultado = rankingUsuarioDAO.inserir(rankingUsuario);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String alterarRankingUsuario(int idRanking, String idUsuario, int qtVotos)
            throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        RankingUsuario rankingUsuario = new RankingUsuario();
        rankingUsuario.setIdRanking(idRanking);
        rankingUsuario.setIdUsuario(idUsuario);
        rankingUsuario.setQtVotos(qtVotos);

        RankingUsuarioDAO rankingUsuarioDAO = new RankingUsuarioDAO(con);
        resultado = rankingUsuarioDAO.alterar(rankingUsuario);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String excluirRankingUsuario(int idRanking) throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        RankingUsuario rankingUsuario = new RankingUsuario();
        rankingUsuario.setIdRanking(idRanking);

        RankingUsuarioDAO rankingUsuarioDAO = new RankingUsuarioDAO(con);
        resultado = rankingUsuarioDAO.excluir(rankingUsuario);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public ArrayList<Object> listarTodosRankingUsuario() throws ClassNotFoundException, SQLException {

        ArrayList<Object> resultado;
        Connection con = ConnectionFactory.abrirConexao();

        RankingUsuarioDAO rankingUsuarioDAO = new RankingUsuarioDAO(con);
        resultado = rankingUsuarioDAO.listarTodos();

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public RankingUsuario pegarUmRankingUsuario(String idUsuario) throws ClassNotFoundException, SQLException {

        RankingUsuario resultado;
        Connection con = ConnectionFactory.abrirConexao();

        RankingUsuarioDAO rankingUsuarioDAO = new RankingUsuarioDAO(con);
        // Note que no seu DAO, a query filtra usando WHERE ID_USUARIO = ?, por isso o parâmetro aqui é String
        resultado = (RankingUsuario) rankingUsuarioDAO.pegarUm(idUsuario);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public int criarIdRanking() throws ClassNotFoundException, SQLException {
        int resultado;
        Connection con = ConnectionFactory.abrirConexao();
        RankingUsuarioDAO rankingUsuarioDAO = new RankingUsuarioDAO(con);
        resultado = (int) rankingUsuarioDAO.criarId();
        ConnectionFactory.fecharConexao(con);
        return resultado;
    }
}
