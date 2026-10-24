package br.com.fiap.dao;

import br.com.fiap.to.AcaoTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;

public class AcaoDAO implements IDAO{
    public AcaoDAO() {}

    public AcaoDAO(Connection con) {

    }

    public AcaoTO save(Object object) throws SQLException {
        AcaoTO acaoTO = (AcaoTO) object;
        String sql = "insert into acao(ID_ACAO,usuario_id_usuario, ds_acao, qt_pontosGerados, dt_acao) values(?,?,?,?,?)";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)) {
            ps.setInt(1, acaoTO.getIdAcao());
            ps.setString(2, acaoTO.getIdUsuario());
            ps.setString(3, acaoTO.getDsAcao());
            ps.setInt(4, acaoTO.getQtPontosGerados());
            //pesquisei e esse é o melhor para datas com horas e minutos o driver vai transformar em data, tentei dar parse e não foi
            ps.setObject(5, acaoTO.getDtAcao());
            if (ps.executeUpdate() > 0) {
                return acaoTO;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }

    public AcaoTO update(Object id, Object object) throws SQLException {
        AcaoTO acaoTO = (AcaoTO) object;
        Integer idAcao = (Integer) id;
        String sql = "update acao set USUARIO_ID_USUARIO=?, DS_ACAO=?, QT_PONTOSGERADOS=?, DT_ACAO=? where ID_ACAO=?";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)) {
            ps.setString(1, acaoTO.getIdUsuario());
            ps.setString(2, acaoTO.getDsAcao());
            ps.setInt(3, acaoTO.getQtPontosGerados());
            ps.setObject(4, acaoTO.getDtAcao());
            ps.setInt(5, idAcao);
            if (ps.executeUpdate() > 0) {
                return acaoTO;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }

    public Boolean delete(Object object) throws SQLException {
        AcaoTO acaoTO = (AcaoTO) object;
        String sql = "delete from acao where ID_ACAO=?";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)) {
            ps.setInt(1, acaoTO.getIdAcao());
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
        String sql = "select * from acao order by USUARIO_ID_USUARIO";
        ArrayList<Object> listaAcao = new ArrayList<>();
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs != null) {
                while (rs.next()) {
                    AcaoTO acaoTO = new AcaoTO();
                    acaoTO.setIdAcao(rs.getInt(1));
                    acaoTO.setDsAcao(rs.getString(2));
                    acaoTO.setQtPontosGerados(rs.getInt(3));
                    //O banco usa Timestampo, então pra pegar uso ele e dou toLocalDateTime
                    Timestamp data = rs.getTimestamp(4);
                    acaoTO.setDtAcao(data.toLocalDateTime());
                    acaoTO.setIdUsuario(rs.getString(5));

                    listaAcao.add(acaoTO);
                }
                return listaAcao;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }

    public AcaoTO findById(Object id) throws SQLException {
        String sql = "SELECT * FROM ACAO WHERE ID_ACAO = ?";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)){
            ps.setObject(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    AcaoTO acaoTO = new AcaoTO();
                    acaoTO.setIdAcao(rs.getInt(1));
                    acaoTO.setDsAcao(rs.getString(2));
                    acaoTO.setQtPontosGerados(rs.getInt(3));
                    Timestamp data = rs.getTimestamp(4);
                    acaoTO.setDtAcao(data.toLocalDateTime());
                    acaoTO.setIdUsuario(rs.getString(5));
                    return acaoTO;
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
