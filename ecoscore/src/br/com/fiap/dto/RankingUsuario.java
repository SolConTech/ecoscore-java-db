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
    public RankingUsuario(int idRanking, String idUsuario, int numPosicao, LocalDateTime dtAtualizacao) {
        this.idRanking = idRanking;
        this.idUsuario = idUsuario;
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
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    public int getQtVotos() {
        return qtVotos;
    }
    public void setQtVotos(int qtVotos) {
        this.qtVotos = qtVotos;
    }

    /*
    // Dentro do seu metodo Main ou classe de visualização:
RankingDAO dao = new RankingDAO();
ArrayList<RankingUsuario> ranking = RankingDAO.getRankingCompleto();

System.out.println("====== RANKING GLOBAL ======");

for (int i = 0; i < ranking.size(); i++) {
    int posicao = i + 1; // Calcula a posição dinamicamente (0+1=1, 1+1=2...)
    UsuarioRanking jogador = ranking.get(i);

    // Formata e exibe tudo diretamente na tela
    System.out.printf("%dº Lugar - %s (%d pontos)%n",
                      posicao, jogador.getNmMissao(), jogador.getPontuacao());
}  */
}
