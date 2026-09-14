package br.com.fiap.model.dto;

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
    public void setIdMissao(int idMissao) throws IllegalArgumentException{
        if (idMissao < 0) { //não pode ser negativo
            throw new IllegalArgumentException("Valor do ID não pode ser negativo.");
        }
        this.idMissao = idMissao;
    }
    public String getNmMissao() {
        return nmMissao;
    }
    public void setNmMissao(String nmMissao) throws IllegalArgumentException{
        if (nmMissao == null || nmMissao.isBlank()) {
            throw new IllegalArgumentException("A nome da missão não pode ser vazio.");
        }
        // varchar(25) no banco
        if (nmMissao.length() > 25) {
            throw new IllegalArgumentException("Nome da missão deve ter no máximo 25 caracteres.");
        }
        this.nmMissao = nmMissao;
    }
    public String getDsMissao() {
        return dsMissao;
    }
    public void setDsMissao(String dsMissao) throws IllegalArgumentException{
        if (dsMissao.length() > 200) { //dificl acontecer, mas validação é importante
            throw new IllegalArgumentException("Descrição da missão tem limite de 200 caracteres.");
        }
        this.dsMissao = dsMissao;
    }
    public String getSelo() {
        return selo;
    }
    public void setSelo(String selo) throws IllegalArgumentException{
        if (selo.length() > 30) {//banco varchar (30)
            throw new IllegalArgumentException("O nome do selo tem limite de 30 caracteres.");
        }
        this.selo = selo;
    }
    public int getQtPontosGerados() {
        return qtPontosGerados;
    }
    public void setQtPontosGerados(int qtPontosGerados) throws IllegalArgumentException{
        if (qtPontosGerados <= 0) {
            throw new IllegalArgumentException("Pontos da missão não podem ser negativos.");
        }
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
        if (dtMissao == null) { //Se uma data não tiver sido regisrtada
            dtMissao = LocalDateTime.now();
        }
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

