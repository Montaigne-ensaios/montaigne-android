package com.montaigne.montaigneandroid.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Projeto implements Serializable {
    private Long id;
    private String nome;
    private String endereco, telefone, tecnico, responsavel;
    private LocalDateTime dataInicio;

    // Perguntar ao professor se adiciona o objeto ou coloca o id
    public Projeto() {
    }

    public Projeto(Long id, String nome, String endereco, String telefone, String tecnico, String responsavel, LocalDateTime dataInicio) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.tecnico = tecnico;
        this.responsavel = responsavel;
        this.dataInicio = dataInicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }
}
