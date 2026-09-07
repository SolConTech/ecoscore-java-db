package br.com.fiap.bean;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Classe que vai guardar as informações de cada usuário
 * @since Java 21
 */
public class Usuario {
    private String idUsuario;
    private String nome;
    private float confiabilidade;
    private int soulCoins;
    private ArrayList<String> atividadeRecente; //Últimas ações realizadas

    public Usuario() {}
    // Construtor para registrar apenas nome do usuário e idUsuario, pois ambos não tem validação
    public Usuario(String nome, String idUsuario) {
        this.nome = nome;
        setIdUsuario(idUsuario);
    }
    // Construtor para registrar usuário quas completo (nome, idUsuario, confiabilidade e soul coins).
    public Usuario(String nome, String idUsuario, float confiabilidade, int soulCoins) {
        this.nome = nome;
        setIdUsuario(idUsuario);
        setConfiabilidade(confiabilidade);
        setSoulCoins(soulCoins);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    // A idUsuario é sempre minúscula.
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario.toLowerCase();
    }

    public float getConfiabilidade() {
        return confiabilidade;
    }

    // Confiabilidade varia entre 0 e 100%, e é mostrada como porcentagem
    public void setConfiabilidade(float confiabilidade) {
        try {
            if (confiabilidade >= 0 && confiabilidade <= 100) {
                this.confiabilidade = confiabilidade;
            } else {
                // 50 é a confiabilidade padrão
                setConfiabilidade(50);
                throw new Exception("Valor inválido, digite um número de 1 a 100 para confiabilidade");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int getSoulCoins() {
        return soulCoins;
    }

    public void setSoulCoins(int soulCoins) {
        try {
            if (soulCoins >= 0) {
                this.soulCoins = soulCoins;
            } else {
                //0 é a confiabilidade padrão mesmo
                throw new Exception("O número de Soul coins não pode ser negativo");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<String> getAtividadeRecente() {
        return atividadeRecente;
    }

    public void setAtividadeRecente(ArrayList<String> atividadeRecente) {
        this.atividadeRecente = atividadeRecente;
    }

    /**
     * Vai registrar uma ação sustentavel para o user.
     * @param soulCoins recebe a quantidade de soulCoins ganhos
     * @param descAcao  é o texto descrevendo a ação realizada
     */
    public void registrarAcao(int soulCoins, String descAcao) {
        // a confiabilidade do usuário deve alterar o recebimento de pontos
        if (confiabilidade <= 30) {
            // Divide pela metade os pontos recebidos e então arredonda para o inteiro mais próximo pra depois transformar em Inteiro.
            this.soulCoins += (int) Math.round(soulCoins * 0.5);
            descAcao += "[pontos reduzidos pela metade]";
        } else {
            // Menor que tres metade, maior já recebe todos os pontos normais
            this.soulCoins += soulCoins;
        }
        atividadeRecente.add(descAcao);
    }

    /**
     * Vai retornar todos os dados do perfil
     * @return Uma string formatada com tudo ajustado
     */
    public String detalhesPerfil() {
        String listaAtividaderecente = "";
        for (String atividade : atividadeRecente) {
            listaAtividaderecente += atividade + "\n";
        }
        return String.format("Nome: %s\nId: %s\nConfiabilidade: %.1f\nSoul Coins: %d\nAtividade recente:\n%s", nome, idUsuario, confiabilidade, soulCoins, atividadeRecente);
    }

    /**
     * Registra uma penalidade de confiabilidade para o usuário
     * @param valorPenalidade o valor que será descontado da confiabilidade do usuário
     * @param descOcorrido    uma string que descreve o ocorrido de uma penalidade
     * @return uma string indicando o registro
     */
    public String registrarPenalidade(float valorPenalidade, String descOcorrido) {
        //Recebe valores positivos e não pode receber negativs, por isso o try
        try {
            // 0 vai permitir que seja "cancelado"
            if (valorPenalidade >= 0) {
                /*Se o valor da penalidade for maior que o valor atual então ele vai ser colocado como zero, isso impede que a confiablidade fique negativa, basicamente o mathmax vai pegar o maior valor, se o resultado for negativo será o 0*/
                this.confiabilidade = Math.max(0, this.confiabilidade -= valorPenalidade);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
        }
        return String.format("Penalidade registrada, %s perdeu %.1f%% de confiabilidade, ficando com %.1f%%. Desc: %s", nome, valorPenalidade, confiabilidade, descOcorrido);
    }
}