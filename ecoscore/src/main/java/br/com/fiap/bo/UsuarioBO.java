package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.UsuarioTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioBO implements IBO{
    private UsuarioDAO UsuarioDAO;

    @Override
    public ArrayList<Object> findAll() throws SQLException {
        UsuarioDAO = new UsuarioDAO();
        return UsuarioDAO.findAll();
    }

    @Override
    public Object findById(Object id) throws SQLException {
        UsuarioDAO = new UsuarioDAO();
        return UsuarioDAO.findById(id);
    }

    @Override
    public Object save(Object body) throws SQLException {
        UsuarioDAO = new UsuarioDAO();
        UsuarioTO usuarioTO = (UsuarioTO) body;
        return UsuarioDAO.save(usuarioTO);
    }

    @Override
    public Object update(Object id, Object body) throws SQLException {
        UsuarioDAO = new UsuarioDAO();
        UsuarioTO usuarioTO = (UsuarioTO) body;
        return UsuarioDAO.update(id, usuarioTO);
    }

    @Override
    public Boolean delete(Object id) throws SQLException {
        UsuarioDAO = new UsuarioDAO();
        return UsuarioDAO.delete(id);
    }
}
