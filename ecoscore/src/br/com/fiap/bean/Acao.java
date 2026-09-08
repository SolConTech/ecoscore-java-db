package br.com.fiap.bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Registra uma ação simples, registrada por QR code ou outros
 * @since Java 21
 */
public class Acao {
    private int idAcao;
    private String idUsuario; //FK de Usuario.
    private String dsAcao; //x reciclados
    private int qtPontosGerados;
    private LocalDateTime dtAcao;

    public Acao() {}
    //Registra apenas o dsAcao da ação, já que data e pontos são obtidos pelos métodos.
    public Acao(String dsAcao) {
        this.dsAcao = dsAcao;
    }

    public String getDsAcao() {
        return dsAcao;
    }
    public void setDsAcao(String dsAcao) {
        this.dsAcao = dsAcao;
    }
    public int getQtPontosGerados() {
        return qtPontosGerados;
    }
    public void setQtPontosGerados(int qtPontosGerados) {
        this.qtPontosGerados = qtPontosGerados;
    }
    public LocalDateTime getDtAcao() {
        return dtAcao;
    }
    public void setDtAcao(LocalDateTime dtAcao) {
        this.dtAcao = dtAcao;
    }

    /**
     * Registrar uma ação
     * @param pontosGerados o número de pontos dessa ação
     * @return O número de Soul Coins gerados
     */
    public int registrarPontos(int pontosGerados) {
        dtAcao = LocalDateTime.now();
        qtPontosGerados = pontosGerados;
        return qtPontosGerados;
    }

    /**
     * Mostra uma descrição da ação realizada * @return retorna uma string com a descrição
     */
    public String detalhesAcao() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("A ação \"%s\" foi realizada em (%s).", dsAcao, dtAcao.format(dtf));
    }
}