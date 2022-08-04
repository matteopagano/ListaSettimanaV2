package com.company;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class CarBuilder {

    private final CarBuilderProperty carBuilderProperty;
    private final Map<String, Integer> person_NumberOfTimesOwnCar;

    public CarBuilder(CarBuilderProperty propertyCar) {
        this.carBuilderProperty = propertyCar;
        this.person_NumberOfTimesOwnCar = new HashMap<>();
        for(Person e : carBuilderProperty.getPeopleOwnCar()){
            this.person_NumberOfTimesOwnCar.put(e.getAbbreviation(), 0);
        }

    }

    private Person takeMin(List<Tuple<Person,Boolean>> l){
        Tuple<Person, Integer> min = new Tuple<>(l.get(0).getT1(), person_NumberOfTimesOwnCar.get(l.get(0).getT1().getAbbreviation()));
        for(Tuple<Person,Boolean> e : l){
            int numeroVolteE = person_NumberOfTimesOwnCar.get(e.getT1().getAbbreviation());
            if(numeroVolteE <= min.getT2()){
                min = new Tuple<>(e.getT1(), numeroVolteE);
            }
        }
        return min.getT1();
    }

    public void makeListWithCarsOptions(WeeklyList listaSettimana) {
        for(DayOfList g : listaSettimana.getListOfDays()){
            //System.out.println(g.getName()+": "+ g.getListaDiPersone());
            if(g.getListOfPeople().containsAll(this.carBuilderProperty.getPeoplePizzeriaCar()) && this.carBuilderProperty.getPeoplePizzeriaCar().size() == getNumeroMacchinePizzeria()){
                ArrayList<Tuple<Person,Boolean>> l = g.getListOfPeopleWithCar().stream().filter(personaBooleanTuple -> carBuilderProperty.getPeopleOwnCar().contains(new Person(personaBooleanTuple.getT1().getAbbreviation()))).collect(Collectors.toCollection(ArrayList::new));

                l.replaceAll(personaBooleanTuple -> new Tuple<>(personaBooleanTuple.getT1(),true));

                g.getListOfPeopleWithCar().replaceAll(personaBooleanTuple -> {
                    Tuple<Person, Boolean> modified = personaBooleanTuple;
                    if(l.contains(personaBooleanTuple)){

                        modified.setT2(true);
                        //numeroVoltePropria.put(personaBooleanTuple.getPerson().getAbbreviazione(),numeroVoltePropria.get(personaBooleanTuple.getPerson().getAbbreviazione())+1);
                        return modified;
                    }else{
                        return personaBooleanTuple;
                    }
                });

                //System.out.println(numeroVoltePropria);


            }
            else{


                long countPeopleWithoutMotorBike = g.getListOfPeople().stream().filter(new Predicate<Person>() {
                    @Override
                    public boolean test(Person persona) {
                        return !carBuilderProperty.getPeopleMotorbike().contains(persona);
                    }
                }).count();

                //System.out.println("numero persone senza motorino: "+ countPeopleWithoutMotorin);

                long mPcounterPersoneChedovrebberoUsareMacchinaPizzeria = g.getListOfPeople().stream().filter(new Predicate<Person>() {
                    @Override
                    public boolean test(Person persona) {
                        return carBuilderProperty.getPeoplePizzeriaCar().contains(persona);
                    }
                }).count();

                long nPersoneCheMacchinaPropria = g.getListOfPeople().stream().filter(new Predicate<Person>() {
                    @Override
                    public boolean test(Person persona) {
                        return carBuilderProperty.getPeopleOwnCar().contains(persona);
                    }
                }).count();

                ArrayList<Tuple<Person,Boolean>> l = g.getListOfPeopleWithCar().stream().filter(new Predicate<Tuple<Person, Boolean>>() {
                    @Override
                    public boolean test(Tuple<Person, Boolean> personaBooleanTuple) {

                        return carBuilderProperty.getPeopleOwnCar().contains(new Person(personaBooleanTuple.getT1().getAbbreviation()));
                    }
                }).collect(Collectors.toCollection(ArrayList::new));


                //System.out.println(l);
                long numeroScooter = g.getListOfPeople().stream().filter(new Predicate<Person>() {
                    @Override
                    public boolean test(Person persona) {
                        return carBuilderProperty.getPeopleMotorbike().contains(persona);
                    }
                }).count();

                //System.out.println("macchina pizzeria counter: "+mPcounterPersoneChedovrebberoUsareMacchinaPizzeria);

                //DEVO CAPIRE QUANTI SCEGLIERNE
                ArrayList<Tuple<Person,Boolean>> personeDaLasciareLaFlagFalse = new ArrayList<>();

                int formula;
                if(getNumeroMacchinePizzeria() > mPcounterPersoneChedovrebberoUsareMacchinaPizzeria && nPersoneCheMacchinaPropria > 0 ){
                    formula = (int) (getNumeroMacchinePizzeria() - mPcounterPersoneChedovrebberoUsareMacchinaPizzeria);
                    //formula = (int) (g.getListaDiPersone().size() - numeroScooter - mPcounterPersoneChedovrebberoUsareMacchinaPizzeria);
                    //System.out.println("dovrei prendere: " + formula);
                    for(int i = 0; i < formula; i++){
                        if(l.size()!=0){
                            Person min = takeMin(l);
                            person_NumberOfTimesOwnCar.put(min.getAbbreviation(), person_NumberOfTimesOwnCar.get(min.getAbbreviation()) + 1);
                            personeDaLasciareLaFlagFalse.add(new Tuple<>(min,true));
                            l.remove(new Tuple<>(min,true));
                        }



                        //System.out.println("Ho rimosso: " + min);
                        //System.out.println("nuova lista da provare : "+ l);
                    }
                }else{

                }

                g.getListOfPeopleWithCar().replaceAll(new UnaryOperator<Tuple<Person, Boolean>>() {
                    @Override
                    public Tuple<Person, Boolean> apply(Tuple<Person, Boolean> personaBooleanTuple) {
                        Tuple<Person, Boolean> modified = personaBooleanTuple;
                        //System.out.println("persone da lasciare con la flag false: "+ personeDaLasciareLaFlagFalse);
                        if(!(personeDaLasciareLaFlagFalse.contains(personaBooleanTuple) || (carBuilderProperty.getPeopleMotorbike().contains(new Person(personaBooleanTuple.getT1().getAbbreviation())))
                                || carBuilderProperty.getPeoplePizzeriaCar().contains(new Person(personaBooleanTuple.getT1().getAbbreviation())))){

                            modified.setT2(true);
                            return modified;
                        }else{
                            return personaBooleanTuple;
                        }
                    }
                });
            }

        }

    }

    private int getNumeroMacchinePizzeria() {
        return carBuilderProperty.getNumberOfPizzeriaCar();
    }

    private int minAux(){
        return Collections.min(person_NumberOfTimesOwnCar.values());
    }
    private int maxAux(){
        return Collections.max(person_NumberOfTimesOwnCar.values());
    }
    public boolean isFair(int i){
        boolean bool = maxAux() - minAux() <= i;
        if(bool){
            return true;
        }else{
            reset();
            return false;
        }
    }

    private void reset() {
        for(Map.Entry<String, Integer> e : person_NumberOfTimesOwnCar.entrySet()){
            e.setValue(0);
        }
    }

    public Integer getValueFromPerson(String p){
        return person_NumberOfTimesOwnCar.get(p);
    }

    public Map<String, Integer> getPerson_NumberOfTimesOwnCar() {
        return person_NumberOfTimesOwnCar;
    }

    public static CarBuilderProperty createPropertyCar(List<Person> listOfPeople, int numberOfPizzeriaCar){
        List<Person> peopleOwnCar = new ArrayList<>();
        List<Person> peoplePizzeriaCar = new ArrayList<>();
        List<Person> peopleMotorbike = new ArrayList<>();

        for(Person p : listOfPeople) {
            switch (p.means) {
                case MOTOR_BIKE -> peopleMotorbike.add(p);
                case OWN_CAR -> peopleOwnCar.add(p);
                case PIZZERIA_CAR -> peoplePizzeriaCar.add(p);
            }
        }
        return new CarBuilderProperty(peopleOwnCar,peoplePizzeriaCar,peopleMotorbike, numberOfPizzeriaCar);
    }
}
