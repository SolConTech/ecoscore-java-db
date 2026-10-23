package br.com.fiap.bo;

import br.com.fiap.dao.MissaoDAO;
import br.com.fiap.to.MissaoTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class MissaoBO implements IBO{
    private MissaoDAO MissaoDAO;

    @Override
    public ArrayList<Object> findAll() throws SQLException {
        MissaoDAO = new MissaoDAO();
        return MissaoDAO.findAll();
    }

    @Override
    public Object findById(Object id) throws SQLException {
        MissaoDAO = new MissaoDAO();
        return MissaoDAO.findById(id);
    }

    @Override
    public Object save(Object body) throws SQLException {
        MissaoDAO = new MissaoDAO();
        MissaoTO missaoTO = (MissaoTO) body;
        return MissaoDAO.save(missaoTO);
    }

    @Override
    public Object update(Object id, Object body) throws SQLException {
        MissaoDAO = new MissaoDAO();
        MissaoTO missaoTO = (MissaoTO) body;
        return MissaoDAO.update(id, missaoTO);
    }

    @Override
    public Boolean delete(Object id) throws SQLException {
        MissaoDAO = new MissaoDAO();
        return MissaoDAO.delete(id);
    }
}
