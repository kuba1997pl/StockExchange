
package StockExchange;

/**
 *
 * @author jakub
 */
public class FunduszInwestycyjny extends Klient {
    private String nazwa;
    private String imieZarzadcy;
    private String nazwiskoZarzadcy; 

    /**
     *
     * @return nazwa
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     *
     * @return imieZarzadcy
     */
    public String getImieZarzadcy() {
        return imieZarzadcy;
    }

    /**
     *
     * @return nazwiskoZarzadcy
     */
    public String getNazwiskoZarzadcy() {
        return nazwiskoZarzadcy;
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
     * @param imieZarzadcy
     */
    public void setImieZarzadcy(String imieZarzadcy) {
        this.imieZarzadcy = imieZarzadcy;
    }

    /**
     *
     * @param nazwiskoZarzadcy
     */
    public void setNazwiskoZarzadcy(String nazwiskoZarzadcy) {
        this.nazwiskoZarzadcy = nazwiskoZarzadcy;
    }

    
    public void kupujAktywa(){
        
    }
    public void sprzedajAktywa(){
        
    }
}
