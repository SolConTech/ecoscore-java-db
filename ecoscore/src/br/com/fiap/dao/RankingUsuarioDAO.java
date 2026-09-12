package br.com.fiap.dao;

import br.com.fiap.dto.Missao;
import br.com.fiap.dto.RankingUsuario;
import br.com.fiap.dto.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;

public class RankingUsuarioDAO implements IDAO{
    private Connection con;

    public RankingUsuarioDAO() {}
    public RankingUsuarioDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public String inserir(Object object){
        RankingUsuario rankingUsuario = (RankingUsuario) object;
        String sql = "insert into ranking_usuario(ID_RANKING,ID_USUARIO,QT_VOTOS) values(?,?,?)";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, rankingUsuario.getIdRanking());
            ps.setString(2, rankingUsuario.getIdUsuario());
            ps.setInt(3, rankingUsuario.getQtVotos());

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
        RankingUsuario rankingUsuario = (RankingUsuario) object;
        String sql = "update ranking_usuario set ID_USUARIO=?, QT_VOTOS=? where ID_RANKING=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, rankingUsuario.getIdUsuario());
            ps.setInt(2, rankingUsuario.getQtVotos());
            ps.setInt(3, rankingUsuario.getIdRanking());

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
        RankingUsuario rankingUsuario = (RankingUsuario) object;
        String sql = "delete from ranking_usuario where ID_RANKING=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, rankingUsuario.getIdRanking());

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
        String sql = "select * from ranking_usuario order by QT_VOTOS DESC";
        ArrayList<Object> listaRankingUsuario = new ArrayList<>();

        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){

            if (rs != null){
                while (rs.next()){
                    RankingUsuario rankingUsuario = new RankingUsuario();

                    rankingUsuario.setIdRanking(rs.getInt(1));
                    rankingUsuario.setIdUsuario(rs.getString(3));
                    rankingUsuario.setQtVotos(rs.getInt(2));

                    listaRankingUsuario.add(rankingUsuario);
                }

                return listaRankingUsuario;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }

    public RankingUsuario pegarUm(Object object){
        String sql = "SELECT * FROM RANKING_USUARIO WHERE ID_USUARIO = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            ps.setObject(1,object);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    RankingUsuario ranking = new RankingUsuario();
                    ranking.setIdRanking(rs.getInt(1));
                    ranking.setQtVotos(rs.getInt(2));
                    ranking.setIdUsuario(rs.getString(3));
                    return ranking;
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
