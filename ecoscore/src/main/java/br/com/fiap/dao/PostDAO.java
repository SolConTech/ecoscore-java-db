package br.com.fiap.dao;

import br.com.fiap.to.PostTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;

public class PostDAO implements IDAO{
    private Connection con;

    public PostDAO() {}
    public PostDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {

        return con;
    }

    public String save(Object object) throws SQLException {
        PostTO postTO = (PostTO) object;
        String sql = "insert into post(ID_POST,USUARIO_ID_USUARIO,DS_POST,DT_POST,NUM_UPVOTES,NUM_DOWNVOTES,NUM_SALDOVOTES) values(?,?,?,?,?,?,?)";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)) {
            ps.setInt(1, postTO.getIdPost());
            ps.setString(2, postTO.getIdUsuario());
            ps.setString(3, postTO.getDsPost());
            ps.setObject(4, postTO.getDtPost());
            ps.setInt(5, postTO.getNumUpVotes());
            ps.setInt(6, postTO.getNumDownVotes());
            ps.setInt(7, postTO.getNumSaldoVotes());

            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso";
            } else {
                return "Erro ao save";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String update(Object id, Object object) throws SQLException {
        PostTO postTO = (PostTO) object;
        Integer idPost = (Integer) id;
        String sql = "update post set USUARIO_ID_USUARIO=?, DS_POST=?, DT_POST=?, NUM_UPVOTES=?, NUM_DOWNVOTES=?, NUM_SALDOVOTES=? where ID_POST=?";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)) {
            ps.setString(1, postTO.getIdUsuario());
            ps.setString(2, postTO.getDsPost());
            ps.setObject(3, postTO.getDtPost());
            ps.setInt(4, postTO.getNumUpVotes());
            ps.setInt(5, postTO.getNumDownVotes());
            ps.setInt(6, postTO.getNumSaldoVotes());
            ps.setInt(7, idPost);

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
        PostTO postTO = (PostTO) object;
        String sql = "delete from post where ID_POST=?";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)) {
            ps.setInt(1, postTO.getIdPost());

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
        String sql = "select * from post order by ID_POST";
        ArrayList<Object> listaPost = new ArrayList<>();

        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){

            if (rs != null){
                while (rs.next()){
                    PostTO postTO = new PostTO();
                    postTO.setIdPost(rs.getInt(1));
                    postTO.setDsPost(rs.getString(2));
                    Timestamp data = rs.getTimestamp(3);
                    postTO.setDtPost(data.toLocalDateTime());
                    postTO.setNumUpVotes(rs.getInt(4));
                    postTO.setNumDownVotes(rs.getInt(5));
                    postTO.setNumSaldoVotes();
                    postTO.setIdUsuario(rs.getString(7));

                    listaPost.add(postTO);
                }

                return listaPost;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }

    public Integer criarId() throws SQLException {
        String sql = "SELECT ID_post FROM POST";
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

    public PostTO findById(Object id) throws SQLException {
        String sql = "SELECT * FROM POST WHERE ID_POST = ?";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)){
            ps.setObject(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    PostTO postTO = new PostTO();
                    postTO.setIdPost(rs.getInt(1));
                    postTO.setDsPost(rs.getString(2));
                    Timestamp data = rs.getTimestamp(3);
                    postTO.setDtPost(data.toLocalDateTime());
                    postTO.setNumUpVotes(rs.getInt(4));
                    postTO.setNumDownVotes(rs.getInt(5));
                    postTO.setNumSaldoVotes();
                    postTO.setIdUsuario(rs.getString(7));

                    return postTO;
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
