package br.com.fiap.view.gui;

import br.com.fiap.controller.*;
import br.com.fiap.model.dto.*;

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

    private Post post;
    private PostController postController;
    private UsuarioController usuarioController;
    private RankingUsuario rankingUsuario;
    private RankingUsuarioController rankingUsuarioController;

    public GUIPostView(Usuario usuario) {
        inicializarComponentes();
        calcularVotos();
        definirEventos(usuario);
    }

    private void inicializarComponentes() {
        setTitle("Ecoscore - Registrar post");
        setBounds(0, 0, 500, 300);
        contentPane = getContentPane();

        painelGeral = new JPanel();
        painelGeral.setLayout(new FlowLayout());
        painel = new JPanel();
        painel.setLayout(new GridLayout(6,0));

        lbDsPost = new JLabel("Descrição do post:");
        tfDsPost = new JTextArea("Descrição do post");
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
        post = new Post();
        try {
            int upVotes = Integer.parseInt(JOptionPane.showInputDialog("Quantos votos UP recebeu?"));
            post.addUpVote(upVotes);
            int downVotes = Integer.parseInt(JOptionPane.showInputDialog("Quantos votos DOWN recebeu?"));
            post.addDownVote(downVotes);
            post.setNumSaldoVotes();
        } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        tfNumUpVotes.setText(String.format("%d", post.getNumUpVotes()));
        tfNumDownVotes.setText(String.format("%d", post.getNumDownVotes()));
        tfNumSaldoVotes.setText(String.format("%d", post.getNumSaldoVotes()));
    }

    private void definirEventos(Usuario usuario) {
        btRegistrarPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                postController = new PostController();
                rankingUsuario = new RankingUsuario();
                rankingUsuarioController = new RankingUsuarioController();
                usuarioController = new UsuarioController();

                try {
                    rankingUsuario = rankingUsuarioController.pegarUmRankingUsuario(usuario.getIdUsuario());
                    post.setDsPost(tfDsPost.getText());
                    post.setIdPost(postController.criarIdPost());
                    post.setIdUsuario(usuario.getIdUsuario());
                    post.setDtPost(LocalDateTime.now());

                    String resultado = postController.inserirPost(post.getIdPost(), post.getIdUsuario(), post.getDsPost(), post.getDtPost(), post.getNumUpVotes(), post.getNumDownVotes());
                    if (rankingUsuario != null) {
                        rankingUsuario.addQtVotos(post.getNumSaldoVotes());
                        rankingUsuarioController.alterarRankingUsuario(rankingUsuario.getIdRanking(), rankingUsuario.getIdUsuario(), rankingUsuario.getQtVotos());
                    } else {
                        rankingUsuario = new RankingUsuario(rankingUsuarioController.criarIdRanking(),usuario.getIdUsuario(),
                                post.getNumSaldoVotes());
                        rankingUsuarioController.inserirRankingUsuario(rankingUsuario.getIdRanking(), rankingUsuario.getIdUsuario(), rankingUsuario.getQtVotos());
                    }
                    usuario.registrarAtividade(post.detalhesPost());
                    usuarioController.alterarUsuario(usuario.getIdUsuario(), usuario.getNmUsuario(), usuario.getVlMerito(), usuario.getQtSoulCoins(),usuario.getDsAtividadeRecente(),usuario.getSelosGanhos());
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
