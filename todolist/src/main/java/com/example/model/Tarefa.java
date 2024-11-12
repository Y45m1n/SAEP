package com.example.model;

public class Tarefa {
    private int id;
    private int usuarioId;
    private String descricao;
    private String nomeSetor;
    private String prioridade;
    private String status;

    // Construtores, getters e setters
    public Tarefa(int id, int usuarioId, String descricao, String nomeSetor, String prioridade, String status) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.descricao = descricao;
        this.nomeSetor = nomeSetor;
        this.prioridade = prioridade;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getDataCriacao() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDataCriacao'");
    }
}
