package com.example.kuhinja.podaci;


public class ListaJela_2 {
    private int id;
    private String naziv;
    private int id_kuhinje;
    private String sastojci;
    private int velicina_porcije;

    public ListaJela_2() {

    }
    public ListaJela_2(String naziv, String sastojci, int velicina_porcije) {
        this.naziv = naziv;
        this.sastojci = sastojci;
        this.velicina_porcije = velicina_porcije;
    }

    public ListaJela_2(int id, String naziv,int id_kuhinje, String sastojci, int velicina_porcije) {
        this.id = id;
        this.naziv = naziv;
        this.id_kuhinje = id_kuhinje;
        this.sastojci = sastojci;
        this.velicina_porcije = velicina_porcije;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getId_kuhinje() {
        return id_kuhinje;
    }

    public void setId_kuhinje(int id_kuhinje) {
        this.id_kuhinje = id_kuhinje;
    }

    public String getSastojci() {
        return sastojci;
    }

    public void setSastojci(String sastojci) {
        this.sastojci = sastojci;
    }

    public int getvelicina_porcije() {
        return velicina_porcije;
    }

    public void setvelicina_porcije(int velicina_porcije) {
        this.velicina_porcije = velicina_porcije;
    }
}
