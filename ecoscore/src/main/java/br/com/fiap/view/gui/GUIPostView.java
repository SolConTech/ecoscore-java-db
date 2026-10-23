package br.com.fiap.view.gui;

import br.com.fiap.controller.PostController;
import br.com.fiap.controller.RankingUsuarioController;
import br.com.fiap.controller.UsuarioController;
import br.com.fiap.to.PostTO;
import br.com.fiap.to.RankingUsuarioTO;
import br.com.fiap.to.UsuarioTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class GUIPostView extends JFrame {
    private Container contentPane;
    private JPanel painel, painelGeral;
    private JTextArea tfDsPost;
    private JScrollPane spDsPost;
    private JTextField tfNumUpVotes, tfNumDownVotes, tfNumSaldoVotes;
    private JLabel lbDsPost, lbNumUpVotes, lbNumDownVotes, lbNumSaldoVotes;
    private JButton btRegistrarPost, btRecalcularVotos;

    private PostTO postTO;
    private PostController postController;
    private UsuarioController usuarioController;
    private RankingUsuarioTO rankingUsuarioTO;
    private RankingUsuarioController rankingUsuarioController;

    public GUIPostView(UsuarioTO usuarioTO) {
        inicializarComponentes();
        calcularVotos();
        definirEventos(usuarioTO);
    }

    private void inicializarComponentes() {
        setTitle("Ecoscore - Registrar postTO");
        setBounds(0, 0, 500, 300);
        contentPane = getContentPane();

        painelGeral = new JPanel();
        painelGeral.setLayout(new FlowLayout());
        painel = new JPanel();
        painel.setLayout(new GridLayout(6,0));

        lbDsPost = new JLabel("Descrição do postTO:");
        tfDsPost = new JTextArea("Descrição do postTO");
        tfDsPost.setColumns(20);
        tfDsPost.setLineWrap(true);
        tfDsPost.setWrapStyleWord(true);
        spDsPost = new JScrollPane(tfDsPost);
        spDsPost.setPreferredSize(new java.awt.Dimension(400, 50));


        lbNumUpVotes = new JLabel("Votos positivos:");
        tfNumUpVotes = new JTextField(10);
        tfNumUpVotes.setEditable(false);
        lbNumDownVotes = new JLabel("Votos negativos:");
        tfNumDownVotes = new JTextField(10);
        tfNumDownVotes.setEditable(false);
        lbNumSaldoVotes = new JLabel("Saldo de votos:");
        tfNumSaldoVotes = new JTextField(10);
        tfNumSaldoVotes.setEditable(false);

        btRegistrarPost = new JButton("Registrar ação");
        btRecalcularVotos = new JButton("Recalcular votos");

        painelGeral.add(lbDsPost);
        painelGeral.add(spDsPost);
        painel.add(lbNumUpVotes);
        painel.add(tfNumUpVotes);
        painel.add(lbNumDownVotes);
        painel.add(tfNumDownVotes);
        painel.add(lbNumSaldoVotes);
        painel.add(tfNumSaldoVotes);

        painel.add(btRecalcularVotos);
        painel.add(btRegistrarPost);
        painelGeral.add(painel);
        add(painelGeral);
    }
    private void calcularVotos(){
        postTO = new PostTO();
        try {
            int upVotes = Integer.parseInt(JOptionPane.showInputDialog("Quantos votos UP recebeu?"));
            postTO.addUpVote(upVotes);
            int downVotes = Integer.parseInt(JOptionPane.showInputDialog("Quantos votos DOWN recebeu?"));
            postTO.addDownVote(downVotes);
            postTO.setNumSaldoVotes();
        } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        tfNumUpVotes.setText(String.format("%d", postTO.getNumUpVotes()));
        tfNumDownVotes.setText(String.format("%d", postTO.getNumDownVotes()));
        tfNumSaldoVotes.setText(String.format("%d", postTO.getNumSaldoVotes()));
    }

    private void definirEventos(UsuarioTO usuarioTO) {
        btRegistrarPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                postController = new PostController();
                rankingUsuarioTO = new RankingUsuarioTO();
                rankingUsuarioController = new RankingUsuarioController();
                usuarioController = new UsuarioController();

                try {
                    rankingUsuarioTO = rankingUsuarioController.pegarUmRankingUsuario(usuarioTO.getIdUsuario());
                    postTO.setDsPost(tfDsPost.getText());
                    postTO.setIdPost(postController.criarIdPost());
                    postTO.setIdUsuario(usuarioTO.getIdUsuario());
                    postTO.setDtPost(LocalDateTime.now());

                    String resultado = postController.inserirPost(postTO.getIdPost(), postTO.getIdUsuario(), postTO.getDsPost(), postTO.getDtPost(), postTO.getNumUpVotes(), postTO.getNumDownVotes());
                    if (rankingUsuarioTO != null) {
                        rankingUsuarioTO.addQtVotos(postTO.getNumSaldoVotes());
                        rankingUsuarioController.alterarRankingUsuario(rankingUsuarioTO.getIdRanking(), rankingUsuarioTO.getIdUsuario(), rankingUsuarioTO.getQtVotos());
                    } else {
                        rankingUsuarioTO = new RankingUsuarioTO(rankingUsuarioController.criarIdRanking(), usuarioTO.getIdUsuario(),
                                postTO.getNumSaldoVotes());
                        rankingUsuarioController.inserirRankingUsuario(rankingUsuarioTO.getIdRanking(), rankingUsuarioTO.getIdUsuario(), rankingUsuarioTO.getQtVotos());
                    }
                    usuarioTO.registrarAtividade(postTO.detalhesPost());
                    usuarioController.alterarUsuario(usuarioTO.getIdUsuario(), usuarioTO.getNmUsuario(), usuarioTO.getVlMerito(), usuarioTO.getQtSoulCoins(), usuarioTO.getDsAtividadeRecente(), usuarioTO.getSelosGanhos());
                    JOptionPane.showMessageDialog(null, resultado, "Posts", JOptionPane.INFORMATION_MESSAGE);
                    GUIPostView.this.dispose();
                    return;

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btRecalcularVotos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                calcularVotos();
            }
        });
    }
}
