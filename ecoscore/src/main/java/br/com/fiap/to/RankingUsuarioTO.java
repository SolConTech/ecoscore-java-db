package br.com.fiap.to;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Cada registro de um usuário do ranking
 */
public class RankingUsuarioTO {
    @Min(value = 0, message = "Valor do id não pode ser negativo")
    @NotNull(message = "Valor não pode ser vazio")
    private Integer idRanking;
    @Size(max = 30, message = "IDs de usuário tem no máximo 30 caracteres")
    @NotBlank(message = "Valor não pode ser vazio")
    private String idUsuario;
    @Min(value = 0, message = "Valor não pode ser negativo")
    @NotNull(message = "Valor não pode ser vazio")
    private int qtVotos;

    public RankingUsuarioTO() {}
    public RankingUsuarioTO(String idUsuario, int qtVotos) {
        this.idRanking = idRanking;
        this.idUsuario = idUsuario;
        this.qtVotos = qtVotos;
    }

    public Integer getIdRanking() {
        return idRanking;
    }
    public void setIdRanking(Integer idRanking) throws IllegalArgumentException{
        if (idRanking < 0) { //não pode ser negativo
            throw new IllegalArgumentException("Valor do ID não pode ser negativo.");
        }
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
