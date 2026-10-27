package br.com.fiap.bo;

import br.com.fiap.dao.PostDAO;
import br.com.fiap.to.PostTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class PostBO implements IBO{
    private PostDAO postDAO;

    @Override
    public ArrayList<Object> findAll() throws SQLException {
        postDAO = new PostDAO();
        return postDAO.findAll();
    }

    @Override
    public Object findById(Object id) throws SQLException, IllegalArgumentException {
        Integer idPost;
        PostTO postTO;
        try {
            // pra pegar erros de parse e cast se o objeto ou id for invalido
            idPost = Integer.parseInt(id.toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID inválido.");
        }
        postTO = new PostTO();
        postTO.setIdPost(idPost);
        postDAO = new PostDAO();
        return postDAO.findById(postTO.getIdPost());
    }

    @Override
    public Object save(Object body) throws SQLException, IllegalArgumentException {
        PostTO postTO;
        try {
            // pra pegar erros de parse e cast se o objeto ou id for invalido
            postTO = (PostTO) body;
        } catch (Exception e) {
            throw new IllegalArgumentException("ID ou Corpo inválido");
        }
        postDAO = new PostDAO();
        return postDAO.save(postTO);
    }

    @Override
    public Object update(Object id, Object body) throws SQLException, IllegalArgumentException {
        Integer idPost;
        PostTO postTO;
        try {
            // pra pegar erros de parse e cast se o objeto ou id for invalido
            idPost = Integer.parseInt(id.toString());
            postTO = (PostTO) body;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID inválido.");
        } catch (Exception e) {
            throw new IllegalArgumentException("ID ou Corpo inválido");
        }
        postDAO = new PostDAO();
        if (postDAO.findById(idPost) == null){
            throw new IllegalArgumentException("ID \'" + idPost + "\' não encontrado no banco!");
        }
        postTO.setIdPost(idPost); //pra reforçar validação com o set.
        return postDAO.update(postTO.getIdPost(), postTO);
    }

    @Override
    public Boolean delete(Object id) throws SQLException, IllegalArgumentException {
        postDAO = new PostDAO();
        Integer idPost;
        try {
            // pra pegar erros de parse e cast se o objeto ou id for invalido
            idPost = Integer.parseInt(id.toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID inválido.");
        }
        if (postDAO.findById(idPost) == null){
            throw new SQLException("ID \'" + idPost + "\' não encontrado no banco!");
        }
        PostTO postTO = new PostTO();
        postTO.setIdPost(idPost);
        return postDAO.delete(postTO.getIdPost());
    }
}

