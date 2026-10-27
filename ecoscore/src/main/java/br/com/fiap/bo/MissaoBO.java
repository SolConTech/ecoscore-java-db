package br.com.fiap.bo;

import br.com.fiap.dao.MissaoDAO;
import br.com.fiap.to.MissaoTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class MissaoBO implements IBO{
    private MissaoDAO missaoDAO;

    @Override
    public ArrayList<Object> findAll() throws SQLException {
        missaoDAO = new MissaoDAO();
        return missaoDAO.findAll();
    }

    @Override
    public Object findById(Object id) throws SQLException, IllegalArgumentException {
        Integer idMissao;
        try {
            // pra pegar erros de parse e cast se o objeto ou id for invalido
            idMissao = Integer.parseInt(id.toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID inválido.");
        }
        missaoDAO = new MissaoDAO();
        return missaoDAO.findById(idMissao);
    }

    @Override
    public Object save(Object body) throws SQLException, IllegalArgumentException {
        MissaoTO missaoTO;
        try {
            // pra pegar erros de parse e cast se o objeto ou id for invalido
            missaoTO = (MissaoTO) body;
        } catch (Exception e) {
            throw new IllegalArgumentException("ID ou Corpo inválido");
        }
        missaoDAO = new MissaoDAO();
        return missaoDAO.save(missaoTO);
    }

    @Override
    public Object update(Object id, Object body) throws SQLException, IllegalArgumentException {
        Integer idMissao;
        MissaoTO missaoTO;
        try {
            // pra pegar erros de parse e cast se o objeto ou id for invalido
            idMissao = Integer.parseInt(id.toString());
            missaoTO = (MissaoTO) body;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID inválido.");
        } catch (Exception e) {
            throw new IllegalArgumentException("ID ou Corpo inválido");
        }
        missaoDAO = new MissaoDAO();
        if (missaoDAO.findById(idMissao) == null){
            throw new IllegalArgumentException("ID \'" + idMissao + "\' não encontrado no banco!");
        }
        return missaoDAO.update(idMissao, missaoTO);
    }

    @Override
    public Boolean delete(Object id) throws SQLException, IllegalArgumentException {
        missaoDAO = new MissaoDAO();
        Integer idMissao;
        try {
            // pra pegar erros de parse e cast se o objeto ou id for invalido
            idMissao = Integer.parseInt(id.toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID inválido.");
        }
        if (missaoDAO.findById(idMissao) == null){
            throw new SQLException("ID \'" + idMissao + "\' não encontrado no banco!");
        }
        return missaoDAO.delete(idMissao);
    }
}
