package com.company;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.*;
import java.util.List;

public class ListaSettimana {
    private List<Persona> listaPersone;
    private List<GiornoLista> listaGiorni;

    public ListaSettimana(List<Persona> listaPersona) {
        this.listaPersone=listaPersona;
        this.listaGiorni=new ArrayList<>();

    }

    public void calcolaLista() throws PrinterException {
        /*for (Persona p:listaPersone){
            System.out.println(p);
        }
        */
        List<Persona> lista=new ArrayList<>();
        for (Persona p:listaPersone){
            for (int i=0;i<p.getNumeroGiorni();++i)
            lista.add(p);
        }
        /*
        for(Persona p:lista){
            System.out.print(p.getAbbreviazione()+", ");
        }
        */

            Collections.shuffle(lista);
        /*
        System.out.println("MESCOLO LA LISTA:");
        for(Persona p:lista){
            System.out.print(p.getAbbreviazione()+", ");

        }
        */
            //System.out.println();
            Queue<Persona> coda= new LinkedList<>();
            coda.addAll(lista);
            while(!coda.isEmpty()){
                Persona personaDaAddare=coda.poll();
                //System.out.println();
                //System.out.println("HO estratto: "+personaDaAddare.getAbbreviazione());
                Iterator<GiornoLista> it=listaGiorni.iterator();
                boolean aggiunto=false;
                while(!aggiunto && it.hasNext()){
                    GiornoLista g=it.next();
                    if(!g.isFull()&&isAddable(personaDaAddare,g)&&!alreadyPresent(personaDaAddare,g)){
                        g.addPersona(personaDaAddare);
                        aggiunto=true;
                    }
                }
            }
            //stampaLista();
            //System.out.println();
            if(!isCorrectLista()){
                //System.out.println("LISTA CLEARATA!");
                //System.out.println("Lista clear...");
                clearGiorni();
                //System.out.println();
                //stampaLista();
                //System.out.println("RICALCOLANDO LISTA!");
                calcolaLista();
            }else {
                System.out.println();
                //System.out.println("CORRETTA");
                stampaLista();
                stampaListaV2();
                //upGrade();
            }
        }

    private void clearGiorni() {
        for (GiornoLista g:listaGiorni){
            g.getListaDiPersona().clear();
        }
    }

    private boolean isCorrectLista() {
        boolean bool=true;
        for (GiornoLista g:listaGiorni){
            if(g.getListaDiPersona().size()!=g.getNumeroGiorniMAx()){
                bool=false;
            }
        }
        return bool;
    }

    private boolean alreadyPresent(Persona personaDaAddare, GiornoLista g) {
        boolean bool=false;
        for(Persona g1:g.getListaDiPersona()){
            if(g1.getAbbreviazione().equals(personaDaAddare.getAbbreviazione())) bool=true;
        }
        return bool;
    }

    public static boolean isAddable(Persona personaDaAddare, GiornoLista g) {
        boolean bool=true;
        String s=g.getName();
        for(Giorno gP:personaDaAddare.getGiorniAssenza()){
            if(gP.getGiorno().equals(s)){
                bool=false;
            }
        }
        return bool;
    }

    public void stampaLista() {
        for (GiornoLista g:listaGiorni){
            System.out.println(g.getName()+": "+g.getListaDiPersona());
        }
    }

