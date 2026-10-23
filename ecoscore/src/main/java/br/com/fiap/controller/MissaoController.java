package br.com.fiap.controller;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.MissaoDAO; // Certifique-se de que o nome da sua DAO é este
import br.com.fiap.to.MissaoTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MissaoController {

    public String inserirMissao(int idMissao, String nmMissao, String dsMissao, String selo, int qtPontosGerados, LocalDateTime dtMissao)
            throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        MissaoTO missaoTO = new MissaoTO();
        missaoTO.setIdMissao(idMissao);
        missaoTO.setNmMissao(nmMissao);
        missaoTO.setDsMissao(dsMissao);
        missaoTO.setSelo(selo);
        missaoTO.setQtPontosGerados(qtPontosGerados);
        missaoTO.setDtMissao(dtMissao);

        MissaoDAO missaoDAO = new MissaoDAO(con);
        resultado = missaoDAO.inserir(missaoTO);

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public String alterarMissao(int idMissao, String nmMissao, String dsMissao, String selo, int qtPontosGerados, LocalDateTime dtMissao)
            throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        MissaoTO missaoTO = new MissaoTO();
        missaoTO.setIdMissao(idMissao);
        missaoTO.setNmMissao(nmMissao);
        missaoTO.setDsMissao(dsMissao);
        missaoTO.setSelo(selo);
        missaoTO.setQtPontosGerados(qtPontosGerados);
        missaoTO.setDtMissao(dtMissao);

        MissaoDAO missaoDAO = new MissaoDAO(con);
        resultado = missaoDAO.alterar(missaoTO.getIdMissao(), missaoTO);

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public String excluirMissao(int idMissao) throws ClassNotFoundException, SQLException {

        String resultado = "";
        Connection con = ConnectionFactory.abrirConexao();

        MissaoTO missaoTO = new MissaoTO();
        missaoTO.setIdMissao(idMissao);

        MissaoDAO missaoDAO = new MissaoDAO(con);
        missaoDAO.excluir(missaoTO.getIdMissao());

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public ArrayList<Object> listarTodosMissao() throws ClassNotFoundException, SQLException {

        ArrayList<Object> resultado;
        Connection con = ConnectionFactory.abrirConexao();

        MissaoDAO missaoDAO = new MissaoDAO(con);
        resultado = missaoDAO.listarTodos();

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public MissaoTO pegarUmMissao(int idMissao) throws ClassNotFoundException, SQLException {

        MissaoTO resultado;
        Connection con = ConnectionFactory.abrirConexao();

        MissaoDAO missaoDAO = new MissaoDAO(con);
        resultado = (MissaoTO) missaoDAO.listarUm(idMissao);

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public int criarIdMissao() throws ClassNotFoundException, SQLException {
        int resultado;
        Connection con = ConnectionFactory.abrirConexao();
        MissaoDAO missaoDAO = new MissaoDAO(con);
        resultado = (int) missaoDAO.criarId();
        ConnectionFactory.fecharConexao();
        return resultado;
    }
}
