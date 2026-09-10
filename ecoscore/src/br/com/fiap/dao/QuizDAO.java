package br.com.fiap.dao;

import br.com.fiap.dto.Post;
import br.com.fiap.dto.Quiz;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class QuizDAO {
    private Connection con;

    public QuizDAO() {}
    public QuizDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {

        return con;
    }

    public String inserir(Quiz quiz){
        String sql = "insert into quiz(ID_QUIZ,USUARIO_ID_USUARIO,NUM_QUESTOES,NUM_ACERTOS,QT_PONTOSPORQUESTAO,QT_PONTOSGERADOS,DT_QUIZ) values(?,?,?,?,?,?,?)";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, quiz.getIdQuiz());
            ps.setString(2, quiz.getIdUsuario());
            ps.setInt(3, quiz.getNumQuestoes());
            ps.setInt(4, quiz.getNumAcertos());
            ps.setInt(5, quiz.getQtPontosPorQuestao());
            ps.setInt(6, quiz.getQtPontosGerados());
            ps.setObject(7, quiz.getDtQuiz());

            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso";
            } else {
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String alterar(Quiz quiz){
        String sql = "update quiz set USUARIO_ID_USUARIO=?, NUM_QUESTOES=?, NUM_ACERTOS=?, QT_PONTOSPORQUESTAO=?, QT_PONTOSGERADOS=?, DT_QUIZ=? where ID_QUIZ=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, quiz.getIdUsuario());
            ps.setInt(2, quiz.getNumQuestoes());
            ps.setInt(3, quiz.getNumAcertos());
            ps.setInt(4, quiz.getQtPontosPorQuestao());
            ps.setInt(5, quiz.getQtPontosGerados());
            ps.setObject(6, quiz.getDtQuiz());
            ps.setInt(7, quiz.getIdQuiz());

            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso";
            } else {
                return "Erro ao alterar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String excluir(Quiz quiz){
        String sql = "delete from quiz where ID_QUIZ=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, quiz.getIdQuiz());

            if (ps.executeUpdate() > 0) {
                return "Excluido com sucesso";
            } else {
                return "Erro ao excluir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public ArrayList<Quiz> listarTodos(){
        String sql = "select * from quiz order by ID_QUIZ";
        ArrayList<Quiz> listaQuiz = new ArrayList<>();

        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){

            if (rs != null){
                while (rs.next()){
                    Quiz quiz = new Quiz();

                    quiz.setIdQuiz(rs.getInt(1));
                    quiz.setNumQuestoes(rs.getInt(2));
                    quiz.setNumAcertos(rs.getInt(3));
                    quiz.setQtPontosPorQuestao(rs.getInt(4));
                    quiz.setQtPontosGerados(); //ela calcula com base no num acertos e num pontos questão

                    Timestamp data = rs.getTimestamp(5);
                    quiz.setDtQuiz(data.toLocalDateTime());
                    quiz.setIdUsuario(rs.getString(6));

                    listaQuiz.add(quiz);
                }

                return listaQuiz;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }

    public Integer criarId() {
        String sql = "SELECT ID_QUIZ FROM QUIZ";
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

    public Quiz pegarUm(int idQuiz){
        String sql = "SELECT * FROM QUIZ WHERE ID_QUIZ = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            ps.setInt(1,idQuiz);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    Quiz quiz = new Quiz();
                    quiz.setIdQuiz(rs.getInt(1));
                    quiz.setNumQuestoes(rs.getInt(2));
                    quiz.setNumAcertos(rs.getInt(3));
                    quiz.setQtPontosPorQuestao(rs.getInt(4));
                    quiz.setQtPontosGerados(); //ela calcula com base no num acertos e num pontos questão
                    //pula o cinco que viria
                    Timestamp data = rs.getTimestamp(6);
                    quiz.setDtQuiz(data.toLocalDateTime());
                    quiz.setIdUsuario(rs.getString(7));

                    return quiz;
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
