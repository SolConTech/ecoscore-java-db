package br.com.fiap.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Uma atividade criada pelo usuario, é o que define sua posição no ranking
 * @since Java 21
 */
public class Post {
    private int idPost;
    private String idUsuario; //FK de Usuario
    private String dsPost;
    private LocalDateTime dtPost;
    private int numUpVotes = 0; //começa em zero
    private int numDownVotes = 0; //começa em zero
    private int numSaldoVotes = 0; //começa em zero

    public Post() {}
    public Post(int idPost, String idUsuario,String dsPost) {
        this.idPost = idPost;
        setIdUsuario(idUsuario);
        dtPost = LocalDateTime.now();
    }

    public int getIdPost() {
        return idPost;
    }
    public void setIdPost(int idPost) {
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
        return String.format("Post publicado em (%s) com saldo de %d votos (+%d/-%d).",
                dtPost.format(dtf), numSaldoVotes, numUpVotes, numDownVotes);
    }
}