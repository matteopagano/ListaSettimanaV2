package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private String abbreviation;
    private String name;
    private int numberOfDaysToDo;
    private List<Day> daysOfAbsence;

    private String color;
    final String ANSI_RESET = "\u001B[0m";

    Means means;
    public Person(int i, String m, String color, String nome, Means means) {
        this.name = nome;
        this.daysOfAbsence = new ArrayList<>();
        this.abbreviation = m;
        this.numberOfDaysToDo = i;
        this.color = color;
        this.means = means;
    }

    public Person(String m) {
        this.abbreviation = m;
    }

    public void addAbsences(List<Day> absences) {
        daysOfAbsence.addAll(absences);
    }

    public List<Day> getDaysOfAbsence() {
        return daysOfAbsence;
    }

    public int getNumberOfDaysToDo() {
        return numberOfDaysToDo;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    @Override
    public String toString() {
        return color + abbreviation + ANSI_RESET;
    }

    public boolean isExceed(){
        int daysOfAbsence = this.daysOfAbsence.size();
        boolean cond = daysOfAbsence + getNumberOfDaysToDo() <= 7;
        if(!cond){
            System.out.println("Controlla: "+this.abbreviation);
            System.out.println("Giorni lavorativi:"+this.getNumberOfDaysToDo());
            System.out.println("Assenze inpostate:"+daysOfAbsence);
        }
        return cond;

    }
    public String getName(){
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person persona = (Person) o;
        return Objects.equals(abbreviation, persona.abbreviation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(abbreviation);
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
