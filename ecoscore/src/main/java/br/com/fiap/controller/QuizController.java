package br.com.fiap.controller;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.QuizDAO;
import br.com.fiap.to.PostTO;
import br.com.fiap.to.QuizTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class QuizController {

    public String inserirQuiz(int idQuiz, String idUsuario, int numQuestoes, int numAcertos, int qtPontosPorQuestao, LocalDateTime dtQuiz)
            throws ClassNotFoundException, SQLException {
        QuizTO quizTO = new QuizTO();
        quizTO.setIdQuiz(idQuiz);
        quizTO.setIdUsuario(idUsuario);
        quizTO.setNumQuestoes(numQuestoes);
        quizTO.setNumAcertos(numAcertos);
        quizTO.setQtPontosPorQuestao(qtPontosPorQuestao);
        quizTO.setQtPontosGerados(); // Executa o cálculo interno do DTO antes do insert
        quizTO.setDtQuiz(dtQuiz);

        QuizDAO quizDAO = new QuizDAO();
        QuizTO resultado = quizDAO.save(quizTO);
        System.out.println("LOG: " + resultado);
        if (resultado != null) {
            return "Inserido com sucesso";
        } else {
            return "Erro a inserir";
        }
    }

    public String alterarQuiz(int idQuiz, String idUsuario, int numQuestoes, int numAcertos, int qtPontosPorQuestao, LocalDateTime dtQuiz)
            throws ClassNotFoundException, SQLException {
        QuizTO quizTO = new QuizTO();
        quizTO.setIdQuiz(idQuiz);
        quizTO.setIdUsuario(idUsuario);
        quizTO.setNumQuestoes(numQuestoes);
        quizTO.setNumAcertos(numAcertos);
        quizTO.setQtPontosPorQuestao(qtPontosPorQuestao);
        quizTO.setQtPontosGerados(); // Atualiza os pontos caso as questões ou acertos tenham mudado
        quizTO.setDtQuiz(dtQuiz);

        QuizDAO quizDAO = new QuizDAO();
        QuizTO resultado = quizDAO.update(quizTO.getIdQuiz(), quizTO);
        System.out.println("LOG: " + resultado);
        if (resultado != null) {
            return "Inserido com sucesso";
        } else {
            return "Erro a inserir";
        }
    }

    public String excluirQuiz(int idQuiz) throws ClassNotFoundException, SQLException {

        String resultado = "";
        Connection con = ConnectionFactory.abrirConexao();

        QuizTO quizTO = new QuizTO();
        quizTO.setIdQuiz(idQuiz);

        QuizDAO quizDAO = new QuizDAO(con);
        quizDAO.delete(quizTO.getIdQuiz());

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public ArrayList<Object> listarTodosQuiz() throws ClassNotFoundException, SQLException {

        ArrayList<Object> resultado;
        Connection con = ConnectionFactory.abrirConexao();

        QuizDAO quizDAO = new QuizDAO(con);
        resultado = quizDAO.findAll();

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public QuizTO pegarUmQuiz(int idQuiz) throws ClassNotFoundException, SQLException {

        QuizTO resultado;
        Connection con = ConnectionFactory.abrirConexao();

        QuizDAO quizDAO = new QuizDAO(con);
        resultado = (QuizTO) quizDAO.findById(idQuiz);

        ConnectionFactory.fecharConexao();
        return resultado;
    }

    public int criarIdQuiz() throws ClassNotFoundException, SQLException {
        int resultado;
        Connection con = ConnectionFactory.abrirConexao();
        QuizDAO quizDAO = new QuizDAO(con);
        resultado = quizDAO.criarId();
        ConnectionFactory.fecharConexao();
        return resultado;
    }
}
