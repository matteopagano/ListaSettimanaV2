package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private String abbreviazione;
    private String nome;
    private int numberOfDaysToDo;

    private List<Day> daysOfAbsence;
    private String color;
    final String ANSI_RESET = "\u001B[0m";

    Means means;
    public Person(int i, String m, String color, String nome, Means means) {
        this.nome = nome;
        this.daysOfAbsence =new ArrayList<>();
        this.abbreviazione=m;
        this.numberOfDaysToDo =i;
        this.color=color;
        this.means = means;
    }

    public Person(String m) {
        this.abbreviazione=m;
    }

    public void addListaAssenze(List<Day> assenze) {
        daysOfAbsence.addAll(assenze);
    }

    public List<Day> getDaysOfAbsence() {
        return daysOfAbsence;
    }

    public int getNumberOfDaysToDo() {
        return numberOfDaysToDo;
    }

    public String getAbbreviazione() {
        return abbreviazione;
    }

    @Override
    public String toString() {
        return color + abbreviazione + ANSI_RESET;
    }

    public boolean isExceed(){
        int daysOfAbsence = this.daysOfAbsence.size();
        boolean cond = daysOfAbsence + getNumberOfDaysToDo() <= 7;
        if(!cond){
            System.out.println("Controlla: "+this.abbreviazione);
            System.out.println("Giorni lavorativi:"+this.getNumberOfDaysToDo());
            System.out.println("Assenze inpostate:"+daysOfAbsence);
        }
        return cond;

    }
    public String getNome(){
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person persona = (Person) o;
        return Objects.equals(abbreviazione, persona.abbreviazione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(abbreviazione);
    }

    public void setAbbreviazione(String abbreviazione) {
        this.abbreviazione = abbreviazione;
    }
}
