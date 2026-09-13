package br.com.fiap.controller;

import br.com.fiap.model.dao.ConnectionFactory;
import br.com.fiap.model.dao.QuizDAO;
import br.com.fiap.model.dto.Quiz;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class QuizController {

    public String inserirQuiz(int idQuiz, String idUsuario, int numQuestoes, int numAcertos, int qtPontosPorQuestao, LocalDateTime dtQuiz)
            throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        Quiz quiz = new Quiz();
        quiz.setIdQuiz(idQuiz);
        quiz.setIdUsuario(idUsuario);
        quiz.setNumQuestoes(numQuestoes);
        quiz.setNumAcertos(numAcertos);
        quiz.setQtPontosPorQuestao(qtPontosPorQuestao);
        quiz.setQtPontosGerados(); // Executa o cálculo interno do DTO antes do insert
        quiz.setDtQuiz(dtQuiz);

        QuizDAO quizDAO = new QuizDAO(con);
        resultado = quizDAO.inserir(quiz);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String alterarQuiz(int idQuiz, String idUsuario, int numQuestoes, int numAcertos, int qtPontosPorQuestao, LocalDateTime dtQuiz)
            throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        Quiz quiz = new Quiz();
        quiz.setIdQuiz(idQuiz);
        quiz.setIdUsuario(idUsuario);
        quiz.setNumQuestoes(numQuestoes);
        quiz.setNumAcertos(numAcertos);
        quiz.setQtPontosPorQuestao(qtPontosPorQuestao);
        quiz.setQtPontosGerados(); // Atualiza os pontos caso as questões ou acertos tenham mudado
        quiz.setDtQuiz(dtQuiz);

        QuizDAO quizDAO = new QuizDAO(con);
        resultado = quizDAO.alterar(quiz);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String excluirQuiz(int idQuiz) throws ClassNotFoundException, SQLException {

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();

        Quiz quiz = new Quiz();
        quiz.setIdQuiz(idQuiz);

        QuizDAO quizDAO = new QuizDAO(con);
        resultado = quizDAO.excluir(quiz);

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

    public Quiz pegarUmQuiz(int idQuiz) throws ClassNotFoundException, SQLException {

        Quiz resultado;
        Connection con = ConnectionFactory.abrirConexao();

        QuizDAO quizDAO = new QuizDAO(con);
        resultado = (Quiz) quizDAO.pegarUm(idQuiz);

        ConnectionFactory.fecharConexao(con);
        return resultado;
    }
}
