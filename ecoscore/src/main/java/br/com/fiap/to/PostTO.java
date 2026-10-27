package br.com.fiap.to;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Uma atividade criada pelo usuario, é o que define sua posição no ranking
 * @since Java 21
 */
public class PostTO {
    @Min(value = 0, message = "Valor do id não pode ser negativo")
    @NotNull(message = "Valor não pode ser vazio")
    private Integer idPost;
    @Size(max = 30, message = "IDs de usuário tem no máximo 30 caracteres")
    @NotBlank(message = "Valor não pode ser vazio")
    private String idUsuario; //FK de UsuarioTO
    @Size(max = 200, message = "Limite de caracteres é 200 para a descrição da missão")
    @NotBlank(message = "A descrição do post não pode ser vazia")
    private String dsPost;
    @PastOrPresent(message = "O post não pode ter sido publicado no futuro")
    private LocalDateTime dtPost;
    @Min(value = 0, message = "Valor não pode ser negativo")
    private int numUpVotes = 0; //começa em zero
    @Min(value = 0, message = "Valor do id não pode ser negativo")
    private int numDownVotes = 0; //começa em zero
    private int numSaldoVotes = 0; //começa em zero

    public PostTO() {}
    public PostTO(String idUsuario, String dsPost) {
        this.idPost = idPost;
        this.dsPost = dsPost;
        setIdUsuario(idUsuario);
        dtPost = LocalDateTime.now();
    }

    public Integer getIdPost() {
        return idPost;
    }
    public void setIdPost(Integer idPost) {
        if (idPost < 0) { //não pode ser negativo
            throw new IllegalArgumentException("Valor do ID não pode ser negativo.");
        }
        this.idPost = idPost;
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
    public String getDsPost() {
        return dsPost;
    }
    public void setDsPost(String dsPost) throws IllegalArgumentException{
        if (dsPost == null || dsPost.isBlank()) {
            throw new IllegalArgumentException("A descrição do post não pode ser vazia, a comunidade precisa entender ele!");
        }
        if (dsPost.length() > 200) {
            throw new IllegalArgumentException("O post não pode ter uma descrição acima de 200 caracteres");
        }
        this.dsPost = dsPost;
    }
    public LocalDateTime getDtPost() {
        return dtPost;
    }
    public void setDtPost(LocalDateTime dtPost) {
        this.dtPost = dtPost;
    }
    public int getNumUpVotes() {
        return numUpVotes;
    }
    public void setNumUpVotes(int numUpVotes) {
        this.numUpVotes = numUpVotes;
    }
    public int getNumDownVotes() {
        return numDownVotes;
    }
    public void setNumDownVotes(int numDownVotes) {
        this.numDownVotes = numDownVotes;
    }
    public int getNumSaldoVotes() {
        return numSaldoVotes;
    }
    public void setNumSaldoVotes() {
        this.numSaldoVotes = numUpVotes - numDownVotes;
    }

    /**
     * Publicar um post, dando data e descrição.
     * @param dsPost é a descrição do post criado.
     */
    public void publicar(String dsPost) {
        dtPost = LocalDateTime.now();
        setDsPost(dsPost); //Lá tem uma validação já.
    }

    /**
     * Adiciona votos positivos para o post
     * @param qtUpVotes quantidade de votos positivos para adicionar
     */
    public void addUpVote(int qtUpVotes) throws IllegalArgumentException {
        if (qtUpVotes <= 0) {
            throw new IllegalArgumentException("Número de votos precisa ser maior que zero");
        }
        numUpVotes += qtUpVotes;
        setNumSaldoVotes();
    }

    /**
     * Remove votos positivos para o post
     * @param qtDownVotes quantidade de votos negativos para adicionar
     */
    public void addDownVote(int qtDownVotes) throws IllegalArgumentException {
        if (qtDownVotes <= 0) {
            throw new IllegalArgumentException("Número de votos precisa ser maior que zero");
        }
        numDownVotes += qtDownVotes;
        setNumSaldoVotes();
    }

    /**
     * Mostra os detalhes do post, descrição, autor, ação atrelada, data e quantidade de votos.
     * @return Uma string formatada com as informações do post.
     */
    public String detalhesPost() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' hh:mm");
        return String.format("PostTO publicado em (%s) com saldo de %d votos (+%d/-%d).",
                dtPost.format(dtf), numSaldoVotes, numUpVotes, numDownVotes);
    }
}