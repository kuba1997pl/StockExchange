/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockExchange.GUI;

import StockExchange.GUI.exception.ElementDodano;
import StockExchange.GUI.exception.DodanoObiektWyjatek;
import StockExchange.Kraje;
import StockExchange.Main;
import StockExchange.Spolka;
import StockExchange.Waluta;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author jakub
 */
public class WyborSpolek extends JFrame {

    Random generator = new Random();

    public WyborSpolek() {
        initComponents();
    }
    
    
    
    private void initComponents() {
        JFrame.setDefaultLookAndFeelDecorated(false);
        JFrame ramka = new JFrame("Dodaj spółki");
        ramka.setLayout(new FlowLayout());
        ramka.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] spolki = {
            "Wytwórnia Pasz Lira",
            "Microsoft Company",
            "Apple Inc.",
            "H & M Hennes & Mauritz AB",
            "Hugo Boss AG",
            "Google LLC",
            "Mars Inc.",
            "Coccodrillo",
            "Volkswagen Group",
            "Polskie Składy Drzewne DrewnoPol Świniec",
            "PKN Orlen SA",
            "PKO BP",
            "Cyfrowy Polsat SA",
            "Dino Polska SA",
            "Indykpol SA",
            "Tadex II Krzywiń",
            "Lechma Poznań",
            "DAREX - AGD RTV Śmigiel"
        };

        /**
         * frame and list parameters
         */
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String we : spolki) {
            model.addElement(we);
        }
        JList lista = new JList(model);
        JScrollPane panel = new JScrollPane(lista);
        Dimension roz = lista.getPreferredSize();
        roz.width = 350;
        roz.height = 315;
        panel.setPreferredSize(roz);
        ramka.add(panel);
        ramka.setSize(360, 360);
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
                    try {
                        String nazwa = (String) lista.getSelectedValue();
                        if (!nazwa.equals("Dodano!")) {
                            Spolka spolka = new Spolka(); //creating new object
                            spolka.setNazwa(nazwa);
                            //filling list included in object with random values
                            // Main.spolkiWprowadzone.add(spolka); //adding object to the main list

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
