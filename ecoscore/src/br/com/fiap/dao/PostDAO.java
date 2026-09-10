package br.com.fiap.dao;

import br.com.fiap.dto.Post;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PostDAO {
    private Connection con;

    public PostDAO() {}
    public PostDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {

        return con;
    }

    public String inserir(Post post){
        String sql = "insert into post(ID_POST,USUARIO_ID_USUARIO,ACAO_ID_ACAO,DS_POST,DT_POST,NUM_UPVOTES,NUM_DOWNVOTES,NUM_SALDOVOTES) values(?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, post.getIdPost());
            ps.setString(2, post.getIdUsuario());
            ps.setInt(3, post.getIdAcao());
            ps.setString(4, post.getDsPost());
            ps.setObject(5, post.getDtPost());
            ps.setInt(6, post.getNumUpVotes());
            ps.setInt(7, post.getNumDownVotes());
            ps.setInt(8, post.getNumSaldoVotes());

            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso";
            } else {
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String alterar(Post post){
        String sql = "update post set USUARIO_ID_USUARIO=?, ACAO_ID_ACAO=?, DS_POST=?, DT_POST=?, NUM_UPVOTES=?, NUM_DOWNVOTES=?, NUM_SALDOVOTES=? where ID_POST=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, post.getIdUsuario());
            ps.setInt(2, post.getIdAcao());
            ps.setString(3, post.getDsPost());
            ps.setObject(4, post.getDtPost());
            ps.setInt(5, post.getNumUpVotes());
            ps.setInt(6, post.getNumDownVotes());
            ps.setInt(7, post.getNumSaldoVotes());
            ps.setInt(8, post.getIdPost());

            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso";
            } else {
                return "Erro ao alterar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String excluir(Post post){
        String sql = "delete from post where ID_POST=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, post.getIdPost());

            if (ps.executeUpdate() > 0) {
                return "Excluido com sucesso";
            } else {
                return "Erro ao excluir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public ArrayList<Post> listarTodos(){
        String sql = "select * from post order by ID_POST";
        ArrayList<Post> listaPost = new ArrayList<>();

        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){

            if (rs != null){
                while (rs.next()){
                    Post post = new Post();
                    post.setIdPost(rs.getInt(1));
                    post.setIdUsuario(rs.getString(2));
                    post.setIdAcao(rs.getInt(3));
                    post.setDsPost(rs.getString(4));
                    Timestamp data = rs.getTimestamp(5);
                    post.setDtPost(data.toLocalDateTime());
                    post.setNumUpVotes(rs.getInt(6));
                    post.setNumDownVotes(rs.getInt(7));
                    post.setNumSaldoVotes(rs.getInt(8));

                    listaPost.add(post);
                }

                return listaPost;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }
}
