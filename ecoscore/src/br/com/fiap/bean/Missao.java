package br.com.fiap.bean;

import javax.swing.*;
import java.util.HashMap;

/**
 * Classe que vai guardar informações de uma missão
 * @since Java 21
 */
public class Missao {
    private int id;
    private String nome;
    private String descricao;
    private ImageIcon selo; //demonstração infelizmente não sei se consigo exibir os selos dps.
    private HashMap<String, Boolean> tarefas = new HashMap<String, Boolean>();
    private float progresso = 0.0f; // é porcentagem, 0 de 10 tarefas exemplo 10%.
    public Missao() {}
    public Missao(int id, String nome, String descricao, ImageIcon selo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.selo = selo;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public ImageIcon getSelo() {
        return selo;
    }
    public void setSelo(ImageIcon selo) {
        this.selo = selo;
    }
    public HashMap<String, Boolean> getTarefas() {
        return tarefas;
    }
    public void setTarefas(HashMap<String, Boolean> tarefas) {
        this.tarefas = tarefas;
    }
    public float getProgresso() {
        return progresso;
    }
    public void setProgresso(float progresso) {
        this.progresso = progresso;
    }

    /**
     * Vai atualizar o progresso da missão, ela usada dentro da classe para atualizar o valor do atributo "progresso"
     */
    private void atualizarProgresso() {
        //privada piorque sói a classe vaiu usar mesmo, ela vai excutar no fuim de cada metodo de tarefa
        //validação básica pra ver se as tarefas estão vazias
        if (tarefas.isEmpty()) {
            progresso = 0.0f;
            return;
        }
        //Stream API que vê os valroes boolean, se for igual a rtue ele vai pra contagem, o count é o final da esteira e vai somar cada valor true
        float concluidas = tarefas.values().stream().filter(estado -> estado == true).count();
        //exemplo 1/10 = 0.10, 0.10 * 100 é igual 10, que seria 10% que é iugal 1/10
        this.progresso = (concluidas / tarefas.size()) * 100.0f; //f pra gArantir float
    }

    /**
     * Adiciona uma tarefa à missão
     * @param tarefaNome o nome da tarefa, com o que tem que ser feito
     * @throws IllegalArgumentException Quando o nome da tarefa é vazio, com mais de 50 de lenght ou já existe na Missao
     */
    public void adicionarTarefa(String tarefaNome) throws IllegalArgumentException {
        //Não pode ser vazia
        if (tarefaNome.isBlank()) {
            throw new IllegalArgumentException("A tarefa não pode ser vazia");
        }
        //regra de negócio, tarefas objetivas de no máximo 50 caracteres.
        if (tarefaNome.length() > 50) {
            throw new IllegalArgumentException("A tarefa deve ter no máximo 50 caracteres");
        }
        if (tarefas.containsKey(tarefaNome)) {
            throw new IllegalArgumentException(String.format("A tarefa \"%s\" já existe", tarefaNome));
        }
        tarefas.put(tarefaNome, false);
        //false == tarefa não realizada e true é igual a tarefa feita
        atualizarProgresso();
    }

    /**
     * Conclui uma das tarefas da missão pelo nome.
     * @param tarefaNome o nome da tarefa que será concluída
     * @return uma mensagem dizendo que a tarefa fopi concluída, ou se ela já tinha sido antes.
     * @throws IllegalArgumentException quando a tarefa não existe
     */
    public String concluirTarefa(String tarefaNome) throws IllegalArgumentException {
        if (! tarefas.containsKey(tarefaNome)) {
            throw new IllegalArgumentException(String.format("A tarefa \"%s\" não existe", tarefaNome));
        }
        // como as chaves são boolean se for true a tarefa já foi feirta.
        if (tarefas.get((tarefaNome))) {
            return "Tarefa \"" + tarefaNome + "\" já foi concluída anteriormente";
        }
        tarefas.put(tarefaNome, true);
        atualizarProgresso();
        return "Tarefa \"" + tarefaNome + "\" concluída";
    }

    /**
     * Informações completas da missão.
     * @return String formatada com id, nome, descrição, progresso e tarefas.
     */
    public String detalhesMissao() {
        String trfs = "";
        for (String tarefa : tarefas.keySet()) {
            trfs += tarefas + "\n";
        }
        if (tarefas.isEmpty()) {
            trfs += "Nenhuma adicionada. Progresso também zero";
        }
        return String.format("Id: %d Nome: %s\nDescrição: %s\nProgresso:%.1f%%\nTarefas:%s\n",
                id, nome, descricao, progresso, trfs);
    }
}
