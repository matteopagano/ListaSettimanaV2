package com.company;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class MacchinePizzeria {


    private Map<String, Integer> numeroVoltePropria;
    private List<Persona> personeMacchinePropria;
    private List<Persona> personeMacchinePizzeria;
    private List<Persona> personeMotorinoPizzeria;
    private int numeroMacchinePizzeria;

    public MacchinePizzeria(List<Persona> macchineProprie, List<Persona> macchinePizzeria, List<Persona> motorinoPizzeria, int numeroMacchinePizzeria){
        this.numeroMacchinePizzeria = numeroMacchinePizzeria;
        this.numeroVoltePropria = new HashMap<>();
        for(Persona e : macchineProprie){
            this.numeroVoltePropria.put(e.getAbbreviazione(), 0);
        }

        //System.out.println(this.numeroVoltePropria);

        this.personeMacchinePropria = macchineProprie;
        //System.out.println(this.personeMacchinePropria);

        this.personeMacchinePizzeria = macchinePizzeria;
        //System.out.println(this.personeMacchinePizzeria);

        this.personeMotorinoPizzeria = motorinoPizzeria;
        //System.out.println(this.personeMotorinoPizzeria);


    }


    private Persona takeMin(List<Tuple<Persona,Boolean>> l){
        //System.out.println(numeroVoltePropria);
        //System.out.println(numeroVoltePropria);
        Tuple<Persona, Integer> min = new Tuple<>(l.get(0).getPerson(),numeroVoltePropria.get(l.get(0).getPerson().getAbbreviazione()));
        for(Tuple<Persona,Boolean> e : l){
            int numeroVolteE = numeroVoltePropria.get(e.getPerson().getAbbreviazione());
            if(numeroVolteE <= min.getUsaMacchinaPropria()){
                min = new Tuple<Persona,Integer>(e.getPerson(),numeroVolteE);
            }
        }
        //System.out.println("Ho preso il minore con: " + min.getPerson() + ", con volte: " +min.getUsaMacchinaPropria());
        return min.getPerson();
    }

    public long countStream(List<Tuple<Persona,Boolean>> l, Predicate<Tuple<Persona, Boolean>> p){
        return l.stream().filter(p).count();
    }

    public void setCar(ListaSettimana listaSettimana) {
        for(GiornoLista g : listaSettimana.getListaGiorni()){
            //System.out.println(g.getName()+": "+ g.getListaDiPersone());
            if(g.getListaDiPersone().containsAll(this.personeMacchinePizzeria) && this.personeMacchinePizzeria.size() == getNumeroMacchinePizzeria()){
                ArrayList<Tuple<Persona,Boolean>> l = g.getListaDiPersoneWithCar().stream().filter(new Predicate<Tuple<Persona, Boolean>>() {
                    @Override
                    public boolean test(Tuple<Persona, Boolean> personaBooleanTuple) {

                        return personeMacchinePropria.contains(new Persona(personaBooleanTuple.getPerson().getAbbreviazione()));
                    }
                }).collect(Collectors.toCollection(ArrayList::new));

                l.replaceAll(new UnaryOperator<Tuple<Persona, Boolean>>() {
                    @Override
                    public Tuple<Persona, Boolean> apply(Tuple<Persona, Boolean> personaBooleanTuple) {
                        return new Tuple<>(personaBooleanTuple.getPerson(),true);
                    }
                });

                g.getListaDiPersoneWithCar().replaceAll(new UnaryOperator<Tuple<Persona, Boolean>>() {
                    @Override
                    public Tuple<Persona, Boolean> apply(Tuple<Persona, Boolean> personaBooleanTuple) {
                        Tuple<Persona, Boolean> modified = personaBooleanTuple;
                        if(l.contains(personaBooleanTuple)){

                            modified.setUsaMacchinaPropria(true);
                            //numeroVoltePropria.put(personaBooleanTuple.getPerson().getAbbreviazione(),numeroVoltePropria.get(personaBooleanTuple.getPerson().getAbbreviazione())+1);
                            return modified;
                        }else{
                            return personaBooleanTuple;
                        }
                    }
                });

                //System.out.println(numeroVoltePropria);


            }
            else{


                long nPSenzaMotorino = g.getListaDiPersone().stream().filter(new Predicate<Persona>() {
                    @Override
                    public boolean test(Persona persona) {
                        return !personeMotorinoPizzeria.contains(persona);
                    }
                }).count();

                //System.out.println("numero persone senza motorino: "+ nPSenzaMotorino);

                long mPcounterPersoneChedovrebberoUsareMacchinaPizzeria = g.getListaDiPersone().stream().filter(new Predicate<Persona>() {
                    @Override
                    public boolean test(Persona persona) {
                        return personeMacchinePizzeria.contains(persona);
                    }
                }).count();

                long nPersoneCheMacchinaPropria = g.getListaDiPersone().stream().filter(new Predicate<Persona>() {
                    @Override
                    public boolean test(Persona persona) {
                        return personeMacchinePropria.contains(persona);
                    }
                }).count();

                ArrayList<Tuple<Persona,Boolean>> l = g.getListaDiPersoneWithCar().stream().filter(new Predicate<Tuple<Persona, Boolean>>() {
                    @Override
                    public boolean test(Tuple<Persona, Boolean> personaBooleanTuple) {

                        return personeMacchinePropria.contains(new Persona(personaBooleanTuple.getPerson().getAbbreviazione()));
                    }
                }).collect(Collectors.toCollection(ArrayList::new));


                //System.out.println(l);
                long numeroScooter = g.getListaDiPersone().stream().filter(new Predicate<Persona>() {
                    @Override
                    public boolean test(Persona persona) {
                        return personeMotorinoPizzeria.contains(persona);
                    }
                }).count();

                //System.out.println("macchina pizzeria counter: "+mPcounterPersoneChedovrebberoUsareMacchinaPizzeria);

                //DEVO CAPIRE QUANTI SCEGLIERNE
                ArrayList<Tuple<Persona,Boolean>> personeDaLasciareLaFlagFalse = new ArrayList<>();

                int formula;
                if(getNumeroMacchinePizzeria() > mPcounterPersoneChedovrebberoUsareMacchinaPizzeria && nPersoneCheMacchinaPropria > 0 ){
                    formula = (int) (getNumeroMacchinePizzeria() - mPcounterPersoneChedovrebberoUsareMacchinaPizzeria);
                    //formula = (int) (g.getListaDiPersone().size() - numeroScooter - mPcounterPersoneChedovrebberoUsareMacchinaPizzeria);
                    //System.out.println("dovrei prendere: " + formula);
                    for(int i = 0; i < formula; i++){
                        if(l.size()!=0){
                            Persona min = takeMin(l);
                            numeroVoltePropria.put(min.getAbbreviazione(), numeroVoltePropria.get(min.getAbbreviazione()) + 1);
                            personeDaLasciareLaFlagFalse.add(new Tuple<>(min,true));
                            l.remove(new Tuple<>(min,true));
                        }



                        //System.out.println("Ho rimosso: " + min);
                        //System.out.println("nuova lista da provare : "+ l);
                    }
                }else{

                }

                g.getListaDiPersoneWithCar().replaceAll(new UnaryOperator<Tuple<Persona, Boolean>>() {
                    @Override
                    public Tuple<Persona, Boolean> apply(Tuple<Persona, Boolean> personaBooleanTuple) {
                        Tuple<Persona, Boolean> modified = personaBooleanTuple;
                        //System.out.println("persone da lasciare con la flag false: "+ personeDaLasciareLaFlagFalse);
                        if(!(personeDaLasciareLaFlagFalse.contains(personaBooleanTuple) || (personeMotorinoPizzeria.contains(new Persona(personaBooleanTuple.getPerson().getAbbreviazione())))
                                || personeMacchinePizzeria.contains(new Persona(personaBooleanTuple.getPerson().getAbbreviazione())))){

                            modified.setUsaMacchinaPropria(true);
                            return modified;
                        }else{
                            return personaBooleanTuple;
                        }
                    }
                });


                /*if(mPcounterPersoneChedovrebberoUsareMacchinaPizzeria == 0){
                    if(g.getListaDiPersone().stream().anyMatch(new Predicate<Persona>() {
                        @Override
                        public boolean test(Persona persona) {
                            return personeMotorinoPizzeria.contains(persona);
                        }
                    })){
                        long numeroScooter = g.getListaDiPersone().stream().filter(new Predicate<Persona>() {
                            @Override
                            public boolean test(Persona persona) {
                                return personeMotorinoPizzeria.contains(persona);
                            }
                        }).count();

                        if(g.getListaDiPersone().size() - numeroScooter >= getNumeroMacchinePizzeria()){
                            System.out.println("devo prendere: " + getNumeroMacchinePizzeria());

                        }else{
                            System.out.println("devo prendere: " + (g.getListaDiPersone().size() - numeroScooter));
                        }

                        System.out.println(("numero persone che usano scooter: " + numeroScooter));


                        for(int i = 0; i < getNumeroMacchinePizzeria() - mPcounterPersoneChedovrebberoUsareMacchinaPizzeria - numeroScooter; i++){

                        }


                    }

                }else{
                    long numeroScooter = g.getListaDiPersone().stream().filter(new Predicate<Persona>() {
                        @Override
                        public boolean test(Persona persona) {
                            return personeMotorinoPizzeria.contains(persona);
                        }
                    }).count();

                        System.out.println("devo prendere: " + formula);

                    for(int i = 0; i < getNumeroMacchinePizzeria() - mPcounterPersoneChedovrebberoUsareMacchinaPizzeria; i++){
                    }
                }

                */




            }

        }

    }

    private int getNumeroMacchinePizzeria() {
        return numeroMacchinePizzeria;
    }

    private int minAux(){
        return Collections.min(numeroVoltePropria.values());
    }
    private int maxAux(){
        return Collections.max(numeroVoltePropria.values());
    }
    public boolean maxDistance(int i){
        boolean bool = maxAux() - minAux() <= i;
        if(bool){
            return true;
        }else{
            reset();
            return false;
        }
    }

    private void reset() {
        for(Map.Entry<String, Integer> e : numeroVoltePropria.entrySet()){
            e.setValue(0);
        }
    }

    public Integer getValueFromPerson(String p){
        return numeroVoltePropria.get(p);
    }

    public Map<String, Integer> getNumeroVoltePropria() {
        return numeroVoltePropria;
    }
}
