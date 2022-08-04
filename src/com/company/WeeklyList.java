package com.company;

import java.awt.print.PrinterException;
import java.util.*;
import java.util.List;

public class WeeklyList {

    private final List<Person> listOfPeople;
    private final List<DayOfList> listOfDays;
    private final CarBuilder carBuilder;

    public WeeklyList(List<Person> listOfPeople, List<DayOfList> listOfDays, int numberOfPizzeriaCar) {
        this.listOfPeople = listOfPeople;
        this.listOfDays = listOfDays;
        this.carBuilder = new CarBuilder(CarBuilder.createPropertyCar(listOfPeople, numberOfPizzeriaCar));
    }

    public void calculate() throws PrinterException {
        if(isCorrectAvailabilityAndPerson() && checkIfExceedsFromInitialOptions() && isCorrectAbsence()){
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

        List<Person> aggregatePeopleList = new ArrayList<>();
        for (Person p : this.listOfPeople){
            for (int i = 0; i < p.getNumberOfDaysToDo() ; ++i)
                aggregatePeopleList.add(p);
        }

        Collections.shuffle(aggregatePeopleList);
        Queue<Person> coda = new LinkedList<>(aggregatePeopleList);

        while(!coda.isEmpty()){
            Person personToAdd = coda.poll();
            Iterator<DayOfList> it = this.listOfDays.iterator();
            boolean added = false;
            while(!added && it.hasNext()){
                DayOfList g = it.next();
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
        for (DayOfList g : listOfDays){
            g.getListOfPeopleWithCar().clear();
        }
    }

    public void printList() {
        for (DayOfList g : listOfDays){
            System.out.println(g.getName()+": "+g.getListOfPeopleWithCar());
        }
        System.out.println();
        System.out.println("* = macchina propria.");
    }

    public static void printList(List<DayOfList> l) {
        for (DayOfList g : l){
            System.out.println(g.getName()+": "+g.getListOfPeopleWithCar());
        }
        System.out.println();
        System.out.println("* = macchina propria.");
    }

    public void sendToPrinter() throws PrinterException {
        Printer.sendToPrinter(this);
    }

    public boolean isCorrectAbsence(){
        int[] occupiedSlots = new int[7];
        for(Person p : this.listOfPeople){
            for(Day g : p.getDaysOfAbsence()){
                switch (g.getDay()) {
                    case "Lunedì" -> occupiedSlots[0] = occupiedSlots[0] + 1;
                    case "Martedì" -> occupiedSlots[1] = occupiedSlots[1] + 1;
                    case "Mercoledì" -> occupiedSlots[2] = occupiedSlots[2] + 1;
                    case "Giovedì" -> occupiedSlots[3] = occupiedSlots[3] + 1;
                    case "Venerdì" -> occupiedSlots[4] = occupiedSlots[4] + 1;
                    case "Sabato" -> occupiedSlots[5] = occupiedSlots[5] + 1;
                    case "Domenica" -> occupiedSlots[6] = occupiedSlots[6] + 1;
                }
            }
        }

        boolean cond = true;

        if(occupiedSlots[0] + this.listOfDays.get(0).getNumberOfSlots() > listOfPeople.size()){
            cond = false;
            System.out.println("Controlla Lunedì");
            System.out.println("Lunedì hai inserito le assenze di " + occupiedSlots[0] + " persone.");
        }else if(occupiedSlots[1] + this.listOfDays.get(1).getNumberOfSlots() > listOfPeople.size()){
            cond = false;
            System.out.println("Controlla Martedì");
            System.out.println("Martedì hai inserito le assenze di " + occupiedSlots[1] + " persone.");
        }else if(occupiedSlots[2] + this.listOfDays.get(2).getNumberOfSlots() > listOfPeople.size()){
            cond = false;
            System.out.println("Controlla Mercoledì");
            System.out.println("Mercoledì hai inserito le assenze di " + occupiedSlots[2] + " persone.");
        }else if(occupiedSlots[3] + this.listOfDays.get(3).getNumberOfSlots() > listOfPeople.size()){
            cond = false;
            System.out.println("Controlla Giovedì");
            System.out.println("Giovedì hai inserito le assenze di " + occupiedSlots[3] + " persone.");
        }else if(occupiedSlots[4] + this.listOfDays.get(4).getNumberOfSlots() > listOfPeople.size()){
            cond = false;
            System.out.println("Controlla Venerdì");
            System.out.println("Venerdì hai inserito le assenze di " + occupiedSlots[4] + " persone.");
        }else if(occupiedSlots[5] + this.listOfDays.get(5).getNumberOfSlots() >listOfPeople.size()){
            cond = false;
            System.out.println("Controlla Sabato");
            System.out.println("Sabato hai inserito le assenze di " + occupiedSlots[5] + " persone.");
        }else if(occupiedSlots[6] + this.listOfDays.get(6).getNumberOfSlots() > listOfPeople.size()){
            cond = false;
            System.out.println("Controlla Domenica");
            System.out.println("Domenica hai inserito le assenze di " + occupiedSlots[6] + " persone.");
        }
        return cond;

    }

    public boolean isCorrectAvailabilityAndPerson(){
        boolean cond = true;
        int numeroPresenzeDaOccupare=0;
        int numeroDisponibilitaPersone=0;

        for (DayOfList g : this.listOfDays){
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

    private boolean isListCorrect() {
        return doSlotsAndPeopleMatch() && doDaysMatchFromInitialOptions();
    }

    public boolean isAlreadyPresent(Person personToAdd, DayOfList g) {
        boolean cond = false;
        for(Tuple<Person,Boolean> g1 : g.getListOfPeopleWithCar()){
            if (g1.getPerson().getAbbreviation().equals(personToAdd.getAbbreviation())) {
                cond = true;
                break;
            }
        }
        return cond;
    }

    public boolean isAddable(Person personToAdd, DayOfList d) {
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

    private boolean doSlotsAndPeopleMatch(){
        boolean bool = true;
        for (DayOfList g : listOfDays){
            if (g.getListOfPeopleWithCar().size() != g.getNumberOfSlots()) {
                bool = false;
                break;
            }
        }
        return bool;
    }

    private boolean doDaysMatchFromInitialOptions(){
        boolean check = true;
        for(Person p : listOfPeople){
            int giorniAssegnati = 0;
            for(DayOfList g : listOfDays){
                if(g.getListOfPeople().contains(p)){
                    giorniAssegnati ++;
                }
            }
            if(p.getNumberOfDaysToDo() != giorniAssegnati) check = false;
        }
        return check;
    }

    public boolean checkIfExceedsFromInitialOptions(){
        boolean cond = true;
        for(Person p : listOfPeople){
            if(!p.isExceed()){
                cond = false;
                break;
            }
        }
        return cond;
    }

    public List<Person> getListOfPeople() {
        return listOfPeople;
    }

    public List<DayOfList> getListOfDays() {
        return listOfDays;
    }



}
