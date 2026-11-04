package br.com.fiap.to;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Registra uma ação simples, registrada por QR code ou outros
 * @since Java 21
 */
public class AcaoTO {
    @Positive(message = "Valor do id não pode ser negativo")
    @Null(message = "ID vai ser registrado pelo próprio sistema")
    private Integer idAcao;
    @Size(max = 30, message = "IDs de usuário tem no máximo 30 caracteres")
    @NotBlank(message = "Valor não pode ser vazio")
    private String idUsuario; //FK de UsuarioTO.
    @Size(max = 50, message = "Limite de caracteres é 50 para a descrição da ação")
    @NotBlank(message = "Decrição da ação não pode ser vazia")
    private String dsAcao; //x reciclados
    @Min(value = 0, message = "Valor do id não pode ser negativo")
    private int qtPontosGerados = 0;
    @PastOrPresent(message = "A ação não pode ter ocorrido no futuro")
    private LocalDateTime dtAcao;

    public AcaoTO() {
        dtAcao = LocalDateTime.now();
    }
    //Registra apenas o dsAcao da ação, já que data e pontos são obtidos pelos métodos.
    public AcaoTO(String dsAcao) {
        this.dsAcao = dsAcao;
        dtAcao = LocalDateTime.now();
    }
    public AcaoTO(Integer idAcao, String idUsuario, String dsAcao) {
        this.idAcao = idAcao;
        this.idUsuario = idUsuario;
        this.dsAcao = dsAcao;
        dtAcao = LocalDateTime.now();
    }

    public Integer getIdAcao() {
        return idAcao;
    }
    public void setIdAcao(Integer idAcao) throws IllegalArgumentException{
        if (idAcao < 0) { //não pode ser negativo
            throw new IllegalArgumentException("Valor do ID não pode ser negativo.");
        }
        this.idAcao = idAcao;
    }
    public String getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(String idUsuario) throws IllegalArgumentException{
        //máx 30 caracteres no banco
        if (idUsuario.length() > 30) {
            throw new IllegalArgumentException("Limite de caracteres é 30");
        }
        this.idUsuario = idUsuario;
    }
    public String getDsAcao() {
        return dsAcao;
    }
    public void setDsAcao(String dsAcao) throws IllegalArgumentException{
        if (dsAcao == null || dsAcao.isBlank()) {
            throw new IllegalArgumentException("A descrição da ação não pode ser vazia.");
        }
        if (dsAcao.length() > 50) {//varchar(50) no banco
            throw new IllegalArgumentException("Limite de caracteres é 50");
        }
        this.dsAcao = dsAcao;
    }
    public int getQtPontosGerados() {
        return qtPontosGerados;
    }
    public void setQtPontosGerados(int qtPontosGerados) {
        if (qtPontosGerados <= 0) {
            throw new IllegalArgumentException("Pontos da ação não podem ser negativos.");
        }
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
        if (dtAcao == null) {
            dtAcao = LocalDateTime.now();
        }
        setQtPontosGerados(pontosGerados);
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