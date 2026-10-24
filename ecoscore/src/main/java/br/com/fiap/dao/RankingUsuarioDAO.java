package br.com.fiap.dao;

import br.com.fiap.to.RankingUsuarioTO;

import java.sql.*;
import java.util.ArrayList;

public class RankingUsuarioDAO implements IDAO{

    public RankingUsuarioDAO() {}

    public RankingUsuarioTO save(Object object) throws SQLException {
        RankingUsuarioTO rankingUsuarioTO = (RankingUsuarioTO) object;
        String sql = "insert into ranking_usuario(ID_USUARIO,QT_VOTOS) values(?,?)";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)) {
            ps.setString(1, rankingUsuarioTO.getIdUsuario());
            ps.setInt(2, rankingUsuarioTO.getQtVotos());

            if (ps.executeUpdate() > 0) {
                return rankingUsuarioTO;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }

    public RankingUsuarioTO update(Object id, Object object) throws SQLException {
        RankingUsuarioTO rankingUsuarioTO = (RankingUsuarioTO) object;
        Integer idRanking = (Integer) id;
        String sql = "update ranking_usuario set ID_USUARIO=?, QT_VOTOS=? where ID_RANKING=?";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)) {
            ps.setString(1, rankingUsuarioTO.getIdUsuario());
            ps.setInt(2, rankingUsuarioTO.getQtVotos());
            ps.setInt(3, idRanking);

            if (ps.executeUpdate() > 0) {
                return rankingUsuarioTO;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }

    public Boolean delete(Object id) throws SQLException {
        Integer idRanking = (Integer) id;
        String sql = "delete from ranking_usuario where ID_RANKING=?";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)) {
            ps.setInt(1, idRanking);

            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }

    public ArrayList<Object> findAll() throws SQLException {
        String sql = "select * from ranking_usuario order by QT_VOTOS DESC";
        ArrayList<Object> listaRankingUsuario = new ArrayList<>();

        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){

            if (rs != null){
                while (rs.next()){
                    RankingUsuarioTO rankingUsuarioTO = new RankingUsuarioTO();

                    rankingUsuarioTO.setIdRanking(rs.getInt(1));
                    rankingUsuarioTO.setIdUsuario(rs.getString(3));
                    rankingUsuarioTO.setQtVotos(rs.getInt(2));

                    listaRankingUsuario.add(rankingUsuarioTO);
                }

                return listaRankingUsuario;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }

    public RankingUsuarioTO findById(Object id) throws SQLException {
        String sql = "SELECT * FROM RANKING_USUARIO WHERE ID_USUARIO = ?";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)){
            ps.setObject(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    RankingUsuarioTO ranking = new RankingUsuarioTO();
                    ranking.setIdRanking(rs.getInt(1));
                    ranking.setQtVotos(rs.getInt(2));
                    ranking.setIdUsuario(rs.getString(3));
                    return ranking;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }
    
}
