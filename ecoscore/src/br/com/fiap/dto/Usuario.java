package br.com.fiap.dto;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Classe que vai guardar as informações de cada usuário
 * @since Java 21
 */
public class Usuario {
    private String idUsuario;
    private String nmUsuario;
    private float vlMerito; //A confiabilidade do usuário, quanto o voto e as ações dele são confiáveis
    private int qtSoulCoins;
    private ArrayList<String> dsAtividadeRecente = new ArrayList<String>(); //Últimas ações realizadas
    private ArrayList<String> selosGanhos = new ArrayList<String>();

    public Usuario() {}
    // Construtor para registrar apenas nmUsuario do usuário e idUsuario, pois ambos não tem validação
    public Usuario(String nmUsuario, String idUsuario) {
        this.nmUsuario = nmUsuario;
        setIdUsuario(idUsuario);
    }
    // Construtor para registrar usuário quase completo (nome, idUsuario, vlMerito e soul coins).
    public Usuario(String nmUsuario, String idUsuario, float vlMerito, int qtSoulCoins) {
        this.nmUsuario = nmUsuario;
        setIdUsuario(idUsuario);
        setVlMerito(vlMerito);
        setQtSoulCoins(qtSoulCoins);
    }

    public String getNmUsuario() {
        return nmUsuario;
    }
    public void setNmUsuario(String nmUsuario) throws IllegalArgumentException{
        //banco está como varchar(50)
        if (nmUsuario.length() > 50) {
            throw new IllegalArgumentException("O nome do usuário deve ser menor que 50 caracteres.");
        }
        this.nmUsuario = nmUsuario;
    }
    public String getIdUsuario() {
        return idUsuario;
    }
    // A idUsuario é sempre minúscula.
    public void setIdUsuario(String idUsuario) throws IllegalArgumentException{
        //máx 30 caracteres no banco
        if (idUsuario.length() > 30) {
            throw new IllegalArgumentException("Limite de caracteres é 30");
        }
        this.idUsuario = idUsuario.toLowerCase();
    }
    public float getVlMerito() {
        return vlMerito;
    }
    // Confiabilidade varia entre 0 e 100%, e é mostrada como porcentagem
    /* EU vi no material que throws é "inutil" com as runtimes,
    porém vou deixar para documentação mais e identificação
      eu uso direto o folding all pra fechar os métodos, com isso saberei se ele tem runtimeexception*/
    public void setVlMerito(float vlMerito) throws IllegalArgumentException{
        try {
            if (vlMerito >= 0 && vlMerito <= 100) {
                this.vlMerito = vlMerito;
            } else {
                // 50 é a vlMerito padrão
                setVlMerito(50);
                throw new IllegalArgumentException("Valor inválido, digite um número de 1 a 100 para vlMerito");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    public int getQtSoulCoins() {
        return qtSoulCoins;
    }
    public void setQtSoulCoins(int qtSoulCoins) throws IllegalArgumentException{
        try {
            if (qtSoulCoins >= 0) {
                this.qtSoulCoins = qtSoulCoins;
            } else {
                //0 é a vlMerito padrão mesmo
                throw new IllegalArgumentException("O número de Soul coins não pode ser negativo");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    public ArrayList<String> getDsAtividadeRecente() {
        return dsAtividadeRecente;
    }
    public void setDsAtividadeRecente(ArrayList<String> dsAtividadeRecente) {
        this.dsAtividadeRecente = dsAtividadeRecente;
    }
    public ArrayList<String> getSelosGanhos() {
        // banco aceita até 200 caracteres, não vai passar iss, mas validação nunca é ruim.
        return selosGanhos;
    }
    public void setSelosGanhos(ArrayList<String> selosGanhos) {
        this.selosGanhos = selosGanhos;
    }

    /**
     * Registrar uma atividade do usuário.
     * @param dsAtividade é o texto descrevendo a atividade realizada
     * @throws IllegalArgumentException quando a dsAtvidade é vazia ou nula
     */
    public void registrarAtividade(String dsAtividade) throws IllegalArgumentException {
        if (dsAtividade == null || dsAtividade.isBlank()) {
            throw new IllegalArgumentException("A descrição da atividade não pode ser vazia");
        }
        dsAtividadeRecente.add(dsAtividade);
    }

    /**
     * Registra uma atividade que gerou pontos ao usuário.
     * @param dsAtividade é o texto descrevendo a atividade realizada.
     * @param qtSoulCoins e a quantidade de pontos gerada por aquela atividade.
     * @throws IllegalArgumentException quando a dsAtvidade é vazia ou nula.
     */
    public void registrarAtividade(String dsAtividade, int qtSoulCoins) throws IllegalArgumentException {
        if (dsAtividade == null || dsAtividade.isBlank()) {
            throw new IllegalArgumentException("A descrição da ação não pode ser vazia");
        }
        // a vlMerito do usuário deve alterar o recebimento de pontos
        if (vlMerito <= 30) {
            // Divide pela metade os pontos recebidos e então arredonda para o inteiro mais próximo pra depois transformar em Inteiro.
            int vlSolconReduzidos = (int) Math.round(qtSoulCoins * 0.5);
            this.qtSoulCoins += vlSolconReduzidos;
            dsAtividade += String.format(" [%d pontos]", vlSolconReduzidos);
            registrarAtividade(dsAtividade);
        } else {
            // Menor que tres metade, maior já recebe todos os pontos normais
            dsAtividade += String.format("[%d pontos]", qtSoulCoins);
            this.qtSoulCoins += qtSoulCoins;
            registrarAtividade(dsAtividade);
        }
    }

    /**
     * Registra uma atividade que gerou pontos e um selo ao usuário.
     * @param dsAtividade é o texto descrevendo a atividade realizada.
     * @param qtSoulCoins e a quantidade de pontos gerada por aquela atividade.
     * @param selo        é o selo recebido
     * @throws IllegalArgumentException quando a dsAtvidade é vazia ou nula.
     */
    public void registrarAtividade(String dsAtividade, int qtSoulCoins, String selo) throws IllegalArgumentException {
        if (dsAtividade == null || dsAtividade.isBlank()) {
            throw new IllegalArgumentException("A descrição da ação não pode ser vazia");
        }
        if (selo == null && selo.isBlank()) {
            selo = "N/A"; //não aplicável
        }
        // a vlMerito do usuário deve alterar o recebimento de pontos
        if (vlMerito <= 30) {
            // Divide pela metade os pontos recebidos e então arredonda para o inteiro mais próximo pra depois transformar em Inteiro.
            int vlSolconReduzidos = (int) Math.round(qtSoulCoins * 0.5);
            this.qtSoulCoins += vlSolconReduzidos;
            dsAtividade += String.format(" [%d pontos]", vlSolconReduzidos);
            registrarAtividade(dsAtividade);
        } else {
            // Menor que tres metade, maior já recebe todos os pontos normais
            dsAtividade += String.format(" [%d pontos]", qtSoulCoins);
            this.qtSoulCoins += qtSoulCoins;
            registrarAtividade(dsAtividade);
        }
        selosGanhos.add(selo);
    }

    /**
     * Vai retornar todos os dados do perfil
     * @return Uma string formatada com tudo ajustado
     */
    public String detalhesPerfil() {
        String listaAtividaderecente = "";
        for (String atividade : dsAtividadeRecente) {
            listaAtividaderecente += "\n-" + atividade;
        }
        String listaSelosGanhos = "";
        for (String selo : selosGanhos) {
            listaSelosGanhos += "\n-"+ selo;
        }

        return String.format("Nome: %s\nId: %s\nConfiabilidade: %.1f\nSoul Coins: %d\nAtividade recente:%s\nSelos ganhos:%s", nmUsuario, idUsuario, vlMerito, qtSoulCoins, listaAtividaderecente, listaSelosGanhos);
    }

    /**
     * Registra uma penalidade de vlMerito para o usuário
     * @param valorPenalidade o valor que será descontado da vlMerito do usuário
     * @param descOcorrido    uma string que descreve o ocorrido de uma penalidade
     * @return uma string indicando o registro
     */
    public String registrarPenalidade(float valorPenalidade, String descOcorrido) {
        //Recebe valores positivos e não pode receber negativs, por isso o try
        try {
            // 0 vai permitir que seja "cancelado"
            if (valorPenalidade >= 0) {
                /*Se o valor da penalidade for maior que o valor atual então ele vai ser colocado como zero, isso impede que a confiablidade fique negativa, basicamente o mathmax vai pegar o maior valor, se o resultado for negativo será o 0*/
                this.vlMerito = Math.max(0, this.vlMerito -= valorPenalidade);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
        }
        return String.format("Penalidade registrada, %s perdeu %.1f%% de vlMerito, ficando com %.1f%%. Desc: %s", nmUsuario, valorPenalidade, vlMerito, descOcorrido);
    }
}