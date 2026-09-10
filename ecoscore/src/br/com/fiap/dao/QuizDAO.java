package br.com.fiap.dao;

import br.com.fiap.dto.Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
                    quiz.setIdUsuario(rs.getString(2));
                    quiz.setNumQuestoes(rs.getInt(3));
                    quiz.setNumAcertos(rs.getInt(4));
                    quiz.setQtPontosPorQuestao(rs.getInt(5));
                    quiz.setQtPontosGerados(); //ela calcula com base no num acertos e num pontos questão

                    LocalDateTime data = (LocalDateTime) rs.getObject(7);
                    quiz.setDtQuiz(data);

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
}
