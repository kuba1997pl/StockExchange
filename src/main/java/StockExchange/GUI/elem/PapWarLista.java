/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StockExchange.GUI.elem;

import StockExchange.GUI.exception.DodanoObiektWyjatek;
import StockExchange.GUI.exception.ElementDodano;
import StockExchange.Main;
import StockExchange.model.Gielda;
import StockExchange.model.Kraje;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 *
 * @author jakub
 */
public class PapWarLista extends JFrame {

    String[] miasta = {
        "Nowy Jork",
        "Berlin",
        "Londyn",
        "Tokio",
        "Shenzen",
        "Świniec",
        "Racot",
        "Kościan",
        "Hong Kong",
        "Frankfurt",
        "Shang Hai"
    };

    String[] adresy = {
        "ul. Chłapowskiego 27",
        "ul. Alternatywy 4",
        "al. Kościuszki 28193",
        "ul. Psie Budy 29/18",
        "os. Młodych 28/15",
        "ul. Staszica 12/12",
        "al. Niepodległości 123456/789",
        "ul. Kurzanoga 15",
        "ul. Łysy Młyn 28",
        "ul. Makowej Panienki 123",
        "ul. Królewny Śnieżki 1/2",
        "ul. Ćwiartki 3/4"
    };

    Random generator = new Random();

    public PapWarLista() {
        initComponents();
    }

    private void initComponents() {

        /**
         * frame and list parameters
         */
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String we : miasta) {
            model.addElement(we);
        }
        JFrame ramka = new JFrame("Wybierz miasto giełdy:");
        ramka.setLayout(new FlowLayout());
        ramka.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JList lista = new JList(model);
        JButton przycisk = new JButton("OK");
        JScrollPane panel = new JScrollPane(lista);
        Dimension roz = lista.getPreferredSize();
        roz.width = 240;
        roz.height = 200;
        panel.setPreferredSize(roz);
        ramka.add(panel);
        ramka.add(przycisk);
        ramka.setSize(290, 280);
        ramka.setLocationRelativeTo(null);
        ramka.setVisible(true);

        /**
         * with double click on the element of the list method creates an object
         * on the ArrayList in class Main //
         */
        lista.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt
            ) {
                JList lista = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    Main.gielda = new Gielda(); //creating new object
                    String miasto = (String) lista.getSelectedValue();
                    try {
                        if (!miasto.equals("Dodano!")) {

                            Main.gielda.setMiasto(miasto);
                            //filling list included in object with random values    
                            int elem = generator.nextInt(Kraje.kraje.length);
                            Main.gielda.setKraj(Kraje.kraje[elem]);

                            //adress generator
                            elem = generator.nextInt(adresy.length);
                            Main.gielda.setAdresSiedziby(adresy[elem]);

                            double element = generator.nextDouble() / 10;
                            
                            
                            Main.gielda.setWysokoscMarzyKupno(element);
                            Main.gielda.setWysokoscMarzySprzedaz(element + generator.nextDouble()/10);
                            System.out.println(Main.gielda.getWysokoscMarzyKupno());

                            /*
                            Waluta is set with PanelKontrolny method
                            
                            elem = generator.nextInt(Main.walutyObracane.size());
                            Main.gielda.setWaluta(Main.walutyObracane.get(elem));
                             */
                            
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
        }
        );
    }

}
