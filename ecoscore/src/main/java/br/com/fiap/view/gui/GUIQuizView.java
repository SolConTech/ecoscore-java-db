package br.com.fiap.view.gui;

import br.com.fiap.controller.QuizController;
import br.com.fiap.controller.UsuarioController;
import br.com.fiap.to.QuizTO;
import br.com.fiap.to.UsuarioTO;

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

    private QuizTO quizTO;
    private QuizController quizController;
    private UsuarioController usuarioController;

    public GUIQuizView(UsuarioTO usuarioTO) {
        inicializarComponentes();
        iniciarValores(usuarioTO);
        definirEventos(usuarioTO);
    }

    private void inicializarComponentes() {
        setTitle("Ecoscore - Registrar quizTO");
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

        btRegistrarQuiz = new JButton("Registrar quizTO");
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

    private void iniciarValores(UsuarioTO usuarioTO){
        try {
            quizTO = new QuizTO();

            int numQuestoes = Integer.parseInt(JOptionPane.showInputDialog("Digite o número de questões: "));
            quizTO.setNumQuestoes(numQuestoes);
            int numAcertos = Integer.parseInt(JOptionPane.showInputDialog("Digite o número de acertos: "));
            quizTO.setNumAcertos(numAcertos);
            int pontosPorQuestao = Integer.parseInt(JOptionPane.showInputDialog("Digite os pontos por questão: "));
            quizTO.setQtPontosPorQuestao(pontosPorQuestao);
            quizTO.setQtPontosGerados();
            quizTO.setDtQuiz(LocalDateTime.now());

            tfNumQuestoes.setText(String.format("%s", quizTO.getNumQuestoes()));
            tfNumAcertos.setText(String.format("%s", quizTO.getNumAcertos()));
            tfQtPontosPorQuestao.setText(String.format("%s", quizTO.getQtPontosPorQuestao()));
            tfQtPontosGerados.setText(String.format("%s", quizTO.getQtPontosGerados()));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void definirEventos(UsuarioTO usuarioTO) {
        btRecalcularPontos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                iniciarValores(usuarioTO);
            }
        });
        btRegistrarQuiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                quizTO = new QuizTO();
                quizController = new QuizController();
                usuarioController = new UsuarioController();

                try {
                    quizTO.setIdQuiz(quizController.criarIdQuiz());
                    quizTO.setIdUsuario(usuarioTO.getIdUsuario());

                    usuarioTO.registrarAtividade(quizTO.detalhesQuiz(),
                                               quizTO.getQtPontosGerados());

                    usuarioController = new UsuarioController();
                    usuarioController.alterarUsuario(usuarioTO.getIdUsuario(), usuarioTO.getNmUsuario(),
                                                     usuarioTO.getVlMerito(), usuarioTO.getQtSoulCoins(),
                                                     usuarioTO.getDsAtividadeRecente(), usuarioTO.getSelosGanhos());

                    String resultado = quizController.inserirQuiz(quizTO.getIdQuiz(), quizTO.getIdUsuario(), quizTO.getNumQuestoes(), quizTO.getNumAcertos(), quizTO.getQtPontosPorQuestao(), quizTO.getDtQuiz());
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
