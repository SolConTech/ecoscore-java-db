package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.UsuarioTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioBO implements IBO{
    private UsuarioDAO usuarioDAO;

    @Override
    public ArrayList<Object> findAll() throws SQLException {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.findAll();
    }

    @Override
    public Object findById(Object id) throws SQLException, IllegalArgumentException {
        String idUsuario = (String) id;
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.findById(idUsuario);
    }

    @Override
    public Object save(Object body) throws SQLException, IllegalArgumentException {
        UsuarioTO usuarioTO;
        try {
            // pra pegar erros de parse e cast se o objeto ou id for invalido
            usuarioTO = (UsuarioTO) body;
        } catch (Exception e) {
            throw new IllegalArgumentException("ID ou Corpo inválido");
        }
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.save(usuarioTO);
    }

    @Override
    public Object update(Object id, Object body) throws SQLException, IllegalArgumentException {
        String idUsuario = (String) id;
        UsuarioTO usuarioTO;
        try {
            // pra pegar erros de parse e cast se o objeto ou id for invalido
            usuarioTO = (UsuarioTO) body;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID inválido.");
        } catch (Exception e) {
            throw new IllegalArgumentException("ID ou Corpo inválido");
        }
        usuarioDAO = new UsuarioDAO();
        if (usuarioDAO.findById(idUsuario) == null){
            throw new IllegalArgumentException("ID \'" + idUsuario + "\' não encontrado no banco!");
        }
        usuarioTO.setIdUsuario(idUsuario); //pra reforçar validação com o set.
        return usuarioDAO.update(usuarioTO.getIdUsuario(), usuarioTO);
    }

    @Override
    public Boolean delete(Object id) throws SQLException, IllegalArgumentException {
        String idUsuario = (String) id;
        usuarioDAO = new UsuarioDAO();
        if (usuarioDAO.findById(idUsuario) == null){
            throw new SQLException("ID \'" + idUsuario + "\' não encontrado no banco!");
        }
        return usuarioDAO.delete(idUsuario);
    }
}
