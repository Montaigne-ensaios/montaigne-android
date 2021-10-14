package com.montaigne.montaigneandroid.model;

public class Amostra {
    private long id;
    private int golpes1, golpes2, golpes3, nspt;

    public Amostra() {

    }

    public Amostra(long id, int golpes1, int golpes2, int golpes3, int nspt) {
        this.id = id;
        this.golpes1 = golpes1;
        this.golpes2 = golpes2;
        this.golpes3 = golpes3;
        this.nspt = nspt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGolpes1() {
        return golpes1;
    }

    public void setGolpes1(int golpes1) {
        this.golpes1 = golpes1;
    }

    public int getGolpes2() {
        return golpes2;
    }

    public void setGolpes2(int golpes2) {
        this.golpes2 = golpes2;
    }

    public int getGolpes3() {
        return golpes3;
    }

    public void setGolpes3(int golpes3) {
        this.golpes3 = golpes3;
    }

    public int getNspt() {
        return nspt;
    }

    public void setNspt(int nspt) {
        this.nspt = nspt;
    }
}
