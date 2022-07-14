package com.company;

import java.awt.print.PrinterException;
import java.util.*;
import java.util.List;

public class WeeklyList {

    private final List<Person> listOfPeople;
    private final List<DayList> listOfDays;
    private final CarBuilder carBuilder;

    public WeeklyList(List<Person> listOfPeople, List<DayList> listOfDays, int numberOfPizzeriaCar) {
        this.listOfPeople = listOfPeople;
        this.listOfDays = listOfDays;
        this.carBuilder = new CarBuilder(CarBuilder.createPropertyCar(listOfPeople, numberOfPizzeriaCar));
    }

    public void calculate() throws PrinterException {
        boolean guard = true;
        int i=0;
        while(i < 1100000 && guard){
            if(calculateAux()){
                guard = false;
                System.out.println("volte: " + i);
            }
            i++;
        }
        if(guard){
            System.out.println("Non esistono combinazioni");
        }
    }

    private boolean calculateAux() throws PrinterException {

        List<Person> lista = new ArrayList<>();
        for (Person p : listOfPeople){
            for (int i = 0; i < p.getNumberOfDaysToDo() ; ++i)
                lista.add(p);
        }

        Collections.shuffle(lista);
        Queue<Person> coda = new LinkedList<>(lista);

        while(!coda.isEmpty()){
            Person personToAdd = coda.poll();
            Iterator<DayList> it = listOfDays.iterator();
            boolean added = false;
            while(!added && it.hasNext()){
                DayList g = it.next();
                if(!g.isFull() && isAddable(personToAdd, g) && !isAlreadyPresent(personToAdd, g)){
                    g.addPerson(personToAdd);
                    added = true;
                }
            }
        }



        if(!isListCorrect()){
            clearDays();
            return false;
        }else {
            this.carBuilder.makeListWithCarsOptions(this);

            if(carBuilder.isFair(1)){
                System.out.println("Distanza Corretta");
                System.out.println(carBuilder.getNumeroVoltePropria());
                printList();
                sendToPrinter();
                return true;
            }else{
                clearDays();
                return false;
            }
        }
    }

    private void clearDays() {
        for (DayList g : listOfDays){
            g.getListOfPeopleWithCar().clear();
        }
    }

    public void printList() {
        for (DayList g : listOfDays){
            System.out.println(g.getName()+": "+g.getListOfPeopleWithCar());
        }
        System.out.println();
        System.out.println("* = macchina propria.");
    }

    public void sendToPrinter() throws PrinterException {
        Printer.sendToPrinter(this);
    }

    private boolean isListCorrect() {
        return doSlotsAndPeopleMatch() && doDaysMatchFromInitialOptions();
    }

    private boolean doSlotsAndPeopleMatch(){
        boolean bool = true;
        for (DayList g : listOfDays){
            if(g.getListOfPeopleWithCar().size() != g.getNumberOfSlots()){
                bool = false;
            }
        }
        return bool;
    }

    public boolean isAlreadyPresent(Person personToAdd, DayList g) {
        boolean cond = false;
        for(Tuple<Person,Boolean> g1 : g.getListOfPeopleWithCar()){
            if (g1.getPerson().getAbbreviazione().equals(personToAdd.getAbbreviazione())) {
                cond = true;
                break;
            }
        }
        return cond;
    }

    public boolean isAddable(Person personToAdd, DayList d) {
        boolean cond = true;
        String s = d.getName();
        for(Giorno g : personToAdd.getDaysOfAbsence()){
            if (g.getGiorno().equals(s)) {
                cond = false;
                break;
            }
        }
        return cond;
    }

    public boolean checkIfExcheedFromInitialOptions(){
        boolean cond = true;
        for(Person p : listOfPeople){
            if(!p.isExceed()){
                cond = false;
                break;
            }
        }
        return cond;
    }

    private boolean doDaysMatchFromInitialOptions(){
        boolean check = true;
        for(Person p : listOfPeople){
            int giorniAssegnati = 0;
            for(DayList g : listOfDays){
                if(g.getListaDiPersone().contains(p)){
                    giorniAssegnati ++;
                }
            }
            if(p.getNumberOfDaysToDo() != giorniAssegnati) check = false;
        }
        return check;
    }

    public List<Person> getListOfPeople() {
        return listOfPeople;
    }

    public List<DayList> getListOfDays() {
        return listOfDays;
    }
}
