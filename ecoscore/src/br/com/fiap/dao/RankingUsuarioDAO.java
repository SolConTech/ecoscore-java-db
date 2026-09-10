package br.com.fiap.dao;

import br.com.fiap.dto.RankingUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RankingUsuarioDAO {
    private Connection con;

    public RankingUsuarioDAO() {}
    public RankingUsuarioDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public String inserir(RankingUsuario rankingUsuario){
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

    public String alterar(RankingUsuario rankingUsuario){
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

    public String excluir(RankingUsuario rankingUsuario){
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

    public ArrayList<RankingUsuario> listarTodos(){
        String sql = "select * from ranking_usuario order by ID_RANKING";
        ArrayList<RankingUsuario> listaRankingUsuario = new ArrayList<>();

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
}
