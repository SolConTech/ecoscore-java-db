package br.com.fiap.bo;

import br.com.fiap.dao.QuizDAO;
import br.com.fiap.to.QuizTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class QuizBO implements IBO{
    private QuizDAO quizDAO;

    @Override
    public ArrayList<Object> findAll() throws SQLException {
        quizDAO = new QuizDAO();
        return quizDAO.findAll();
    }

    @Override
    public Object findById(Object id) throws SQLException, IllegalArgumentException {
        Integer idQuiz;
        try {
            // pra pegar erros de parse e cast se o objeto ou id for invalido
            idQuiz = Integer.parseInt(id.toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID inválido.");
        }
        quizDAO = new QuizDAO();
        return quizDAO.findById(idQuiz);
    }

    @Override
    public Object save(Object body) throws SQLException, IllegalArgumentException {
        QuizTO quizTO;
        try {
            // pra pegar erros de parse e cast se o objeto ou id for invalido
            quizTO = (QuizTO) body;
        } catch (Exception e) {
            throw new IllegalArgumentException("ID ou Corpo inválido");
        }
        quizDAO = new QuizDAO();
        return quizDAO.save(quizTO);
    }

    @Override
    public Object update(Object id, Object body) throws SQLException, IllegalArgumentException {
        Integer idQuiz;
        QuizTO quizTO;
        try {
            // pra pegar erros de parse e cast se o objeto ou id for invalido
            idQuiz = Integer.parseInt(id.toString());
            quizTO = (QuizTO) body;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID inválido.");
        } catch (Exception e) {
            throw new IllegalArgumentException("ID ou Corpo inválido");
        }
        quizDAO = new QuizDAO();
        if (quizDAO.findById(idQuiz) == null){
            throw new IllegalArgumentException("ID \'" + idQuiz + "\' não encontrado no banco!");
        }
        return quizDAO.update(idQuiz, quizTO);
    }

    @Override
    public Boolean delete(Object id) throws SQLException, IllegalArgumentException {
        quizDAO = new QuizDAO();
        Integer idQuiz;
        try {
            // pra pegar erros de parse e cast se o objeto ou id for invalido
            idQuiz = Integer.parseInt(id.toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID inválido.");
        }
        if (quizDAO.findById(idQuiz) == null){
            throw new SQLException("ID \'" + idQuiz + "\' não encontrado no banco!");
        }
        return quizDAO.delete(idQuiz);
    }
}

