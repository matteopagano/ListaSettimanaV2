package com.company;

import java.util.ArrayList;
import java.util.List;

public class Persona {
    private String abbreviazione;
    private String nome;
    private int numeroGiorni;
    private List<Giorno> giorniAssenza;
    private String color;
    final String ANSI_RESET = "\u001B[0m";
    double giorniMacchinaPizzeria = 0;
    public Persona(int i, String m, String color, String nome) {
        this.nome = nome;
        this.giorniAssenza=new ArrayList<>();
        this.abbreviazione=m;
        this.numeroGiorni=i;
        this.color=color;
    }

    public void addListaAssenze(List<Giorno> assenze) {
        giorniAssenza.addAll(assenze);
    }

    public List<Giorno> getGiorniAssenza() {
        return giorniAssenza;
    }

    public int getNumeroGiorni() {
        return numeroGiorni;
    }

    public String getAbbreviazione() {
        return abbreviazione;
    }

    @Override
    public String toString() {
        return color + abbreviazione + ANSI_RESET;
    }

    public boolean isCorrect(){
        int giorniAs=giorniAssenza.size();
        boolean bool=giorniAs + getNumeroGiorni() <= 7;
        if(!bool){
            System.out.println("Controlla: "+this.abbreviazione);
            System.out.println("Giorni lavorativi:"+this.getNumeroGiorni());
            System.out.println("Assenze inpostate:"+giorniAs);
        }
        return bool;

    }
    public String getNome(){
        return nome;
    }
}
