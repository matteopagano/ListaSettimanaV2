package com.company;

import java.util.ArrayList;
import java.util.List;

public class DayOfList {

    private final int numberOfSlots;
    private final String name;
    private List<Tuple<Person, Boolean>> listOfPeople;

    public DayOfList(String g, int n) {
        listOfPeople =new ArrayList<>();
        name=g;
        numberOfSlots=n;
    }
    public void addPerson(Person p){
        listOfPeople.add(new Tuple<>(p, false));
    }

    public String getName() {
        return name;
    }

    public int getNumberOfSlots() {
        return numberOfSlots;
    }

    public List<Tuple<Person,Boolean>> getListOfPeopleWithCar() {
        return listOfPeople;
    }

    public List<Person> getListOfPeople() {
        List<Person> list = new ArrayList<>();
        for (Tuple<Person, Boolean> e : this.listOfPeople){
            list.add(e.getT1());
        }
        return list;
    }

    public void setListOfPeople(List<Tuple<Person, Boolean>> listOfPeople) {
        this.listOfPeople = listOfPeople;
    }

    public boolean isFull(){
        return getNumberOfSlots() <= listOfPeople.size();
    }
    @Override
    public boolean equals(Object o){
        DayOfList g;
        boolean bool=false;
        if(o instanceof DayOfList){
            g=(DayOfList) o;
            bool=this.name.equals(g.name);
        }
        return bool;
    }

    @Override
    public String toString() {
        return "GiornoLista{" +
                "numeroGiorniMAx=" + getNumberOfSlots() +
                ", name='" + name + '\'' +
                ", listaDiPersona=" + listOfPeople +
                '}';
    }
}
