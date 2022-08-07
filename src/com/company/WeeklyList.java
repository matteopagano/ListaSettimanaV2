package com.company;

import java.awt.print.PrinterException;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;

public class WeeklyList {

    private final List<Person> listOfPeople;
    private final Map<Day,DayOfList> listOfDays;
    private final CarBuilder carBuilder;
    private Map<Day,Integer> test = new HashMap<>();

    public WeeklyList(List<Person> listOfPeople, List<DayOfList> listOfDays, int numberOfPizzeriaCar) {
        this.listOfPeople = listOfPeople;
        this.listOfDays = new HashMap<>();
        for (DayOfList d : listOfDays){
            this.listOfDays.put(Day.getDayByName(d.getName()),d);
        }

        for(Map.Entry<Day, DayOfList> e : this.listOfDays.entrySet()){
            this.test.put(e.getKey(), 0);
        }
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
                    printTest();
                }
                i++;
            }
            if(guard){
                System.out.println("Non esistono combinazioni");
                //printTest();
                getHint(adjustWorkingDay());
            }
        }
    }

    public Person getPersonThatWorkMinus(){
        Person ret = null;
        for(Person p : this.listOfPeople){
            if(ret == null && p.getNumberOfDaysToDo() != 0){
                ret = p;
            }else{
                if(p.getNumberOfDaysToDo() < ret.getNumberOfDaysToDo() && p.getNumberOfDaysToDo() != 0){
                    ret = p;
                }
            }
        }
        return ret;
    }

    public Person getPersonThatWorkPlus(){
        Person ret = null;
        for(Person p : this.listOfPeople){
            if(ret == null && p.getNumberOfDaysToDo() != 0){
                ret = p;
            }else{
                if(p.getNumberOfDaysToDo() > ret.getNumberOfDaysToDo() && p.getNumberOfDaysToDo() != 0){
                    ret = p;
                }
            }
        }
        return ret;
    }

    public void getHint(Day d){
        System.out.println("Queste sono le persone che NON POSSONO lavorare " + d.getDay() + " : " + getPeopleCantWorkThisDay(d));
        System.out.println("Queste sono le persone che POSSONO lavorare " + d.getDay() + " : " + getPeopleCanWorkThisDay(d));

        System.out.println("Prova a modificare " + getPersonThatWorkMinus());
        System.out.println("Prova a modificare " + getPersonThatWorkPlus());



    }

    public List<Person> getPeopleCantWorkThisDay(Day d){
        List<Person> list = new ArrayList<>();
        for(Person p : this.listOfPeople){
            if(p.getDaysOfAbsence().contains(d)){
                list.add(p);
            }
        }
        return list;
    }

    public List<Person> getPeopleCanWorkThisDay(Day d){
        List<Person> list = new ArrayList<>();
        for(Person p : this.listOfPeople){
            if(!p.getDaysOfAbsence().contains(d)){
                list.add(p);
            }
        }
        return list;
    }

    private void printTest(){
        for (Map.Entry<Day, Integer> e : this.test.entrySet()){
            System.out.println(e.getKey() + " : " +e.getValue());
        }
    }
    private List<Person> makeAggregateOccurrencesRand(){
        List<Person> aggregateOccurrencesOfPeople = new ArrayList<>();
        for (Person p : this.listOfPeople){
            for (int i = 0; i < p.getNumberOfDaysToDo() ; ++i)
                aggregateOccurrencesOfPeople.add(p);
        }
        Collections.shuffle(aggregateOccurrencesOfPeople);
        return aggregateOccurrencesOfPeople;
    }

    private Day adjustWorkingDay(){
        Tuple<Day, Integer> t = null;
        for(Map.Entry<Day, Integer> e : this.test.entrySet()){
            if(t == null){
                t = new Tuple<>(e.getKey(),e.getValue());
            }else{
                if(e.getValue() < t.getT2()){
                    t = new Tuple<>(e.getKey(), e.getValue());
                }
            }
        }
        if(t != null){
            System.out.println("Mi sa che dovresti aggiustare " + t.getT1().getDay());
        }
        return t.getT1();
    }

    private void generateList(Queue<Person> coda){
        while(!coda.isEmpty()){
            Person personToAdd = coda.poll();

            Iterator<Map.Entry<Day, DayOfList>> it = this.listOfDays.entrySet().iterator();
            boolean added = false;
            while(!added && it.hasNext()){
                Map.Entry<Day, DayOfList> g = it.next();
                if(!g.getValue().isFull() && isAddable(personToAdd, g.getValue()) && !isAlreadyPresent(personToAdd, g.getValue())){
                    g.getValue().addPerson(personToAdd);
                    added = true;
                }
            }
        }
    }



    private boolean isCorrectDay(DayOfList d){
        return d.getListOfPeople().size() == d.getNumberOfSlots();
    }
    private void updateTest(){
        for (Map.Entry<Day, DayOfList> e : this.listOfDays.entrySet()){
            if(isCorrectDay(e.getValue())){
                this.test.replace(e.getKey(), this.test.get(e.getKey()) + 1);
            }
        }
    }

    private boolean calculateAux() throws PrinterException {
        Queue<Person> randomQueue = new LinkedList<>(makeAggregateOccurrencesRand());
        generateList(randomQueue);

        updateTest();



        //printList();
        if(!isListCorrect()){
            clearDays();
            return false;
        } else {
            this.carBuilder.makeListWithCarsOptions(this);

            if(carBuilder.isFair(1)){
                System.out.println("Distanza Corretta");
                System.out.println(carBuilder.getPerson_NumberOfTimesOwnCar());
                printListInOrder();
                //sendToPrinter();
                return true;
            }else{
                clearDays();
                return false;
            }
        }
    }

    private void clearDays() {
        for (Map.Entry<Day, DayOfList>  g : listOfDays.entrySet()){
            g.getValue().getListOfPeopleWithCar().clear();
        }
    }

    public void printList() {
        for (Map.Entry<Day, DayOfList>  g : listOfDays.entrySet()){
            System.out.println(g.getValue().getName()+": "+g.getValue().getListOfPeopleWithCar());
        }
        System.out.println();
        System.out.println("* = macchina propria.");
    }

    public void printListInOrder() {

        System.out.println(listOfDays.get(Day.getLunedi()).getName()+": "+listOfDays.get(Day.getLunedi()).getListOfPeopleWithCar());
        System.out.println(listOfDays.get(Day.getMartedi()).getName()+": "+listOfDays.get(Day.getMartedi()).getListOfPeopleWithCar());
        System.out.println(listOfDays.get(Day.getMercoledi()).getName()+": "+listOfDays.get(Day.getMercoledi()).getListOfPeopleWithCar());
        System.out.println(listOfDays.get(Day.getGiovedi()).getName()+": "+listOfDays.get(Day.getGiovedi()).getListOfPeopleWithCar());
        System.out.println(listOfDays.get(Day.getVenerdi()).getName()+": "+listOfDays.get(Day.getVenerdi()).getListOfPeopleWithCar());
        System.out.println(listOfDays.get(Day.getSabato()).getName()+": "+listOfDays.get(Day.getSabato()).getListOfPeopleWithCar());
        System.out.println(listOfDays.get(Day.getDomenica()).getName()+": "+listOfDays.get(Day.getDomenica()).getListOfPeopleWithCar());

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

        Map<Day, Integer> occupiedSlots = fillOccupiedSlots();
        boolean cond = true;

        //System.out.println(occupiedSlots);
        for (Map.Entry<Day, Integer> m : occupiedSlots.entrySet()){
            if(m.getValue() + this.listOfDays.get(m.getKey()).getNumberOfSlots() > listOfPeople.size()){
                cond = false;
                System.out.println("Controlla " + m.getKey());
                System.out.println(m.getKey().toString() + " hai inserito le assenze di " + occupiedSlots.get(m.getKey()) + " persone.");
            }
        }
        return cond;

    }

    private Map<Day, Integer> fillOccupiedSlots() {
        Map<Day, Integer> occupiedSlots = new HashMap<>();
        occupiedSlots.put(Day.getLunedi(),0);
        occupiedSlots.put(Day.getMartedi(),0);
        occupiedSlots.put(Day.getMercoledi(),0);
        occupiedSlots.put(Day.getGiovedi(),0);
        occupiedSlots.put(Day.getVenerdi(),0);
        occupiedSlots.put(Day.getSabato(),0);
        occupiedSlots.put(Day.getDomenica(),0);


        for(Person p : this.listOfPeople){
            for(Day g : p.getDaysOfAbsence()){
                switch (g.getDay()) {
                    case "Lunedì" -> occupiedSlots.put(Day.getLunedi(),occupiedSlots.get(Day.getLunedi() ) + 1);
                    case "Martedì" -> occupiedSlots.put(Day.getMartedi(),occupiedSlots.get(Day.getMartedi() ) + 1);
                    case "Mercoledì" -> occupiedSlots.put(Day.getMercoledi(),occupiedSlots.get(Day.getMercoledi() ) + 1);
                    case "Giovedì" -> occupiedSlots.put(Day.getGiovedi(),occupiedSlots.get(Day.getGiovedi() ) + 1);
                    case "Venerdì" -> occupiedSlots.put(Day.getVenerdi(),occupiedSlots.get(Day.getVenerdi() ) + 1);
                    case "Sabato" -> occupiedSlots.put(Day.getSabato(),occupiedSlots.get(Day.getSabato() ) + 1);
                    case "Domenica" -> occupiedSlots.put(Day.getDomenica(),occupiedSlots.get(Day.getDomenica() ) + 1);
                }
            }
        }
        return occupiedSlots;
    }

    public boolean isCorrectAvailabilityAndPerson(){
        boolean cond = true;
        int numeroPresenzeDaOccupare=0;
        int numeroDisponibilitaPersone=0;

        for (Map.Entry<Day, DayOfList>  g : listOfDays.entrySet()){
            numeroPresenzeDaOccupare+=g.getValue().getNumberOfSlots();
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
            if (g1.getT1().getAbbreviation().equals(personToAdd.getAbbreviation())) {
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
        for (Map.Entry<Day, DayOfList>  g : listOfDays.entrySet()){
            if (g.getValue().getListOfPeopleWithCar().size() != g.getValue().getNumberOfSlots()) {
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
            for(Map.Entry<Day, DayOfList>  g : listOfDays.entrySet()){
                if(g.getValue().getListOfPeople().contains(p)){
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
            if(!p.isAbsenceAndDayToDoCorrects()){
                cond = false;
                break;
            }
        }
        return cond;
    }

    public List<Person> getListOfPeople() {
        return listOfPeople;
    }

    public Map<Day,DayOfList> getListOfDays() {
        return listOfDays;
    }



}
