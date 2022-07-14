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


        DayList LunediLista=new DayList("Lunedì",3);
        DayList MartedìLista=new DayList("Martedì",2);
        DayList MercolediLista=new DayList("Mercoledì",3);
        DayList GiovediLista=new DayList("Giovedì",3);
        DayList VenerdiLista=new DayList("Venerdì",4);
        DayList SabatoLista=new DayList("Sabato",5);
        DayList DomenicaLista=new DayList("Domenica",5);

        List<DayList> listaGiorni=new ArrayList<>();
        listaGiorni.add(LunediLista);
        listaGiorni.add(MartedìLista);
        listaGiorni.add(MercolediLista);
        listaGiorni.add(GiovediLista);
        listaGiorni.add(VenerdiLista);
        listaGiorni.add(SabatoLista);
        listaGiorni.add(DomenicaLista);

        Giorno lunedi=Giorno.getLunedi();
        Giorno martedi=Giorno.getMartedi();
        Giorno mercoledi=Giorno.getMercoledi();
        Giorno giovedi=Giorno.getGiovedi();
        Giorno venerdi=Giorno.getVenerdi();
        Giorno sabato=Giorno.getSabato();
        Giorno domenica=Giorno.getDomenica();


        Person matteo = new Person(3,"M",ANSI_RED, "Matteo Piccolo", Means.PIZZERIA_CAR);
        Person matteoNicolo = new Person(3,"M/N",ANSI_GREEN, "Matteo Grande", Means.MOTOR_BIKE);
        Person gigi = new Person(3,"G",ANSI_YELLOW, "Gigi", Means.OWN_CAR);
        Person enrico = new Person(3,"E",ANSI_BLUE, "Enrico", Means.OWN_CAR);
        Person lally = new Person(5,"F",ANSI_PURPLE, "Filippo", Means.OWN_CAR);
        Person pietro = new Person(3,"D",ANSI_CYAN, "Pietro", Means.PIZZERIA_CAR);
        Person pandi = new Person(3,"P",ANSI_WHITE, "Pandolfo", Means.OWN_CAR);
        Person omar = new Person(2,"O",idk, "Omar", Means.OWN_CAR);


        int maxDistance = 1;

        List<Giorno> assenzeMatteo=new ArrayList<>();
        assenzeMatteo.add(lunedi);
        assenzeMatteo.add(sabato);
        assenzeMatteo.add(domenica);

        //pietro -> enrico -> gigi -> lally -> (pandi me matteo)

        List<Giorno> assenzeNicolo=new ArrayList<>();


        List<Giorno> assenzeGigi=new ArrayList<>();




        List<Giorno> assenzeEnrico=new ArrayList<>();


        List<Giorno> assenzeLally=new ArrayList<>();


        List<Giorno> assenzePietro=new ArrayList<>();

        List<Giorno> assenzePandi=new ArrayList<>();


        List<Giorno> assenzeOmar = new ArrayList<>();






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

        int[] prova = new int[7];
        for(Person p : listaPersona){
            for(Giorno g : p.getDaysOfAbsence()){
                if(g.getGiorno().toString().equals("Lunedì")){
                    prova[0] = prova[0] + 1;
                }else if(g.getGiorno().toString().equals("Martedì")){
                    prova[1] = prova[1] + 1;
                }else if(g.getGiorno().toString().equals("Mercoledì")){
                    prova[2] = prova[2] + 1;
                }else if(g.getGiorno().toString().equals("Giovedì")){
                    prova[3] = prova[3] + 1;
                }else if(g.getGiorno().toString().equals("Venerdì")){
                    prova[4] = prova[4] + 1;
                }else if(g.getGiorno().toString().equals("Sabato")){
                    prova[5] = prova[5] + 1;
                }else if(g.getGiorno().toString().equals("Domenica")){
                    prova[6] = prova[6] + 1;
                }
            }
        }

        boolean cond = true;
        if(prova[0] + LunediLista.getNumberOfSlots() > listaPersona.size()){
            cond = false;
            System.out.println("Controlla Lunedì");
            System.out.println("Lunedì hai inserito le assenze di " + prova[0] + " persone.");
        }else if(prova[1] + MartedìLista.getNumberOfSlots() > listaPersona.size()){
            cond = false;
            System.out.println("Controlla Martedì");
            System.out.println("Martedì hai inserito le assenze di " + prova[1] + " persone.");
        }else if(prova[2] + MercolediLista.getNumberOfSlots() > listaPersona.size()){
            cond = false;
            System.out.println("Controlla Mercoledì");
            System.out.println("Mercoledì hai inserito le assenze di " + prova[2] + " persone.");
        }else if(prova[3] + GiovediLista.getNumberOfSlots() > listaPersona.size()){
            cond = false;
            System.out.println("Controlla Giovedì");
            System.out.println("Giovedì hai inserito le assenze di " + prova[3] + " persone.");
        }else if(prova[4] + VenerdiLista.getNumberOfSlots() > listaPersona.size()){
            cond = false;
            System.out.println("Controlla Venerdì");
            System.out.println("Venerdì hai inserito le assenze di " + prova[4] + " persone.");
        }else if(prova[5] + SabatoLista.getNumberOfSlots() >listaPersona.size()){
            cond = false;
            System.out.println("Controlla Sabato");
            System.out.println("Sabato hai inserito le assenze di " + prova[5] + " persone.");
        }else if(prova[6] + DomenicaLista.getNumberOfSlots() > listaPersona.size()){
            cond = false;
            System.out.println("Controlla Domenica");
            System.out.println("Domenica hai inserito le assenze di " + prova[6] + " persone.");
        }

        ArrayList<Person> personeMacchinaPropria = new ArrayList<>();
        personeMacchinaPropria.add(pandi);
        personeMacchinaPropria.add(enrico);
        personeMacchinaPropria.add(gigi);
        personeMacchinaPropria.add(lally);
        //personeMacchinaPropria.add(omar);

        ArrayList<Person> personeMacchinaPizzeria = new ArrayList<>();
        personeMacchinaPizzeria.add(matteo);
        personeMacchinaPizzeria.add(pietro);
        personeMacchinaPizzeria.add(omar);


        ArrayList<Person> personeMotorinoPizzeria = new ArrayList<>();
        personeMotorinoPizzeria.add(matteoNicolo);

        WeeklyList l = new WeeklyList(listaPersona, listaGiorni, 2);



        if(l.checkIfExcheedFromInitialOptions()){

            int numeroPresenzeDaOccupare=0;
            int numeroDisponibilitaPersone=0;
            for (DayList g:listaGiorni){
                numeroPresenzeDaOccupare+=g.getNumberOfSlots();
            }
            for (Person p:listaPersona){
                numeroDisponibilitaPersone+=p.getNumberOfDaysToDo();
            }

            if(numeroDisponibilitaPersone>numeroPresenzeDaOccupare){
                System.out.println("Diminuisici di "+(numeroDisponibilitaPersone-numeroPresenzeDaOccupare)+" i giorni lavorativi alle persone!");
            }else if(numeroDisponibilitaPersone<numeroPresenzeDaOccupare){
                System.out.println("Aggiungi di "+(numeroPresenzeDaOccupare-numeroDisponibilitaPersone)+" i giorni lavorativi alle persone!");
            }
            else{
                if(cond){
                    //l.calcolaLista();
                    l.calculate();
                }
            }
        }


    }


}
