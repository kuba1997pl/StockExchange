/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockExchange.GUI.elem;

import java.awt.FlowLayout;
import StockExchange.GUI.exception.DodanoObiektWyjatek;
import StockExchange.GUI.exception.ElementDodano;
import StockExchange.model.Waluta;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author jakub
 * 
 * class generates random values of Waluta objects 
 */
public class WalutyLista extends JFrame {

    public WalutyLista() {
        initComponents();
    }
    Random generator = new Random();

    private void initComponents() {
        //JFrame.setDefaultLookAndFeelDecorated(false);
        JFrame ramka = new JFrame("Dodaj waluty:");
        ramka.setLayout(new FlowLayout());
        ramka .setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] waluty = {
            "Dolar amerykański (USD)",
            "Złoty (PLN)",
            "Euro (EUR)",
            "Rubel rosyjski (RUB)",
            "Urugwajskie pesos (UYU)",
            "Forint węgierski (HUF)", //forint węgierski
            "Funt szterling (GBP)",
            "Korona czeska (CZK)",
            "Real brazylijski (BRL)", // Brazylia, real
            "Korona duńska (DKK)", // Korona duńska 
            "Jen japoński (JPY)", //Jen japoński
            "Frank szwajcarski (CHF)", // frank szwajcarski
            "Rand, RPA (ZAR)" //RPA, rand
        };

        /**
         * frame and list parameters 
         */
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String we : waluty) {
            model.addElement(we);
        }
        JList lista = new JList(model);
        JScrollPane panel = new JScrollPane(lista);
        Dimension roz = lista.getPreferredSize();
        roz.width=230;
        roz.height=230;
        panel.setPreferredSize(roz);
        ramka.add(panel);
        ramka.setSize(280,280);
        //frame.pack();
        ramka.setLocationRelativeTo(null);
        ramka.setVisible(true);

        /**
         * with double click on the element of the list method creates an object
         * on the ArrayList in class Main
         */
        lista.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList lista = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    Waluta waluta = new Waluta(); //creating new object
                    String nazwa = (String) lista.getSelectedValue();
                    try {
                        if (!nazwa.equals("Dodano!")){

                            waluta.setNazwa(nazwa);
                            //adding object to the main list
                            //Main.rynekWalut.add(waluta);
                            int index = lista.getSelectedIndex();
                            model.setElementAt("Dodano!", index);

                        } else {
                            throw new DodanoObiektWyjatek();
                        }
                    } catch (DodanoObiektWyjatek wyj) {
                        java.awt.EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                new ElementDodano().setVisible(true);
                            }
                        });
                    }
                }
            }
        });
    }
}
