package br.com.fiap.controller;

import br.com.fiap.dao.AcaoDAO;
import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.to.AcaoTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AcaoController {

    public String inserirAcao(int idAcao, String idUsuario, String dsAcao, int qtPontosGerados, LocalDateTime dtAcao)
            throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        AcaoTO acaoTO = new AcaoTO();
        acaoTO.setIdAcao(idAcao);
        acaoTO.setIdUsuario(idUsuario);
        acaoTO.setDsAcao(dsAcao);
        acaoTO.setQtPontosGerados(qtPontosGerados);
        acaoTO.setDtAcao(dtAcao);

        AcaoDAO acaoDAO = new AcaoDAO(con);
        resultado = acaoDAO.inserir(acaoTO);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String alterarAcao(int idAcao, String idUsuario, String dsAcao, int qtPontosGerados, LocalDateTime dtAcao)
            throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        AcaoTO acaoTO = new AcaoTO();
        acaoTO.setIdAcao(idAcao);
        acaoTO.setIdUsuario(idUsuario);
        acaoTO.setDsAcao(dsAcao);
        acaoTO.setQtPontosGerados(qtPontosGerados);
        acaoTO.setDtAcao(dtAcao);

        AcaoDAO acaoDAO = new AcaoDAO(con);
        resultado = acaoDAO.alterar(acaoTO);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String excluirAcao(int idAcao) throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        AcaoTO acaoTO = new AcaoTO();
        acaoTO.setIdAcao(idAcao);

        AcaoDAO acaoDAO = new AcaoDAO(con);
        resultado = acaoDAO.excluir(acaoTO);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public ArrayList<Object> listarTodosAcao() throws ClassNotFoundException, SQLException {

        ArrayList<Object> resultado;
        Connection con = ConnectionFactory.abrirConexao();

        AcaoDAO acaoDAO = new AcaoDAO(con);
        resultado = acaoDAO.listarTodos();

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public AcaoTO pegarUmAcao(int idAcao) throws ClassNotFoundException, SQLException {

        AcaoTO resultado;
        Connection con = ConnectionFactory.abrirConexao();

        AcaoDAO acaoDAO = new AcaoDAO(con);
        // Passa o idAcao diretamente como o Object esperado pelo seu DAO
        resultado = (AcaoTO) acaoDAO.pegarUm(idAcao);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public int criarIdAcao() throws ClassNotFoundException, SQLException {
        int resultado;
        Connection con = ConnectionFactory.abrirConexao();
        AcaoDAO acaoDAO = new AcaoDAO(con);
        resultado = (int) acaoDAO.criarId();
        ConnectionFactory.fecharConexao(con);
        return resultado;
    }
}
