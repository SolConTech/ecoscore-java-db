package br.com.fiap.dao;

import br.com.fiap.dto.Acao;
import br.com.fiap.dto.Missao;
import br.com.fiap.dto.Post;

import java.sql.*;
import java.time.LocalDateTime;
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

    public String inserir(Object object){
        Post post = (Post) object;
        String sql = "insert into post(ID_POST,USUARIO_ID_USUARIO,DS_POST,DT_POST,NUM_UPVOTES,NUM_DOWNVOTES,NUM_SALDOVOTES) values(?,?,?,?,?,?,?)";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, post.getIdPost());
            ps.setString(2, post.getIdUsuario());
            ps.setString(3, post.getDsPost());
            ps.setObject(4, post.getDtPost());
            ps.setInt(5, post.getNumUpVotes());
            ps.setInt(6, post.getNumDownVotes());
            ps.setInt(7, post.getNumSaldoVotes());

            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso";
            } else {
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String alterar(Object object){
        Post post = (Post) object;
        String sql = "update post set USUARIO_ID_USUARIO=?, DS_POST=?, DT_POST=?, NUM_UPVOTES=?, NUM_DOWNVOTES=?, NUM_SALDOVOTES=? where ID_POST=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, post.getIdUsuario());
            ps.setString(2, post.getDsPost());
            ps.setObject(3, post.getDtPost());
            ps.setInt(4, post.getNumUpVotes());
            ps.setInt(5, post.getNumDownVotes());
            ps.setInt(6, post.getNumSaldoVotes());
            ps.setInt(7, post.getIdPost());

            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso";
            } else {
                return "Erro ao alterar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String excluir(Object object){
        Post post = (Post) object;
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

    public ArrayList<Object> listarTodos(){
        String sql = "select * from post order by ID_POST";
        ArrayList<Object> listaPost = new ArrayList<>();

        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){

            if (rs != null){
                while (rs.next()){
                    Post post = new Post();
                    post.setIdPost(rs.getInt(1));
                    post.setDsPost(rs.getString(2));
                    Timestamp data = rs.getTimestamp(3);
                    post.setDtPost(data.toLocalDateTime());
                    post.setNumUpVotes(rs.getInt(4));
                    post.setNumDownVotes(rs.getInt(5));
                    post.setNumSaldoVotes(rs.getInt(6));
                    post.setIdUsuario(rs.getString(7));

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

    public Integer criarId() {
        String sql = "SELECT ID_post FROM POST";
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

    public Post pegarUm(Object object){
        String sql = "SELECT * FROM POST WHERE ID_POST = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            ps.setObject(1,object);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    Post post = new Post();
                    post.setIdPost(rs.getInt(1));
                    post.setDsPost(rs.getString(2));
                    Timestamp data = rs.getTimestamp(3);
                    post.setDtPost(data.toLocalDateTime());
                    post.setNumUpVotes(rs.getInt(4));
                    post.setNumDownVotes(rs.getInt(5));
                    post.setNumSaldoVotes(rs.getInt(6));
                    post.setIdUsuario(rs.getString(7));

                    return post;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }
}
