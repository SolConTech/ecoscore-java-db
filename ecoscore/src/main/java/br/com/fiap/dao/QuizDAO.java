package br.com.fiap.dao;

import br.com.fiap.to.QuizTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;

public class QuizDAO implements IDAO{
    private Connection con;

    public QuizDAO() {}
    public QuizDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {

        return con;
    }

    public String inserir(Object object) throws SQLException {
        QuizTO quizTO = (QuizTO) object;
        String sql = "insert into quiz(ID_QUIZ,USUARIO_ID_USUARIO,NUM_QUESTOES,NUM_ACERTOS,QT_PONTOSPORQUESTAO,QT_PONTOSGERADOS,DT_QUIZ) values(?,?,?,?,?,?,?)";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)) {
            ps.setInt(1, quizTO.getIdQuiz());
            ps.setString(2, quizTO.getIdUsuario());
            ps.setInt(3, quizTO.getNumQuestoes());
            ps.setInt(4, quizTO.getNumAcertos());
            ps.setInt(5, quizTO.getQtPontosPorQuestao());
            ps.setInt(6, quizTO.getQtPontosGerados());
            ps.setObject(7, quizTO.getDtQuiz());

            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso";
            } else {
                return "Erro ao inserir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    public String alterar(Object id, Object object) throws SQLException {
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
                return "Alterado com sucesso";
            } else {
                return "Erro ao alterar";
            }
        } catch (SQLException e) {
            throw new SQLException("Erro de SQL: " + e.getMessage());
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }

    public Boolean excluir(Object object) throws SQLException {
        QuizTO quizTO = (QuizTO) object;
        String sql = "delete from quiz where ID_QUIZ=?";
        try (PreparedStatement ps = ConnectionFactory.abrirConexao().prepareStatement(sql)) {
            ps.setInt(1, quizTO.getIdQuiz());

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

    public ArrayList<Object> listarTodos() throws SQLException {
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

    public Integer criarId() throws SQLException{
        String sql = "SELECT ID_QUIZ FROM QUIZ";
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

    public QuizTO listarUm(Object id) throws SQLException {
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
