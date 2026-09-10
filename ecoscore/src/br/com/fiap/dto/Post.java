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
    private Integer idAcao; //FK de Acao, é Integer pois pode ser null, post pode ser feito sem Ação
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
        this.idPost = idPost;
    }
    public String getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario.toLowerCase();
    }
    public Integer getIdAcao() {
        return idAcao;
    }
    public void setIdAcao(Integer idAcao) {
        this.idAcao = idAcao;
    }
    public String getDsPost() {
        return dsPost;
    }
    public void setDsPost(String dsPost) {
        if (dsPost == null || dsPost.isBlank()) {
            throw new IllegalArgumentException("A descrição do post não pode ser vazia, a comunidade precisa entender ele!");
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
    public void setNumSaldoVotes(int numSaldoVotes) {
        this.numSaldoVotes = numSaldoVotes;
    }

    /**
     * Será executada a cada voto adicionado, recalcula o saldo de votos com base no up e no down votes
     */
    private void updtSaldoVotes() {
        /*Ela é privada, pois só vai mexer com e nos valores da própria classe,
        sendo usada toda vez que um novo voto for adicionado,
        mas se fosse pública não daria nenhum erro, mas seria inútil
        */
        numSaldoVotes = numUpVotes - numDownVotes;
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
        updtSaldoVotes();
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
        updtSaldoVotes();
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