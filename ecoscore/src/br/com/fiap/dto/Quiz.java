package br.com.fiap.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Uma atividade criada pelo usuário, capaz de gerar pontos
 * @since Java 21
 */
public class Quiz {
    private int idQuiz;
    private String idUsuario;
    private int numQuestoes;
    private int numAcertos;
    private int qtPontosPorQuestao;
    private int qtPontosGerados;
    private LocalDateTime dtQuiz;

    public Quiz() {}
    public Quiz(int idQuiz, String idUsuario, int numQuestoes, int numAcertos, int qtPontosPorQuestao) {
        this.idQuiz = idQuiz;
        this.idUsuario = idUsuario;
        setNumQuestoes(numQuestoes);
        setNumAcertos(numAcertos);
        setQtPontosPorQuestao(qtPontosPorQuestao);
        setQtPontosGerados();
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
    public int getNumQuestoes() {
        return numQuestoes;
    }
    public void setNumQuestoes(int numQuestoes) throws IllegalArgumentException {
        if (numQuestoes <= 0) {
            throw new IllegalArgumentException("A qtde. de questões deve ser maior que zero.");
        }
        this.numQuestoes = numQuestoes;
    }
    public int getNumAcertos() {
        return numAcertos;
    }
    public void setNumAcertos(int numAcertos) throws IllegalArgumentException {
        if (numAcertos <= 0 || numAcertos > numQuestoes) {
            throw new IllegalArgumentException("A qtde. questões deve ser maior que zero e menor que o número de questões");
        }
        this.numAcertos = numAcertos;
    }
    public int getQtPontosPorQuestao() {
        return qtPontosPorQuestao;
    }
    public void setQtPontosPorQuestao(int qtPontosPorQuestao) throws IllegalArgumentException {
        if (qtPontosPorQuestao <= 0) {
            throw new IllegalArgumentException("A qtde. de pontos por questão deve ser maior que zero.");
        }
        this.qtPontosPorQuestao = qtPontosPorQuestao;
    }
    public int getQtPontosGerados() {
        return qtPontosGerados;
    }
    public void setQtPontosGerados() {
        //depende apenas dos valores da própria classe.
        this.qtPontosGerados = numAcertos * qtPontosPorQuestao;
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
        setQtPontosGerados(); //se o objeto não for criado com construtor com passagem.
        return qtPontosGerados;
    }

    /**
     * Gera uma descrição da atividade
     * @return Uma formatação com data e número de pontos gerados
     */
    public String detalhesQuiz() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("Um quiz foi realizada em (%s), acertando %d/%d.", dtQuiz.format(dtf), numAcertos, numQuestoes);
    }
}
