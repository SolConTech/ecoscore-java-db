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
        AcaoTO acaoTO = new AcaoTO();
        acaoTO.setIdAcao(idAcao);
        acaoTO.setIdUsuario(idUsuario);
        acaoTO.setDsAcao(dsAcao);
        acaoTO.setQtPontosGerados(qtPontosGerados);
        acaoTO.setDtAcao(dtAcao);

        AcaoDAO acaoDAO = new AcaoDAO();
        AcaoTO resultado = acaoDAO.save(acaoTO);
        System.out.println("LOG: " + resultado);
        if (resultado != null) {
            return "Inserido com sucesso";
        } else {
            return "Erro a inserir";
        }
    }

    public String alterarAcao(int idAcao, String idUsuario, String dsAcao, int qtPontosGerados, LocalDateTime dtAcao)
            throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();

        AcaoTO acaoTO = new AcaoTO();
        acaoTO.setIdAcao(idAcao);
        acaoTO.setIdUsuario(idUsuario);
        acaoTO.setDsAcao(dsAcao);
        acaoTO.setQtPontosGerados(qtPontosGerados);
        acaoTO.setDtAcao(dtAcao);

        AcaoDAO acaoDAO = new AcaoDAO(con);
        AcaoTO resultado = acaoDAO.update(acaoTO.getIdUsuario(), acaoTO);
        System.out.println("LOG: " + resultado);
        if (resultado != null) {
            return "Inserido com sucesso";
        } else {
            return "Erro a inserir";
        }
    }

    public String excluirAcao(int idAcao) throws ClassNotFoundException, SQLException {

        String resultado = "";
        Connection con = ConnectionFactory.abrirConexao();

        AcaoTO acaoTO = new AcaoTO();
        acaoTO.setIdAcao(idAcao);

        AcaoDAO acaoDAO = new AcaoDAO(con);
        acaoDAO.delete(acaoTO.getIdAcao());

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public ArrayList<Object> listarTodosAcao() throws ClassNotFoundException, SQLException {

        ArrayList<Object> resultado;
        Connection con = ConnectionFactory.abrirConexao();

        AcaoDAO acaoDAO = new AcaoDAO(con);
        resultado = acaoDAO.findAll();

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public AcaoTO pegarUmAcao(int idAcao) throws ClassNotFoundException, SQLException {

        AcaoTO resultado;
        Connection con = ConnectionFactory.abrirConexao();

        AcaoDAO acaoDAO = new AcaoDAO(con);
        // Passa o idAcao diretamente como o Object esperado pelo seu DAO
        resultado = (AcaoTO) acaoDAO.findById(idAcao);

        ConnectionFactory.fecharConexao();
        return resultado;
    }
}
