package br.com.fiap.bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Uma atividade criada pelo usuário, capaz de gerar pontos
 * @since Java 21
 */
public class Quiz {
    private int idQuiz;
    private String idUsuario;
    private int numQuestao;
    private int numAcertos;
    private int qtPontosPorQuestao;
    private int qtPontosGerados;
    private LocalDateTime dtQuiz;

    public Quiz() {}
    public Quiz(int idQuiz, String idUsuario, int numQuestao, int numAcertos, int qtPontosPorQuestao, int qtPontosGerados) {
        this.idQuiz = idQuiz;
        this.idUsuario = idUsuario;
        this.numQuestao = numQuestao;
        this.numAcertos = numAcertos;
        this.qtPontosPorQuestao = qtPontosPorQuestao;
        this.qtPontosGerados = qtPontosGerados;
        this.dtQuiz = LocalDateTime.now();
    }

    public int getIdQuiz() {
        return idQuiz;
    }
    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }
    public String getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario.toLowerCase();
        ;
    }
    public int getNumQuestao() {
        return numQuestao;
    }
    public void setNumQuestao(int numQuestao) {
        this.numQuestao = numQuestao;
    }
    public int getNumAcertos() {
        return numAcertos;
    }
    public void setNumAcertos(int numAcertos) {
        this.numAcertos = numAcertos;
    }
    public int getQtPontosPorQuestao() {
        return qtPontosPorQuestao;
    }
    public void setQtPontosPorQuestao(int qtPontosPorQuestao) {
        this.qtPontosPorQuestao = qtPontosPorQuestao;
    }
    public int getQtPontosGerados() {
        return qtPontosGerados;
    }
    public void setQtPontosGerados(int qtPontosGerados) {
        this.qtPontosGerados = qtPontosGerados;
    }
    public LocalDateTime getDtQuiz() {
        return dtQuiz;
    }
    public void setDtQuiz(LocalDateTime dtQuiz) {
        this.dtQuiz = dtQuiz;
    }

    /**
     * Serve para retornar ao usuário quantos pontos essa atividade gerou
     * @return a quantidade de acertos vezes o quanto cada ponto vale
     */
    public int registrarPontos() {
        return numAcertos * qtPontosPorQuestao;
    }

    /**
     * Gera uma descrição da atividade
     * @return Uma formatação com data e número de pontos gerados
     */
    public String detalhesQuiz() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("Um quiz foi realizada em (%s) e gerou %d Soul Coins.", dtQuiz.format(dtf), qtPontosGerados);
    }
}
