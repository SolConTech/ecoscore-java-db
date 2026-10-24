package br.com.fiap.controller;

import br.com.fiap.dao.AcaoDAO;
import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.to.AcaoTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AcaoController {

    public String inserirAcao(String idUsuario, String dsAcao, int qtPontosGerados, LocalDateTime dtAcao)
            throws ClassNotFoundException, SQLException {
        AcaoTO acaoTO = new AcaoTO();
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
        AcaoTO acaoTO = new AcaoTO();
        acaoTO.setIdUsuario(idUsuario);
        acaoTO.setDsAcao(dsAcao);
        acaoTO.setQtPontosGerados(qtPontosGerados);
        acaoTO.setDtAcao(dtAcao);

        AcaoDAO acaoDAO = new AcaoDAO();
        AcaoTO resultado = acaoDAO.update(idAcao, acaoTO);
        System.out.println("LOG: " + resultado);
        if (resultado != null) {
            return "Inserido com sucesso";
        } else {
            return "Erro a inserir";
        }
    }

    public String excluirAcao(int idAcao) throws ClassNotFoundException, SQLException {
        AcaoDAO acaoDAO = new AcaoDAO();
        boolean resultado = acaoDAO.delete(idAcao);
        System.out.println("LOG: " + resultado);
        if (resultado) {
            return "Deletado com sucesso";
        } else {
            return "Erro ao deletar";
        }
    }

    public ArrayList<Object> listarTodosAcao() throws ClassNotFoundException, SQLException {
        ArrayList<Object> resultado;
        AcaoDAO acaoDAO = new AcaoDAO();
        resultado = acaoDAO.findAll();
        return resultado;
    }

    public AcaoTO pegarUmAcao(int idAcao) throws ClassNotFoundException, SQLException {
        AcaoTO resultado;
        AcaoDAO acaoDAO = new AcaoDAO();
        resultado = acaoDAO.findById(idAcao);
        return resultado;
    }
}
