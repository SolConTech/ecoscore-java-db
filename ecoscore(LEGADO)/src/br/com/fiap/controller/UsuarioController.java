package br.com.fiap.controller;

import br.com.fiap.model.dao.ConnectionFactory;
import br.com.fiap.model.dao.UsuarioDAO;
import br.com.fiap.model.dto.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioController {

    public String inserirUsuario(String idUsuario, String nmUsuario, float vlMerito, int qtSoulCoins,String dsAtividadeRecente,String selosGanhos)
            throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        usuario.setNmUsuario(nmUsuario);
        usuario.setVlMerito(vlMerito);
        usuario.setQtSoulCoins(qtSoulCoins);
        usuario.setDsAtividadeRecente(dsAtividadeRecente);
        usuario.setSelosGanhos(selosGanhos);

        UsuarioDAO usuarioDAO = new UsuarioDAO(con);
        resultado = usuarioDAO.inserir(usuario);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String alterarUsuario(String idUsuario, String nmUsuario, float vlMerito, int qtSoulCoins, String dsAtividadeRecente, String selosGanhos)
            throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        usuario.setNmUsuario(nmUsuario);
        usuario.setVlMerito(vlMerito);
        usuario.setQtSoulCoins(qtSoulCoins);
        usuario.setDsAtividadeRecente(dsAtividadeRecente);
        usuario.setSelosGanhos(selosGanhos);

        UsuarioDAO usuarioDAO = new UsuarioDAO(con);
        resultado = usuarioDAO.alterar(usuario);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String excluirUsuario(String idUsuario) throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);

        UsuarioDAO usuarioDAO = new UsuarioDAO(con);
        resultado = usuarioDAO.excluir(usuario);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public ArrayList<Object> listarTodosUsuario() throws ClassNotFoundException, SQLException {

        ArrayList<Object> resultado;
        Connection con = ConnectionFactory.abrirConexao();

        UsuarioDAO usuarioDAO = new UsuarioDAO(con);
        resultado = usuarioDAO.listarTodos();

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public Usuario pegarUmUsuario(String idUsuario) throws ClassNotFoundException, SQLException {

        Usuario resultado;
        Connection con = ConnectionFactory.abrirConexao();

        // Criando o objeto contendo o ID exigido pelo cast interno da sua UsuarioDAO
        Usuario usuarioBusca = new Usuario();
        usuarioBusca.setIdUsuario(idUsuario);

        UsuarioDAO usuarioDAO = new UsuarioDAO(con);
        resultado = (Usuario) usuarioDAO.pegarUm(usuarioBusca);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }
}
