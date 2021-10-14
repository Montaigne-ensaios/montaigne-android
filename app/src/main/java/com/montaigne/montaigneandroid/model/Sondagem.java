package com.montaigne.montaigneandroid.model;

import java.time.LocalDateTime;

public class Sondagem {
    private Long id;
    private int numero;
    private float nivelDAgua;
    private float cota;
    private float totalPerfurado;
    private String coordenada;
    private LocalDateTime dataInicio;

    public Sondagem(){

    }

    public Sondagem(Long id, int numero, float nivelDAgua,
                    float cota, float totalPerfurado,
                    String coordenada, LocalDateTime dataInicio) {
        this.id = id;
        this.numero = numero;
        this.nivelDAgua = nivelDAgua;
        this.cota = cota;
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

    public float getCota() {
        return cota;
    }

    public void setCota(float cota) {
        this.cota = cota;
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
