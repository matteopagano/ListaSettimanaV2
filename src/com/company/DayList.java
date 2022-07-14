package com.company;

import java.util.ArrayList;
import java.util.List;

public class DayList {

    private int numberOfSlots;
    private String name;
    private List<Tuple<Person, Boolean>> listaDiPersone;

    public DayList(String g, int n) {
        listaDiPersone=new ArrayList<>();
        name=g;
        numberOfSlots=n;
    }
    public void addPerson(Person p){
        listaDiPersone.add(new Tuple<>(p, false));
    }

    public String getName() {
        return name;
    }

    public int getNumberOfSlots() {
        return numberOfSlots;
    }

    public List<Tuple<Person,Boolean>> getListOfPeopleWithCar() {
        return listaDiPersone;
    }


    public List<Person> getListaDiPersone() {
        List<Person> lista = new ArrayList<>();
        for (Tuple<Person, Boolean> e : this.listaDiPersone){
            lista.add(e.getPerson());
        }
        return lista;
    }

    public void setListaDiPersone(List<Tuple<Person, Boolean>> listaDiPersone) {
        this.listaDiPersone = listaDiPersone;
    }

    public boolean isFull(){
        return getNumberOfSlots() <= listaDiPersone.size();
    }
    @Override
    public boolean equals(Object o){
        DayList g;
        boolean bool=false;
        if(o instanceof DayList){
            g=(DayList) o;
            bool=this.name.equals(g.name);
        }
        return bool;
    }

    @Override
    public String toString() {
        return "GiornoLista{" +
                "numeroGiorniMAx=" + getNumberOfSlots() +
                ", name='" + name + '\'' +
                ", listaDiPersona=" + listaDiPersone +
                '}';
    }
}
