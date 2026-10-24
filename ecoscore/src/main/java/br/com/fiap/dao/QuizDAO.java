package br.com.fiap.dao;

import br.com.fiap.to.QuizTO;

import java.sql.*;
import java.util.ArrayList;

public class QuizDAO implements IDAO{

    public QuizDAO() {}

    public QuizTO save(Object object) throws SQLException {
        QuizTO quizTO = (QuizTO) object;
        String sql = "insert into quiz(USUARIO_ID_USUARIO,NUM_QUESTOES,NUM_ACERTOS,QT_PONTOSPORQUESTAO,QT_PONTOSGERADOS,DT_QUIZ) values(?,?,?,?,?,?)";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)) {
            ps.setString(1, quizTO.getIdUsuario());
            ps.setInt(2, quizTO.getNumQuestoes());
            ps.setInt(3, quizTO.getNumAcertos());
            ps.setInt(4, quizTO.getQtPontosPorQuestao());
            ps.setInt(5, quizTO.getQtPontosGerados());
            ps.setObject(6, quizTO.getDtQuiz());

            if (ps.executeUpdate() > 0) {
                return quizTO;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }

    public QuizTO update(Object id, Object object) throws SQLException {
        QuizTO quizTO = (QuizTO) object;
        Integer idQuiz = (Integer) id;
        String sql = "update quiz set USUARIO_ID_USUARIO=?, NUM_QUESTOES=?, NUM_ACERTOS=?, QT_PONTOSPORQUESTAO=?, QT_PONTOSGERADOS=?, DT_QUIZ=? where ID_QUIZ=?";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)) {
            ps.setString(1, quizTO.getIdUsuario());
            ps.setInt(2, quizTO.getNumQuestoes());
            ps.setInt(3, quizTO.getNumAcertos());
            ps.setInt(4, quizTO.getQtPontosPorQuestao());
            ps.setInt(5, quizTO.getQtPontosGerados());
            ps.setObject(6, quizTO.getDtQuiz());
            ps.setInt(7, idQuiz);

            if (ps.executeUpdate() > 0) {
                return quizTO;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }

    public Boolean delete(Object id) throws SQLException {
        Integer idQuiz = (Integer) id;
        String sql = "delete from quiz where ID_QUIZ=?";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)) {
            ps.setInt(1, idQuiz);

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
        String sql = "select * from quiz order by ID_QUIZ";
        ArrayList<Object> listaQuiz = new ArrayList<>();

        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){

            if (rs != null){
                while (rs.next()){
                    QuizTO quizTO = new QuizTO();

                    quizTO.setIdQuiz(rs.getInt(1));
                    quizTO.setNumQuestoes(rs.getInt(2));
                    quizTO.setNumAcertos(rs.getInt(3));
                    quizTO.setQtPontosPorQuestao(rs.getInt(4));
                    quizTO.setQtPontosGerados(); //ela calcula com base no num acertos e num pontos questão

                    Timestamp data = rs.getTimestamp(6);
                    quizTO.setDtQuiz(data.toLocalDateTime());
                    quizTO.setIdUsuario(rs.getString(7));

                    listaQuiz.add(quizTO);
                }

                return listaQuiz;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }

    public QuizTO findById(Object id) throws SQLException {
        String sql = "SELECT * FROM QUIZ WHERE ID_QUIZ = ?";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)){
            ps.setObject(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    QuizTO quizTO = new QuizTO();
                    quizTO.setIdQuiz(rs.getInt(1));
                    quizTO.setNumQuestoes(rs.getInt(2));
                    quizTO.setNumAcertos(rs.getInt(3));
                    quizTO.setQtPontosPorQuestao(rs.getInt(4));
                    quizTO.setQtPontosGerados();
                    Timestamp data = rs.getTimestamp(6);
                    quizTO.setDtQuiz(data.toLocalDateTime());
                    quizTO.setIdUsuario(rs.getString(7));

                    return quizTO;
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
