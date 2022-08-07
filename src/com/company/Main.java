package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {


        List<Person> listOfPeople = new ArrayList<>();

        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";
        final String idk = "\u001B[38m";


        DayOfList LunediLista = new DayOfList("Lunedì", 3);
        DayOfList MartediLista = new DayOfList("Martedì", 2);
        DayOfList MercolediLista = new DayOfList("Mercoledì", 2);
        DayOfList GiovediLista = new DayOfList("Giovedì", 3);
        DayOfList VenerdiLista = new DayOfList("Venerdì", 4);
        DayOfList SabatoLista = new DayOfList("Sabato", 5);
        DayOfList DomenicaLista = new DayOfList("Domenica", 5);


        List<DayOfList> listaGiorni = new ArrayList<>();
        listaGiorni.add(LunediLista);
        listaGiorni.add(MartediLista);
        listaGiorni.add(MercolediLista);
        listaGiorni.add(GiovediLista);
        listaGiorni.add(VenerdiLista);
        listaGiorni.add(SabatoLista);
        listaGiorni.add(DomenicaLista);

        Day lunedi = Day.getLunedi();
        Day martedi = Day.getMartedi();
        Day mercoledi = Day.getMercoledi();
        Day giovedi = Day.getGiovedi();
        Day venerdi = Day.getVenerdi();
        Day sabato = Day.getSabato();
        Day domenica = Day.getDomenica();

        Person matteo = new Person(3, "M", ANSI_RED, "Matteo Piccolo", Means.PIZZERIA_CAR);
        Person matteoNicolo = new Person(0, "M/N", ANSI_GREEN, "Matteo Grande", Means.MOTOR_BIKE);
        Person gigi = new Person(4, "G", ANSI_YELLOW, "Gigi", Means.OWN_CAR);
        Person enrico = new Person(4, "E", ANSI_BLUE, "Enrico", Means.OWN_CAR);
        Person lally = new Person(4, "F", ANSI_PURPLE, "Filippo", Means.OWN_CAR);
        Person pietro = new Person(5, "D", ANSI_CYAN, "Pietro", Means.PIZZERIA_CAR);
        Person pandi = new Person(0, "P", ANSI_WHITE, "Pandolfo", Means.OWN_CAR);
        Person omar = new Person(2, "O", idk, "Omar", Means.OWN_CAR);
        Person andrea = new Person(2, "A", ANSI_GREEN, "Andrea", Means.PIZZERIA_CAR);

        //pietro -> enrico -> gigi -> lally -> (pandi me matteo)

        List<Day> assenzeMatteo = new ArrayList<>();
        assenzeMatteo.add(lunedi);
        assenzeMatteo.add(martedi);
        assenzeMatteo.add(mercoledi);

        List<Day> assenzeNicolo = new ArrayList<>();

        List<Day> assenzeGigi = new ArrayList<>();
        assenzeGigi.add(martedi);

        List<Day> assenzeEnrico = new ArrayList<>();
        assenzeEnrico.add(lunedi);
        assenzeEnrico.add(martedi);

        List<Day> assenzeLally = new ArrayList<>();
        assenzeLally.add(mercoledi);
        assenzeLally.add(giovedi);


        List<Day> assenzePietro = new ArrayList<>();

        List<Day> assenzePandi = new ArrayList<>();

        List<Day> assenzeOmar = new ArrayList<>();
        assenzeOmar.add(mercoledi);
        assenzeOmar.add(venerdi);

        List<Day> assenzeAndrea = new ArrayList<>();

        pandi.addAbsences(assenzePandi);
        pietro.addAbsences(assenzePietro);
        lally.addAbsences(assenzeLally);
        enrico.addAbsences(assenzeEnrico);
        gigi.addAbsences(assenzeGigi);
        matteoNicolo.addAbsences(assenzeNicolo);
        matteo.addAbsences(assenzeMatteo);
        omar.addAbsences(assenzeOmar);
        omar.addAbsences(assenzeAndrea);

        listOfPeople.add(matteo);
        listOfPeople.add(matteoNicolo);
        listOfPeople.add(gigi);
        listOfPeople.add(enrico);
        listOfPeople.add(lally);
        listOfPeople.add(pietro);
        listOfPeople.add(pandi);
        listOfPeople.add(omar);
        listOfPeople.add(andrea);

        WeeklyList weeklyList = new WeeklyList(listOfPeople, listaGiorni, 2);





        weeklyList.calculate();



    }


}
