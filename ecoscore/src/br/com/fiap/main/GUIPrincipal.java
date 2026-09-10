package br.com.fiap.main;

import br.com.fiap.dto.*;
import br.com.fiap.dao.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class GUIPrincipal extends JFrame {
    private Usuario usuario = new Usuario();
    private Acao acao;
    private Missao missao;
    private Post post;
    private Quiz quiz;
    private CalculadoraPontos calculadora = new CalculadoraPontos();
    private UsuarioDAO usuarioDAO;
    private AcaoDAO acaoDAO;
    private MissaoDAO missaoDAO;
    private QuizDAO quizDAO;
    private PostDAO postDAO;
    private RankingUsuarioDAO rankingUsuarioDAO;
    private Container contentPane;
    public JMenuBar mnBarra;
    public JMenu mnArquivo, mnUsuario,mnMissao,mnAcao,mnPost,mnQuiz,mnAjuda;
    public JMenuItem miSair, miAjuda, miEditarExemplo,miLimparAtividades,miLimparSelos,miCriarUsuario,miLerUsuario,miAtualizarUsuario,miExcluirUsuario,miCriarMissao,miLerMissao,miAtualizarMissao,miExcluirMissao,miCriarAcao,miLerAcao,miAtualizarAcao,miExcluirAcao,miCriarPost,miLerPost,miAtualizarPost,miExcluirPost,miCriarQuiz,miLerQuiz,miAtualizarQuiz,miExcluirQuiz;
    private JPanel painel, painelBts, painelTxt;
    private JButton btRegistrarAcao, btRegistrarConquista, btRegistrarQuiz, btRegistrarPost, btRegistrarPenalidade, btSair;
    private JPopupMenu teste;
    private JTextArea taDetalhesPerfil = new JTextArea(usuario.detalhesPerfil());
    private JLabel lbPerfil;

    public GUIPrincipal() {
        iniciarValores();
        inicializarComponentes();
        definirEventos();
    }

    public void iniciarValores(){
        try (Connection con = ConnectionFactory.abrirConexao()){
            usuarioDAO = new UsuarioDAO(con);
            usuario = usuarioDAO.pegarUm("dragonborn123"); //como se fosse um login
            System.out.println(usuario);
            acao = new Acao();
            missao = new Missao();
            acaoDAO = new AcaoDAO(con);
            missaoDAO = new MissaoDAO(con);
            acao = acaoDAO.pegarUm(4);
            usuario.registrarAtividade(acao.detalhesAcao(), acao.getQtPontosGerados());
            missao = missaoDAO.pegarUm(0);
            usuario.registrarAtividade(missao.detalhesMissao(),missao.getQtPontosGerados(),missao.getSelo());
            post = new Post(70,"dragonborn123","Veja esse post de M. Souza, onde ele posta sobre suas ações sociais mais recentes.");
            post.addUpVote(57);
            post.addDownVote(13);
            usuario.registrarAtividade(post.detalhesPost());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL:" + e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void inicializarComponentes() {
        setTitle("Ecoscore - SoulUp");
        setBounds(0, 0, 900, 300);
        contentPane = getContentPane();
        mnBarra = new JMenuBar();
        mnArquivo = new JMenu("Arquivo");
        mnUsuario = new JMenu("Usuário");
        mnMissao = new JMenu("Missão");
        mnAcao = new JMenu("Ação");
        mnPost = new JMenu("Post");
        mnQuiz = new JMenu("Quiz");
        mnAjuda = new JMenu("Ajuda");

        miSair = new JMenuItem("Sair");
        miLimparAtividades = new JMenuItem("Limpar atividades");
        miLimparSelos = new JMenuItem("Limpar selos");
        miAjuda = new JMenuItem("Tutorial");
        miCriarUsuario = new JMenuItem("Criar usuário");
        miLerUsuario = new JMenuItem("Ler usuário");
        miAtualizarUsuario = new JMenuItem("Atualizar usuário");
        miExcluirUsuario = new JMenuItem("Excluir usuário");

        miCriarMissao = new JMenuItem("Criar missão");
        miLerMissao = new JMenuItem("Ler missão");
        miAtualizarMissao = new JMenuItem("Atualizar missão");
        miExcluirMissao = new JMenuItem("Excluir missão");

        miCriarAcao = new JMenuItem("Criar ação");
        miLerAcao = new JMenuItem("Ler ação");
        miAtualizarAcao = new JMenuItem("Atualizar ação");
        miExcluirAcao = new JMenuItem("Excluir ação");

        miCriarPost = new JMenuItem("Criar post");
        miLerPost = new JMenuItem("Ler post");
        miAtualizarPost = new JMenuItem("Atualizar post");
        miExcluirPost = new JMenuItem("Excluir post");
        miCriarQuiz = new JMenuItem("Criar quiz");
        miLerQuiz = new JMenuItem("Ler quiz");
        miAtualizarQuiz = new JMenuItem("Atualizar quiz");
        miExcluirQuiz = new JMenuItem("Excluir quiz");
        miEditarExemplo = new JMenuItem("Editar exemplo");

        painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painelBts = new JPanel();
        painelBts.setLayout(new FlowLayout());
        painelTxt = new JPanel();
        btRegistrarAcao = new JButton("Registrar ação");
        btRegistrarConquista = new JButton("Registrar missão");
        btRegistrarQuiz = new JButton("Registrar quiz");
        btRegistrarPost = new JButton("Registrar post");
        btRegistrarPenalidade = new JButton("Registrar penalidade");
        taDetalhesPerfil = new JTextArea(usuario.detalhesPerfil());
        taDetalhesPerfil.setEditable(false);
        lbPerfil = new JLabel("Exemplo de perfil:");
        taDetalhesPerfil.add(lbPerfil);

        setJMenuBar(mnBarra);
        mnBarra.add(mnArquivo);
        mnBarra.add(mnUsuario);
        mnBarra.add(mnMissao);
        mnBarra.add(mnAcao);
        mnBarra.add(mnPost);
        mnBarra.add(mnQuiz);
        mnBarra.add(mnAjuda);
        teste = new JPopupMenu("teste");

        mnArquivo.add(miEditarExemplo);
        mnArquivo.add(miLimparAtividades);
        mnArquivo.add(miLimparSelos);
        mnArquivo.add(miSair);
        mnUsuario.add(miCriarUsuario);
        mnUsuario.add(miLerUsuario);
        mnUsuario.add(miAtualizarUsuario);
        mnUsuario.add(miExcluirUsuario);
        mnMissao.add(miCriarMissao);
        mnMissao.add(miLerMissao);
        mnMissao.add(miAtualizarMissao);
        mnMissao.add(miExcluirMissao);
        mnAcao.add(miCriarAcao);
        mnAcao.add(miLerAcao);
        mnAcao.add(miAtualizarAcao);
        mnAcao.add(miExcluirAcao);
        mnPost.add(miCriarPost);
        mnPost.add(miLerPost);
        mnPost.add(miAtualizarPost);
        mnPost.add(miExcluirPost);
        mnQuiz.add(miCriarQuiz);
        mnQuiz.add(miLerQuiz);
        mnQuiz.add(miAtualizarQuiz);
        mnQuiz.add(miExcluirQuiz);
        mnAjuda.add(miAjuda);

        painelBts.add(btRegistrarAcao);
        painelBts.add(btRegistrarConquista);
        painelBts.add(btRegistrarQuiz);
        painelBts.add(btRegistrarPost);
        painelBts.add(btRegistrarPenalidade);

        painelTxt.add(lbPerfil);
        painelTxt.add(taDetalhesPerfil);

        painel.add(painelTxt);
        painel.add(painelBts);
        add(painel);
    }
    private void definirEventos() {
        miSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        miAjuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null,
                        "Esse protótipo registra ações sustentáveis e penalidades pra um usuário, mostrando na tela alterações\n\nAções possíveis:\nNormal - representa ações reais\nConquista - representa uma missão realizada\nPost - representa um post encerrado\nQuiz - representa um quiz de uma missão\n\nTipos de ação:\nNatureza - ações de plantio e natureza\nÁgua - ações de economia de água\nCarbono - relacionadas a crédit de carbono e automoveis\nReciclagem - relacionadas a reciclagem\n\nPenalidade:\nUma opção que reduz a confiabilidade do usuário, normalmente quando ele comete uma infração",
                        "Ajuda", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        miEditarExemplo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection con = ConnectionFactory.abrirConexao()){
                    String nome = JOptionPane.showInputDialog("Digite o novo nome do usuário:");
                    float confiabilidade = Float.parseFloat(JOptionPane.showInputDialog("Digite a nova confiabilidade desse usuário (0-100):"));

                    int soulCoins = Integer.parseInt(JOptionPane.showInputDialog("Quantos Soul Coins ele terá agora?"));

                    if (JOptionPane.showConfirmDialog(null,
                                             "Isso editará o exemplo na tela e atualizará o mesmo usuário no banco, você tem certeza disso?",
                                                 "Confirme",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
                        usuarioDAO = new UsuarioDAO(con);
                        usuario.setNmUsuario(nome);
                        usuario.setVlMerito(confiabilidade);
                        usuario.setQtSoulCoins(soulCoins);

                        JOptionPane.showMessageDialog(null,usuarioDAO.alterar(usuario));
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
                taDetalhesPerfil.setText(usuario.detalhesPerfil());
            }
        });
        miLimparAtividades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (JOptionPane.showConfirmDialog(null, "Tem certa? Isso apagará toda a Atividade Recente do perfil do usuário!","Confirme",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
                    ArrayList<String> atividadesVazio = new ArrayList<>();
                    usuario.setDsAtividadeRecente(atividadesVazio);
                }
                taDetalhesPerfil.setText(usuario.detalhesPerfil());
            }
        });
        miLimparSelos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (JOptionPane.showConfirmDialog(null, "Tem certa? Isso apagará todos os selos ganhos do perfil do usuário!","Confirme",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
                    ArrayList<String> selosVazio = new ArrayList<>();
                    usuario.setSelosGanhos(selosVazio);
                }
                taDetalhesPerfil.setText(usuario.detalhesPerfil());
            }
        });
        btRegistrarAcao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int escolha = Integer.parseInt(JOptionPane.showInputDialog("Digite o tipo de ação:\n1. Natureza\n2. Carbono\n3. Água\n4. Reciclagem\n0. cancelar ação"));
                    acao = new Acao(JOptionPane.showInputDialog("Digite o nome da ação: "));
                    switch (escolha) {
                        case 1:
                            int dificuldade = Integer.parseInt(JOptionPane.showInputDialog("Qual é a dificuldade da ação realizada? Digite (entre 1 a 5): "));
                            usuario.registrarAtividade(acao.detalhesAcao(),
                                                       acao.registrarPontos(calculadora.pontosNatureza(dificuldade)));
                            break;
                        case 2:
                            float kgCarbono = Integer.parseInt(JOptionPane.showInputDialog("Qual é a qtde. de carbono? Digite (um número): "));
                            usuario.registrarAtividade(acao.detalhesAcao(),
                                                       acao.registrarPontos(calculadora.pontosCarbono(kgCarbono)));
                            break;
                        case 3:
                            float litros = Integer.parseInt(JOptionPane.showInputDialog("Qual é a qtde. de litros economizados? Digite (um número): "));
                            usuario.registrarAtividade(acao.detalhesAcao(),
                                                       acao.registrarPontos(calculadora.pontosAgua(litros)));
                            break;
                        case 4:
                            float kgReciclados = Integer.parseInt(JOptionPane.showInputDialog("Qual é a qtde. de KG reciclados? Digite (um número): "));
                            usuario.registrarAtividade(acao.detalhesAcao(),
                                                       acao.registrarPontos(calculadora.pontosReciclagem(kgReciclados)));
                            break;
                        case 0:
                            break;
                        default:
                            throw new Exception("Opção inválida (0-4)");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }

                taDetalhesPerfil.setText(usuario.detalhesPerfil());
            }
        });
        btRegistrarConquista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Missao missao;
                try {
                    missao = new Missao(Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da missão (máx. 20 caracteres): ")),
                                      JOptionPane.showInputDialog("Digite o nome da conquista: "),
                                      JOptionPane.showInputDialog("Qual a descrição da conquista? (máx. 50 caracteres): "),
                                      JOptionPane.showInputDialog("Qual o nome do selo que a missão vai dar? (máx. 10 caracteres): "),
                                      Integer.parseInt(JOptionPane.showInputDialog("Digite quantos pontos essa missão vai gerar: ")));
                    usuario.registrarAtividade(missao.getNmMissao(), missao.getQtPontosGerados(), missao.getSelo());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
                taDetalhesPerfil.setText(usuario.detalhesPerfil());
            }
        });
        btRegistrarQuiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Quiz quiz;
                try {
                    quiz = new Quiz(Integer.parseInt(JOptionPane.showInputDialog("Digite o id do quiz: ")),
                                    usuario.getIdUsuario(),
                                    Integer.parseInt(JOptionPane.showInputDialog("Digite o num. de questões desse quiz: ")),
                                    Integer.parseInt(JOptionPane.showInputDialog("Digite o num. de acertos desse quiz: ")),
                                    Integer.parseInt(JOptionPane.showInputDialog("Digite o num. de pontos por questão: ")));
                    usuario.registrarAtividade(quiz.detalhesQuiz(), quiz.getQtPontosGerados());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
                taDetalhesPerfil.setText(usuario.detalhesPerfil());
            }
        });
        btRegistrarPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Post post;
                try {
                    post = new Post(Integer.parseInt(JOptionPane.showInputDialog("Digite o id do post: ")), usuario.getIdUsuario(), "t");
                    usuario.registrarAtividade(post.detalhesPost());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
                taDetalhesPerfil.setText(usuario.detalhesPerfil());
            }
        });
        btRegistrarPenalidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int valorPenalidade = Integer.parseInt(JOptionPane.showInputDialog("Digite o valor da penalidade de confiabilidade (0-100, a confiabilidade não cairá abaixo de 0): "));
                    String descOcorrido = JOptionPane.showInputDialog("Descreva o ocorrido: ");
                    //Mensagem de confirmação mostrando o texto e confiabilidade que será tirada.
                    if (JOptionPane.showConfirmDialog(null,
                            String.format("Tem certeza que quer retirar %d de confiabilidade do usuário %s com a descrição:\n%s", valorPenalidade, usuario.getNmUsuario(), descOcorrido), "Confirme",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                        usuario.registrarPenalidade(valorPenalidade, descOcorrido);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }

                taDetalhesPerfil.setText(usuario.detalhesPerfil());
            }
        });
    }
    public static void main(String[] args) {
        GUIPrincipal frame = new GUIPrincipal();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((tela.width - frame.getSize().width) / 2,
                (tela.height - frame.getSize().height) / 2);
        frame.setVisible(true);
    }
}