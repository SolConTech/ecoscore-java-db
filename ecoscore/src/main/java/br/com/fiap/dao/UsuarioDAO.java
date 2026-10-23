package br.com.fiap.dao;

import br.com.fiap.to.UsuarioTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

public class UsuarioDAO implements IDAO{
    private Connection con;

    public UsuarioDAO() {}
    public UsuarioDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {

        return con;
    }

    public String save(Object object) throws SQLException {
        UsuarioTO usuarioTO = (UsuarioTO) object;
        String sql = "insert into usuario(ID_USUARIO,NM_USUARIO,VL_MERITO,QT_SOULCOINS,DS_DESCRICAO,SELOS_GANHOS) values(?,?,?,?,?,?)";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)) {
            ps.setString(1, usuarioTO.getIdUsuario());
            ps.setString(2, usuarioTO.getNmUsuario());
            ps.setFloat(3, usuarioTO.getVlMerito());
            ps.setInt(4, usuarioTO.getQtSoulCoins());
            ps.setString(5, usuarioTO.getDsAtividadeRecente());
            ps.setString(6, usuarioTO.getSelosGanhos());

            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso";
            } else {
                return "Erro ao save";
            }
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }

    public String update(Object id, Object object) throws SQLException {
        UsuarioTO usuarioTO = (UsuarioTO) object;
        String idUsuario = (String) id;
        String sql = "update usuario set NM_USUARIO=?, VL_MERITO=?, QT_SOULCOINS=?,DS_DESCRICAO=?,SELOS_GANHOS=? where ID_USUARIO=?";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)) {
            ps.setString(1, usuarioTO.getNmUsuario());
            ps.setFloat(2, usuarioTO.getVlMerito());
            ps.setInt(3, usuarioTO.getQtSoulCoins());
            ps.setString(4, usuarioTO.getIdUsuario());
            ps.setString(5, usuarioTO.getDsAtividadeRecente());
            ps.setString(6, idUsuario);

            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso";
            } else {
                return "Erro ao update";
            }
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }

    public Boolean delete(Object object) throws SQLException {
        UsuarioTO usuarioTO = (UsuarioTO) object;
        String sql = "delete from usuario where ID_USUARIO=?";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)) {
            ps.setString(1, usuarioTO.getIdUsuario());

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
        String sql = "select * from usuario order by ID_USUARIO";
        ArrayList<Object> listaUsuario = new ArrayList<>();

        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){

            if (rs != null){
                while (rs.next()){
                    UsuarioTO usuarioTO = new UsuarioTO();

                    usuarioTO.setIdUsuario(rs.getString(1));
                    usuarioTO.setNmUsuario(rs.getString(2));
                    usuarioTO.setVlMerito(rs.getFloat(3));
                    usuarioTO.setQtSoulCoins(rs.getInt(4));
                    usuarioTO.setDsAtividadeRecente(rs.getString(5));
                    usuarioTO.setSelosGanhos(rs.getString(6));

                    listaUsuario.add(usuarioTO);
                }

                return listaUsuario;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }

    public Object findById(Object id) throws SQLException {
        UsuarioTO usuarioTO = (UsuarioTO) id;
        String sql = "SELECT * FROM USUARIO WHERE ID_USUARIO = ?";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)){
            ps.setObject(1, usuarioTO.getIdUsuario());
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    usuarioTO = new UsuarioTO();
                    usuarioTO.setIdUsuario(rs.getString(1));
                    usuarioTO.setNmUsuario(rs.getString(2));
                    usuarioTO.setVlMerito(rs.getFloat(3));
                    usuarioTO.setQtSoulCoins(rs.getInt(4));
                    usuarioTO.setDsAtividadeRecente(rs.getString(5));
                    usuarioTO.setSelosGanhos(rs.getString(6));

                    return usuarioTO;
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

    public Object criarId()throws SQLException{
        String sql = "SELECT ID_USUARIO FROM USUARIO";
        ArrayList<Integer> listaIds = new ArrayList<Integer>();
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    listaIds.add(id);
                }
                //o orElse serve para não retornar 0 nada se a lista estiver vazia, o max retorna Integer
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
