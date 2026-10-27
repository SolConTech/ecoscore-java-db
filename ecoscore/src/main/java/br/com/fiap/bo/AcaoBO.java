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
    public Object findById(Object id) throws SQLException, IllegalArgumentException {
        Integer idAcao;
        try {
            // pra pegar erros de parse e cast se o objeto ou id for invalido
            idAcao = Integer.parseInt(id.toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID inválido.");
        }
        acaoDAO = new AcaoDAO();
        return acaoDAO.findById(idAcao);
    }

    @Override
    public Object save(Object body) throws SQLException, IllegalArgumentException {
        AcaoTO acaoTO;
        try {
            // pra pegar erros de parse e cast se o objeto ou id for invalido
            acaoTO = (AcaoTO) body;
        } catch (Exception e) {
            throw new IllegalArgumentException("ID ou Corpo inválido");
        }
        acaoDAO = new AcaoDAO();
        return acaoDAO.save(acaoTO);
    }

    @Override
    public Object update(Object id, Object body) throws SQLException, IllegalArgumentException {
        Integer idAcao;
        AcaoTO acaoTO;
        try {
            // pra pegar erros de parse e cast se o objeto ou id for invalido
            idAcao = Integer.parseInt(id.toString());
            acaoTO = (AcaoTO) body;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID inválido.");
        } catch (Exception e) {
            throw new IllegalArgumentException("ID ou Corpo inválido");
        }
        acaoDAO = new AcaoDAO();
        if (acaoDAO.findById(idAcao) == null){
            throw new IllegalArgumentException("ID \'" + idAcao + "\' não encontrado no banco!");
        }
        return acaoDAO.update(idAcao, acaoTO);
    }

    @Override
    public Boolean delete(Object id) throws SQLException, IllegalArgumentException {
        Integer idAcao;
        try {
            // pra pegar erros de parse e cast se o objeto ou id for invalido
            idAcao = Integer.parseInt(id.toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID inválido.");
        }
        acaoDAO = new AcaoDAO();
        if (acaoDAO.findById(idAcao) == null){
            throw new IllegalArgumentException("ID \'" + idAcao + "\' não encontrado no banco!");
        }
        return acaoDAO.delete(idAcao);
    }
}
