package br.com.fiap.controller;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.UsuarioTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioController {

    public String inserirUsuario(String idUsuario, String nmUsuario, float vlMerito, int qtSoulCoins,String dsAtividadeRecente,String selosGanhos)
            throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        UsuarioTO usuarioTO = new UsuarioTO();
        usuarioTO.setIdUsuario(idUsuario);
        usuarioTO.setNmUsuario(nmUsuario);
        usuarioTO.setVlMerito(vlMerito);
        usuarioTO.setQtSoulCoins(qtSoulCoins);
        usuarioTO.setDsAtividadeRecente(dsAtividadeRecente);
        usuarioTO.setSelosGanhos(selosGanhos);

        UsuarioDAO usuarioDAO = new UsuarioDAO(con);
        resultado = usuarioDAO.inserir(usuarioTO);

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public String alterarUsuario(String idUsuario, String nmUsuario, float vlMerito, int qtSoulCoins, String dsAtividadeRecente, String selosGanhos)
            throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        UsuarioTO usuarioTO = new UsuarioTO();
        usuarioTO.setIdUsuario(idUsuario);
        usuarioTO.setNmUsuario(nmUsuario);
        usuarioTO.setVlMerito(vlMerito);
        usuarioTO.setQtSoulCoins(qtSoulCoins);
        usuarioTO.setDsAtividadeRecente(dsAtividadeRecente);
        usuarioTO.setSelosGanhos(selosGanhos);

        UsuarioDAO usuarioDAO = new UsuarioDAO(con);
        resultado = usuarioDAO.alterar(, usuarioTO);

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public String excluirUsuario(String idUsuario) throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        UsuarioTO usuarioTO = new UsuarioTO();
        usuarioTO.setIdUsuario(idUsuario);

        UsuarioDAO usuarioDAO = new UsuarioDAO(con);
        resultado = usuarioDAO.excluir(usuarioTO);

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public ArrayList<Object> listarTodosUsuario() throws ClassNotFoundException, SQLException {

        ArrayList<Object> resultado;
        Connection con = ConnectionFactory.abrirConexao();

        UsuarioDAO usuarioDAO = new UsuarioDAO(con);
        resultado = usuarioDAO.listarTodos();

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public UsuarioTO pegarUmUsuario(String idUsuario) throws ClassNotFoundException, SQLException {

        UsuarioTO resultado;
        Connection con = ConnectionFactory.abrirConexao();

        // Criando o objeto contendo o ID exigido pelo cast interno da sua UsuarioDAO
        UsuarioTO usuarioTOBusca = new UsuarioTO();
        usuarioTOBusca.setIdUsuario(idUsuario);

        UsuarioDAO usuarioDAO = new UsuarioDAO(con);
        resultado = (UsuarioTO) usuarioDAO.listarUm(usuarioTOBusca);

        ConnectionFactory.fecharConexao();
        return resultado;
    }
}
