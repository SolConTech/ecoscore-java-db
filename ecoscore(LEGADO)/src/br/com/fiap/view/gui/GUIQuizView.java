package br.com.fiap.view.gui;

import br.com.fiap.controller.QuizController;
import br.com.fiap.controller.UsuarioController;
import br.com.fiap.model.dto.Quiz;
import br.com.fiap.model.dto.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class GUIQuizView extends JFrame {
    private Container contentPane;
    private JPanel painel, painelGeral;
    private JTextField tfNumQuestoes, tfNumAcertos, tfQtPontosPorQuestao, tfQtPontosGerados;
    private JLabel lbNumQuestoes, lbNumAcertos, lbQtPontosPorQuestao, lbQtPontosGerados;
    private JButton btRecalcularPontos, btRegistrarQuiz;

    private Quiz quiz;
    private QuizController quizController;
    private UsuarioController usuarioController;

    public GUIQuizView(Usuario usuario) {
        inicializarComponentes();
        iniciarValores(usuario);
        definirEventos(usuario);
    }

    private void inicializarComponentes() {
        setTitle("Ecoscore - Registrar quiz");
        setBounds(0, 0, 500, 175);
        contentPane = getContentPane();

        painelGeral = new JPanel();
        painelGeral.setLayout(new FlowLayout());
        painel = new JPanel();
        painel.setLayout(new GridLayout(5,0));

        lbNumQuestoes = new JLabel("Qtde. de questões: ");
        tfNumQuestoes = new JTextField(20);
        tfNumQuestoes.setEditable(false);

        lbNumAcertos = new JLabel("Qtde. de acertos: ");
        tfNumAcertos = new JTextField(20);
        tfNumAcertos.setEditable(false);

        lbQtPontosPorQuestao = new JLabel("Qtde. de pontos por questão: ");
        tfQtPontosPorQuestao = new JTextField(20);
        tfQtPontosPorQuestao.setEditable(false);

        lbQtPontosGerados = new JLabel("Qtde. de pontos gerados: ");
        tfQtPontosGerados = new JTextField(20);
        tfQtPontosGerados.setEditable(false);

        lbQtPontosGerados = new JLabel("Pontos gerados:");
        tfQtPontosGerados = new JTextField(10);
        tfNumQuestoes.setEditable(false);

        btRegistrarQuiz = new JButton("Registrar quiz");
        btRecalcularPontos = new JButton("Recalcular pontos");

        painel.add(lbNumQuestoes);
        painel.add(tfNumQuestoes);
        painel.add(lbNumAcertos);
        painel.add(tfNumAcertos);
        painel.add(lbQtPontosPorQuestao);
        painel.add(tfQtPontosPorQuestao);
        painel.add(lbQtPontosGerados);
        painel.add(tfQtPontosGerados);
        painel.add(btRegistrarQuiz);

        painelGeral.add(painel);
        add(painelGeral);
    }

    private void iniciarValores(Usuario usuario){
        try {
            quiz = new Quiz();

            int numQuestoes = Integer.parseInt(JOptionPane.showInputDialog("Digite o número de questões: "));
            quiz.setNumQuestoes(numQuestoes);
            int numAcertos = Integer.parseInt(JOptionPane.showInputDialog("Digite o número de acertos: "));
            quiz.setNumAcertos(numAcertos);
            int pontosPorQuestao = Integer.parseInt(JOptionPane.showInputDialog("Digite os pontos por questão: "));
            quiz.setQtPontosPorQuestao(pontosPorQuestao);
            quiz.setQtPontosGerados();
            quiz.setDtQuiz(LocalDateTime.now());

            tfNumQuestoes.setText(String.format("%s",quiz.getNumQuestoes()));
            tfNumAcertos.setText(String.format("%s",quiz.getNumAcertos()));
            tfQtPontosPorQuestao.setText(String.format("%s",quiz.getQtPontosPorQuestao()));
            tfQtPontosGerados.setText(String.format("%s",quiz.getQtPontosGerados()));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void definirEventos(Usuario usuario) {
        btRecalcularPontos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                iniciarValores(usuario);
            }
        });
        btRegistrarQuiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                quiz = new Quiz();
                quizController = new QuizController();
                usuarioController = new UsuarioController();

                try {
                    quiz.setIdQuiz(quizController.criarIdQuiz());
                    quiz.setIdUsuario(usuario.getIdUsuario());

                    usuario.registrarAtividade(quiz.detalhesQuiz(),
                                               quiz.getQtPontosGerados());

                    usuarioController = new UsuarioController();
                    usuarioController.alterarUsuario(usuario.getIdUsuario(),usuario.getNmUsuario(),
                                                     usuario.getVlMerito(), usuario.getQtSoulCoins(),
                                                     usuario.getDsAtividadeRecente(),usuario.getSelosGanhos());

                    String resultado = quizController.inserirQuiz(quiz.getIdQuiz(), quiz.getIdUsuario(), quiz.getNumQuestoes(), quiz.getNumAcertos(), quiz.getQtPontosPorQuestao(), quiz.getDtQuiz());
                    JOptionPane.showMessageDialog(null, resultado,"Conexão",JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
