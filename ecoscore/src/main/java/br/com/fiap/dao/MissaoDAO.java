package br.com.fiap.dao;

import br.com.fiap.to.MissaoTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;

public class MissaoDAO implements IDAO{

    public MissaoDAO() {}

    public MissaoTO save(Object objeto) throws SQLException {
        MissaoTO missaoTO = (MissaoTO) objeto;
        String sql = "insert into missao(ID_MISSAO,nm_missao, DS_MISSAO, SELO, qt_pontosGerados, dt_missao) values(?,?,?,?,?,?)";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)) {
            ps.setInt(1, missaoTO.getIdMissao());
            ps.setString(2, missaoTO.getNmMissao());
            ps.setString(3, missaoTO.getDsMissao());
            ps.setString(4, missaoTO.getSelo());
            ps.setInt(5, missaoTO.getQtPontosGerados());
            ps.setObject(6, missaoTO.getDtMissao());
            if (ps.executeUpdate() > 0) {
                return missaoTO;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }

    public MissaoTO update(Object id, Object objeto) throws SQLException {
        MissaoTO missaoTO = (MissaoTO) objeto;
        Integer idMissao = (Integer) id;
        String sql = "update missao set NM_MISSAO=?, DS_MISSAO=?, SELO=?, QT_PONTOSGERADOS=?, DT_MISSAO=? where ID_MISSAO=?";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)) {
            ps.setString(1, missaoTO.getNmMissao());
            ps.setString(2, missaoTO.getDsMissao());
            ps.setString(3, missaoTO.getSelo());
            ps.setInt(4, missaoTO.getQtPontosGerados());
            ps.setObject(5, missaoTO.getDtMissao());
            ps.setInt(6, idMissao);
            if (ps.executeUpdate() > 0) {
                return missaoTO;
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
        Integer idMissao = (Integer) id;
        String sql = "delete from missao where ID_MISSAO=?";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)) {
            ps.setInt(1, idMissao);
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
        String sql = "select * from missao order by ID_MISSAO";
        ArrayList<Object> listaMissao = new ArrayList<>();
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){
            if (rs != null){
                while (rs.next()){
                    MissaoTO missaoTO = new MissaoTO();
                    missaoTO.setIdMissao(rs.getInt(1));
                    missaoTO.setNmMissao(rs.getString(2));
                    missaoTO.setDsMissao(rs.getString(3));
                    missaoTO.setSelo(rs.getString(4));
                    missaoTO.setQtPontosGerados(rs.getInt(5));
                    Timestamp data = rs.getTimestamp(6);
                    missaoTO.setDtMissao(data.toLocalDateTime());

                    listaMissao.add(missaoTO);
                }
                return listaMissao;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }

    public MissaoTO findById(Object id) throws SQLException {
        String sql = "SELECT * FROM MISSAO WHERE ID_MISSAO = ?";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)){
            ps.setObject(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    MissaoTO missaoTO = new MissaoTO();
                    missaoTO.setIdMissao(rs.getInt(1));
                    missaoTO.setNmMissao(rs.getString(2));
                    missaoTO.setDsMissao(rs.getString(3));
                    missaoTO.setSelo(rs.getString(4));
                    missaoTO.setQtPontosGerados(rs.getInt(5));
                    Timestamp data = rs.getTimestamp(6);
                    missaoTO.setDtMissao(data.toLocalDateTime());
                    return missaoTO;
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
