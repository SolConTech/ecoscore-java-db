package br.com.fiap.controller;

import br.com.fiap.model.dao.AcaoDAO;
import br.com.fiap.model.dao.ConnectionFactory;
import br.com.fiap.model.dto.Acao;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AcaoController {

    public String inserirAcao(int idAcao, String idUsuario, String dsAcao, int qtPontosGerados, LocalDateTime dtAcao)
            throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        Acao acao = new Acao();
        acao.setIdAcao(idAcao);
        acao.setIdUsuario(idUsuario);
        acao.setDsAcao(dsAcao);
        acao.setQtPontosGerados(qtPontosGerados);
        acao.setDtAcao(dtAcao);

        AcaoDAO acaoDAO = new AcaoDAO(con);
        resultado = acaoDAO.inserir(acao);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String alterarAcao(int idAcao, String idUsuario, String dsAcao, int qtPontosGerados, LocalDateTime dtAcao)
            throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        Acao acao = new Acao();
        acao.setIdAcao(idAcao);
        acao.setIdUsuario(idUsuario);
        acao.setDsAcao(dsAcao);
        acao.setQtPontosGerados(qtPontosGerados);
        acao.setDtAcao(dtAcao);

        AcaoDAO acaoDAO = new AcaoDAO(con);
        resultado = acaoDAO.alterar(acao);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String excluirAcao(int idAcao) throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        Acao acao = new Acao();
        acao.setIdAcao(idAcao);

        AcaoDAO acaoDAO = new AcaoDAO(con);
        resultado = acaoDAO.excluir(acao);

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

    public Acao pegarUmAcao(int idAcao) throws ClassNotFoundException, SQLException {

        Acao resultado;
        Connection con = ConnectionFactory.abrirConexao();

        AcaoDAO acaoDAO = new AcaoDAO(con);
        // Passa o idAcao diretamente como o Object esperado pelo seu DAO
        resultado = (Acao) acaoDAO.pegarUm(idAcao);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }
}
