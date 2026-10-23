package br.com.fiap.view.gui;

import br.com.fiap.controller.*;
import br.com.fiap.to.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class GUIMainView extends JFrame {
    private UsuarioTO usuarioTO = new UsuarioTO();
    private AcaoTO acaoTO;
    private MissaoTO missaoTO;
    private PostTO postTO;
    private QuizTO quizTO;
    private CalculadoraPontos calculadora = new CalculadoraPontos();
    private RankingUsuarioTO ranking;
    private UsuarioController usuarioController;
    private AcaoController acaoController;
    private MissaoController missaoController;
    private QuizController quizController;
    private PostController postController;
    private RankingUsuarioController rankingUsuarioController;
    private Container contentPane;
    public JMenuBar mnBarra;
    private JMenu mnArquivo, mnUsuario,mnMissao,mnAcao,mnPost,mnQuiz,mnAjuda;
    private JMenuItem miSair, miAjuda, miAjuda2, miEditarExemplo,miTrocarUsuario,miLimparAtividades,miLimparSelos,miCriarUsuario,miLerUsuario,miAtualizarUsuario,miExcluirUsuario,miCriarMissao,miLerMissao,miAtualizarMissao,miExcluirMissao,miCriarAcao,miLerAcao,miAtualizarAcao,miExcluirAcao,miCriarPost,miLerPost,miAtualizarPost,miExcluirPost,miCriarQuiz,miLerQuiz,miAtualizarQuiz,miExcluirQuiz;
    private JPanel painel, painelBts, painelTxt;
    private JButton btRegistrarAcao, btRegistrarMissao, btRegistrarQuiz, btRegistrarPost, btRegistrarPenalidade, btRanking;
    private JTextArea taDetalhesPerfil = new JTextArea(usuarioTO.detalhesPerfil());
    private JLabel lbPerfil;

    public GUIMainView() {
        iniciarUsuario();
        inicializarComponentes();
        definirEventos();
    }

    public void iniciarUsuario(){
        try {
            usuarioController = new UsuarioController();

            usuarioTO.setIdUsuario("dragonborn123");
            usuarioTO = usuarioController.pegarUmUsuario(usuarioTO.getIdUsuario()); //como se fosse um login

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
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
        mnPost = new JMenu("PostTO");
        mnQuiz = new JMenu("QuizTO");
        mnAjuda = new JMenu("Ajuda");

        miSair = new JMenuItem("Sair");
        miLimparAtividades = new JMenuItem("Limpar atividades");
        miLimparSelos = new JMenuItem("Limpar selos");
        miAjuda = new JMenuItem("Tutorial menus");
        miAjuda2 = new JMenuItem("Tutorial botões");
        miTrocarUsuario = new JMenuItem("Trocar usuário");
        miCriarUsuario = new JMenuItem("Criar usuário");
        miLerUsuario = new JMenuItem("Ler usuário");
        miAtualizarUsuario = new JMenuItem("Atualizar usuário");
        miExcluirUsuario = new JMenuItem("Excluir usuário");

        miCriarMissao = new JMenuItem("Criar missão");
        miLerMissao = new JMenuItem("Listar missões");
        miAtualizarMissao = new JMenuItem("Atualizar missão");
        miExcluirMissao = new JMenuItem("Excluir missão");

        miCriarAcao = new JMenuItem("Criar ação");
        miLerAcao = new JMenuItem("Listar ações");
        miAtualizarAcao = new JMenuItem("Atualizar ação");
        miExcluirAcao = new JMenuItem("Excluir ação");

        miCriarPost = new JMenuItem("Criar postTO");
        miLerPost = new JMenuItem("Listar posts");
        miAtualizarPost = new JMenuItem("Atualizar postTO");
        miExcluirPost = new JMenuItem("Excluir postTO");
        miCriarQuiz = new JMenuItem("Criar quizTO");
        miLerQuiz = new JMenuItem("Listar quizzes");
        miAtualizarQuiz = new JMenuItem("Atualizar quizTO");
        miExcluirQuiz = new JMenuItem("Excluir quizTO");
        miEditarExemplo = new JMenuItem("Editar exemplo");

        painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painelBts = new JPanel();
        painelBts.setLayout(new FlowLayout());
        painelTxt = new JPanel();
        btRegistrarAcao = new JButton("Registrar ação");
        btRegistrarMissao = new JButton("Registrar missão");
        btRegistrarQuiz = new JButton("Registrar quizTO");
        btRegistrarPost = new JButton("Registrar postTO");
        btRegistrarPenalidade = new JButton("Registrar penalidade");
        btRanking = new JButton("Ver ranking");
        taDetalhesPerfil = new JTextArea(usuarioTO.detalhesPerfil());
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

        mnArquivo.add(miEditarExemplo);
        mnArquivo.add(miLimparAtividades);
        mnArquivo.add(miLimparSelos);
        mnArquivo.add(miTrocarUsuario);
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
        mnAjuda.add(miAjuda2);

        painelBts.add(btRegistrarAcao);
        painelBts.add(btRegistrarMissao);
        painelBts.add(btRegistrarQuiz);
        painelBts.add(btRegistrarPost);
        painelBts.add(btRegistrarPenalidade);
        painelBts.add(btRanking);

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
                        "Ação registra uma atitude sustentável, gerando pontos.\n\nTipos: Natureza, Carbono, Água e Reciclagem, cada um com seu cálculo próprio\nCriar - escolhe o tipo e informa os dados\nListar - mostra todas as ações cadastradas\nAtualizar - edita uma ação existente\nExcluir - remove uma ação do banco\n\nMissão é uma conquista que gera pontos e um selo pro usuário.\n\nCriar - cadastra nome, descrição, selo e pontos gerados\nListar - mostra todas as missões cadastradas\nAtualizar - edita uma missão existente\nExcluir - remove uma missão do banco\n\nPostTO é uma publicação do usuário que recebe votos da comunidade.\n\nCriar - registra o texto e os votos (up/down)\nListar - mostra todos os posts cadastrados\nAtualizar - edita um postTO existente\nExcluir - remove um postTO do banco\n\nObs: o saldo de votos entra automaticamente no Ranking\n\nQuizTO registra o resultado de um quizTO de uma missão, gerando pontos.\n\nCriar - informa questões, acertos e pontos por questão\nListar - mostra todos os quizzes cadastrados\nAtualizar - edita um quizTO existente\nExcluir - remove um quizTO do banco\n\nRanking mostra a posição dos usuários pelo saldo de votos dos posts.\n\nVer ranking - lista os usuários do maior pro menor saldo de votos",
                        "Ajuda", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        miAjuda2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null,"TUTORIAL RÁPIDO — ECOSCORE\n\nO EcoScore transforma ações sustentáveis em uma experiência de gamificação.\n\n• REGISTRAR AÇÃO\nCadastre uma ação sustentável e receba pontos.\n\n• REGISTRAR MISSÃO\nComplete uma missão e avance no seu progresso, recebendo pontos e selos.\n\n• REGISTRAR QUIZ\nResponda ao quizTO e receba pontos de acordo com seus acertos.\n\n• REGISTRAR POST\nCompartilhe sua ação sustentável. Outros usuários podem votar no conteúdo.\n\n• REGISTRAR PENALIDADE\nRegistre uma penalidade para ações inadequadas ou repetidas.\n\n• VER RANKING\nAcompanhe sua posição e compare seu desempenho com os demais usuários.\n\nFLUXO PRINCIPAL:\nAção → Pontos → PostTO → Votos → Ranking\n\nUse os menus para consultar e gerenciar os dados do sistema.","Erro",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        miEditarExemplo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String nome = JOptionPane.showInputDialog("Digite o novo nome do usuário:");
                    float confiabilidade = Float.parseFloat(JOptionPane.showInputDialog("Digite a nova confiabilidade desse usuário (0-100):"));

                    int soulCoins = Integer.parseInt(JOptionPane.showInputDialog("Quantos Soul Coins ele terá agora?"));

                    if (JOptionPane.showConfirmDialog(null,
                            "Isso editará o exemplo na tela e atualizará o mesmo usuário no banco, você tem certeza disso?",
                            "Confirme",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
                        usuarioController = new UsuarioController();
                        usuarioTO.setNmUsuario(nome);
                        usuarioTO.setVlMerito(confiabilidade);
                        usuarioTO.setQtSoulCoins(soulCoins);

                        JOptionPane.showMessageDialog(null,usuarioController.alterarUsuario(usuarioTO.getIdUsuario(), usuarioTO.getNmUsuario(), usuarioTO.getVlMerito(), usuarioTO.getQtSoulCoins(), usuarioTO.getDsAtividadeRecente(), usuarioTO.getSelosGanhos()));
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,"Erro: "+ e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
                taDetalhesPerfil.setText(usuarioTO.detalhesPerfil());
            }
        });
        miLimparAtividades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (JOptionPane.showConfirmDialog(null, "Tem certa? Isso apagará toda a Atividade Recente (não do banco)","Confirme",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
                    usuarioTO.setDsAtividadeRecente("");
                }
                taDetalhesPerfil.setText(usuarioTO.detalhesPerfil());
            }
        });
        miLimparSelos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (JOptionPane.showConfirmDialog(null, "Tem certa? Isso apagará todos os selos (não do banco)","Confirme",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
                    usuarioTO.setSelosGanhos("");
                }
                taDetalhesPerfil.setText(usuarioTO.detalhesPerfil());
            }
        });
        miTrocarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    UsuarioTO testarSeExiste = new UsuarioTO();
                    usuarioController = new UsuarioController();

                    String novoId = JOptionPane.showInputDialog("Qual usuário você quer usar?: (dica: Liste os usuários pelo menu)");
                    testarSeExiste = usuarioController.pegarUmUsuario(novoId);
                    if (testarSeExiste != null) {
                        if (JOptionPane.showConfirmDialog(null,
                                "Usuário encontado, você tem certeza que quer mudar isso pode apagar algumas alterações!",
                                "Confirme!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0) {
                            usuarioTO = new UsuarioTO();
                            usuarioTO = usuarioController.pegarUmUsuario(novoId);
                            JOptionPane.showMessageDialog(null,"Logado como " + usuarioTO.getNmUsuario(),
                                    "Novo usuário!", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null,"Troca cancelada",
                                    "Nada acontece", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Esse usuário não existe!",
                                "Erro",JOptionPane.WARNING_MESSAGE);
                    }
                }  catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
                taDetalhesPerfil.setText(usuarioTO.detalhesPerfil());
            }
        });

        btRegistrarAcao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                    GUIAcaoView frameAcao = new GUIAcaoView(usuarioTO);
                    frameAcao.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
                    frameAcao.setLocation((tela.width - frameAcao.getSize().width) / 2,
                            (tela.height - frameAcao.getSize().height) / 2);
                    frameAcao.setVisible(true);
                    taDetalhesPerfil.setText(usuarioTO.detalhesPerfil());
            }
        });
        btRegistrarMissao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GUIMissaoView frameMissao = new GUIMissaoView(usuarioTO);
                frameMissao.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
                frameMissao.setLocation((tela.width - frameMissao.getSize().width) / 2,(tela.height - frameMissao.getSize().height) / 2);
                frameMissao.setVisible(true);
                taDetalhesPerfil.setText(usuarioTO.detalhesPerfil());

            }
        });
        btRegistrarQuiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GUIQuizView frameQuiz = new GUIQuizView(usuarioTO);
                frameQuiz.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
                frameQuiz.setLocation((tela.width - frameQuiz.getSize().width) / 2,(tela.height - frameQuiz.getSize().height) / 2);
                frameQuiz.setVisible(true);
                taDetalhesPerfil.setText(usuarioTO.detalhesPerfil());
            }
        });
        btRegistrarPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GUIPostView framePost = new GUIPostView(usuarioTO);
                framePost.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
                framePost.setLocation((tela.width - framePost.getSize().width) / 2,
                        (tela.height - framePost.getSize().height) / 2);
                framePost.setVisible(true);
                taDetalhesPerfil.setText(usuarioTO.detalhesPerfil());
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
                            String.format("Tem certeza que quer retirar %d de confiabilidade do usuário %s com a descrição:\n%s", valorPenalidade, usuarioTO.getNmUsuario(), descOcorrido), "Confirme",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                        usuarioController = new UsuarioController();
                        usuarioTO.registrarPenalidade(valorPenalidade, descOcorrido);
                        usuarioController.alterarUsuario(usuarioTO.getIdUsuario(), usuarioTO.getNmUsuario(), usuarioTO.getVlMerito(), usuarioTO.getQtSoulCoins(), usuarioTO.getDsAtividadeRecente(), usuarioTO.getSelosGanhos());
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }

                taDetalhesPerfil.setText(usuarioTO.detalhesPerfil());
            }
        });

        miCriarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    usuarioTO = new UsuarioTO();
                    usuarioController = new UsuarioController();

                    String id = JOptionPane.showInputDialog("Digite o ID do usuário: ");
                    String nome = JOptionPane.showInputDialog("Digite o nome do usuário: ");
                    float merito = Float.parseFloat(JOptionPane.showInputDialog("Digite o valor de mérito: "));
                    int soulCoins = Integer.parseInt(JOptionPane.showInputDialog("Digite a qtde. de SoulCoins: "));

                    usuarioTO.setIdUsuario(id);
                    usuarioTO.setNmUsuario(nome);
                    usuarioTO.setVlMerito(merito);
                    usuarioTO.setQtSoulCoins(soulCoins);

                    String resultado = usuarioController.inserirUsuario(usuarioTO.getIdUsuario(), usuarioTO.getNmUsuario(), usuarioTO.getVlMerito(), usuarioTO.getQtSoulCoins(), usuarioTO.getDsAtividadeRecente(), usuarioTO.getSelosGanhos());
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
        miLerUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    usuarioController = new UsuarioController();
                    ArrayList<Object> listaUsuarios = usuarioController.listarTodosUsuario();
                    String allUsuarios = "";
                    if (listaUsuarios != null) {
                        for (Object object : listaUsuarios) {
                            usuarioTO = (UsuarioTO) object;
                            allUsuarios += String.format("ID: %s | Nome: %s | Mérito: %.1f | SoulCoins: %d\n",
                                    usuarioTO.getIdUsuario(), usuarioTO.getNmUsuario(),
                                    usuarioTO.getVlMerito(), usuarioTO.getQtSoulCoins());
                        }
                    }
                    JOptionPane.showMessageDialog(null, allUsuarios, "Usuários", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        miAtualizarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    usuarioController = new UsuarioController();
                    UsuarioTO novoUsuarioTO = new UsuarioTO();
                    String id = JOptionPane.showInputDialog("Digite o ID do usuário a atualizar: ");
                    String nome = JOptionPane.showInputDialog("Novo nome: ");
                    float merito = Float.parseFloat(JOptionPane.showInputDialog("Novo valor de mérito: "));
                    int soulCoins = Integer.parseInt(JOptionPane.showInputDialog("Nova qtde. de SoulCoins: "));
                    novoUsuarioTO.setIdUsuario(id);
                    novoUsuarioTO.setNmUsuario(nome);
                    novoUsuarioTO.setVlMerito(merito);
                    novoUsuarioTO.setQtSoulCoins(soulCoins);
                    String resultado = usuarioController.alterarUsuario(novoUsuarioTO.getIdUsuario(), novoUsuarioTO.getNmUsuario(), novoUsuarioTO.getVlMerito(), novoUsuarioTO.getQtSoulCoins(), novoUsuarioTO.getDsAtividadeRecente(), novoUsuarioTO.getSelosGanhos());
                    JOptionPane.showMessageDialog(null, resultado,"Conexão",JOptionPane.INFORMATION_MESSAGE);
                    //Se atualizar o usuário atual
                    usuarioTO = usuarioController.pegarUmUsuario(usuarioTO.getIdUsuario());
                    taDetalhesPerfil.setText(usuarioTO.detalhesPerfil());
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        miExcluirUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    usuarioController = new UsuarioController();
                    usuarioTO = new UsuarioTO();
                    String id = JOptionPane.showInputDialog("Digite o ID do usuário a excluir: ");
                    usuarioTO.setIdUsuario(id);

                    String resultado = usuarioController.excluirUsuario(usuarioTO.getIdUsuario());
                    JOptionPane.showMessageDialog(null, resultado,"Conexão",JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        miCriarMissao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btRegistrarMissao.doClick();
            }
        });
        miLerMissao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    missaoController = new MissaoController();
                    ArrayList<Object> lista = missaoController.listarTodosMissao();
                    String todasMissoes = "";
                    if (lista != null) {
                        for (Object object : lista) {
                            missaoTO = (MissaoTO) object;
                            todasMissoes += String.format("ID: %d | Nome: %s | Selo: %s SoulPoints: %d\n",
                                    missaoTO.getIdMissao(), missaoTO.getNmMissao(),
                                    missaoTO.getSelo(), missaoTO.getQtPontosGerados());
                        }
                    } else {
                        todasMissoes += "Nenhuma missão encontrada!";
                    }
                    JOptionPane.showMessageDialog(null, todasMissoes, "Missões", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        miAtualizarMissao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    missaoTO = new MissaoTO();
                    missaoController = new MissaoController();
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da missão a atualizar: "));
                    String nome = JOptionPane.showInputDialog("Novo nome: ");
                    String descricao = JOptionPane.showInputDialog("Nova descrição: ");
                    String selo = JOptionPane.showInputDialog("Novo selo: ");
                    int pontos = Integer.parseInt(JOptionPane.showInputDialog("Nova qtde. de pontos gerados: "));

                    missaoTO.setIdMissao(id);
                    missaoTO.setNmMissao(nome);
                    missaoTO.setDsMissao(descricao);
                    missaoTO.setSelo(selo);
                    missaoTO.setQtPontosGerados(pontos);
                    missaoTO.setDtMissao(LocalDateTime.now());

                    String resultado = missaoController.alterarMissao(missaoTO.getIdMissao(), missaoTO.getNmMissao(), missaoTO.getDsMissao(), missaoTO.getSelo(), missaoTO.getQtPontosGerados(), missaoTO.getDtMissao());
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
        miExcluirMissao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    missaoTO = new MissaoTO();
                    missaoController = new MissaoController();
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da missão a excluir: "));
                    missaoTO.setIdMissao(id);
                    String resultado = missaoController.excluirMissao(missaoTO.getIdMissao());
                    JOptionPane.showMessageDialog(null, resultado, "Missão", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        miCriarAcao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btRegistrarAcao.doClick(); // vai disparar a ação do botão, só pra evitar código, eu ia copiar e colar o que tava lá aqui
            }
        });
        miLerAcao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    acaoController = new AcaoController();
                    ArrayList<Object> lista = acaoController.listarTodosAcao();
                    String texto = "";
                    if (lista != null) {
                        for (Object object : lista) {
                            acaoTO = (AcaoTO) object;
                            texto += String.format("ID: %d | Usuário: %s | Descrição: %s | Pontos: %d\n",
                                    acaoTO.getIdAcao(), acaoTO.getIdUsuario(),
                                    acaoTO.getDsAcao(), acaoTO.getQtPontosGerados());
                        }
                    }
                    JOptionPane.showMessageDialog(null, texto, "Ações", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        miAtualizarAcao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    acaoController = new AcaoController();
                    acaoTO = new AcaoTO();

                    int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da ação a atualizar: "));
                    String descricao = JOptionPane.showInputDialog("Nova descrição: ");
                    int pontos = Integer.parseInt(JOptionPane.showInputDialog("Nova qtde. de pontos gerados: "));

                    acaoTO.setIdAcao(id);
                    acaoTO.setIdUsuario(usuarioTO.getIdUsuario());
                    acaoTO.setDsAcao(descricao);
                    acaoTO.setQtPontosGerados(pontos);
                    acaoTO.setDtAcao(LocalDateTime.now());

                    String resultado = acaoController.alterarAcao(acaoTO.getIdAcao(), acaoTO.getIdUsuario(), acaoTO.getDsAcao(), acaoTO.getQtPontosGerados(), acaoTO.getDtAcao());
                    JOptionPane.showMessageDialog(null, resultado, "Ação", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        miExcluirAcao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    acaoController = new AcaoController();
                    acaoTO = new AcaoTO();

                    int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da ação a excluir: "));
                    acaoTO.setIdAcao(id);

                    String resultado = acaoController.excluirAcao(acaoTO.getIdAcao());
                    JOptionPane.showMessageDialog(null, resultado, "Ação", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        miCriarPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btRegistrarPost.doClick();
            }
        });
        miLerPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    postController = new PostController();
                    ArrayList<Object> lista = postController.listarTodosPost();
                    String texto = "";
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm");
                    if (lista != null) {
                        for (Object object : lista) {
                            postTO = (PostTO) object;
                            texto += String.format("ID: %d | Usuário: %s | Votos: +%d-%d=%d | Data: %s\n",
                                    postTO.getIdPost(), postTO.getIdUsuario(), postTO.getNumUpVotes(),
                                    postTO.getNumDownVotes(), postTO.getNumSaldoVotes(), postTO.getDtPost().format(dtf));
                        }
                    }
                    JOptionPane.showMessageDialog(null, texto, "Posts", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        miAtualizarPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    postController = new PostController();
                    postTO = new PostTO();

                    int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do postTO a atualizar: "));
                    String texto = JOptionPane.showInputDialog("Novo texto do postTO: ");
                    int upVotes = Integer.parseInt(JOptionPane.showInputDialog("Qtde. de upvotes: "));
                    int downVotes = Integer.parseInt(JOptionPane.showInputDialog("Qtde. de downvotes: "));

                    postTO.setIdPost(id);
                    postTO.setIdUsuario(usuarioTO.getIdUsuario());
                    postTO.setDsPost(texto);
                    postTO.setDtPost(LocalDateTime.now());
                    postTO.setNumUpVotes(upVotes);
                    postTO.setNumDownVotes(downVotes);
                    postTO.setNumSaldoVotes();

                    String resultado = postController.alterarPost(postTO.getIdPost(), postTO.getIdUsuario(), postTO.getDsPost(), postTO.getDtPost(), postTO.getNumUpVotes(), postTO.getNumDownVotes());
                    JOptionPane.showMessageDialog(null, texto, "Posts", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        miExcluirPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    postController = new PostController();
                    postTO = new PostTO();

                    int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do postTO a excluir: "));
                    postTO.setIdPost(id);

                    String resultado = postController.excluirPost(postTO.getIdPost());
                    JOptionPane.showMessageDialog(null, resultado, "Posts", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        miCriarQuiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btRegistrarQuiz.doClick();
            }
        });
        miLerQuiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    quizController = new QuizController();
                    ArrayList<Object> lista = quizController.listarTodosQuiz();
                    String texto = "";
                    if (lista != null) {
                        for (Object object : lista) {
                            quizTO = (QuizTO) object;
                            texto += String.format("ID: %d | Usuário: %s | Acertos: %d/%d | Pontos: %d\n",
                                    quizTO.getIdQuiz(), quizTO.getIdUsuario(),
                                    quizTO.getNumAcertos(), quizTO.getNumQuestoes(),
                                    quizTO.getQtPontosGerados());
                        }
                    }
                    JOptionPane.showMessageDialog(null, texto, "Quizzes", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        miAtualizarQuiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    quizController = new QuizController();
                    quizTO = new QuizTO();

                    int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do quizTO a atualizar: "));
                    int numQuestoes = Integer.parseInt(JOptionPane.showInputDialog("Novo número de questões: "));
                    int numAcertos = Integer.parseInt(JOptionPane.showInputDialog("Novo número de acertos: "));
                    int pontosPorQuestao = Integer.parseInt(JOptionPane.showInputDialog("Novos pontos por questão: "));

                    quizTO.setIdQuiz(id);
                    quizTO.setIdUsuario(usuarioTO.getIdUsuario());
                    quizTO.setNumQuestoes(numQuestoes);
                    quizTO.setNumAcertos(numAcertos);
                    quizTO.setQtPontosPorQuestao(pontosPorQuestao);
                    quizTO.setQtPontosGerados();
                    quizTO.setDtQuiz(LocalDateTime.now());

                    String resultado = quizController.alterarQuiz(quizTO.getIdQuiz(), quizTO.getIdUsuario(), quizTO.getNumQuestoes(), quizTO.getNumAcertos(), quizTO.getQtPontosPorQuestao(), quizTO.getDtQuiz());
                    JOptionPane.showMessageDialog(null, resultado, "QuizTO", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        miExcluirQuiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    quizController = new QuizController();
                    quizTO = new QuizTO();

                    int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do quizTO a excluir: "));
                    quizTO.setIdQuiz(id);

                    String resultado = quizController.excluirQuiz(quizTO.getIdQuiz());
                    JOptionPane.showMessageDialog(null, resultado, "QuizTO", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btRanking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    ranking = new RankingUsuarioTO();
                    rankingUsuarioController = new RankingUsuarioController();
                    ArrayList<Object> listaRanking = rankingUsuarioController.listarTodosRankingUsuario();
                    String texto = "";
                    int posicao = 1;
                    for (Object object : listaRanking) {
                        ranking = (RankingUsuarioTO) object;
                        texto += String.format("POS.: %dº | Nome: %s | Votos: %d\n",
                                posicao,ranking.getIdUsuario(),ranking.getQtVotos());
                        posicao++;
                    }
                    JOptionPane.showMessageDialog(null,texto,"Ranking",JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}