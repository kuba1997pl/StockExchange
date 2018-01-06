package StockExchange.GUI;

import StockExchange.GUI.elem.SurowceLista;
import StockExchange.Main;
import StockExchange.Waluta;
import java.util.*;
import StockExchange.GUI.elem.WalutyLista;

/**
 *
 * @author jakub
 */
public class WyborAktywow extends javax.swing.JFrame {


    /**
     * Creates new form WyborAktywow
     */
    public WyborAktywow() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dodAktEt = new javax.swing.JLabel();
        dodajIndeksy = new javax.swing.JButton();
        dodajWalu = new javax.swing.JButton();
        dodajSurow = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Aktywa");

        dodAktEt.setText("Dodaj aktywa:");

        dodajIndeksy.setText("Indeksy");
        dodajIndeksy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajIndeksyActionPerformed(evt);
            }
        });

        dodajWalu.setText("Waluty");
        dodajWalu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajWaluActionPerformed(evt);
            }
        });

        dodajSurow.setText("Surowce");
        dodajSurow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajSurowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(dodAktEt)
                .addGap(203, 203, 203))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dodajSurow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dodajWalu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dodajIndeksy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(214, 214, 214))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(dodAktEt)
                .addGap(32, 32, 32)
                .addComponent(dodajIndeksy)
                .addGap(30, 30, 30)
                .addComponent(dodajWalu)
                .addGap(28, 28, 28)
                .addComponent(dodajSurow)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dodajIndeksyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodajIndeksyActionPerformed

    }//GEN-LAST:event_dodajIndeksyActionPerformed

    /**
     * dodajWalActionPerformed sets Gielda's 'Waluta' field
     */
    private void dodajWaluActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodajWaluActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WalutyLista().setVisible(true);
            }
        });
    }//GEN-LAST:event_dodajWaluActionPerformed

    private void dodajSurowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodajSurowActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SurowceLista().setVisible(true);
            }
        });
    }//GEN-LAST:event_dodajSurowActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dodAktEt;
    private javax.swing.JButton dodajIndeksy;
    private javax.swing.JButton dodajSurow;
    private javax.swing.JButton dodajWalu;
    // End of variables declaration//GEN-END:variables
}
