package br.com.fiap.controller;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.MissaoDAO; // Certifique-se de que o nome da sua DAO é este
import br.com.fiap.to.AcaoTO;
import br.com.fiap.to.MissaoTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MissaoController {

    public String inserirMissao(int idMissao, String nmMissao, String dsMissao, String selo, int qtPontosGerados, LocalDateTime dtMissao)
            throws ClassNotFoundException, SQLException {
        MissaoTO missaoTO = new MissaoTO();
        missaoTO.setIdMissao(idMissao);
        missaoTO.setNmMissao(nmMissao);
        missaoTO.setDsMissao(dsMissao);
        missaoTO.setSelo(selo);
        missaoTO.setQtPontosGerados(qtPontosGerados);
        missaoTO.setDtMissao(dtMissao);

        MissaoDAO missaoDAO = new MissaoDAO();
        MissaoTO resultado = missaoDAO.save(missaoTO);
        System.out.println("LOG: " + resultado);
        if (resultado != null) {
            return "Inserido com sucesso";
        } else {
            return "Erro a inserir";
        }
    }

    public String alterarMissao(int idMissao, String nmMissao, String dsMissao, String selo, int qtPontosGerados, LocalDateTime dtMissao)
            throws ClassNotFoundException, SQLException {
        MissaoTO missaoTO = new MissaoTO();
        missaoTO.setIdMissao(idMissao);
        missaoTO.setNmMissao(nmMissao);
        missaoTO.setDsMissao(dsMissao);
        missaoTO.setSelo(selo);
        missaoTO.setQtPontosGerados(qtPontosGerados);
        missaoTO.setDtMissao(dtMissao);

        MissaoDAO missaoDAO = new MissaoDAO();
        MissaoTO resultado = missaoDAO.update(missaoTO.getIdMissao(), missaoTO);
        System.out.println("LOG: " + resultado);
        if (resultado != null) {
            return "Inserido com sucesso";
        } else {
            return "Erro a inserir";
        }
    }

    public String excluirMissao(int idMissao) throws ClassNotFoundException, SQLException {

        String resultado = "";
        Connection con = ConnectionFactory.abrirConexao();

        MissaoTO missaoTO = new MissaoTO();
        missaoTO.setIdMissao(idMissao);

        MissaoDAO missaoDAO = new MissaoDAO(con);
        missaoDAO.delete(missaoTO.getIdMissao());

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public ArrayList<Object> listarTodosMissao() throws ClassNotFoundException, SQLException {

        ArrayList<Object> resultado;
        Connection con = ConnectionFactory.abrirConexao();

        MissaoDAO missaoDAO = new MissaoDAO(con);
        resultado = missaoDAO.findAll();

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public MissaoTO pegarUmMissao(int idMissao) throws ClassNotFoundException, SQLException {

        MissaoTO resultado;
        Connection con = ConnectionFactory.abrirConexao();

        MissaoDAO missaoDAO = new MissaoDAO(con);
        resultado = (MissaoTO) missaoDAO.findById(idMissao);

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
