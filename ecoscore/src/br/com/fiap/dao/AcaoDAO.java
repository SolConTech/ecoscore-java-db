package br.com.fiap.dao;

import br.com.fiap.dto.Acao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AcaoDAO {
    private Connection con;

    public AcaoDAO(){};
    public AcaoDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public String inserir(Acao acao){
        String sql = "insert into acao(ID_ACAO,usuario_id_usuario, ds_acao, qt_pontosGerados, dt_acao) values(?,?,?,?,?)";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1,acao.getIdAcao());
            ps.setString(2, acao.getIdUsuario());
            ps.setString(3, acao.getDsAcao());
            ps.setInt(4, acao.getQtPontosGerados());
            //pesquisei e esse é o melhor para datas com horas e minutos o driver vai transformar em data, tentei dar parse e não foi
            ps.setObject(5,acao.getDtAcao());
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso";
            } else {
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String alterar(Acao acao){
        String sql = "update acao set USUARIO_ID_USUARIO=?, DS_ACAO=?, QT_PONTOSGERADOS=?, DT_ACAO=? where ID_ACAO=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, acao.getIdUsuario());
            ps.setString(2, acao.getDsAcao());
            ps.setInt(3, acao.getQtPontosGerados());
            ps.setObject(4,acao.getDtAcao());
            ps.setInt(5,acao.getIdAcao());
            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso";
            } else {
                return "Erro ao alterar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String excluir(Acao acao){
        String sql = "delete from acao where ID_ACAO=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, acao.getIdAcao());
            if (ps.executeUpdate() > 0) {
                return "Excluido com sucesso";
            } else {
                return "Erro ao excluir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public ArrayList<Acao> listarTodos(){
        String sql = "select * from acao order by USUARIO_ID_USUARIO";
        ArrayList<Acao> listaAcao = new ArrayList<>();
        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){
            if (rs != null){
                while (rs.next()){
                    Acao acao = new Acao();
                    acao.setIdAcao(rs.getInt(1));
                    acao.setIdUsuario(rs.getString(2));
                    acao.setDsAcao(rs.getString(3));
                    acao.setQtPontosGerados(rs.getInt(4));
                    //agora rpa pegra vou usar o getObjetc tbm, só vou ter que dar um cast nele antes
                    LocalDateTime data = (LocalDateTime) rs.getObject(5);
                    acao.setDtAcao(data);

                    listaAcao.add(acao);
                }
                return listaAcao;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }

}
