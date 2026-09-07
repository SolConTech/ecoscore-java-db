package br.com.fiap.bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post {
    private int idPost;
    private String idUsuario; //FK de Usuario
    private Integer idAcao; //FK de Acao, é Integer pois pode ser null, post pode ser feito sem Ação
    private String dsPost;
    private LocalDateTime dtPost;
    private int numUpVotes;
    private int numDownVotes;
    private int numSaldoVotes;

    public Post() {}
    public Post(int idPost, String idUsuario) {
        this.idPost = idPost;
        this.idUsuario = idUsuario;
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
        this.idUsuario = idUsuario;
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
    public void addUpVote(int qtUpVotes) {
        numUpVotes += qtUpVotes;
    }

    /**
     * Remove votos positivos para o post
     * @param qtDownVotes quantidade de votos negativos para adicionar
     */
    public void addDownVote(int qtDownVotes) {
        numDownVotes += qtDownVotes;
    }

    /**
     * Mostra os detalhes do post, descrição, autor, ação atrelada, data e quantidade de votos.
     * @return Uma string formatada com as informações do post.
     */
    public String detalhes() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' hh:mm");
        String detalhes = String.format("Autor = ?"); //Adicionar informação do usuário vindo do DAO
        if (idAcao != null) {
            //se for atrelado a uma ação a FK terá algum valor diferente de nulo
            detalhes += String.format("\nAção = ?"); //Adicionar informação da ação vindo do DAO
        }
        detalhes += String.format("Publicado em: %s\nPositivos: %d | Negativos: %d | Saldo: %d", dtPost.format(dtf), numUpVotes, numDownVotes, numSaldoVotes);
        return detalhes;
    }
}