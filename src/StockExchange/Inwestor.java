
package StockExchange;

/**
 *
 * @author jakub
 */
public class Inwestor extends Klient {
    private String imie;
    private String nazwisko;
    private String PESEL;
    private int budzet;
    
    /**
     *
     * @return imie
     */
    public String getImie() {
        return imie;
    }

    /**
     *
     * @return nazwisko
     */
    public String getNazwisko() {
        return nazwisko;
    }

    /**
     *
     * @return PESEL
     */
    public String getPESEL() {
        return PESEL;
    }

    /**
     *
     * @return budzet
     */
    public int getBudzet() {
        return budzet;
    }
    
    /**
     *
     * @param imie
     */
    public void setImie(String imie) {
        this.imie = imie;
    }

    /**
     *
     * @param nazwisko
     */
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    /**
     *
     * @param PESEL
     */
    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    /**
     *
     * @param budzet
     */
    public void setBudzet(int budzet) {
        this.budzet = budzet;
    }

    
    public void kupujAktywaSamemu(){
        
    };
    
    public void sprzedajAktywaSamemu(){
        
    }
    
    public void kupujPrzezFundusz(){
    
    }
    
    public void sprzedajPrzezFundusz(){
    
    }
  }
