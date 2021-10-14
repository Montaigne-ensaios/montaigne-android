package com.montaigne.montaigneandroid.model;

import java.io.Serializable;

public class Ensaio implements Serializable {
    private String nome;
    private int imagem;

    public Ensaio(String nome, int imagem) {
        this.nome = nome;
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public int getImagem() {
        return imagem;
    }
}
