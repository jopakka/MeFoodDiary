package com.example.projectapp.food_stuff;

/**
 * Food object class
 */
public class Food {
    private String name;
    private String energia;
    private String rasva;
    private String hiilihyraatti;
    private String kuitu;
    private String proteiini;
    private String suola;
    private int id;

    /**
     * Creates food object
     * @param id Foods ID number
     * @param name Foods name
     * @param energia Foods energy
     * @param rasva Foods fat
     * @param hiilihyraatti Foods carbonhydrate
     * @param kuitu Foods fiber
     * @param proteiini Foods protein
     * @param suola Foods salt
     */
    public Food(int id, String name, String energia, String rasva, String hiilihyraatti, String kuitu, String proteiini, String suola) {
        this.id = id;
        this.name = name;
        this.energia = energia;
        this.rasva = rasva;
        this.hiilihyraatti = hiilihyraatti;
        this.kuitu = kuitu;
        this.proteiini = proteiini;
        this.suola = suola;
    }

    /**
     * Return string that contains foods name
     * @return Foods name string
     */
    public String getName() {
        return name;
    }

    /**
     * Return string that contains foods energy
     * @return Foods energy string
     */
    public String getEnergia() {
        return energia;
    }

    /**
     * Return string that contains foods fat
     * @return Foods fat string
     */
    public String getRasva() {
        return rasva;
    }

    /**
     * Return string that contains foods carbonhydrate
     * @return Foods carbonhydrate string
     */
    public String getHiilihyraatti() {
        return hiilihyraatti;
    }

    /**
     * Return string that contains foods fiber
     * @return Foods fiber string
     */
    public String getKuitu() {
        return kuitu;
    }

    /**
     * Return string that contains foods protein value
     * @return Foods protein string
     */
    public String getProteiini() {
        return proteiini;
    }

    /**
     * Return string that contains foods salt value
     * @return Foods salt string
     */
    public String getSuola() {
        return suola;
    }

    /**
     * Return string that contains foods ID
     * @return Foods ID string
     */
    public int getId(){
        return id;
    }

    /**
     * Return string that contains foods name
     * @return Foods name string
     */
    @Override
    public String toString() {
        return name;
    }
}
