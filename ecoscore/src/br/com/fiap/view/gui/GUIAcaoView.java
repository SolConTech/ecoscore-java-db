package br.com.fiap.view.gui;

import br.com.fiap.controller.*;
import br.com.fiap.model.dto.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class GUIAcaoView extends JFrame {
    private Container contentPane;
    private JPanel painel, painelGeral;
    private JTextField tfDsAcao, tfQtPontosGerados;
    private JLabel lbDsAcao, lbQtPontosGerados;
    private JButton btRegistrarAcao, btRecalcularPontos;

    private Acao acao;
    private AcaoController acaoController;
    private UsuarioController usuarioController;
    private CalculadoraPontos calculadora;

    public GUIAcaoView(Usuario usuario) {
        inicializarComponentes();
        calcularPontos();
        definirEventos(usuario);
    }

    private void inicializarComponentes() {
        setTitle("Ecoscore - Registrar ação");
        setBounds(0, 0, 450, 160);
        contentPane = getContentPane();

        painelGeral = new JPanel();
        painelGeral.setLayout(new FlowLayout());
        painel = new JPanel();
        painel.setLayout(new GridLayout(4,0));

        lbDsAcao = new JLabel("Descrição da ação:");
        tfDsAcao = new JTextField(20);
        tfDsAcao.setToolTipText("Jardinagem de 10 árvores...");

        lbQtPontosGerados = new JLabel("Pontos gerados:");
        tfQtPontosGerados = new JTextField(10);
        tfQtPontosGerados.setEditable(false);

        btRegistrarAcao = new JButton("Registrar ação");
        btRecalcularPontos = new JButton("Recalcular pontos");

        painel.add(lbDsAcao);
        painel.add(tfDsAcao);
        painel.add(lbQtPontosGerados);
        painel.add(tfQtPontosGerados);

        painel.add(btRecalcularPontos);
        painel.add(btRegistrarAcao);
        painelGeral.add(painel);
        add(painelGeral);
    }
    private void calcularPontos(){
        acao = new Acao();
        calculadora = new CalculadoraPontos();

        String[] tipos = {"Natureza", "Carbono", "Água", "Reciclagem", "Cancelar"};
        int escolha;
        do {
            escolha = JOptionPane.showOptionDialog(null,"Digite o tipo de ação",
                    "Escolha",JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,null,tipos,tipos[0]);
            try {
                switch (escolha) {
                    case 0:
                        int dificuldade = Integer.parseInt(JOptionPane.showInputDialog("Qual é a dificuldade da ação realizada? Digite (entre 1 a 5): "));
                        acao.registrarPontos(calculadora.pontosNatureza(dificuldade));
                        break;
                    case 1:
                        float kgCarbono = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de carbono? Digite (um número): "));
                        acao.registrarPontos(calculadora.pontosCarbono(kgCarbono));
                        break;
                    case 2:
                        float litros = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de litros economizados? Digite (um número): "));
                        acao.registrarPontos(calculadora.pontosAgua(litros));
                        break;
                    case 3:
                        float kgReciclados = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de KG reciclados? Digite (um número): "));
                        acao.registrarPontos(calculadora.pontosReciclagem(kgReciclados));
                        break;
                    case 4:
                        this.dispose(); //pra fechar a janela
                        return; //fecha a janela
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        } while (escolha > 4 || escolha < 0);
        tfQtPontosGerados.setText(String.format("%d", acao.getQtPontosGerados()));
    }

    private void definirEventos(Usuario usuario) {
        btRegistrarAcao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                acaoController = new AcaoController();

                try {
                    //conferir se ds descrição está certa
                    acao.setDsAcao(tfDsAcao.getText());
                    if (!acao.getDsAcao().isBlank()){
                        acao.setIdAcao(acaoController.criarIdAcao());
                        acao.setIdUsuario(usuario.getIdUsuario());
                        acao.setDtAcao(LocalDateTime.now());

                        usuario.registrarAtividade(acao.detalhesAcao(),
                                acao.getQtPontosGerados());
                        String resultado = acaoController.inserirAcao(acao.getIdAcao(), acao.getIdUsuario(), acao.getDsAcao(), acao.getQtPontosGerados(), acao.getDtAcao());

                        usuarioController = new UsuarioController();
                        usuarioController.alterarUsuario(usuario.getIdUsuario(), usuario.getNmUsuario(), usuario.getVlMerito(), usuario.getQtSoulCoins(),usuario.getDsAtividadeRecente(),usuario.getSelosGanhos());
                        JOptionPane.showMessageDialog(null, resultado,"Conexão",JOptionPane.INFORMATION_MESSAGE);
                        GUIAcaoView.this.dispose();
                        return;
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btRecalcularPontos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // Eu poderia chamar o método, mas a opção 4 iria fechar e aqui deve só não fazer nada.
                acao = new Acao();
                calculadora = new CalculadoraPontos();

                String[] tipos = {"Natureza", "Carbono", "Água", "Reciclagem", "Cancelar"};
                int escolha;
                do {
                    escolha = JOptionPane.showOptionDialog(null,"Digite o tipo de ação",
                            "Escolha",JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,null,tipos,tipos[0]);
                    try {
                        switch (escolha) {
                            case 0:
                                int dificuldade = Integer.parseInt(JOptionPane.showInputDialog("Qual é a dificuldade da ação realizada? Digite (entre 1 a 5): "));
                                acao.registrarPontos(calculadora.pontosNatureza(dificuldade));
                                break;
                            case 1:
                                float kgCarbono = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de carbono? Digite (um número): "));
                                acao.registrarPontos(calculadora.pontosCarbono(kgCarbono));
                                break;
                            case 2:
                                float litros = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de litros economizados? Digite (um número): "));
                                acao.registrarPontos(calculadora.pontosAgua(litros));
                                break;
                            case 3:
                                float kgReciclados = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de KG reciclados? Digite (um número): "));
                                acao.registrarPontos(calculadora.pontosReciclagem(kgReciclados));
                                break;
                            case 4:
                                //muda nada então, preciso definir o mesmo valor de antes
                                acao.setQtPontosGerados(Integer.parseInt(tfQtPontosGerados.getText()));
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                    }
                } while (escolha > 4 || escolha < 0);
                tfQtPontosGerados.setText(String.format("%d", acao.getQtPontosGerados()));
            }
        });
    }
}
