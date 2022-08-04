package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Printer implements Printable {
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

    public static void sendToPrinter(WeeklyList weeklyList) throws PrinterException {


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
            for (DayOfList g : weeklyList.getListOfDays()) {

                switch (g.getName()) {
                    case "Lunedì" -> lunedi = g.getNumberOfSlots();
                    case "Martedì" -> martedi = g.getNumberOfSlots();
                    case "Mercoledì" -> mercoledi = g.getNumberOfSlots();
                    case "Giovedì" -> giovedi = g.getNumberOfSlots();
                    case "Venerdì" -> venerdi = g.getNumberOfSlots();
                    case "Sabato" -> sabato = g.getNumberOfSlots();
                    case "Domenica" -> domenica = g.getNumberOfSlots();
                }

            }
            o = new Object[]{personeNum, lunedi, martedi, mercoledi, giovedi, venerdi, sabato, domenica};
            tableModel.addRow(o);
        }

        for (Person p : weeklyList.getListOfPeople()){
            String nome = p.getName();
            String lunedi = " ";
            String martedi= " ";
            String mercoledi= " ";
            String giovedi= " ";
            String venerdi= " ";
            String sabato= " ";
            String domenica= " ";

            Object[] objs;
            for(DayOfList g : weeklyList.getListOfDays()){
                switch (g.getName()) {
                    case "Lunedì":
                        if (weeklyList.isAlreadyPresent(p, g)) {

                            ArrayList<Tuple<Person, Boolean>> l = g.getListOfPeopleWithCar().stream().filter(personaBooleanTuple -> personaBooleanTuple.equals(new Tuple<>(new Person(p.getAbbreviation()), true))).collect(Collectors.toCollection(ArrayList::new));

                            if (l.get(0).getT2()) {
                                lunedi = "\u24CD";
                            } else {
                                lunedi = "X";
                            }


                        }
                        break;
                    case "Martedì":
                        if (weeklyList.isAlreadyPresent(p, g)) {
                            ArrayList<Tuple<Person, Boolean>> l = g.getListOfPeopleWithCar().stream().filter(personaBooleanTuple -> personaBooleanTuple.equals(new Tuple<>(new Person(p.getAbbreviation()), true))).collect(Collectors.toCollection(ArrayList::new));

                            if (l.get(0).getT2()) {
                                martedi = "\u24CD";
                            } else {
                                martedi = "X";
                            }
                        }
                        break;
                    case "Mercoledì":
                        if (weeklyList.isAlreadyPresent(p, g)) {
                            ArrayList<Tuple<Person, Boolean>> l = g.getListOfPeopleWithCar().stream().filter(personaBooleanTuple -> personaBooleanTuple.equals(new Tuple<>(new Person(p.getAbbreviation()), true))).collect(Collectors.toCollection(ArrayList::new));

                            if (l.get(0).getT2()) {
                                mercoledi = "\u24CD";
                            } else {
                                mercoledi = "X";
                            }
                        }
                        break;
                    case "Giovedì":
                        if (weeklyList.isAlreadyPresent(p, g)) {
                            ArrayList<Tuple<Person, Boolean>> l = g.getListOfPeopleWithCar().stream().filter(personaBooleanTuple -> personaBooleanTuple.equals(new Tuple<>(new Person(p.getAbbreviation()), true))).collect(Collectors.toCollection(ArrayList::new));

                            if (l.get(0).getT2()) {
                                giovedi = "\u24CD";
                            } else {
                                giovedi = "X";
                            }
                        }
                        break;
                    case "Venerdì":
                        if (weeklyList.isAlreadyPresent(p, g)) {
                            ArrayList<Tuple<Person, Boolean>> l = g.getListOfPeopleWithCar().stream().filter(personaBooleanTuple -> personaBooleanTuple.equals(new Tuple<>(new Person(p.getAbbreviation()), true))).collect(Collectors.toCollection(ArrayList::new));

                            if (l.get(0).getT2()) {
                                venerdi = "\u24CD";
                            } else {
                                venerdi = "X";
                            }
                        }
                        break;
                    case "Sabato":
                        if (weeklyList.isAlreadyPresent(p, g)) {
                            ArrayList<Tuple<Person, Boolean>> l = g.getListOfPeopleWithCar().stream().filter(personaBooleanTuple -> personaBooleanTuple.equals(new Tuple<>(new Person(p.getAbbreviation()), true))).collect(Collectors.toCollection(ArrayList::new));

                            if (l.get(0).getT2()) {
                                sabato = "\u24CD";
                            } else {
                                sabato = "X";
                            }
                        }
                        break;
                    case "Domenica":
                        if (weeklyList.isAlreadyPresent(p, g)) {
                            ArrayList<Tuple<Person, Boolean>> l = g.getListOfPeopleWithCar().stream().filter(personaBooleanTuple -> personaBooleanTuple.equals(new Tuple<>(new Person(p.getAbbreviation()), true))).collect(Collectors.toCollection(ArrayList::new));

                            if (l.get(0).getT2()) {
                                domenica = "\u24CD";
                            } else {
                                domenica = "X";
                            }
                        }
                        break;
                }
            }
            String total = "";

            int macchinaPiz = 0;
            int macchinaPropria = 0;

            if(lunedi.equals("X")){
                macchinaPiz ++;
            }else if(lunedi.equals("\u24CD")){
                macchinaPropria ++;
            }
            if(martedi.equals("X")){
                macchinaPiz ++;
            }else if(martedi.equals("\u24CD")){
                macchinaPropria ++;
            }
            if(mercoledi.equals("X")){
                macchinaPiz ++;
            }else if(mercoledi.equals("\u24CD")){
                macchinaPropria ++;
            }
            if(giovedi.equals("X")){
                macchinaPiz ++;
            }else if(giovedi.equals("\u24CD")){
                macchinaPropria ++;
            }
            if(venerdi.equals("X")){
                macchinaPiz ++;
            }else if(venerdi.equals("\u24CD")){
                macchinaPropria ++;
            }
            if(sabato.equals("X")){
                macchinaPiz ++;
            }else if(sabato.equals("\u24CD")){
                macchinaPropria ++;
            }
            if(domenica.equals("X")){
                macchinaPiz ++;
            }else if(domenica.equals("\u24CD")){
                macchinaPropria ++;
            }


            if(macchinaPiz == 0 && macchinaPropria == 0){
                total = 0 + "";
            }else if(macchinaPiz != 0 && macchinaPropria == 0){

                total = macchinaPiz + "";
            }else if(macchinaPiz == 0){
                total = macchinaPropria + "Macchina";
            }else {
                total = macchinaPiz + " + " + macchinaPropria + " Macchina";
            }



            objs = new Object[]{nome,lunedi,martedi,mercoledi,giovedi,venerdi,sabato,domenica,total};
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
}
