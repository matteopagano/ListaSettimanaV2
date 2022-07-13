package com.company;

import java.util.ArrayList;
import java.util.List;

public class GiornoLista{

    private int numeroGiorniMAx;
    private String name;
    private List<Tuple<Persona, Boolean>> listaDiPersone;

    public GiornoLista(String g,int n) {
        listaDiPersone=new ArrayList<>();
        name=g;
        numeroGiorniMAx=n;
    }
    public void addPersona(Persona p){
        listaDiPersone.add(new Tuple<>(p, false));
    }

    public String getName() {
        return name;
    }

    public int getNumeroGiorniMAx() {
        return numeroGiorniMAx;
    }

    public List<Tuple<Persona,Boolean>> getListaDiPersoneWithCar() {
        return listaDiPersone;
    }


    public List<Persona> getListaDiPersone() {
        List<Persona> lista = new ArrayList<>();
        for (Tuple<Persona, Boolean> e : this.listaDiPersone){
            lista.add(e.getPerson());
        }
        return lista;
    }

    public void setListaDiPersone(List<Tuple<Persona, Boolean>> listaDiPersone) {
        this.listaDiPersone = listaDiPersone;
    }

    public boolean isFull(){
        return numeroGiorniMAx<=listaDiPersone.size();
    }
    @Override
    public boolean equals(Object o){
        GiornoLista g;
        boolean bool=false;
        if(o instanceof GiornoLista){
            g=(GiornoLista) o;
            bool=this.name.equals(g.name);
        }
        return bool;
    }

    @Override
    public String toString() {
        return "GiornoLista{" +
                "numeroGiorniMAx=" + numeroGiorniMAx +
                ", name='" + name + '\'' +
                ", listaDiPersona=" + listaDiPersone +
                '}';
    }
}
