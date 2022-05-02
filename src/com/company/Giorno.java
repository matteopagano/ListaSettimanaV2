package com.company;

public class Giorno {
    private String giorno;

    private Giorno(String g){
        giorno=g;
    }
    public static Giorno getLunedi(){
        return new Giorno("Lunedì");
    }
    public static Giorno getMartedi(){
        return new Giorno("Martedì");
    }
    public static Giorno getMercoledi(){
        return new Giorno("Mercoledì");
    }
    public static Giorno getGiovedi(){
        return new Giorno("Giovedì");
    }
    public static Giorno getVenerdi(){
        return new Giorno("Venerdì");
    }
    public static Giorno getSabato(){
        return new Giorno("Sabato");
    }
    public static Giorno getDomenica(){
        return new Giorno("Domenica");
    }

    public String getGiorno() {
        return giorno;
    }

    @Override
    public String toString() {
        return "Giorno{" +
                "giorno='" + giorno + '\'' +
                '}';
    }
}
