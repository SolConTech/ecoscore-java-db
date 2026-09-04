package br.com.fiap.bean;

import javax.management.openmbean.KeyAlreadyExistsException;
import javax.swing.*;
import java.util.HashMap;
import java.util.HashSet;

public class Missao {
    private int id;
    private String nome;
    private String descricao;
    private ImageIcon selo; //demonstração infelizmente não sei se consigo exibir os selos dps.
    private HashMap<String, Boolean> tarefas = new HashMap<String, Boolean>();
    private float progresso = 0.0f; // é porcentagem, 0 de 10 tarefas exemplo 10%.
    public Missao(){}
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
    //privada piorque sói a classe vaiu usar mesmo, ela vai excutar no fuim de cada metodo de tarefa
    private void atualizarProgresso(){
        //validação básica pra ver se as tarefas estão vazias
        if (tarefas.isEmpty()){
            progresso = 0.0f;
            return;
        }
        //Stream API que vê os valroes boolean, se for igual a rtue ele vai pra contagem, o count é o final da esteira e vai somar cada valor true
        float concluidas = tarefas.values().stream().filter(estado -> estado == true).count();
        //exemplo 1/10 = 0.10, 0.10 * 100 é igual 10, que seria 10% que é iugal 1/10
        this.progresso = (concluidas/ tarefas.size()) * 100.0f; //f pra gArantir float
    }

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
    public String concluirTarefa(String tarefaNome){
        if (!tarefas.containsKey(tarefaNome)) {
            throw new IllegalArgumentException(String.format("A tarefa \"%s\" não existe", tarefaNome));
        }
        // como as chaves são boolean se for true a tarefa já foi feirta.
        if (tarefas.get((tarefaNome))){
            return "Tarefa \"" + tarefaNome + "\" já foi concluída anteriormente";
        }
        tarefas.put(tarefaNome, true);
        atualizarProgresso();
        return "Tarefa \"" + tarefaNome + "\" concluída";
    }
    public String detalhesMissao(){
        String trfs = "";
        for (String tarefa : tarefas.keySet()){
            trfs += tarefas + "\n";
        }
        if (tarefas.isEmpty()){
            trfs += "Nenhuma adicionada. Progresso também zero";
        }
        return String.format("Nome: %s\nDescrição: %s\nProgresso:%.1f%%\nTarefas:%s\n",
                             nome, descricao, progresso, trfs);

    }
}
