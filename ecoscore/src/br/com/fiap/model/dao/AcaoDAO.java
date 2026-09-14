package br.com.fiap.model.dao;

import br.com.fiap.model.dto.Acao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;

public class AcaoDAO implements IDAO{
    private Connection con;

    public AcaoDAO() {}
    public AcaoDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public String inserir(Object object) throws SQLException {
        Acao acao = (Acao) object;
        String sql = "insert into acao(ID_ACAO,usuario_id_usuario, ds_acao, qt_pontosGerados, dt_acao) values(?,?,?,?,?)";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, acao.getIdAcao());
            ps.setString(2, acao.getIdUsuario());
            ps.setString(3, acao.getDsAcao());
            ps.setInt(4, acao.getQtPontosGerados());
            //pesquisei e esse é o melhor para datas com horas e minutos o driver vai transformar em data, tentei dar parse e não foi
            ps.setObject(5, acao.getDtAcao());
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso";
            } else {
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        }
    }

    public String alterar(Object object) throws SQLException {
        Acao acao = (Acao) object;
        String sql = "update acao set USUARIO_ID_USUARIO=?, DS_ACAO=?, QT_PONTOSGERADOS=?, DT_ACAO=? where ID_ACAO=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, acao.getIdUsuario());
            ps.setString(2, acao.getDsAcao());
            ps.setInt(3, acao.getQtPontosGerados());
            ps.setObject(4, acao.getDtAcao());
            ps.setInt(5, acao.getIdAcao());
            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso";
            } else {
                return "Erro ao alterar";
            }
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        }
    }

    public String excluir(Object object) throws SQLException {
        Acao acao = (Acao) object;
        String sql = "delete from acao where ID_ACAO=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, acao.getIdAcao());
            if (ps.executeUpdate() > 0) {
                return "Excluido com sucesso";
            } else {
                return "Erro ao excluir";
            }
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        }
    }

    public ArrayList<Object> listarTodos() throws SQLException {
        String sql = "select * from acao order by USUARIO_ID_USUARIO";
        ArrayList<Object> listaAcao = new ArrayList<>();
        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs != null) {
                while (rs.next()) {
                    Acao acao = new Acao();
                    acao.setIdAcao(rs.getInt(1));
                    acao.setDsAcao(rs.getString(2));
                    acao.setQtPontosGerados(rs.getInt(3));
                    //O banco usa Timestampo, então pra pegar uso ele e dou toLocalDateTime
                    Timestamp data = rs.getTimestamp(4);
                    acao.setDtAcao(data.toLocalDateTime());
                    acao.setIdUsuario(rs.getString(5));

                    listaAcao.add(acao);
                }
                return listaAcao;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        }
    }

    public Object criarId() throws SQLException {
        String sql = "SELECT ID_ACAO FROM ACAO";
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
            throw new SQLException("Erro de SQL: " + e.getMessage());
        }
    }

    public Acao pegarUm(Object object) throws SQLException {
        String sql = "SELECT * FROM ACAO WHERE ID_ACAO = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            ps.setObject(1,object);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    Acao acao = new Acao();
                    acao.setIdAcao(rs.getInt(1));
                    acao.setDsAcao(rs.getString(2));
                    acao.setQtPontosGerados(rs.getInt(3));
                    Timestamp data = rs.getTimestamp(4);
                    acao.setDtAcao(data.toLocalDateTime());
                    acao.setIdUsuario(rs.getString(5));
                    return acao;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        }
    }
}
