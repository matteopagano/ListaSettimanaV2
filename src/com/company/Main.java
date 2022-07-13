package com.company;

import javax.crypto.spec.PSource;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {


        List<Persona> listaPersona = new ArrayList<>();

        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";
        final String idk = "\u001B[38m";


        GiornoLista LunediLista=new GiornoLista("Lunedì",3);
        GiornoLista MartedìLista=new GiornoLista("Martedì",2);
        GiornoLista MercolediLista=new GiornoLista("Mercoledì",3);
        GiornoLista GiovediLista=new GiornoLista("Giovedì",3);
        GiornoLista VenerdiLista=new GiornoLista("Venerdì",4);
        GiornoLista SabatoLista=new GiornoLista("Sabato",5);
        GiornoLista DomenicaLista=new GiornoLista("Domenica",5);

        List<GiornoLista> listaGiorni=new ArrayList<>();
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


        Persona matteo = new Persona(3,"M",ANSI_RED, "Matteo Piccolo");
        Persona matteoNicolo = new Persona(4,"M/N",ANSI_GREEN, "Matteo Grande");
        Persona gigi = new Persona(4,"G",ANSI_YELLOW, "Gigi");
        Persona enrico = new Persona(4,"E",ANSI_BLUE, "Enrico");
        Persona lally = new Persona(3,"F",ANSI_PURPLE, "Filippo");
        Persona pietro = new Persona(3,"D",ANSI_CYAN, "Pietro");
        Persona pandi = new Persona(3,"P",ANSI_WHITE, "Pandolfo");
        Persona omar = new Persona(1,"O",idk, "Omar");

        List<Giorno> assenzeMatteo=new ArrayList<>();

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
        for(Persona p : listaPersona){
            for(Giorno g : p.getGiorniAssenza()){
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
        if(prova[0] + LunediLista.getNumeroGiorniMAx() > listaPersona.size()){
            cond = false;
            System.out.println("Controlla Lunedì");
            System.out.println("Lunedì hai inserito le assenze di " + prova[0] + " persone.");
        }else if(prova[1] + MartedìLista.getNumeroGiorniMAx() > listaPersona.size()){
            cond = false;
            System.out.println("Controlla Martedì");
            System.out.println("Martedì hai inserito le assenze di " + prova[1] + " persone.");
        }else if(prova[2] + MercolediLista.getNumeroGiorniMAx() > listaPersona.size()){
            cond = false;
            System.out.println("Controlla Mercoledì");
            System.out.println("Mercoledì hai inserito le assenze di " + prova[2] + " persone.");
        }else if(prova[3] + GiovediLista.getNumeroGiorniMAx() > listaPersona.size()){
            cond = false;
            System.out.println("Controlla Giovedì");
            System.out.println("Giovedì hai inserito le assenze di " + prova[3] + " persone.");
        }else if(prova[4] + VenerdiLista.getNumeroGiorniMAx() > listaPersona.size()){
            cond = false;
            System.out.println("Controlla Venerdì");
            System.out.println("Venerdì hai inserito le assenze di " + prova[4] + " persone.");
        }else if(prova[5] + SabatoLista.getNumeroGiorniMAx() >listaPersona.size()){
            cond = false;
            System.out.println("Controlla Sabato");
            System.out.println("Sabato hai inserito le assenze di " + prova[5] + " persone.");
        }else if(prova[6] + DomenicaLista.getNumeroGiorniMAx() > listaPersona.size()){
            cond = false;
            System.out.println("Controlla Domenica");
            System.out.println("Domenica hai inserito le assenze di " + prova[6] + " persone.");
        }

        ArrayList<Persona> personeMacchinaPropria = new ArrayList<>();
        personeMacchinaPropria.add(pandi);
        personeMacchinaPropria.add(enrico);
        personeMacchinaPropria.add(gigi);
        personeMacchinaPropria.add(lally);
        personeMacchinaPropria.add(omar);

        ArrayList<Persona> personeMacchinaPizzeria = new ArrayList<>();
        personeMacchinaPizzeria.add(matteo);
        personeMacchinaPizzeria.add(pietro);

        ArrayList<Persona> personeMotorinoPizzeria = new ArrayList<>();
        personeMotorinoPizzeria.add(matteoNicolo);

        ListaSettimana l=new ListaSettimana(listaPersona, personeMacchinaPropria, personeMacchinaPizzeria, personeMotorinoPizzeria, 2);


        if(l.isCorrectOccorrenzePersone()){
            l.add(listaGiorni);

            int numeroPresenzeDaOccupare=0;
            int numeroDisponibilitaPersone=0;
            for (GiornoLista g:listaGiorni){
                numeroPresenzeDaOccupare+=g.getNumeroGiorniMAx();
            }
            for (Persona p:listaPersona){
                numeroDisponibilitaPersone+=p.getNumeroGiorni();
            }

            if(numeroDisponibilitaPersone>numeroPresenzeDaOccupare){
                System.out.println("Diminuisici di "+(numeroDisponibilitaPersone-numeroPresenzeDaOccupare)+" i giorni lavorativi alle persone!");
            }else if(numeroDisponibilitaPersone<numeroPresenzeDaOccupare){
                System.out.println("Aggiungi di "+(numeroPresenzeDaOccupare-numeroDisponibilitaPersone)+" i giorni lavorativi alle persone!");
            }
            else{
                if(cond){
                    //l.calcolaLista();
                    l.calcola();
                }
            }
        }


    }


}
