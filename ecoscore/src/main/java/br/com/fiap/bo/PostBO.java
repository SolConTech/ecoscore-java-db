package br.com.fiap.bo;

import br.com.fiap.dao.PostDAO;
import br.com.fiap.to.PostTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class PostBO implements IBO{
    private PostDAO PostDAO;

    @Override
    public ArrayList<Object> findAll() throws SQLException {
        PostDAO = new PostDAO();
        return PostDAO.findAll();
    }

    @Override
    public Object findById(Object id) throws SQLException {
        PostDAO = new PostDAO();
        return PostDAO.findById(id);
    }

    @Override
    public Object save(Object body) throws SQLException {
        PostDAO = new PostDAO();
        PostTO postTO = (PostTO) body;
        return PostDAO.save(postTO);
    }

    @Override
    public Object update(Object id, Object body) throws SQLException {
        PostDAO = new PostDAO();
        PostTO postTO = (PostTO) body;
        return PostDAO.update(id, postTO);
    }

    @Override
    public Boolean delete(Object id) throws SQLException {
        PostDAO = new PostDAO();
        return PostDAO.delete(id);
    }
}
