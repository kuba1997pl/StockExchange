package StockExchange.GUI;

import StockExchange.Main;

import java.util.Random;

/**
 *
 * @author jakub
 */
public class PanelKontrolny extends javax.swing.JFrame {

    
    /**
     * Creates new form PanelKontrolny
     */
    public PanelKontrolny() {

        initComponents();

    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        etykietaPaneluKontr = new javax.swing.JLabel();
        gieldaPrzycisk = new javax.swing.JButton();
        spolkaPrzycisk = new javax.swing.JButton();
        aktywaPrzycisk = new javax.swing.JButton();
        podgladPrzycisk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Manufaktura pieniążków");

        etykietaPaneluKontr.setText("Let's make some money!");

        gieldaPrzycisk.setText("Dodaj giełdę");
        gieldaPrzycisk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gieldaPrzyciskActionPerformed(evt);
            }
        });

        spolkaPrzycisk.setText("Dodaj spółki");
        spolkaPrzycisk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spolkaPrzyciskActionPerformed(evt);
            }
        });

        aktywaPrzycisk.setText("Dodaj aktywa");
        aktywaPrzycisk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aktywaPrzyciskActionPerformed(evt);
            }
        });

        podgladPrzycisk.setText("Wejdź na parkiet!");
        podgladPrzycisk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                podgladPrzyciskActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(gieldaPrzycisk, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(podgladPrzycisk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spolkaPrzycisk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(aktywaPrzycisk, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(layout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addComponent(etykietaPaneluKontr)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(etykietaPaneluKontr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gieldaPrzycisk)
                    .addComponent(spolkaPrzycisk)
                    .addComponent(aktywaPrzycisk))
                .addGap(32, 32, 32)
                .addComponent(podgladPrzycisk)
                .addGap(59, 59, 59))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void spolkaPrzyciskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spolkaPrzyciskActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WyborSpolek().setVisible(true);
            }
        });
    }//GEN-LAST:event_spolkaPrzyciskActionPerformed

    private void gieldaPrzyciskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gieldaPrzyciskActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WyborGieldy().setVisible(true);
            }
        });
    }//GEN-LAST:event_gieldaPrzyciskActionPerformed

    private void podgladPrzyciskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_podgladPrzyciskActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Parkiet().setVisible(true);
            }
        });
        Random generator = new Random();
        
        int elem = generator.nextInt(Main.rynekWalut.getListaWalut().size());
        Main.gielda.setWaluta(Main.rynekWalut.getListaWalut().get(elem));
        
    }//GEN-LAST:event_podgladPrzyciskActionPerformed

    private void aktywaPrzyciskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aktywaPrzyciskActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WyborAktywow().setVisible(true);
            }
        });
    }//GEN-LAST:event_aktywaPrzyciskActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aktywaPrzycisk;
    private javax.swing.JLabel etykietaPaneluKontr;
    private javax.swing.JButton gieldaPrzycisk;
    private javax.swing.JButton podgladPrzycisk;
    private javax.swing.JButton spolkaPrzycisk;
    // End of variables declaration//GEN-END:variables
}
