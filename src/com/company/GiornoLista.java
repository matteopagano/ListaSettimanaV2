package com.company;

import java.util.ArrayList;
import java.util.List;

public class GiornoLista{

    private int numeroGiorniMAx;
    private String name;
    private List<Persona> listaDiPersona;

    public GiornoLista(String g,int n) {
        listaDiPersona=new ArrayList<>();
        name=g;
        numeroGiorniMAx=n;
    }
    public void addPersona(Persona p){
        listaDiPersona.add(p);
    }

    public String getName() {
        return name;
    }

    public int getNumeroGiorniMAx() {
        return numeroGiorniMAx;
    }

    public List<Persona> getListaDiPersona() {
        return listaDiPersona;
    }

    public boolean isFull(){
        return numeroGiorniMAx<=listaDiPersona.size();
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
}
