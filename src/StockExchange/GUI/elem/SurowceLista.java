/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockExchange.GUI.elem;

import StockExchange.GUI.exception.DodanoObiektWyjatek;
import StockExchange.GUI.exception.ElementDodano;
import StockExchange.Main;
import StockExchange.Surowiec;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.*;

public class SurowceLista extends JFrame {

    String[] surowce = {
        "Kakao",
        "Herbata",
        "Kawa",
        "Ropa",
        "Złoto",
        "Platyna",
        "Pallad",
        "Wieprzowina",
        "Kauczuk",
        "Olej Opałowy",
        "Miedź",
        "Cynk",
        "Srebro",
        "Cukier",
        "Kukurydza",
        "Sok pomarańczowy",
        "Śruta sojowa"
    };

    String[] jednostki = {
        "baryłka",
        "uncja",
        "tona",
        "funt",
        "galon",
        "kilogram",
        "korzec"
    };

    Random generator = new Random();

    public SurowceLista() {
        initComponents();
    }

    private void initComponents() {

        /**
         * frame and list parameters
         */
     
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String we : surowce) {
            model.addElement(we);
        }
        JFrame ramka = new JFrame("Dodaj surowce:");
        ramka.setLayout(new FlowLayout());
        ramka.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JList lista = new JList(model);
        JScrollPane panel = new JScrollPane(lista);
        Dimension roz = lista.getPreferredSize();
        roz.width = 200;
        roz.height = 200;
        panel.setPreferredSize(roz);
        ramka.add(panel);
        ramka.setSize(250, 250);
        ramka.setLocationRelativeTo(null);
        ramka.setVisible(true);

        /**
         * with double click on the element of the list method creates an object
         * on the ArrayList in class Main //
         */
        lista.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList lista = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    Surowiec surowiec = new Surowiec(); //creating new object
                    String nazwa = (String) lista.getSelectedValue();
                    try {
                        if (!nazwa.equals("Dodano!")) {
                            surowiec.setNazwa(nazwa);
                            //filling list included in object with random values

                            double min = generator.nextDouble() * 12000;
                            double maks = min + generator.nextDouble() * 500;
                            double akt = (maks + min) / 2 + generator.nextDouble();
                            surowiec.setWartoscMaksymalna(maks);
                            surowiec.setWartoscMinimalna(min);
                            surowiec.setWartoscAktualna(akt);

                            int rozm = Main.rynekWalut.getListaWalut().size();
                            int elem = generator.nextInt(rozm); //generating random index 
                            surowiec.setWaluta(Main.rynekWalut.getListaWalut().get(elem));//Setting Waluta value

                            surowiec.setJednostkaHandlowa(jednostki[generator.nextInt(jednostki.length)]);

                            Main.rynekSurowcow.add(surowiec);

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
