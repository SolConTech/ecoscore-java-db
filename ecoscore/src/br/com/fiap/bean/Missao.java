package br.com.fiap.bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe que vai guardar informações de uma missão
 * @since Java 21
 */
public class Missao {
    private int idMissao;
    private String nmMissao;
    private String dsMissao;
    private String selo; //demonstração infelizmente não sei se consigo exibir os selos dps.
    private int qtPontosGerados;
    private LocalDateTime dtMissao;

    public Missao() {}
    public Missao(int idMissao, String nmMissao, String dsMissao, String selo, int qtPontosGerados) {
        this.idMissao = idMissao;
        this.nmMissao = nmMissao;
        this.dsMissao = dsMissao;
        this.selo = selo;
        this.qtPontosGerados = qtPontosGerados;
        dtMissao = LocalDateTime.now();
    }

    public int getIdMissao() {
        return idMissao;
    }
    public void setIdMissao(int idMissao) {
        this.idMissao = idMissao;
    }
    public String getNmMissao() {
        return nmMissao;
    }
    public void setNmMissao(String nmMissao) {
        this.nmMissao = nmMissao;
    }
    public String getDsMissao() {
        return dsMissao;
    }
    public void setDsMissao(String dsMissao) {
        this.dsMissao = dsMissao;
    }
    public String getSelo() {
        return selo;
    }
    public void setSelo(String selo) {
        this.selo = selo;
    }
    public int getQtPontosGerados() {
        return qtPontosGerados;
    }
    public void setQtPontosGerados(int qtPontosGerados) {
        this.qtPontosGerados = qtPontosGerados;
    }
    public LocalDateTime getDtMissao() {
        return dtMissao;
    }
    public void setDtMissao(LocalDateTime dtMissao) {
        this.dtMissao = dtMissao;
    }

    /**
     * Retornar ao Usuario quantos pontos essa atividade gerou
     * @return o número de pontos gerados
     */
    public int registrarPontos() {
        return qtPontosGerados;
    }

    /**
     * Informações completas da missão.
     * @return String formatada com idMissao, nmMissao, descrição, progresso e tarefas.
     */
    public String detalhesMissao() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("Missão \"%s\" realizada em (%s).",
                nmMissao, dtMissao.format(dtf));
    }
}

