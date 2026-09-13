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
    private RankingUsuario ranking;
    private UsuarioDAO usuarioDAO;
    private AcaoDAO acaoDAO;
    private MissaoDAO missaoDAO;
    private QuizDAO quizDAO;
    private PostDAO postDAO;
    private RankingUsuarioDAO rankingUsuarioDAO;
    private Container contentPane;
    private JMenuBar mnBarra;
    private JMenu mnArquivo, mnUsuario,mnMissao,mnAcao,mnPost,mnQuiz,mnAjuda;
    private JMenuItem miSair, miAjuda, miEditarExemplo,miTrocarUsuario,miLimparAtividades,miLimparSelos,miCriarUsuario,miLerUsuario,miAtualizarUsuario,miExcluirUsuario,miCriarMissao,miLerMissao,miAtualizarMissao,miExcluirMissao,miCriarAcao,miLerAcao,miAtualizarAcao,miExcluirAcao,miCriarPost,miLerPost,miAtualizarPost,miExcluirPost,miCriarQuiz,miLerQuiz,miAtualizarQuiz,miExcluirQuiz;
    private JPanel painel, painelBts, painelTxt;
    private JButton btRegistrarAcao, btRegistrarMissao, btRegistrarQuiz, btRegistrarPost, btRegistrarPenalidade, btRanking;
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

            acao = new Acao();
            missao = new Missao();
            post = new Post();
            quiz = new Quiz();
            acaoDAO = new AcaoDAO(con);
            missaoDAO = new MissaoDAO(con);
            postDAO = new PostDAO(con);
            quizDAO = new QuizDAO(con);

            usuario.setIdUsuario("dragonborn123");
            usuario = (Usuario) usuarioDAO.pegarUm(usuario); //como se fosse um login
            acao = acaoDAO.pegarUm(4);
            usuario.registrarAtividade(acao.detalhesAcao());
            missao = (Missao) missaoDAO.pegarUm(0);
            usuario.registrarAtividade(missao.detalhesMissao(),missao.getQtPontosGerados(),missao.getSelo());
            post = postDAO.pegarUm(0);
            usuario.registrarAtividade(post.detalhesPost());
            quiz = quizDAO.pegarUm(0);
            usuario.registrarAtividade(quiz.detalhesQuiz());

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

        miCriarPost = new JMenuItem("Criar post");
        miLerPost = new JMenuItem("Listar posts");
        miAtualizarPost = new JMenuItem("Atualizar post");
        miExcluirPost = new JMenuItem("Excluir post");
        miCriarQuiz = new JMenuItem("Criar quiz");
        miLerQuiz = new JMenuItem("Listar quizzes");
        miAtualizarQuiz = new JMenuItem("Atualizar quiz");
        miExcluirQuiz = new JMenuItem("Excluir quiz");
        miEditarExemplo = new JMenuItem("Editar exemplo");

        painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painelBts = new JPanel();
        painelBts.setLayout(new FlowLayout());
        painelTxt = new JPanel();
        btRegistrarAcao = new JButton("Registrar ação");
        btRegistrarMissao = new JButton("Registrar missão");
        btRegistrarQuiz = new JButton("Registrar quiz");
        btRegistrarPost = new JButton("Registrar post");
        btRegistrarPenalidade = new JButton("Registrar penalidade");
        btRanking = new JButton("Ver ranking");
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
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,"Erro: "+ e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
                taDetalhesPerfil.setText(usuario.detalhesPerfil());
            }
        });
        miLimparAtividades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (JOptionPane.showConfirmDialog(null, "Tem certa? Isso apagará toda a Atividade Recente (não do banco)","Confirme",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
                    ArrayList<String> atividadesVazio = new ArrayList<>();
                    usuario.setDsAtividadeRecente(atividadesVazio);
                }
                taDetalhesPerfil.setText(usuario.detalhesPerfil());
            }
        });
        miLimparSelos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (JOptionPane.showConfirmDialog(null, "Tem certa? Isso apagará todos os selos (não do banco)","Confirme",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
                    ArrayList<String> selosVazio = new ArrayList<>();
                    usuario.setSelosGanhos(selosVazio);
                }
                taDetalhesPerfil.setText(usuario.detalhesPerfil());
            }
        });
        miTrocarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection con = ConnectionFactory.abrirConexao()){
                    Usuario testarSeExiste = new Usuario();
                    usuarioDAO = new UsuarioDAO(con);

                    String novoId = JOptionPane.showInputDialog("Qual usuário você quer usar?: (dica: Liste os usuários pelo menu)");
                    testarSeExiste = (Usuario) usuarioDAO.pegarUm(novoId);
                    if (testarSeExiste != null) {
                        if (JOptionPane.showConfirmDialog(null,
                                                 "Usuário encontado, você tem certeza que quer mudar isso pode apagar algumas alterações!",
                                                          "Confirme!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0) {
                            usuario = new Usuario();
                            usuario = (Usuario) usuarioDAO.pegarUm(novoId);
                            JOptionPane.showMessageDialog(null,"Logado como " + usuario.getNmUsuario(),
                                                          "Novo usuário!", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null,"Troca cancelada",
                                                     "Nada acontece", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Esse usuário não existe!",
                                                      "Erro",JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
                taDetalhesPerfil.setText(usuario.detalhesPerfil());
            }
        });

        btRegistrarAcao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection con = ConnectionFactory.abrirConexao()) {
                    acaoDAO = new AcaoDAO(con);
                    acao = new Acao();

                    int escolha = Integer.parseInt(JOptionPane.showInputDialog("Digite o tipo de ação:\n1. Natureza\n2. Carbono\n3. Água\n4. Reciclagem\n0. cancelar ação"));
                    switch (escolha) {
                        case 1:
                            int dificuldade = Integer.parseInt(JOptionPane.showInputDialog("Qual é a dificuldade da ação realizada? Digite (entre 1 a 5): "));
                            acao.registrarPontos(calculadora.pontosNatureza(dificuldade));
                            break;
                        case 2:
                            float kgCarbono = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de carbono? Digite (um número): "));
                            acao.registrarPontos(calculadora.pontosCarbono(kgCarbono));
                            break;
                        case 3:
                            float litros = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de litros economizados? Digite (um número): "));
                            acao.registrarPontos(calculadora.pontosAgua(litros));
                            break;
                        case 4:
                            float kgReciclados = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de KG reciclados? Digite (um número): "));
                            acao.registrarPontos(calculadora.pontosReciclagem(kgReciclados));
                            break;
                        case 0:
                            break;
                        default:
                            throw new Exception("Opção inválida (0-4)");
                    }
                    if (escolha != 0){
                        String descricao = JOptionPane.showInputDialog("Digite a descrição da ação: ");
                        acao.setIdAcao((int) acaoDAO.criarId());
                        acao.setIdUsuario(usuario.getIdUsuario());
                        acao.setDsAcao(descricao);
                        acao.setDtAcao(LocalDateTime.now());

                        usuario.registrarAtividade(acao.detalhesAcao(),acao.getQtPontosGerados());
                        String resultado = acaoDAO.inserir(acao);

                        usuarioDAO = new UsuarioDAO(con);
                        usuarioDAO.alterar(usuario);

                        taDetalhesPerfil.setText(usuario.detalhesPerfil());
                        JOptionPane.showMessageDialog(null, resultado,"Conexão",JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btRegistrarMissao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Missao missao;
                try (Connection con = ConnectionFactory.abrirConexao()){
                    missao = new Missao();
                    missaoDAO = new MissaoDAO(con);
                    String nome = JOptionPane.showInputDialog("Digite o nome da missão: ");
                    String descricao = JOptionPane.showInputDialog("Digite a descrição da missão: ");
                    String selo = JOptionPane.showInputDialog("Digite o selo da missão: ");
                    int pontos = Integer.parseInt(JOptionPane.showInputDialog("Digite a qtde. de pontos gerados: "));

                    missao.setIdMissao((int) missaoDAO.criarId());
                    missao.setNmMissao(nome);
                    missao.setDsMissao(descricao);
                    missao.setSelo(selo);
                    missao.setQtPontosGerados(pontos);
                    missao.setDtMissao(LocalDateTime.now());

                    usuario.registrarAtividade(missao.detalhesMissao(),
                            missao.getQtPontosGerados(),
                            missao.getSelo());

                    usuarioDAO = new UsuarioDAO(con);
                    usuarioDAO.alterar(usuario);

                    String resultado = missaoDAO.inserir(missao);
                    taDetalhesPerfil.setText(usuario.detalhesPerfil());
                    JOptionPane.showMessageDialog(null, resultado,"Conexão",JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btRegistrarQuiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Quiz quiz;
                try (Connection con = ConnectionFactory.abrirConexao()) {
                    quizDAO = new QuizDAO(con);
                    quiz = new Quiz();

                    int numQuestoes = Integer.parseInt(JOptionPane.showInputDialog("Digite o número de questões: "));
                    int numAcertos = Integer.parseInt(JOptionPane.showInputDialog("Digite o número de acertos: "));
                    int pontosPorQuestao = Integer.parseInt(JOptionPane.showInputDialog("Digite os pontos por questão: "));

                    quiz.setIdQuiz(quizDAO.criarId());
                    quiz.setIdUsuario(usuario.getIdUsuario());
                    quiz.setNumQuestoes(numQuestoes);
                    quiz.setNumAcertos(numAcertos);
                    quiz.setQtPontosPorQuestao(pontosPorQuestao);
                    quiz.setQtPontosGerados();
                    quiz.setDtQuiz(LocalDateTime.now());

                    usuario.registrarAtividade(quiz.detalhesQuiz(),
                            quiz.getQtPontosGerados());

                    usuarioDAO = new UsuarioDAO(con);
                    usuarioDAO.alterar(usuario);

                    taDetalhesPerfil.setText(usuario.detalhesPerfil());
                    String resultado = quizDAO.inserir(quiz);
                    JOptionPane.showMessageDialog(null, resultado,"Conexão",JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btRegistrarPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Post post;
                try (Connection con = ConnectionFactory.abrirConexao()) {
                    postDAO = new PostDAO(con);
                    post = new Post();
                    ranking = new RankingUsuario();
                    rankingUsuarioDAO = new RankingUsuarioDAO(con);

                    String texto = JOptionPane.showInputDialog("Digite o texto do post: (máx. 200 caracteres)");
                    int upVotes = Integer.parseInt(JOptionPane.showInputDialog("Quantos votos UP recebeu?"));
                    int downVotes = Integer.parseInt(JOptionPane.showInputDialog("Quantos votos DOWN recebeu?"));
                    post.setIdPost(postDAO.criarId());
                    post.setIdUsuario(usuario.getIdUsuario());
                    post.setDsPost(texto);
                    post.setDtPost(LocalDateTime.now());
                    post.addUpVote(upVotes);
                    post.addDownVote(downVotes);

                    String resultado = postDAO.inserir(post);
                    ranking = rankingUsuarioDAO.pegarUm(usuario.getIdUsuario());
                    if (ranking != null) {
                        ranking.addQtVotos(post.getNumSaldoVotes());
                        rankingUsuarioDAO.alterar(ranking);
                    } else {
                        ranking = new RankingUsuario((int) rankingUsuarioDAO.criarId(),usuario.getIdUsuario(),
                                                     post.getNumSaldoVotes());
                        rankingUsuarioDAO.inserir(ranking);
                    }
                    usuario.registrarAtividade(post.detalhesPost());
                    taDetalhesPerfil.setText(usuario.detalhesPerfil());
                    JOptionPane.showMessageDialog(null, resultado, "Posts", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro:", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btRegistrarPenalidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection con = ConnectionFactory.abrirConexao()){
                    int valorPenalidade = Integer.parseInt(JOptionPane.showInputDialog("Digite o valor da penalidade de confiabilidade (0-100, a confiabilidade não cairá abaixo de 0): "));
                    String descOcorrido = JOptionPane.showInputDialog("Descreva o ocorrido: ");
                    //Mensagem de confirmação mostrando o texto e confiabilidade que será tirada.
                    if (JOptionPane.showConfirmDialog(null,
                            String.format("Tem certeza que quer retirar %d de confiabilidade do usuário %s com a descrição:\n%s", valorPenalidade, usuario.getNmUsuario(), descOcorrido), "Confirme",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                        usuarioDAO = new UsuarioDAO(con);
                        usuario.registrarPenalidade(valorPenalidade, descOcorrido);
                        usuarioDAO.alterar(usuario);
                    }
                }catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }

                taDetalhesPerfil.setText(usuario.detalhesPerfil());
            }
        });

        miCriarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection con = ConnectionFactory.abrirConexao()){
                    usuario = new Usuario();
                    usuarioDAO = new UsuarioDAO(con);

                    String id = JOptionPane.showInputDialog("Digite o ID do usuário: ");
                    String nome = JOptionPane.showInputDialog("Digite o nome do usuário: ");
                    float merito = Float.parseFloat(JOptionPane.showInputDialog("Digite o valor de mérito: "));
                    int soulCoins = Integer.parseInt(JOptionPane.showInputDialog("Digite a qtde. de SoulCoins: "));

                    usuario.setIdUsuario(id);
                    usuario.setNmUsuario(nome);
                    usuario.setVlMerito(merito);
                    usuario.setQtSoulCoins(soulCoins);

                    String resultado = usuarioDAO.inserir(usuario);
                    JOptionPane.showMessageDialog(null, resultado,"Conexão",JOptionPane.INFORMATION_MESSAGE);
                }catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro numérico: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        miLerUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection con = ConnectionFactory.abrirConexao()){
                    usuarioDAO = new UsuarioDAO(con);
                    ArrayList<Object> listaUsuarios = usuarioDAO.listarTodos();
                    String allUsuarios = "";
                    if (listaUsuarios != null) {
                        for (Object object : listaUsuarios) {
                            usuario = (Usuario) object;
                            allUsuarios += String.format("ID: %s | Nome: %s | Mérito: %.1f | SoulCoins: %d\n",
                                                         usuario.getIdUsuario(), usuario.getNmUsuario(),
                                                         usuario.getVlMerito(), usuario.getQtSoulCoins());
                        }
                    }
                    JOptionPane.showMessageDialog(null, allUsuarios, "Usuários", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        miAtualizarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection con = ConnectionFactory.abrirConexao()){
                    usuarioDAO = new UsuarioDAO(con);
                    Usuario novoUsuario = new Usuario();
                    String id = JOptionPane.showInputDialog("Digite o ID do usuário a atualizar: ");
                    String nome = JOptionPane.showInputDialog("Novo nome: ");
                    float merito = Float.parseFloat(JOptionPane.showInputDialog("Novo valor de mérito: "));
                    int soulCoins = Integer.parseInt(JOptionPane.showInputDialog("Nova qtde. de SoulCoins: "));
                    novoUsuario.setIdUsuario(id);
                    novoUsuario.setNmUsuario(nome);
                    novoUsuario.setVlMerito(merito);
                    novoUsuario.setQtSoulCoins(soulCoins);
                    String resultado = usuarioDAO.alterar(novoUsuario);
                    JOptionPane.showMessageDialog(null, resultado,"Conexão",JOptionPane.INFORMATION_MESSAGE);
                    //Se atualizar o usuário atual
                    ArrayList<String> arAntiga = usuario.getDsAtividadeRecente();
                    ArrayList<String> sgAntiga = usuario.getSelosGanhos();
                    usuario = (Usuario) usuarioDAO.pegarUm(usuario.getIdUsuario());
                    usuario.setDsAtividadeRecente(arAntiga);
                    usuario.setSelosGanhos(sgAntiga);
                    taDetalhesPerfil.setText(usuario.detalhesPerfil());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        miExcluirUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection con = ConnectionFactory.abrirConexao()){
                    usuarioDAO = new UsuarioDAO(con);
                    usuario = new Usuario();
                    String id = JOptionPane.showInputDialog("Digite o ID do usuário a excluir: ");
                    usuario.setIdUsuario(id);

                    String resultado = usuarioDAO.excluir(usuario);
                    JOptionPane.showMessageDialog(null, resultado,"Conexão",JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        miCriarMissao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection con = ConnectionFactory.abrirConexao()){
                    missao = new Missao();
                    missaoDAO = new MissaoDAO(con);
                    String nome = JOptionPane.showInputDialog("Digite o nome da missão: ");
                    String descricao = JOptionPane.showInputDialog("Digite a descrição da missão: ");
                    String selo = JOptionPane.showInputDialog("Digite o selo da missão: ");
                    int pontos = Integer.parseInt(JOptionPane.showInputDialog("Digite a qtde. de pontos gerados: "));

                    missao.setIdMissao((int) missaoDAO.criarId());
                    missao.setNmMissao(nome);
                    missao.setDsMissao(descricao);
                    missao.setSelo(selo);
                    missao.setQtPontosGerados(pontos);
                    missao.setDtMissao(LocalDateTime.now());

                    usuario.registrarAtividade(missao.detalhesMissao(),
                                               missao.getQtPontosGerados(),
                                               missao.getSelo());

                    usuarioDAO = new UsuarioDAO(con);
                    usuarioDAO.alterar(usuario);

                    String resultado = missaoDAO.inserir(missao);
                    taDetalhesPerfil.setText(usuario.detalhesPerfil());
                    JOptionPane.showMessageDialog(null, resultado,"Conexão",JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        miLerMissao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection con = ConnectionFactory.abrirConexao()){
                    missaoDAO = new MissaoDAO(con);
                    ArrayList<Object> lista = missaoDAO.listarTodos();
                    String todasMissoes = "";
                    if (lista != null) {
                        for (Object object : lista) {
                            missao = (Missao) object;
                            todasMissoes += String.format("ID: %d | Nome: %s | Selo: %s SoulPoints: %d\n",
                                                         missao.getIdMissao(), missao.getNmMissao(),
                                                         missao.getSelo(),missao.getQtPontosGerados());
                        }
                    } else {
                        todasMissoes += "Nenhuma missão encontrada!";
                    }
                    JOptionPane.showMessageDialog(null, todasMissoes, "Missões", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        miAtualizarMissao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection con = ConnectionFactory.abrirConexao()){
                    missao = new Missao();
                    missaoDAO = new MissaoDAO(con);
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da missão a atualizar: "));
                    String nome = JOptionPane.showInputDialog("Novo nome: ");
                    String descricao = JOptionPane.showInputDialog("Nova descrição: ");
                    String selo = JOptionPane.showInputDialog("Novo selo: ");
                    int pontos = Integer.parseInt(JOptionPane.showInputDialog("Nova qtde. de pontos gerados: "));

                    missao.setIdMissao(id);
                    missao.setNmMissao(nome);
                    missao.setDsMissao(descricao);
                    missao.setSelo(selo);
                    missao.setQtPontosGerados(pontos);
                    missao.setDtMissao(LocalDateTime.now());

                    String resultado = missaoDAO.alterar(missao);
                    JOptionPane.showMessageDialog(null, resultado,"Conexão",JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        miExcluirMissao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection con = ConnectionFactory.abrirConexao()){
                    missao = new Missao();
                    missaoDAO = new MissaoDAO(con);
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da missão a excluir: "));
                    missao.setIdMissao(id);
                    String resultado = missaoDAO.excluir(missao);
                    JOptionPane.showMessageDialog(null, resultado, "Missão", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        miCriarAcao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection con = ConnectionFactory.abrirConexao()) {
                    acaoDAO = new AcaoDAO(con);
                    acao = new Acao();

                    int escolha = Integer.parseInt(JOptionPane.showInputDialog("Digite o tipo de ação:\n1. Natureza\n2. Carbono\n3. Água\n4. Reciclagem\n0. cancelar ação"));
                    switch (escolha) {
                        case 1:
                            int dificuldade = Integer.parseInt(JOptionPane.showInputDialog("Qual é a dificuldade da ação realizada? Digite (entre 1 a 5): "));
                            acao.registrarPontos(calculadora.pontosNatureza(dificuldade));
                            break;
                        case 2:
                            float kgCarbono = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de carbono? Digite (um número): "));
                            acao.registrarPontos(calculadora.pontosCarbono(kgCarbono));
                            break;
                        case 3:
                            float litros = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de litros economizados? Digite (um número): "));
                            acao.registrarPontos(calculadora.pontosAgua(litros));
                            break;
                        case 4:
                            float kgReciclados = Float.parseFloat(JOptionPane.showInputDialog("Qual é a qtde. de KG reciclados? Digite (um número): "));
                            acao.registrarPontos(calculadora.pontosReciclagem(kgReciclados));
                            break;
                        case 0:
                            break;
                        default:
                            throw new Exception("Opção inválida (0-4)");
                    }
                    if (escolha != 0){
                        String descricao = JOptionPane.showInputDialog("Digite a descrição da ação: ");
                        acao.setIdAcao((int) acaoDAO.criarId());
                        acao.setIdUsuario(usuario.getIdUsuario());
                        acao.setDsAcao(descricao);
                        acao.setDtAcao(LocalDateTime.now());

                        usuario.registrarAtividade(acao.detalhesAcao(),
                                                   acao.getQtPontosGerados());
                        String resultado = acaoDAO.inserir(acao);

                        usuarioDAO = new UsuarioDAO(con);
                        usuarioDAO.alterar(usuario);

                        taDetalhesPerfil.setText(usuario.detalhesPerfil());
                        JOptionPane.showMessageDialog(null, resultado,"Conexão",JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        miLerAcao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection con = ConnectionFactory.abrirConexao()) {
                    acaoDAO = new AcaoDAO(con);
                    ArrayList<Object> lista = acaoDAO.listarTodos();
                    String texto = "";
                    if (lista != null) {
                        for (Object object : lista) {
                            acao = (Acao) object;
                            texto += String.format("ID: %d | Usuário: %s | Descrição: %s | Pontos: %d\n",
                                                   acao.getIdAcao(),acao.getIdUsuario(),
                                                   acao.getDsAcao(), acao.getQtPontosGerados());
                        }
                    }
                    JOptionPane.showMessageDialog(null, texto, "Ações", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        miAtualizarAcao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection con = ConnectionFactory.abrirConexao()) {
                    acaoDAO = new AcaoDAO(con);
                    acao = new Acao();

                    int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da ação a atualizar: "));
                    String descricao = JOptionPane.showInputDialog("Nova descrição: ");
                    int pontos = Integer.parseInt(JOptionPane.showInputDialog("Nova qtde. de pontos gerados: "));

                    acao.setIdAcao(id);
                    acao.setIdUsuario(usuario.getIdUsuario());
                    acao.setDsAcao(descricao);
                    acao.setQtPontosGerados(pontos);
                    acao.setDtAcao(LocalDateTime.now());

                    String resultado = acaoDAO.alterar(acao);
                    JOptionPane.showMessageDialog(null, resultado, "Ação", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        miExcluirAcao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection con = ConnectionFactory.abrirConexao()) {
                    acaoDAO = new AcaoDAO(con);
                    acao = new Acao();

                    int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da ação a excluir: "));
                    acao.setIdAcao(id);

                    String resultado = acaoDAO.excluir(acao);
                    JOptionPane.showMessageDialog(null, resultado, "Ação", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        miCriarPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection con = ConnectionFactory.abrirConexao()) {
                    postDAO = new PostDAO(con);
                    post = new Post();
                    rankingUsuarioDAO = new RankingUsuarioDAO(con);
                    ranking = new RankingUsuario();

                    String texto = JOptionPane.showInputDialog("Digite o texto do post: (máx. 200 caracteres)");
                    int upVotes = Integer.parseInt(JOptionPane.showInputDialog("Quantos votos UP recebeu?"));
                    int downVotes = Integer.parseInt(JOptionPane.showInputDialog("Quantos votos DOWN recebeu?"));
                    post.setIdPost(postDAO.criarId());
                    post.setIdUsuario(usuario.getIdUsuario());
                    post.setDsPost(texto);
                    post.setDtPost(LocalDateTime.now());
                    post.setNumUpVotes(upVotes);
                    post.setNumDownVotes(downVotes);

                    String resultado = postDAO.inserir(post);
                    ranking = rankingUsuarioDAO.pegarUm(usuario.getIdUsuario());
                    ranking.addQtVotos(post.getNumSaldoVotes());
                    rankingUsuarioDAO.alterar(ranking);
                    usuario.registrarAtividade(post.detalhesPost());
                    taDetalhesPerfil.setText(usuario.detalhesPerfil());
                    JOptionPane.showMessageDialog(null, resultado, "Posts", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        miLerPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection con = ConnectionFactory.abrirConexao()) {
                    postDAO = new PostDAO(con);
                    ArrayList<Object> lista = postDAO.listarTodos();
                    String texto = "";
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm");
                    if (lista != null) {
                        for (Object object : lista) {
                            post = (Post) object;
                            texto += String.format("ID: %d | Usuário: %s | Votos: +%d-%d=%d | Data: %s\n",
                                                   post.getIdPost(),post.getIdUsuario(),post.getNumUpVotes(),
                                                   post.getNumDownVotes(),post.getNumSaldoVotes(),post.getDtPost().format(dtf)); 
                        }
                    }
                    JOptionPane.showMessageDialog(null, texto, "Posts", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        miAtualizarPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection con = ConnectionFactory.abrirConexao()) {
                    postDAO = new PostDAO(con);
                    post = new Post();

                    int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do post a atualizar: "));
                    String texto = JOptionPane.showInputDialog("Novo texto do post: ");
                    int upVotes = Integer.parseInt(JOptionPane.showInputDialog("Qtde. de upvotes: "));
                    int downVotes = Integer.parseInt(JOptionPane.showInputDialog("Qtde. de downvotes: "));

                    post.setIdPost(id);
                    post.setIdUsuario(usuario.getIdUsuario());
                    post.setDsPost(texto);
                    post.setDtPost(LocalDateTime.now());
                    post.setNumUpVotes(upVotes);
                    post.setNumDownVotes(downVotes);
                    post.setNumSaldoVotes();

                    String resultado = postDAO.alterar(post);
                    JOptionPane.showMessageDialog(null, texto, "Posts", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        miExcluirPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection con = ConnectionFactory.abrirConexao()) {
                    postDAO = new PostDAO(con);
                    post = new Post();

                    int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do post a excluir: "));
                    post.setIdPost(id);

                    String resultado = postDAO.excluir(post);
                    JOptionPane.showMessageDialog(null, resultado, "Posts", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        miCriarQuiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection con = ConnectionFactory.abrirConexao()) {
                    quizDAO = new QuizDAO(con);
                    quiz = new Quiz();

                    int numQuestoes = Integer.parseInt(JOptionPane.showInputDialog("Digite o número de questões: "));
                    int numAcertos = Integer.parseInt(JOptionPane.showInputDialog("Digite o número de acertos: "));
                    int pontosPorQuestao = Integer.parseInt(JOptionPane.showInputDialog("Digite os pontos por questão: "));

                    quiz.setIdQuiz(quizDAO.criarId());
                    quiz.setIdUsuario(usuario.getIdUsuario());
                    quiz.setNumQuestoes(numQuestoes);
                    quiz.setNumAcertos(numAcertos);
                    quiz.setQtPontosPorQuestao(pontosPorQuestao);
                    quiz.setQtPontosGerados();
                    quiz.setDtQuiz(LocalDateTime.now());

                    usuario.registrarAtividade(quiz.detalhesQuiz(),
                                               quiz.getQtPontosGerados());

                    usuarioDAO = new UsuarioDAO(con);
                    usuarioDAO.alterar(usuario);

                    taDetalhesPerfil.setText(usuario.detalhesPerfil());
                    String resultado = quizDAO.inserir(quiz);
                    JOptionPane.showMessageDialog(null, resultado,"Conexão",JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        miLerQuiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection con = ConnectionFactory.abrirConexao()) {
                    quizDAO = new QuizDAO(con);
                    ArrayList<Object> lista = quizDAO.listarTodos();
                    String texto = "";
                    if (lista != null) {
                        for (Object object : lista) {
                            quiz = (Quiz) object;
                            texto += String.format("ID: %d | Usuário: %s | Acertos: %d/%d | Pontos: %d\n",
                                                   quiz.getIdQuiz(),quiz.getIdUsuario(),
                                                   quiz.getNumAcertos(),quiz.getNumQuestoes(),
                                                   quiz.getQtPontosGerados());
                        }
                    }
                    JOptionPane.showMessageDialog(null, texto, "Quizzes", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        miAtualizarQuiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection con = ConnectionFactory.abrirConexao()) {
                    quizDAO = new QuizDAO(con);
                    quiz = new Quiz();

                    int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do quiz a atualizar: "));
                    int numQuestoes = Integer.parseInt(JOptionPane.showInputDialog("Novo número de questões: "));
                    int numAcertos = Integer.parseInt(JOptionPane.showInputDialog("Novo número de acertos: "));
                    int pontosPorQuestao = Integer.parseInt(JOptionPane.showInputDialog("Novos pontos por questão: "));

                    quiz.setIdQuiz(id);
                    quiz.setIdUsuario(usuario.getIdUsuario());
                    quiz.setNumQuestoes(numQuestoes);
                    quiz.setNumAcertos(numAcertos);
                    quiz.setQtPontosPorQuestao(pontosPorQuestao);
                    quiz.setQtPontosGerados();
                    quiz.setDtQuiz(LocalDateTime.now());

                    String resultado = quizDAO.alterar(quiz);
                    JOptionPane.showMessageDialog(null, resultado, "Quiz", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        miExcluirQuiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection con = ConnectionFactory.abrirConexao()) {
                    quizDAO = new QuizDAO(con);
                    quiz = new Quiz();

                    int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do quiz a excluir: "));
                    quiz.setIdQuiz(id);

                    String resultado = quizDAO.excluir(quiz);
                    JOptionPane.showMessageDialog(null, resultado, "Quiz", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btRanking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try (Connection con = ConnectionFactory.abrirConexao()){
                    ranking = new RankingUsuario();
                    rankingUsuarioDAO = new RankingUsuarioDAO(con);
                    ArrayList<Object> listaRanking = rankingUsuarioDAO.listarTodos();
                    String texto = "";
                    int posicao = 1;
                    for (Object object : listaRanking) {
                        ranking = (RankingUsuario) object;
                        texto += String.format("POS.: %dº | Nome: %s | Votos: %d\n",
                                               posicao,ranking.getIdUsuario(),ranking.getQtVotos());
                        posicao++;
                    }
                    JOptionPane.showMessageDialog(null,texto,"Ranking",JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
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