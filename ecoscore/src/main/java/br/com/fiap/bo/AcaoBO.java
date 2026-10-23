package br.com.fiap.bo;

import br.com.fiap.dao.AcaoDAO;
import br.com.fiap.to.AcaoTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class AcaoBO implements IBO{

    private AcaoDAO acaoDAO;

    @Override
    public ArrayList<Object> findAll() throws SQLException {
        acaoDAO = new AcaoDAO();
        return acaoDAO.findAll();
    }

    @Override
    public Object findById(Object id) throws SQLException {
        acaoDAO = new AcaoDAO();
        return acaoDAO.findById(id);
    }

    @Override
    public Object save(Object body) throws SQLException {
        acaoDAO = new AcaoDAO();
        AcaoTO acaoTO = (AcaoTO) body;
        return acaoDAO.save(acaoTO);
    }

    @Override
    public Object update(Object id, Object body) throws SQLException {
        acaoDAO = new AcaoDAO();
        AcaoTO acaoTO = (AcaoTO) body;
        return acaoDAO.update(id, acaoTO);
    }

    @Override
    public Boolean delete(Object id) throws SQLException {
        acaoDAO = new AcaoDAO();
        return acaoDAO.delete(id);
    }
}
