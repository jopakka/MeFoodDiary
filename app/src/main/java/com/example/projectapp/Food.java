package com.example.projectapp;

public class Food {
    private String name;
    private String energia;
    private String rasva;
    private String hiilihyraatti;
    private String kuitu;
    private String proteiini;
    private String suola;


    public Food(String name, String energia, String rasva, String hiilihyraatti, String kuitu, String proteiini, String suola) {
        this.name = name;
        this.energia = energia;
        this.rasva = rasva;
        this.hiilihyraatti = hiilihyraatti;
        this.kuitu = kuitu;
        this.proteiini = proteiini;
        this.suola = suola;
    }

    public String getName() {
        return name;
    }

    public String getEnergia() {
        return energia;
    }

    public String getRasva() {
        return rasva;
    }

    public String getHiilihyraatti() {
        return hiilihyraatti;
    }

    public String getKuitu() {
        return kuitu;
    }

    public String getProteiini() {
        return proteiini;
    }

    public String getSuola() {
        return suola;
    }

    @Override
    public String toString() {
        return name;
    }
}
