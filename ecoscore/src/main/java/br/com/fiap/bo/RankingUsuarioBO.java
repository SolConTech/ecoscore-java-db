package br.com.fiap.bo;

import br.com.fiap.dao.RankingUsuarioDAO;
import br.com.fiap.to.RankingUsuarioTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class RankingUsuarioBO implements IBO{
    private RankingUsuarioDAO rankingUsuarioDAO;

    @Override
    public ArrayList<Object> findAll() throws SQLException {
        rankingUsuarioDAO = new RankingUsuarioDAO();
        return rankingUsuarioDAO.findAll();
    }

    @Override
    public Object findById(Object id) throws SQLException, IllegalArgumentException {
        Integer idRankingUsuario;
        try {
            // pra pegar erros de parse e cast se o objeto ou id for invalido
            idRankingUsuario = Integer.parseInt(id.toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID inválido.");
        }
        rankingUsuarioDAO = new RankingUsuarioDAO();
        return rankingUsuarioDAO.findById(idRankingUsuario);
    }

    @Override
    public Object save(Object body) throws SQLException, IllegalArgumentException {
        RankingUsuarioTO rankingUsuarioTO;
        try {
            // pra pegar erros de parse e cast se o objeto ou id for invalido
            rankingUsuarioTO = (RankingUsuarioTO) body;
        } catch (Exception e) {
            throw new IllegalArgumentException("ID ou Corpo inválido");
        }
        rankingUsuarioDAO = new RankingUsuarioDAO();
        return rankingUsuarioDAO.save(rankingUsuarioTO);
    }

    @Override
    public Object update(Object id, Object body) throws SQLException, IllegalArgumentException {
        Integer idRankingUsuario;
        RankingUsuarioTO rankingUsuarioTO;
        try {
            // pra pegar erros de parse e cast se o objeto ou id for invalido
            idRankingUsuario = Integer.parseInt(id.toString());
            rankingUsuarioTO = (RankingUsuarioTO) body;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID inválido.");
        } catch (Exception e) {
            throw new IllegalArgumentException("ID ou Corpo inválido");
        }
        rankingUsuarioDAO = new RankingUsuarioDAO();
        if (rankingUsuarioDAO.findById(idRankingUsuario) == null){
            throw new IllegalArgumentException("ID \'" + idRankingUsuario + "\' não encontrado no banco!");
        }
        return rankingUsuarioDAO.update(idRankingUsuario, rankingUsuarioTO);
    }

    @Override
    public Boolean delete(Object id) throws SQLException, IllegalArgumentException {
        rankingUsuarioDAO = new RankingUsuarioDAO();
        Integer idRankingUsuario;
        try {
            // pra pegar erros de parse e cast se o objeto ou id for invalido
            idRankingUsuario = Integer.parseInt(id.toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID inválido.");
        }
        if (rankingUsuarioDAO.findById(idRankingUsuario) == null){
            throw new SQLException("ID \'" + idRankingUsuario + "\' não encontrado no banco!");
        }
        return rankingUsuarioDAO.delete(idRankingUsuario);
    }
}

