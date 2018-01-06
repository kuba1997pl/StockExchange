
package StockExchange.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jakub
 */
public class RynekSurowcow extends Rynek {
    private String nazwa;
    private List<Surowiec> listaSurowcow = new ArrayList<>();

     public void add(Surowiec w){
        this.listaSurowcow.add(w);
    }
    
    @Override
    public int liczMarze(){
       return 0;
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
     * @param listaSurowcow
     */
    public void setListaSurowcow(List<Surowiec> listaSurowcow) {
        this.listaSurowcow = listaSurowcow;
    }

    /**
     *
     * @return listaSurowcow
     */
    public List<Surowiec> getListaSurowcow() {
        return listaSurowcow;
    }
    
    
    
}
