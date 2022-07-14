package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {


        List<Person> listaPersona = new ArrayList<>();

        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";
        final String idk = "\u001B[38m";


        DayList LunediLista = new DayList("Lunedì", 3);
        DayList MartedìLista = new DayList("Martedì", 2);
        DayList MercolediLista = new DayList("Mercoledì", 3);
        DayList GiovediLista = new DayList("Giovedì", 3);
        DayList VenerdiLista = new DayList("Venerdì", 4);
        DayList SabatoLista = new DayList("Sabato", 5);
        DayList DomenicaLista = new DayList("Domenica", 5);

        List<DayList> listaGiorni = new ArrayList<>();
        listaGiorni.add(LunediLista);
        listaGiorni.add(MartedìLista);
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
        Person matteoNicolo = new Person(4, "M/N", ANSI_GREEN, "Matteo Grande", Means.MOTOR_BIKE);
        Person gigi = new Person(3, "G", ANSI_YELLOW, "Gigi", Means.OWN_CAR);
        Person enrico = new Person(3, "E", ANSI_BLUE, "Enrico", Means.OWN_CAR);
        Person lally = new Person(5, "F", ANSI_PURPLE, "Filippo", Means.OWN_CAR);
        Person pietro = new Person(3, "D", ANSI_CYAN, "Pietro", Means.PIZZERIA_CAR);
        Person pandi = new Person(2, "P", ANSI_WHITE, "Pandolfo", Means.OWN_CAR);
        Person omar = new Person(2, "O", idk, "Omar", Means.OWN_CAR);

        //pietro -> enrico -> gigi -> lally -> (pandi me matteo)

        List<Day> assenzeMatteo = new ArrayList<>();

        List<Day> assenzeNicolo = new ArrayList<>();
        assenzeNicolo.add(sabato);

        List<Day> assenzeGigi = new ArrayList<>();
        assenzeGigi.add(sabato);

        List<Day> assenzeEnrico = new ArrayList<>();
        assenzeEnrico.add(lunedi);
        assenzeEnrico.add(martedi);
        assenzeEnrico.add(mercoledi);
        assenzeEnrico.add(sabato);

        List<Day> assenzeLally = new ArrayList<>();

        List<Day> assenzePietro = new ArrayList<>();

        List<Day> assenzePandi = new ArrayList<>();

        List<Day> assenzeOmar = new ArrayList<>();

        pandi.addListaAssenze(assenzePandi);
        pietro.addListaAssenze(assenzePietro);
        lally.addListaAssenze(assenzeLally);
        enrico.addListaAssenze(assenzeEnrico);
        gigi.addListaAssenze(assenzeGigi);
        matteoNicolo.addListaAssenze(assenzeNicolo);
        matteo.addListaAssenze(assenzeMatteo);
        omar.addListaAssenze(assenzeOmar);

        listaPersona.add(matteo);
        listaPersona.add(matteoNicolo);
        listaPersona.add(gigi);
        listaPersona.add(enrico);
        listaPersona.add(lally);
        listaPersona.add(pietro);
        listaPersona.add(pandi);
        listaPersona.add(omar);

        WeeklyList weeklyList = new WeeklyList(listaPersona, listaGiorni, 2);

        weeklyList.calculate();


    }


}
