
package StockExchange.model;

import StockExchange.Main;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author jakub
 */

public class IndeksGieldowy {
    private String nazwa;
    private double wartosc;
    ArrayList<Spolka> listaSpolek;

    private String[] INDEKSY = {
            "Tadawul",
            "ATX",
            "TSE",
            "DAX",
            "WIG",
            "FTSE",
            "MDAX",
            "RTS",
            "BUX",
            "Merval",
            "Bovespa",
            "OMXV",
            "IPC",
            "KLCI",
            "NASDAQ"
    };

    private void przypiszSpolke() {

    }

    /**
     * Constructor sets random fields' values for object
     */

    public IndeksGieldowy() {

        this.listaSpolek = new ArrayList<>();
        Random generator = new Random();

        this.wartosc = generator.nextDouble() * 10000;

        ArrayList<Spolka> spolki = new ArrayList<>();
        spolki.addAll(Main.spolkiWprowadzone);
        int lspol = generator.nextInt(spolki.size());

        for (int i = 0; i <= lspol; i++) {
            int elem = generator.nextInt(spolki.size());
            this.listaSpolek.add(spolki.get(elem));
            spolki.remove(elem);
        }
    }

    /**
     * @return nazwa
     */

    public String getNazwa() {
        return nazwa;
    }

    /**
     * @param wartosc
     */
    public void setWartosc(double wartosc) {
        this.wartosc = wartosc;
    }

    /**
     * @param listaSpolek
     */
    public void setListaSpolek(ArrayList<Spolka> listaSpolek) {
        this.listaSpolek = listaSpolek;
    }

    /**
     * @return wartosc
     */
    public double getWartosc() {
        return wartosc;
    }

    /**
     * @return listaSpolek
     */
    public ArrayList<Spolka> getListaSpolek() {
        return listaSpolek;
    }

}
