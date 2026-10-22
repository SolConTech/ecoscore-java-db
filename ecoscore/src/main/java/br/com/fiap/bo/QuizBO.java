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
        return QuizDAO.listarTodos();
    }

    @Override
    public Object findById(Object id) throws SQLException {
        QuizDAO = new QuizDAO();
        return QuizDAO.listarUm(id);
    }

    @Override
    public Object save(Object body) throws SQLException {
        QuizDAO = new QuizDAO();
        QuizTO quizTO = (QuizTO) body;
        return QuizDAO.inserir(quizTO);
    }

    @Override
    public Object update(Object id, Object body) throws SQLException {
        QuizDAO = new QuizDAO();
        QuizTO quizTO = (QuizTO) body;
        return QuizDAO.alterar(id, quizTO);
    }

    @Override
    public Object delete(Object id) throws SQLException {
        QuizDAO = new QuizDAO();
        return QuizDAO.excluir(id);
    }
}
