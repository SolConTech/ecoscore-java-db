package br.com.fiap.main;

import br.com.fiap.dto.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class GUIPrincipal extends JFrame {
    private Usuario usuario = new Usuario("Murilo", "@murilosouza", 80, 220);
    private Acao acao;
    private AcaoConquista acaoConquista;
    private AcaoPost acaoPost;
    private AcaoQuiz acaoQuiz;
    private CalculadoraPontos calculadora = new CalculadoraPontos();
    private Container contentPane;
    private JMenuBar mnBarra;
    private JMenu mnSistema;
    private JMenuItem miSair, miAjuda, miEditarUsuario;
    private JPanel painel, painelBts, painelTxt;
    private JButton btRegistrarAcao, btRegistrarConquista, btRegistrarQuiz, btRegistrarPost, btRegistrarPenalidade, btSair;
    private JTextArea taDetalhesPerfil = new JTextArea(usuario.detalhesPerfil());
    private JLabel lbPerfil;

    public GUIPrincipal() {
        iniciarValores();
        inicializarComponentes();
        definirEventos();
    }
    public void iniciarValores() {
        try {
            String nome = JOptionPane.showInputDialog("Digite o nome do usuário:\n(Poderá ser alterado mais tarde)");
            usuario.setNome(nome);
            String id = JOptionPane.showInputDialog("Digite o id do usuário:\n(Poderá ser alterado mais tarde)");
            usuario.setIdUsuario(id);
            float confiabilidade = Float.parseFloat(JOptionPane.showInputDialog("Digite a confiabilidade desse usuário (0-100):\n(Poderá ser alterado mais tarde)"));
            usuario.setVlMerito(confiabilidade);
            int soulCoins = Integer.parseInt(JOptionPane.showInputDialog("Quantos Soul Coins ele tem?\n(Poderá ser alterado mais tarde)"));
            usuario.setQtSoulCoins(soulCoins);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
        }
        acao = new Acao("3kg de lixo reciclados");
        usuario.registrarAcao(acao.registrarPontos(calculadora.pontosReciclagem(3)), acao.detalhes());
        acao = new Acao("100L de água economizados");
        usuario.registrarAcao(acao.registrarPontos(calculadora.pontosAgua(100)), acao.detalhes());
        acao = new Acao("20kg de Carbono a menos no planeta");
        usuario.registrarAcao(acao.registrarPontos(calculadora.pontosCarbono(20)), acao.detalhes());
        acao = new Acao("Compra de uma muda de árvore");
        usuario.registrarAcao(acao.registrarPontos(calculadora.pontosNatureza(2)), acao.detalhes());
    }
    private void inicializarComponentes() {
        setTitle("Ecoscore - SoulUp");
        setBounds(0, 0, 900, 300);
        contentPane = getContentPane();
        mnBarra = new JMenuBar();
        mnSistema = new JMenu("Sistema");
        mnSistema.setMnemonic('S');
        miSair = new JMenuItem("Sair");
        miAjuda = new JMenuItem("Ajuda");
        miEditarUsuario = new JMenuItem("Editar usuário");
        painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painelBts = new JPanel();
        painelBts.setLayout(new FlowLayout());
        painelTxt = new JPanel();
        btRegistrarAcao = new JButton("Registrar Ação simples");
        btRegistrarConquista = new JButton("Registrar Conquista ganha");
        btRegistrarQuiz = new JButton("Registrar Quiz feito");
        btRegistrarPost = new JButton("Registrar Post encerrado");
        btRegistrarPenalidade = new JButton("Registrar penalidade");
        taDetalhesPerfil = new JTextArea(usuario.detalhesPerfil());
        taDetalhesPerfil.setEditable(false);
        lbPerfil = new JLabel("Exemplo de pefil:");
        taDetalhesPerfil.add(lbPerfil);

        setJMenuBar(mnBarra);
        mnBarra.add(mnSistema);
        mnSistema.add(miSair);
        mnSistema.add(miAjuda);
        mnSistema.add(miEditarUsuario);

        painelBts.add(btRegistrarAcao);
        painelBts.add(btRegistrarConquista);
        painelBts.add(btRegistrarQuiz);
        painelBts.add(btRegistrarPost);
        painelBts.add(btRegistrarPenalidade);
        painelTxt.add(lbPerfil);
        painelTxt.add(taDetalhesPerfil);
        painel.add(painelTxt, BorderLayout.LINE_END);
        painel.add(painelBts, BorderLayout.LINE_START);
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
        miEditarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String nome = JOptionPane.showInputDialog("Digite o novo nome do usuário:");
                    usuario.setNome(nome);
                    String id = JOptionPane.showInputDialog("Digite o novo id do usuário:");
                    usuario.setIdUsuario(id);
                    float confiabilidade = Float.parseFloat(JOptionPane.showInputDialog("Digite a nova confiabilidade desse usuário (0-100):"));
                    usuario.setVlMerito(confiabilidade);
                    int soulCoins = Integer.parseInt(JOptionPane.showInputDialog("Quantos Soul Coins ele terá agora?"));
                    usuario.setQtSoulCoins(soulCoins);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
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
                            usuario.registrarAcao(acao.registrarPontos(calculadora.pontosNatureza(dificuldade)),
                                    acao.detalhes());
                            break;
                        case 2:
                            float kgCarbono = Integer.parseInt(JOptionPane.showInputDialog("Qual é a qtde. de carbono? Digite (um número): "));
                            usuario.registrarAcao(acao.registrarPontos(calculadora.pontosCarbono(kgCarbono)),
                                    acao.detalhes());
                            break;
                        case 3:
                            float litros = Integer.parseInt(JOptionPane.showInputDialog("Qual é a qtde. de litros economizados? Digite (um número): "));
                            usuario.registrarAcao(acao.registrarPontos(calculadora.pontosAgua(litros)),
                                    acao.detalhes());
                            break;
                        case 4:
                            float kgReciclados = Integer.parseInt(JOptionPane.showInputDialog("Qual é a qtde. de KG reciclados? Digite (um número): "));
                            usuario.registrarAcao(acao.registrarPontos(calculadora.pontosReciclagem(kgReciclados)),
                                    acao.detalhes());
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
                AcaoConquista acao;
                try {
                    int escolha = Integer.parseInt(JOptionPane.showInputDialog("Digite o tipo de conquista:\n1. Natureza\n2. Carbono\n3. Água\n4. Reciclagem\n0. cancelar ação"));
                    acao = new AcaoConquista(JOptionPane.showInputDialog("Digite o nome da conquista: "), Integer.parseInt(JOptionPane.showInputDialog("Qual é a raridade dessa conquista? Digite (número de 1 a 4): ")));
                    switch (escolha) {
                        case 1:
                            int dificuldade = Integer.parseInt(JOptionPane.showInputDialog("Qual é a dificuldade da conquista realizada? Digite (entre 1 a 5): "));
                            usuario.registrarAcao(acao.registrarPontos(calculadora.pontosNatureza(dificuldade)),
                                    acao.detalhes());
                            break;
                        case 2:
                            float kgCarbono = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de carbono? Digite (um número): "));
                            usuario.registrarAcao(acao.registrarPontos(calculadora.pontosCarbono(kgCarbono)),
                                    acao.detalhes());
                            break;
                        case 3:
                            float litros = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de litros economizados? Digite (um número): "));
                            usuario.registrarAcao(acao.registrarPontos(calculadora.pontosAgua(litros)),
                                    acao.detalhes());
                            break;
                        case 4:
                            float kgReciclados = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de KG reciclados? Digite (um número): "));
                            usuario.registrarAcao(acao.registrarPontos(calculadora.pontosReciclagem(kgReciclados)),
                                    acao.detalhes());
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
        btRegistrarQuiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AcaoQuiz acao;
                try {
                    int escolha = Integer.parseInt(JOptionPane.showInputDialog("Digite o tipo de quiz:\n1. Natureza\n2. Carbono\n3. Água\n4. Reciclagem\n5. sem ação (Pontos apenas por resposta)\n0. Cancelar ação"));
                    acao = new AcaoQuiz(JOptionPane.showInputDialog("Digite o nome do quiz: "),
                            Integer.parseInt(JOptionPane.showInputDialog("Digite a qtde. de acertos nesse quiz: ")),
                            Integer.parseInt(JOptionPane.showInputDialog("Digite o valor do quiz, a qtde. pontos por acerto: ")));
                    switch (escolha) {
                        case 1:
                            int dificuldade = Integer.parseInt(JOptionPane.showInputDialog("Qual é a dificuldade da ação atrelada ao quiz realizado? Digite (entre 1 a 5): "));
                            usuario.registrarAcao(acao.registrarPontos(calculadora.pontosNatureza(dificuldade)),
                                    acao.detalhes());
                            break;
                        case 2:
                            float kgCarbono = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de carbono atrelada? Digite (um número): "));
                            usuario.registrarAcao(acao.registrarPontos(calculadora.pontosCarbono(kgCarbono)),
                                    acao.detalhes());
                            break;
                        case 3:
                            float litros = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de litros atrelaods? Digite (um número): "));
                            usuario.registrarAcao(acao.registrarPontos(calculadora.pontosAgua(litros)),
                                    acao.detalhes());
                            break;
                        case 4:
                            float kgReciclados = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de KG reciclados atrelados? Digite (um número): "));
                            usuario.registrarAcao(acao.registrarPontos(calculadora.pontosReciclagem(kgReciclados)),
                                    acao.detalhes());
                            break;
                        case 5:
                            usuario.registrarAcao(acao.registrarPontos(), acao.detalhes());
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
        btRegistrarPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AcaoPost acao;
                try {
                    int escolha = Integer.parseInt(JOptionPane.showInputDialog("Digite o tipo de post:\n1. Natureza\n2. Carbono\n3. Água\n4. Reciclagem\n0. Cancelar ação"));
                    acao = new AcaoPost(JOptionPane.showInputDialog("Digite o nome do quiz: "),
                            Integer.parseInt(JOptionPane.showInputDialog("Digite a posição do post no ranking: ")),
                            Integer.parseInt(JOptionPane.showInputDialog("Digite o saldo de votos do post: ")));
                    switch (escolha) {
                        case 1:
                            int dificuldade = Integer.parseInt(JOptionPane.showInputDialog("Qual é a dificuldade da ação atrelada ao post? Digite (entre 1 a 5): "));
                            usuario.registrarAcao(acao.registrarPontos(calculadora.pontosNatureza(dificuldade)),
                                    acao.detalhes());
                            break;
                        case 2:
                            float kgCarbono = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de carbono atrelada? Digite (um número): "));
                            usuario.registrarAcao(acao.registrarPontos(calculadora.pontosCarbono(kgCarbono)),
                                    acao.detalhes());
                            break;
                        case 3:
                            float litros = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de litros atrelaods? Digite (um número): "));
                            usuario.registrarAcao(acao.registrarPontos(calculadora.pontosAgua(litros)),
                                    acao.detalhes());
                            break;
                        case 4:
                            float kgReciclados = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de KG reciclados atrelados? Digite (um número): "));
                            usuario.registrarAcao(acao.registrarPontos(calculadora.pontosReciclagem(kgReciclados)),
                                    acao.detalhes());
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
        btRegistrarPenalidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int valorPenalidade = Integer.parseInt(JOptionPane.showInputDialog("Digite o valor da penalidade de confiabilidade (0-100, a confiabilidade não cairá abaixo de 0): "));
                    String descOcorrido = JOptionPane.showInputDialog("Descreva o ocorrido: ");
                    //Mensagem de confirmação mostrando o texto e confiabilidade que será tirada.
                    if (JOptionPane.showConfirmDialog(null,
                            String.format("Tem certeza que quer retirar %d de confiabilidade do usuário %s com a descrição:\n%s", valorPenalidade, usuario.getNome(), descOcorrido), "Confirme",
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