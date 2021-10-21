package com.montaigne.montaigneandroid.model;

import java.time.LocalDateTime;

public class Sondagem {
    private Long id, idSpt;
    private int numero;
    private float nivelDAgua;
    private float nivelFuro;
    private float nivelReferencia;
    private float totalPerfurado;
    private String coordenada;
    private LocalDateTime dataInicio;

    public Sondagem(){

    }

    public Sondagem(Long id, Long id_Spt, int numero, float nivelDAgua, float nivelFuro, float nivelReferencia,
                    float totalPerfurado, String coordenada, LocalDateTime dataInicio) {
        this.id = id;
        this.idSpt = id;
        this.numero = numero;
        this.nivelDAgua = nivelDAgua;
        this.nivelFuro = nivelFuro;
        this.nivelReferencia = nivelReferencia;
        this.totalPerfurado = totalPerfurado;
        this.coordenada = coordenada;
        this.dataInicio = dataInicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdSpt() {
        return idSpt;
    }

    public void setIdSpt(Long idSpt) {
        this.idSpt = idSpt;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public float getNivelDAgua() {
        return nivelDAgua;
    }

    public void setNivelDAgua(float nivelDAgua) {
        this.nivelDAgua = nivelDAgua;
    }

    public float getNivelFuro() {
        return nivelFuro;
    }

    public void setNivelFuro(float nivelFuro) {
        this.nivelFuro = nivelFuro;
    }

    public float getNivelReferencia() {
        return nivelReferencia;
    }

    public void setNivelReferencia(float nivelReferencia) {
        this.nivelReferencia = nivelReferencia;
    }

    public float getTotalPerfurado() {
        return totalPerfurado;
    }

    public void setTotalPerfurado(float totalPerfurado) {
        this.totalPerfurado = totalPerfurado;
    }

    public String getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(String coordenada) {
        this.coordenada = coordenada;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }
}
