package br.com.fiap.controller;

import br.com.fiap.dao.MissaoDAO; // Certifique-se de que o nome da sua DAO é este
import br.com.fiap.to.MissaoTO;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MissaoController {

    public String inserirMissao(String nmMissao, String dsMissao, String selo, int qtPontosGerados, LocalDateTime dtMissao)
            throws ClassNotFoundException, SQLException {
        MissaoTO missaoTO = new MissaoTO();
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
        missaoTO.setNmMissao(nmMissao);
        missaoTO.setDsMissao(dsMissao);
        missaoTO.setSelo(selo);
        missaoTO.setQtPontosGerados(qtPontosGerados);
        missaoTO.setDtMissao(dtMissao);

        MissaoDAO missaoDAO = new MissaoDAO();
        MissaoTO resultado = missaoDAO.update(idMissao, missaoTO);
        System.out.println("LOG: " + resultado);
        if (resultado != null) {
            return "Inserido com sucesso";
        } else {
            return "Erro a inserir";
        }
    }

    public String excluirMissao(int idMissao) throws ClassNotFoundException, SQLException {
        MissaoDAO missaoDAO = new MissaoDAO();
        boolean resultado = missaoDAO.delete(idMissao);
        System.out.println("LOG: " + resultado);
        if (resultado) {
            return "Deletado com sucesso";
        } else {
            return "Erro ao deletar";
        }
    }

    public ArrayList<Object> listarTodosMissao() throws ClassNotFoundException, SQLException {
        ArrayList<Object> resultado;
        MissaoDAO missaoDAO = new MissaoDAO();
        resultado = missaoDAO.findAll();
        return resultado;
    }

    public MissaoTO pegarUmMissao(int idMissao) throws ClassNotFoundException, SQLException {
        MissaoTO resultado;
        MissaoDAO missaoDAO = new MissaoDAO();
        resultado = missaoDAO.findById(idMissao);
        return resultado;
    }

}
