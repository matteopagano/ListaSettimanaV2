package com.company;

import java.util.Objects;

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

    public static Day getDayByName(String name){
        Day d = null;
        switch (name) {
            case "Lunedì" -> d = getLunedi();
            case "Martedì" -> d = getMartedi();
            case "Mercoledì" -> d = getMercoledi();
            case "Giovedì" -> d = getGiovedi();
            case "Venerdì" -> d = getVenerdi();
            case "Sabato" -> d = getSabato();
            case "Domenica" -> d = getDomenica();
        }
        return d;
    }

    @Override
    public String toString() {
        return "Giorno{" +
                "giorno='" + day + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day1 = (Day) o;
        return Objects.equals(day, day1.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day);
    }
}
