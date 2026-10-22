package br.com.fiap.bo;

import br.com.fiap.dao.RankingUsuarioDAO;
import br.com.fiap.to.RankingUsuarioTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class RankingUsuarioBO implements IBO{
    private RankingUsuarioDAO RankingUsuarioDAO;

    @Override
    public ArrayList<Object> findAll() throws SQLException {
        RankingUsuarioDAO = new RankingUsuarioDAO();
        return RankingUsuarioDAO.listarTodos();
    }

    @Override
    public Object findById(Object id) throws SQLException {
        RankingUsuarioDAO = new RankingUsuarioDAO();
        return RankingUsuarioDAO.listarUm(id);
    }

    @Override
    public Object save(Object body) throws SQLException {
        RankingUsuarioDAO = new RankingUsuarioDAO();
        RankingUsuarioTO rankingTO = (RankingUsuarioTO) body;
        return RankingUsuarioDAO.inserir(rankingTO);
    }

    @Override
    public Object update(Object id, Object body) throws SQLException {
        RankingUsuarioDAO = new RankingUsuarioDAO();
        RankingUsuarioTO rankingTO = (RankingUsuarioTO) body;
        return RankingUsuarioDAO.alterar(id, rankingTO);
    }

    @Override
    public Object delete(Object id) throws SQLException {
        RankingUsuarioDAO = new RankingUsuarioDAO();
        return RankingUsuarioDAO.excluir(id);
    }
}
