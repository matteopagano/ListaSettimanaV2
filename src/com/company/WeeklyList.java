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
        if(isCorrectDisponibilityAndPerson() && checkIfExcheedFromInitialOptions() && isCorrectAbsence()){
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
                //sendToPrinter();
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
        for(Day g : personToAdd.getDaysOfAbsence()){
            if (g.getDay().equals(s)) {
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

    public boolean isCorrectAbsence(){
        int[] prova = new int[7];
        for(Person p : this.listOfPeople){
            for(Day g : p.getDaysOfAbsence()){
                if(g.getDay().toString().equals("Lunedì")){
                    prova[0] = prova[0] + 1;
                }else if(g.getDay().toString().equals("Martedì")){
                    prova[1] = prova[1] + 1;
                }else if(g.getDay().toString().equals("Mercoledì")){
                    prova[2] = prova[2] + 1;
                }else if(g.getDay().toString().equals("Giovedì")){
                    prova[3] = prova[3] + 1;
                }else if(g.getDay().toString().equals("Venerdì")){
                    prova[4] = prova[4] + 1;
                }else if(g.getDay().toString().equals("Sabato")){
                    prova[5] = prova[5] + 1;
                }else if(g.getDay().toString().equals("Domenica")){
                    prova[6] = prova[6] + 1;
                }
            }
        }

        boolean cond = true;


        if(prova[0] + this.listOfDays.get(0).getNumberOfSlots() > listOfPeople.size()){
            cond = false;
            System.out.println("Controlla Lunedì");
            System.out.println("Lunedì hai inserito le assenze di " + prova[0] + " persone.");
        }else if(prova[1] + this.listOfDays.get(1).getNumberOfSlots() > listOfPeople.size()){
            cond = false;
            System.out.println("Controlla Martedì");
            System.out.println("Martedì hai inserito le assenze di " + prova[1] + " persone.");
        }else if(prova[2] + this.listOfDays.get(2).getNumberOfSlots() > listOfPeople.size()){
            cond = false;
            System.out.println("Controlla Mercoledì");
            System.out.println("Mercoledì hai inserito le assenze di " + prova[2] + " persone.");
        }else if(prova[3] + this.listOfDays.get(3).getNumberOfSlots() > listOfPeople.size()){
            cond = false;
            System.out.println("Controlla Giovedì");
            System.out.println("Giovedì hai inserito le assenze di " + prova[3] + " persone.");
        }else if(prova[4] + this.listOfDays.get(4).getNumberOfSlots() > listOfPeople.size()){
            cond = false;
            System.out.println("Controlla Venerdì");
            System.out.println("Venerdì hai inserito le assenze di " + prova[4] + " persone.");
        }else if(prova[5] + this.listOfDays.get(5).getNumberOfSlots() >listOfPeople.size()){
            cond = false;
            System.out.println("Controlla Sabato");
            System.out.println("Sabato hai inserito le assenze di " + prova[5] + " persone.");
        }else if(prova[6] + this.listOfDays.get(6).getNumberOfSlots() > listOfPeople.size()){
            cond = false;
            System.out.println("Controlla Domenica");
            System.out.println("Domenica hai inserito le assenze di " + prova[6] + " persone.");
        }
        return cond;

    }

    public boolean isCorrectDisponibilityAndPerson(){
        boolean cond = true;
        int numeroPresenzeDaOccupare=0;
        int numeroDisponibilitaPersone=0;

        for (DayList g : this.listOfDays){
            numeroPresenzeDaOccupare+=g.getNumberOfSlots();
        }
        for (Person p : this.listOfPeople){
            numeroDisponibilitaPersone+=p.getNumberOfDaysToDo();
        }

        if(numeroDisponibilitaPersone>numeroPresenzeDaOccupare){
            cond = false;
            System.out.println("Diminuisici di "+(numeroDisponibilitaPersone-numeroPresenzeDaOccupare)+" i giorni lavorativi alle persone!");
        }else if(numeroDisponibilitaPersone<numeroPresenzeDaOccupare){
            cond = false;
            System.out.println("Aggiungi di "+(numeroPresenzeDaOccupare-numeroDisponibilitaPersone)+" i giorni lavorativi alle persone!");
        }
        return cond;
    }
}
