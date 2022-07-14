package com.company;

public class Day {
    private String day;

    private Day(String g){
        day = g;
    }
    public static Day getLunedi(){
        return new Day("Lunedì");
    }
    public static Day getMartedi(){
        return new Day("Martedì");
    }
    public static Day getMercoledi(){
        return new Day("Mercoledì");
    }
    public static Day getGiovedi(){
        return new Day("Giovedì");
    }
    public static Day getVenerdi(){
        return new Day("Venerdì");
    }
    public static Day getSabato(){
        return new Day("Sabato");
    }
    public static Day getDomenica(){
        return new Day("Domenica");
    }

    public String getDay() {
        return day;
    }

    @Override
    public String toString() {
        return "Giorno{" +
                "giorno='" + day + '\'' +
                '}';
    }
}
