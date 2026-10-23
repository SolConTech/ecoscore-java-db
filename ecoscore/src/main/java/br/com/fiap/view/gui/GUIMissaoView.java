package br.com.fiap.view.gui;

import br.com.fiap.controller.MissaoController;
import br.com.fiap.controller.UsuarioController;
import br.com.fiap.to.MissaoTO;
import br.com.fiap.to.UsuarioTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class GUIMissaoView extends JFrame {
    private Container contentPane;
    private JPanel painel, painelGeral;
    private JTextField tfNmMissao, tfDsMissao, tfSelo, tfQtPontosGerados;
    private JLabel lbNmMissao, lbDsMissao, lbSelo, lbQtPontosGerados;
    private JButton btRegistrarMissao;

    private MissaoTO missaoTO;
    private MissaoController missaoController;
    private UsuarioController usuarioController;

    public GUIMissaoView(UsuarioTO usuarioTO) {
        inicializarComponentes();
        definirEventos(usuarioTO);
    }

    private void inicializarComponentes() {
        setTitle("Ecoscore - Registrar missão");
        setBounds(0, 0, 500, 175);
        contentPane = getContentPane();

        painelGeral = new JPanel();
        painelGeral.setLayout(new FlowLayout());
        painel = new JPanel();
        painel.setLayout(new GridLayout(5,0));

        lbNmMissao = new JLabel("Nome da missão:");
        tfNmMissao = new JTextField(20);
        tfNmMissao.setToolTipText("máximo de 25 caracteres");

        lbDsMissao = new JLabel("Descrição da missão:");
        tfDsMissao = new JTextField(20);

        lbSelo = new JLabel("Nome do selo");
        lbSelo.setToolTipText("máximo de 30 caracteres");
        tfSelo = new JTextField(10);

        lbQtPontosGerados = new JLabel("Pontos gerados:");
        tfQtPontosGerados = new JTextField(10);
        tfQtPontosGerados.setToolTipText("Número inteiro maior que zero");

        btRegistrarMissao = new JButton("Registrar ação");
        btRegistrarMissao.setPreferredSize(new Dimension(400,25));

        painel.add(lbNmMissao);
        painel.add(tfNmMissao);
        painel.add(lbDsMissao);
        painel.add(tfDsMissao);
        painel.add(lbSelo);
        painel.add(tfSelo);
        painel.add(lbQtPontosGerados);
        painel.add(tfQtPontosGerados);

        painelGeral.add(painel);
        painelGeral.add(btRegistrarMissao);
        add(painelGeral);
    }

    private void definirEventos(UsuarioTO usuarioTO) {
        btRegistrarMissao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                missaoTO = new MissaoTO();
                missaoController = new MissaoController();
                usuarioController = new UsuarioController();

                try {
                    missaoTO.setNmMissao(tfNmMissao.getText());
                    missaoTO.setDsMissao(tfNmMissao.getText());
                    missaoTO.setSelo(tfSelo.getText());
                    missaoTO.setQtPontosGerados(Integer.parseInt(tfQtPontosGerados.getText()));
                    if (! missaoTO.getNmMissao().isBlank() && ! missaoTO.getDsMissao().isBlank()){
                        missaoTO.setIdMissao(missaoController.criarIdMissao());
                        missaoTO.setDtMissao(LocalDateTime.now());

                        usuarioTO.registrarAtividade(missaoTO.detalhesMissao(),
                                                   missaoTO.getQtPontosGerados(),
                                                   missaoTO.getSelo());
                        String resultado = missaoController.inserirMissao(missaoTO.getIdMissao(), missaoTO.getNmMissao(),
                                                                          missaoTO.getDsMissao(), missaoTO.getSelo(),
                                                                          missaoTO.getQtPontosGerados(), missaoTO.getDtMissao());

                        usuarioController = new UsuarioController();
                        usuarioController.alterarUsuario(usuarioTO.getIdUsuario(), usuarioTO.getNmUsuario(), usuarioTO.getVlMerito(), usuarioTO.getQtSoulCoins(), usuarioTO.getDsAtividadeRecente(), usuarioTO.getSelosGanhos());
                        JOptionPane.showMessageDialog(null, resultado,"Conexão",JOptionPane.INFORMATION_MESSAGE);
                        GUIMissaoView.this.dispose();
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
    }
}
