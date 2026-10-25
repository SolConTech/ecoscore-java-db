package br.com.fiap.bo;

import br.com.fiap.dao.QuizDAO;
import br.com.fiap.to.QuizTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class QuizBO implements IBO{
    private QuizDAO QuizDAO;

    @Override
    public ArrayList<Object> findAll() throws SQLException {
        QuizDAO = new QuizDAO();
        return QuizDAO.findAll();
    }

    @Override
    public Object findById(Object id) throws SQLException {
        QuizDAO = new QuizDAO();
        return QuizDAO.findById(id);
    }

    @Override
    public Object save(Object body) throws SQLException {
        QuizDAO = new QuizDAO();
        QuizTO quizTO = (QuizTO) body;
        return QuizDAO.save(quizTO);
    }

    @Override
    public Object update(Object id, Object body) throws SQLException {
        QuizDAO = new QuizDAO();
        QuizTO quizTO = (QuizTO) body;
        return QuizDAO.update(id, quizTO);
    }

    @Override
    public Boolean delete(Object id) throws SQLException {
        QuizDAO = new QuizDAO();
        return QuizDAO.delete(id);
    }
}
