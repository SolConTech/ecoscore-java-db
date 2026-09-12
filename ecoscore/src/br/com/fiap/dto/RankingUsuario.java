package br.com.fiap.dto;

import java.time.LocalDateTime;

/**
 * Cada registro de um usuário do ranking
 */
public class RankingUsuario {
    private int idRanking;
    private String idUsuario;
    private int qtVotos;

    public RankingUsuario() {}
    public RankingUsuario(int idRanking, String idUsuario, int qtVotos) {
        this.idRanking = idRanking;
        this.idUsuario = idUsuario;
        this.qtVotos = qtVotos;
    }

    public int getIdRanking() {
        return idRanking;
    }
    public void setIdRanking(int idRanking) {
        this.idRanking = idRanking;
    }
    public String getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(String idUsuario) throws IllegalArgumentException{
        //máx 30 caracteres no banco
        if (idUsuario.length() > 30) {
            throw new IllegalArgumentException("Limite de caracteres é 30");
        }
        this.idUsuario = idUsuario.toLowerCase();
    }
    public int getQtVotos() {
        return qtVotos;
    }
    public void setQtVotos(int qtVotos) {
        this.qtVotos = qtVotos;
    }
    public void addQtVotos(int qtVotos){
        this.qtVotos += qtVotos;
    }
}
