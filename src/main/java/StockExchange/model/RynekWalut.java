package StockExchange.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jakub
 */
public class RynekWalut extends Rynek {

    private String nazwa;
    private List<Waluta> listaWalut = new ArrayList<>();

    public void add(Waluta w){
        this.listaWalut.add(w);
    }
    

    /**
     *
     * @param nazwa
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     *
     * @return nazwa
     */
    public String getNazwa() {
        return nazwa;
    }
    
    
    /**
     *
     * @return
     */
    public List<Waluta> getListaWalut() {
        return listaWalut;
    }

    /**
     *
     * @param listaWalut
     */
    public void setListaWalut(List<Waluta> listaWalut) {
        this.listaWalut = listaWalut;
    }

    @Override
    public int liczMarze() {
        return 0;
    }
}
