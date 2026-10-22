package br.com.fiap.dao;

import br.com.fiap.to.MissaoTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;

public class MissaoDAO implements IDAO{
    private Connection con;

    public MissaoDAO() {}
    public MissaoDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        
        return con;
    }

    public String inserir(Object objeto) throws SQLException {
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
                return "Inserido com sucesso";
            } else {
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }

    public String alterar(Object objeto) throws SQLException {
        MissaoTO missaoTO = (MissaoTO) objeto;
        String sql = "update missao set NM_MISSAO=?, DS_MISSAO=?, SELO=?, QT_PONTOSGERADOS=?, DT_MISSAO=? where ID_MISSAO=?";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)) {
            ps.setString(1, missaoTO.getNmMissao());
            ps.setString(2, missaoTO.getDsMissao());
            ps.setString(3, missaoTO.getSelo());
            ps.setInt(4, missaoTO.getQtPontosGerados());
            ps.setObject(5, missaoTO.getDtMissao());
            ps.setInt(6, missaoTO.getIdMissao());
            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso";
            } else {
                return "Erro ao alterar";
            }
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }

    public String excluir(Object objeto) throws SQLException {
        MissaoTO missaoTO = (MissaoTO) objeto;
        String sql = "delete from missao where ID_MISSAO=?";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)) {
            ps.setInt(1, missaoTO.getIdMissao());
            if (ps.executeUpdate() > 0) {
                return "Excluido com sucesso";
            } else {
                return "Erro ao excluir";
            }
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }

    public ArrayList<Object> listarTodos() throws SQLException {
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

    public Object listarUm(Object object) throws SQLException {
        String sql = "SELECT * FROM MISSAO WHERE ID_MISSAO = ?";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)){
            ps.setObject(1,object);
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

    public Object criarId() throws SQLException {
        String sql = "SELECT ID_MISSAO FROM MISSAO";
        ArrayList<Integer> listaIds = new ArrayList<Integer>();
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    listaIds.add(id);
                }
                //o orElse serve para não retornar 0«nada se a lista estiver vazia, o max retorna Integer
                Integer id = listaIds.stream().max(Comparator.naturalOrder()).orElse(null);
                return id+1;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }
}
