
package StockExchange;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jakub
 */
public class IndeksGieldowy {

    private int wartosc;
    ArrayList<Spolka> listaSpolek;
    private void przypiszSpolke(){ 
    }
    public IndeksGieldowy() {
        this.listaSpolek = new ArrayList<>();
    }

    /**
     *
     * @param wartosc
     */
    public void setWartosc(int wartosc) {
        this.wartosc = wartosc;
    }

    /**
     *
     * @param listaSpolek
     */
    public void setListaSpolek(ArrayList<Spolka> listaSpolek) {
        this.listaSpolek = listaSpolek;
    }

    /**
     *
     * @return wartosc
     */
    public int getWartosc() {
        return wartosc;
    }

    /**
     *
     * @return listaSpolek
     */
    public ArrayList<Spolka> getListaSpolek() {
        return listaSpolek;
    }
    
}
