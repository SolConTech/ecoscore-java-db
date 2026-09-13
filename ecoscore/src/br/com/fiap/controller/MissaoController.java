package br.com.fiap.controller;

import br.com.fiap.model.dao.ConnectionFactory;
import br.com.fiap.model.dao.MissaoDAO; // Certifique-se de que o nome da sua DAO é este
import br.com.fiap.model.dto.Missao;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MissaoController {

    public String inserirMissao(int idMissao, String nmMissao, String dsMissao, String selo, int qtPontosGerados, LocalDateTime dtMissao)
            throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        Missao missao = new Missao();
        missao.setIdMissao(idMissao);
        missao.setNmMissao(nmMissao);
        missao.setDsMissao(dsMissao);
        missao.setSelo(selo);
        missao.setQtPontosGerados(qtPontosGerados);
        missao.setDtMissao(dtMissao);

        MissaoDAO missaoDAO = new MissaoDAO(con);
        resultado = missaoDAO.inserir(missao);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String alterarMissao(int idMissao, String nmMissao, String dsMissao, String selo, int qtPontosGerados, LocalDateTime dtMissao)
            throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        Missao missao = new Missao();
        missao.setIdMissao(idMissao);
        missao.setNmMissao(nmMissao);
        missao.setDsMissao(dsMissao);
        missao.setSelo(selo);
        missao.setQtPontosGerados(qtPontosGerados);
        missao.setDtMissao(dtMissao);

        MissaoDAO missaoDAO = new MissaoDAO(con);
        resultado = missaoDAO.alterar(missao);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String excluirMissao(int idMissao) throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        Missao missao = new Missao();
        missao.setIdMissao(idMissao);

        MissaoDAO missaoDAO = new MissaoDAO(con);
        resultado = missaoDAO.excluir(missao);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public ArrayList<Object> listarTodosMissao() throws ClassNotFoundException, SQLException {

        ArrayList<Object> resultado;
        Connection con = ConnectionFactory.abrirConexao();

        MissaoDAO missaoDAO = new MissaoDAO(con);
        resultado = missaoDAO.listarTodos();

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public Missao pegarUmMissao(int idMissao) throws ClassNotFoundException, SQLException {

        Missao resultado;
        Connection con = ConnectionFactory.abrirConexao();

        MissaoDAO missaoDAO = new MissaoDAO(con);
        resultado = (Missao) missaoDAO.pegarUm(idMissao);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }
}
