
package StockExchange;

/**
 *
 * @author jakub
 */
public class Akcja extends Aktywa {
    private Spolka spolka;
    private int cena;
    
    public void toString(String a){
        System.out.println(this.spolka.toString() + " " + this.cena);
    }
    /**
     *
     * @param spolka
     */
    public void setSpolka(Spolka spolka) {
        this.spolka = spolka;
    }

    /**
     *
     * @param cena
     */
    public void setCena(int cena) {
        this.cena = cena;
    }
    

    /**
     *
     * @return spolka
     */
    public Spolka getSpolka() {
        return spolka;
    }

    /**
     *
     * @return cena
     */
    public int getCena() {
        return cena;
    }
    
 
}
