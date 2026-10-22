package br.com.fiap.controller;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.QuizDAO;
import br.com.fiap.to.QuizTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class QuizController {

    public String inserirQuiz(int idQuiz, String idUsuario, int numQuestoes, int numAcertos, int qtPontosPorQuestao, LocalDateTime dtQuiz)
            throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        QuizTO quizTO = new QuizTO();
        quizTO.setIdQuiz(idQuiz);
        quizTO.setIdUsuario(idUsuario);
        quizTO.setNumQuestoes(numQuestoes);
        quizTO.setNumAcertos(numAcertos);
        quizTO.setQtPontosPorQuestao(qtPontosPorQuestao);
        quizTO.setQtPontosGerados(); // Executa o cálculo interno do DTO antes do insert
        quizTO.setDtQuiz(dtQuiz);

        QuizDAO quizDAO = new QuizDAO(con);
        resultado = quizDAO.inserir(quizTO);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String alterarQuiz(int idQuiz, String idUsuario, int numQuestoes, int numAcertos, int qtPontosPorQuestao, LocalDateTime dtQuiz)
            throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        QuizTO quizTO = new QuizTO();
        quizTO.setIdQuiz(idQuiz);
        quizTO.setIdUsuario(idUsuario);
        quizTO.setNumQuestoes(numQuestoes);
        quizTO.setNumAcertos(numAcertos);
        quizTO.setQtPontosPorQuestao(qtPontosPorQuestao);
        quizTO.setQtPontosGerados(); // Atualiza os pontos caso as questões ou acertos tenham mudado
        quizTO.setDtQuiz(dtQuiz);

        QuizDAO quizDAO = new QuizDAO(con);
        resultado = quizDAO.alterar(quizTO);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String excluirQuiz(int idQuiz) throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        QuizTO quizTO = new QuizTO();
        quizTO.setIdQuiz(idQuiz);

        QuizDAO quizDAO = new QuizDAO(con);
        resultado = quizDAO.excluir(quizTO);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public ArrayList<Object> listarTodosQuiz() throws ClassNotFoundException, SQLException {

        ArrayList<Object> resultado;
        Connection con = ConnectionFactory.abrirConexao();

        QuizDAO quizDAO = new QuizDAO(con);
        resultado = quizDAO.listarTodos();

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public QuizTO pegarUmQuiz(int idQuiz) throws ClassNotFoundException, SQLException {

        QuizTO resultado;
        Connection con = ConnectionFactory.abrirConexao();

        QuizDAO quizDAO = new QuizDAO(con);
        resultado = (QuizTO) quizDAO.pegarUm(idQuiz);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public int criarIdQuiz() throws ClassNotFoundException, SQLException {
        int resultado;
        Connection con = ConnectionFactory.abrirConexao();
        QuizDAO quizDAO = new QuizDAO(con);
        resultado = quizDAO.criarId();
        ConnectionFactory.fecharConexao(con);
        return resultado;
    }
}
