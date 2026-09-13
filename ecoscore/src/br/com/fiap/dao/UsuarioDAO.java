package br.com.fiap.dao;

import br.com.fiap.dto.Usuario;

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

    public String inserir(Object object) throws SQLException {
        Usuario usuario = (Usuario) object;
        String sql = "insert into usuario(ID_USUARIO,NM_USUARIO,VL_MERITO,QT_SOULCOINS) values(?,?,?,?)";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, usuario.getIdUsuario());
            ps.setString(2, usuario.getNmUsuario());
            ps.setFloat(3, usuario.getVlMerito());
            ps.setInt(4, usuario.getQtSoulCoins());

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
        Usuario usuario = (Usuario) object;
        String sql = "update usuario set NM_USUARIO=?, VL_MERITO=?, QT_SOULCOINS=? where ID_USUARIO=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, usuario.getNmUsuario());
            ps.setFloat(2, usuario.getVlMerito());
            ps.setInt(3, usuario.getQtSoulCoins());
            ps.setString(4,usuario.getIdUsuario());

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
        Usuario usuario = (Usuario) object;
        String sql = "delete from usuario where ID_USUARIO=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, usuario.getIdUsuario());

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
        String sql = "select * from usuario order by ID_USUARIO";
        ArrayList<Object> listaUsuario = new ArrayList<>();

        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){

            if (rs != null){
                while (rs.next()){
                    Usuario usuario = new Usuario();

                    usuario.setIdUsuario(rs.getString(1));
                    usuario.setNmUsuario(rs.getString(2));
                    usuario.setVlMerito(rs.getFloat(3));
                    usuario.setQtSoulCoins(rs.getInt(4));

                    listaUsuario.add(usuario);
                }

                return listaUsuario;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        }
    }

    public Object pegarUm(Object object) throws SQLException {
        Usuario usuario = (Usuario) object;
        String sql = "SELECT * FROM USUARIO WHERE ID_USUARIO = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            ps.setObject(1,usuario.getIdUsuario());
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getString(1));
                    usuario.setNmUsuario(rs.getString(2));
                    usuario.setVlMerito(rs.getFloat(3));
                    usuario.setQtSoulCoins(rs.getInt(4));

                    return usuario;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        }
    }

    public Object criarId()throws SQLException{
        String sql = "SELECT ID_USUARIO FROM USUARIO";
        ArrayList<Integer> listaIds = new ArrayList<Integer>();
        try (PreparedStatement ps = getCon().prepareStatement(sql);
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
        }
    }
}