    public void stampaListaV2() throws PrinterException {

        // frame
        JFrame f;
        // Table
        JTable j;

        // Frame initialization
        f = new JFrame();

        // Frame Title
        f.setTitle("Lista della settimana");


        // Data to be displayed in the JTable
        String[][] data = {
                { "x","x", "x", "x", "x", "x", "x", "x", "x" },
                { "x","x", "x", "x", "x", "x", "x", "x", "x" }
        };

        // Column Names
        String[] columnNames = { "Dal     al        ", "LUNEDI", "MARTEDI", "MERCOLEDI", "GIOVEDI", "VENERDI", "SABATO", "DOMENICA", "TOT. SERATE" };

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        // Initializing the JTable



        j = new JTable(tableModel);
        j.getTableHeader().setFont(new Font("Ariel", Font.BOLD, 12));
        j.setRowHeight(30);
        Object[] o;
        {
            String personeNum = "N° persone";
            int lunedi = 0;
            int martedi = 0;
            int mercoledi = 0;
            int giovedi = 0;
            int venerdi = 0;
            int sabato = 0;
            int domenica = 0;
            for (GiornoLista g : listaGiorni) {

                if (g.getName().equals("Lunedì")) {
                    lunedi = g.getNumeroGiorniMAx();
                } else if (g.getName().equals("Martedì")) {
                    martedi = g.getNumeroGiorniMAx();
                } else if (g.getName().equals("Mercoledì")) {
                    mercoledi = g.getNumeroGiorniMAx();
                } else if (g.getName().equals("Giovedì")) {
                    giovedi = g.getNumeroGiorniMAx();
                } else if (g.getName().equals("Venerdì")) {
                    venerdi = g.getNumeroGiorniMAx();
                } else if (g.getName().equals("Sabato")) {
                    sabato = g.getNumeroGiorniMAx();
                } else if (g.getName().equals("Domenica")) {
                    domenica = g.getNumeroGiorniMAx();
                }

            }
            o = new Object[]{personeNum, lunedi, martedi, mercoledi, giovedi, venerdi, sabato, domenica};
            tableModel.addRow(o);
        }

        for (Persona p : listaPersone){
            String nome = p.getNome();
            String lunedi = " ";
            String martedi= " ";
            String mercoledi= " ";
            String giovedi= " ";
            String venerdi= " ";
            String sabato= " ";
            String domenica= " ";
            int totSere = p.getNumeroGiorni();
            Object[] objs;
            for(GiornoLista g : listaGiorni){
                if(g.getName().equals("Lunedì")){
                    if(alreadyPresent(p,g)){
                        lunedi = "X";
                    }
                }else if(g.getName().equals("Martedì")){
                    if(alreadyPresent(p,g)){
                        martedi = "X";
                    }
                }else if(g.getName().equals("Mercoledì")){
                    if(alreadyPresent(p,g)){
                        mercoledi = "X";
                    }
                }else if(g.getName().equals("Giovedì")){
                    if(alreadyPresent(p,g)){
                        giovedi = "X";
                    }
                }else if(g.getName().equals("Venerdì")){
                    if(alreadyPresent(p,g)){
                        venerdi = "X";
                    }
                }else if(g.getName().equals("Sabato")){
                    if(alreadyPresent(p,g)){
                        sabato = "X";
                    }
                }else if(g.getName().equals("Domenica")){
                    if(alreadyPresent(p,g)){
                        domenica = "X";
                    }
                }
            }
            objs = new Object[]{nome,lunedi,martedi,mercoledi,giovedi,venerdi,sabato,domenica,totSere};
            tableModel.addRow(objs);

        }


        for(int x=0;x<j.getColumnCount();x++){
            DefaultTableCellRenderer rdr = new DefaultTableCellRenderer() {
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column) {
                    super.getTableCellRendererComponent(
                            table, value, isSelected, hasFocus, row, column);
                    setHorizontalAlignment(JLabel.CENTER);
                    setFont(getFont().deriveFont(12f));
                    return this;
                }

            };

            TableColumn col = j.getColumnModel().getColumn(x);
            col.setCellRenderer(rdr);

        }

        j.setBounds(60, 60, 50, 300);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        sp.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        f.add(sp);
        // Frame Size
        f.setSize(825, 330);
        // Frame Visible = true
        f.setVisible(true);
        PrinterJob pjob = PrinterJob.getPrinterJob();
        PageFormat preformat = pjob.defaultPage();
        preformat.setOrientation(PageFormat.LANDSCAPE);
        PageFormat postformat = pjob.pageDialog(preformat);
        //If user does not hit cancel then print.
        if (preformat != postformat) {
            //Set print component
            pjob.setPrintable(new Printer(f), postformat);
            if (pjob.printDialog()) {
                pjob.print();
            }
        }

    }

    public void add(List<GiornoLista> l) {
        this.listaGiorni.addAll(l);
    }

    public boolean isCorrectOccorrenzePersone(){
        boolean bool=true;
        for(Persona p: listaPersone){
            if(!p.isCorrect()){
                bool=false;
            }
        }
        return bool;
    }

    public class MyTableCellEditor extends AbstractCellEditor implements TableCellEditor {
        private JTextField component = new JTextField();
        private Font font = new Font("Arial Unicode MS", 0, 1);

        public Component getTableCellEditorComponent(JTable table,
                                                     Object value, boolean isSelected, int rowIndex, int vColIndex) {
            component.setText((String) value);
            component.setFont(font);
            return component;
        }

        @Override
        public Object getCellEditorValue() {
            return component.getText();
        }
    }


    public static class Printer implements Printable {
        final Component comp;

        public Printer(Component comp){
            this.comp = comp;
        }

        @Override
        public int print(Graphics g, PageFormat format, int page_index)
                throws PrinterException {
            if (page_index > 0) {
                return Printable.NO_SUCH_PAGE;
            }

            // get the bounds of the component
            Dimension dim = comp.getSize();
            double cHeight = dim.getHeight();
            double cWidth = dim.getWidth();

            // get the bounds of the printable area
            double pHeight = format.getImageableHeight();
            double pWidth = format.getImageableWidth();

            double pXStart = format.getImageableX();
            double pYStart = format.getImageableY();

            double xRatio = pWidth / cWidth;
            double yRatio = pHeight / cHeight;


            Graphics2D g2 = (Graphics2D) g;
            g2.translate(pXStart, pYStart);
            g2.scale(xRatio, yRatio);
            comp.paint(g2);

            return Printable.PAGE_EXISTS;
        }
    }
}
