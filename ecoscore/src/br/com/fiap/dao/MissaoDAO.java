package br.com.fiap.dao;

import br.com.fiap.dto.Missao;

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
        Missao missao = (Missao) objeto;
        String sql = "insert into missao(ID_MISSAO,nm_missao, DS_MISSAO, SELO, qt_pontosGerados, dt_missao) values(?,?,?,?,?,?)";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1,missao.getIdMissao());
            ps.setString(2, missao.getNmMissao());
            ps.setString(3, missao.getDsMissao());
            ps.setString(4, missao.getSelo());
            ps.setInt(5,missao.getQtPontosGerados());
            ps.setObject(6,missao.getDtMissao());
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso";
            } else {
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String alterar(Object objeto) throws SQLException {
        Missao missao = (Missao) objeto;
        String sql = "update missao set NM_MISSAO=?, DS_MISSAO=?, SELO=?, QT_PONTOSGERADOS=?, DT_MISSAO=? where ID_MISSAO=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, missao.getNmMissao());
            ps.setString(2, missao.getDsMissao());
            ps.setString(3, missao.getSelo());
            ps.setInt(4, missao.getQtPontosGerados());
            ps.setObject(5,missao.getDtMissao());
            ps.setInt(6,missao.getIdMissao());
            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso";
            } else {
                return "Erro ao alterar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String excluir(Object objeto) throws SQLException {
        Missao missao = (Missao) objeto;
        String sql = "delete from missao where ID_MISSAO=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, missao.getIdMissao());
            if (ps.executeUpdate() > 0) {
                return "Excluido com sucesso";
            } else {
                return "Erro ao excluir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public ArrayList<Object> listarTodos() throws SQLException {
        String sql = "select * from missao order by ID_MISSAO";
        ArrayList<Object> listaMissao = new ArrayList<>();
        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){
            if (rs != null){
                while (rs.next()){
                    Missao missao = new Missao();
                    missao.setIdMissao(rs.getInt(1));
                    missao.setNmMissao(rs.getString(2));
                    missao.setDsMissao(rs.getString(3));
                    missao.setSelo(rs.getString(4));
                    missao.setQtPontosGerados(rs.getInt(5));
                    Timestamp data = rs.getTimestamp(6);
                    missao.setDtMissao(data.toLocalDateTime());

                    listaMissao.add(missao);
                }
                return listaMissao;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }

    public Object pegarUm(Object object) throws SQLException {
        String sql = "SELECT * FROM MISSAO WHERE ID_MISSAO = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            ps.setObject(1,object);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    Missao missao = new Missao();
                    missao.setIdMissao(rs.getInt(1));
                    missao.setNmMissao(rs.getString(2));
                    missao.setDsMissao(rs.getString(3));
                    missao.setSelo(rs.getString(4));
                    missao.setQtPontosGerados(rs.getInt(5));
                    Timestamp data = rs.getTimestamp(6);
                    missao.setDtMissao(data.toLocalDateTime());
                    return missao;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }

    public Object criarId() throws SQLException {
        String sql = "SELECT ID_MISSAO FROM MISSAO";
        ArrayList<Integer> listaIds = new ArrayList<Integer>();
        try (PreparedStatement ps = getCon().prepareStatement(sql);
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
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }
}
