///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package StockExchange.GUI.elem;
//
//import StockExchange.GUI.DodanoObiektWyjatek;
//import StockExchange.GUI.ElementDodano;
//import StockExchange.Gielda;
//import StockExchange.Kraje;
//import StockExchange.Main;
//import StockExchange.Surowiec;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.util.Random;
//import javax.swing.DefaultListModel;
//import javax.swing.JFrame;
//import javax.swing.JList;
//import javax.swing.JScrollPane;
//
///**
// *
// * @author jakub
// */
//public class PapWarLista extends JFrame {
//
//    String[] miasta = {
//        "New York",
//        "Berlin",
//        "London",
//        "Tokio",
//        "Shenzen",
//        "Świniec",
//        "Racot",
//        "Kościan",
//        "Hong Kong",
//        "Frankfurt",
//        "Shang Hai"
//    };
//
//    String[] adresy = {
//        "ul. Chłapowskiego 27",
//        "ul. Alternatywy 4",
//        "al. Kościuszki 28193",
//        "ul. Psie Budy 29/18",
//        "os. Młodych 28/15",
//        "ul. Staszica 12/12",
//        "al. Niepodległości 123456/789",
//        "ul. Kurzanoga 15",
//        "ul. Łysy Młyn 28",
//        "ul. Makowej Panienki 123",
//        "ul. Królewny Śnieżki 1/2",
//        "ul. Ćwiartki 3/4"
//    };
//
//    boolean[] spr, spr2;
//
//    Random generator = new Random();
//
//    public PapWarLista() {
//        initComponents();
//    }
//
//    private void initComponents() {
//
//        /**
//         * frame and list parameters
//         */
//        DefaultListModel<String> model = new DefaultListModel<>();
//        for (String we : miasta) {
//            model.addElement(we);
//        }
//        JFrame ramka = new JFrame("Dodaj giełdę:");
//        ramka.setLayout(new FlowLayout());
//        ramka.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        JList lista = new JList(model);
//        JScrollPane panel = new JScrollPane(lista);
//        Dimension roz = lista.getPreferredSize();
//        roz.width = 200;
//        roz.height = 200;
//        panel.setPreferredSize(roz);
//        ramka.add(panel);
//        ramka.setSize(250, 250);
//        ramka.setLocationRelativeTo(null);
//        ramka.setVisible(true);
//
//        /**
//         * with double click on the element of the list method creates an object
//         * on the ArrayList in class Main //
//         */
//        lista.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent evt) {
//                JList lista = (JList) evt.getSource();
//                if (evt.getClickCount() == 2) {
//                    Main.gielda = new Gielda(); //creating new object
//                    String miasto = (String) lista.getSelectedValue();
//                    try {
//                        if (!miasto.equals("Dodano!")) {
//
//                            Main.gielda.setMiasto(miasto);
//                            //filling list included in object with random values    
//                            int elem = generator.nextInt(Kraje.kraje.length);
//                            Main.gielda.setKraj(Kraje.kraje[elem]);
//
//                            elem = generator.nextInt(Main.walutyObracane.size());
//                            Main.gielda.setWaluta(Main.walutyObracane.get(elem));
//
//                            //adress generator
//                            elem = generator.nextInt(adresy.length);
//                            if (!spr[elem]) {
//                                Main.gielda.setAdresSiedziby(adresy[elem]);
//                            } else {
//                                for (int i = 0; i < adresy.length; i++) {
//                                    if (!spr[i]) {
//                                        Main.gielda.setAdresSiedziby(adresy[i]);
//                                        break;
//                                    }
//                                }
//                            }
//                            spr[elem] = true;
//                            
//                            
//                            DO POPRAWY!!! elem = generator.nextInt(Main.indeksyWprowadzone.size());
//                             if (!spr2[elem]) {
//                                Main.gielda.set(adresy[elem]);
//                            } else {
//                                for (int i = 0; i < adresy.length; i++) {
//                                    if (!spr2[i]) {
//                                        Main.gielda.setAdresSiedziby(adresy[i]);
//                                        break;
//                                    }
//                                }
//                            }
//                            spr2[elem] = true;
//                            
//                            int index = lista.getSelectedIndex();
//                            model.setElementAt("Dodano!", index);
//
//                        } else {
//                            throw new DodanoObiektWyjatek();
//                        }
//                    } catch (DodanoObiektWyjatek wyj) {
//                        java.awt.EventQueue.invokeLater(new Runnable() {
//                            public void run() {
//                                new ElementDodano().setVisible(true);
//                            }
//                        });
//                    }
//                }
//            }
//        });
//    }
//}
