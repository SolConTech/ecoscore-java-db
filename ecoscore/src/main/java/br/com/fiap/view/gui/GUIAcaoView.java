package br.com.fiap.view.gui;

import br.com.fiap.controller.AcaoController;
import br.com.fiap.controller.UsuarioController;
import br.com.fiap.to.AcaoTO;
import br.com.fiap.to.CalculadoraPontos;
import br.com.fiap.to.UsuarioTO;

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

    private AcaoTO acaoTO;
    private AcaoController acaoController;
    private UsuarioController usuarioController;
    private CalculadoraPontos calculadora;

    public GUIAcaoView(UsuarioTO usuarioTO) {
        inicializarComponentes();
        calcularPontos();
        definirEventos(usuarioTO);
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
        acaoTO = new AcaoTO();
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
                        acaoTO.registrarPontos(calculadora.pontosNatureza(dificuldade));
                        break;
                    case 1:
                        float kgCarbono = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de carbono? Digite (um número): "));
                        acaoTO.registrarPontos(calculadora.pontosCarbono(kgCarbono));
                        break;
                    case 2:
                        float litros = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de litros economizados? Digite (um número): "));
                        acaoTO.registrarPontos(calculadora.pontosAgua(litros));
                        break;
                    case 3:
                        float kgReciclados = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de KG reciclados? Digite (um número): "));
                        acaoTO.registrarPontos(calculadora.pontosReciclagem(kgReciclados));
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
        tfQtPontosGerados.setText(String.format("%d", acaoTO.getQtPontosGerados()));
    }

    private void definirEventos(UsuarioTO usuarioTO) {
        btRegistrarAcao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                acaoController = new AcaoController();

                try {
                    //conferir se ds descrição está certa
                    acaoTO.setDsAcao(tfDsAcao.getText());
                    if (! acaoTO.getDsAcao().isBlank()){
                        acaoTO.setIdAcao(acaoController.criarIdAcao());
                        acaoTO.setIdUsuario(usuarioTO.getIdUsuario());
                        acaoTO.setDtAcao(LocalDateTime.now());

                        usuarioTO.registrarAtividade(acaoTO.detalhesAcao(),
                                acaoTO.getQtPontosGerados());
                        String resultado = acaoController.inserirAcao(acaoTO.getIdAcao(), acaoTO.getIdUsuario(), acaoTO.getDsAcao(), acaoTO.getQtPontosGerados(), acaoTO.getDtAcao());

                        usuarioController = new UsuarioController();
                        usuarioController.alterarUsuario(usuarioTO.getIdUsuario(), usuarioTO.getNmUsuario(), usuarioTO.getVlMerito(), usuarioTO.getQtSoulCoins(), usuarioTO.getDsAtividadeRecente(), usuarioTO.getSelosGanhos());
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
                acaoTO = new AcaoTO();
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
                                acaoTO.registrarPontos(calculadora.pontosNatureza(dificuldade));
                                break;
                            case 1:
                                float kgCarbono = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de carbono? Digite (um número): "));
                                acaoTO.registrarPontos(calculadora.pontosCarbono(kgCarbono));
                                break;
                            case 2:
                                float litros = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de litros economizados? Digite (um número): "));
                                acaoTO.registrarPontos(calculadora.pontosAgua(litros));
                                break;
                            case 3:
                                float kgReciclados = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de KG reciclados? Digite (um número): "));
                                acaoTO.registrarPontos(calculadora.pontosReciclagem(kgReciclados));
                                break;
                            case 4:
                                //muda nada então, preciso definir o mesmo valor de antes
                                acaoTO.setQtPontosGerados(Integer.parseInt(tfQtPontosGerados.getText()));
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                    }
                } while (escolha > 4 || escolha < 0);
                tfQtPontosGerados.setText(String.format("%d", acaoTO.getQtPontosGerados()));
            }
        });
    }
}
