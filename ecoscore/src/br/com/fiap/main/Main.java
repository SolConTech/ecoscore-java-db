package br.com.fiap.main;

import br.com.fiap.dao.*;
import br.com.fiap.dto.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Acao acao;
        CalculadoraPontos calc;
        Missao missao;
        Post post;
        Quiz quiz;
        RankingUsuario rank;
        Usuario usuario;
        AcaoDAO acaoDAO;
        MissaoDAO missaoDAO;
        PostDAO postDAO;
        QuizDAO quizDAO;
        RankingUsuarioDAO rankDAO;
        UsuarioDAO usuarioDAO;
        Connection con = ConnectionFactory.abrirConexao();
        usuarioDAO = new UsuarioDAO(con);
        System.out.println(usuarioDAO.listarTodos());
        usuario = new Usuario("carlos", "carlos", 12,12);
        System.out.println(usuario.detalhesPerfil());
        //Main para testes mocados
    }
}
