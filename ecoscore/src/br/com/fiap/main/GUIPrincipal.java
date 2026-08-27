package br.com.fiap.main;

import br.com.fiap.bean.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.JTextComponent;

@SuppressWarnings("serial")
public class GUIPrincipal extends JFrame{
    private Usuario usuario = new Usuario("Murilo Souza","@murilo.souza",80, 220);
    private Acao acao;
    private AcaoConquista acaoConquista;
    private AcaoPost acaoPost;
    private AcaoQuiz acaoQuiz;
    private CalculadoraPontos calculadora = new CalculadoraPontos();
    private Container contentPane;
    private JMenuBar mnBarra;
    private JMenu mnSistema;
    private JMenuItem miSair, miAjuda;
    private JPanel painel;
    private JButton btRegistrarAcao, btRegistrarConquista, btRegistrarQuiz, btRegistrarPost, btRegistrarPenalidade, btSair;
    private JTextArea taDetalhesPerfil = new JTextArea(usuario.detalhesPerfil());

    public GUIPrincipal(){
        iniciarValores();
        inicializarComponentes();
        definirEventos();
    }
    public void iniciarValores(){
        acao = new Acao("3kg de lixo reciclados");
        usuario.registrarAcao(acao.registrarPontos(calculadora.pontosReciclagem(3)), acao.detalhes());
        acao = new Acao("100L de água economizados");
        usuario.registrarAcao(acao.registrarPontos(calculadora.pontosAgua(100)), acao.detalhes());
        acao = new Acao("20kg de Carbono a menos no planeta");
        usuario.registrarAcao(acao.registrarPontos(calculadora.pontosCarbono(20)), acao.detalhes());
        acao = new Acao("Compra de uma muda de árvore");
        usuario.registrarAcao(acao.registrarPontos(calculadora.pontosNatureza(2)), acao.detalhes());

    }
    private void inicializarComponentes(){
        setTitle("Ecoscore - SoulUp");
        setBounds(0,0,800,400);
        contentPane = getContentPane();
        mnBarra = new JMenuBar();
        mnSistema = new JMenu("Sistema");
        mnSistema.setMnemonic('S');
        miSair = new JMenuItem("Sair");
        miAjuda = new JMenuItem("Ajuda");
        painel = new JPanel();
        painel.setLayout(new FlowLayout());
        btRegistrarAcao = new JButton("Registrar Ação simples");
        btRegistrarConquista = new JButton("Registrar Conquista ganha");
        btRegistrarQuiz = new JButton("Registrar Quiz feito");
        btRegistrarPost = new JButton("Registrar Post encerrado");
        btRegistrarPenalidade = new JButton("Registrar penalidade");
        taDetalhesPerfil = new JTextArea(usuario.detalhesPerfil());
        taDetalhesPerfil.setEditable(false);

        setJMenuBar(mnBarra);
        mnBarra.add(mnSistema);
        mnSistema.add(miSair);
        mnSistema.add(miAjuda);

        painel.add(btRegistrarAcao);
        painel.add(btRegistrarConquista);
        painel.add(btRegistrarQuiz);
        painel.add(btRegistrarPost);
        painel.add(btRegistrarPenalidade);
        painel.add(taDetalhesPerfil);
        add(painel);
    }
    private void definirEventos(){
        miSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        miAjuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Texto de ajuda", "Ajuda", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        btRegistrarAcao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int dificuldade = Integer.parseInt(JOptionPane.showInputDialog("Qual é a dificuldade da ação realizada? Digite (entre 1 a 5): "));
                usuario.registrarAcao(acao.registrarPontos(calculadora.pontosNatureza(dificuldade)),
                        acao.detalhes());

                taDetalhesPerfil.setText(usuario.detalhesPerfil());
            }
        });
    }
    public static void main(String[] args) {
        GUIPrincipal frame = new GUIPrincipal();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((tela.width - frame.getSize().width) / 2 ,
                (tela.height - frame.getSize().height) / 2);
        frame.setVisible(true);



    }
}