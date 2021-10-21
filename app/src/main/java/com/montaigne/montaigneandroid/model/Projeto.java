package com.montaigne.montaigneandroid.model;

import java.io.Serializable;
import java.util.Date;

public class Projeto implements Serializable {
    private Long id;
    private String nome, cliente, empresa, telefone, tecnicoResponsavel, endereco;
    private int numeroFuros;
    private Date dataInicio;

    public Projeto() {
    }

    public Projeto(Long id, String nome, String cliente, String empresa,
                   String telefone, String tecnicoResponsavel, String endereco, int numeroFuros,
                   Date dataInicio) {
        this.id = id;
        this.nome = nome;
        this.cliente = cliente;
        this.empresa = empresa;
        this.telefone = telefone;
        this.tecnicoResponsavel = tecnicoResponsavel;
        this.endereco = endereco;
        this.numeroFuros = numeroFuros;
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

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTecnicoResponsavel() {
        return tecnicoResponsavel;
    }

    public void setTecnicoResponsavel(String tecnicoResponsavel) {
        this.tecnicoResponsavel = tecnicoResponsavel;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumeroFuros() {
        return numeroFuros;
    }

    public void setNumeroFuros(int numeroFuros) {
        this.numeroFuros = numeroFuros;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
}
