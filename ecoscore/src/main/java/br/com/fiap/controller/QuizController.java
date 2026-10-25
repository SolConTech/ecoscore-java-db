package br.com.fiap.controller;

import br.com.fiap.dao.QuizDAO;
import br.com.fiap.to.QuizTO;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class QuizController {

    public String inserirQuiz(String idUsuario, int numQuestoes, int numAcertos, int qtPontosPorQuestao, LocalDateTime dtQuiz)
            throws ClassNotFoundException, SQLException {
        QuizTO quizTO = new QuizTO();
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

    public String alterarQuiz(Integer idQuiz, String idUsuario, int numQuestoes, int numAcertos, int qtPontosPorQuestao, LocalDateTime dtQuiz)
            throws ClassNotFoundException, SQLException {
        QuizTO quizTO = new QuizTO();
        quizTO.setIdUsuario(idUsuario);
        quizTO.setNumQuestoes(numQuestoes);
        quizTO.setNumAcertos(numAcertos);
        quizTO.setQtPontosPorQuestao(qtPontosPorQuestao);
        quizTO.setQtPontosGerados(); // Atualiza os pontos caso as questões ou acertos tenham mudado
        quizTO.setDtQuiz(dtQuiz);

        QuizDAO quizDAO = new QuizDAO();
        QuizTO resultado = quizDAO.update(idQuiz, quizTO);
        System.out.println("LOG: " + resultado);
        if (resultado != null) {
            return "Inserido com sucesso";
        } else {
            return "Erro a inserir";
        }
    }

    public String excluirQuiz(Integer idQuiz) throws ClassNotFoundException, SQLException {
        QuizDAO quizDAO = new QuizDAO();
        boolean resultado = quizDAO.delete(idQuiz);
        System.out.println("LOG: " + resultado);
        if (resultado) {
            return "Deletado com sucesso";
        } else {
            return "Erro ao deletar";
        }
    }

    public ArrayList<Object> listarTodosQuiz() throws ClassNotFoundException, SQLException {
        ArrayList<Object> resultado;
        QuizDAO quizDAO = new QuizDAO();
        resultado = quizDAO.findAll();
        return resultado;
    }

    public QuizTO pegarUmQuiz(Integer idQuiz) throws ClassNotFoundException, SQLException {
        QuizTO resultado;
        QuizDAO quizDAO = new QuizDAO();
        resultado = quizDAO.findById(idQuiz);
        return resultado;
    }
}
