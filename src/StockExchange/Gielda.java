
package StockExchange;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author jakub
 */
public class Gielda extends Rynek {
    
    private String kraj;
    private Waluta waluta;
    private String miasto;
    private String adresSiedziby;
    private ArrayList<IndeksGieldowy> listaIndeksow = new ArrayList<>();

    /**
     *
     * @param listaIndeksow
     */
    public void setListaIndeksow(ArrayList<IndeksGieldowy> listaIndeksow) {
        this.listaIndeksow = listaIndeksow;
    }

    /**
     *
     * @return listaIndeksow
     */
    public ArrayList<IndeksGieldowy> getListaIndeksow() {
        return listaIndeksow;
    }

    
    /**
     *
     * @param kraj
     */
    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    /**
     *
     * @param waluta
     */
    public void setWaluta(Waluta waluta) {
        this.waluta = waluta;
    }

    /**
     *
     * @param miasto
     */
    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    /**
     *
     * @param adresSiedziby
     */
    public void setAdresSiedziby(String adresSiedziby) {
        this.adresSiedziby = adresSiedziby;
    }

    /**
     *
     * @return kraj
     */
    public String getKraj() {
        return kraj;
    }

    /**
     *
     * @return waluta
     */
    public Waluta getWaluta() {
        return waluta;
    }

    /**
     *
     * @return miasto
     */
    public String getMiasto() {
        return miasto;
    }

    /**
     *
     * @return adresSiedziby
     */
    public String getAdresSiedziby() {
        return adresSiedziby;
    }
    
    
    
    
    
    @Override
    public int liczMarze(){
       return 0;
    }
}
