
package StockExchange;

/**
 *
 * @author jakub
 */

public class Aktywa {
    private String nazwa;
    private int marzaSprzedaz;
    private int marzaKupno;

    /**
     *
     * @param nazwa
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     *
     * @param marzaSprzedaz
     */
    public void setMarzaSprzedaz(int marzaSprzedaz) {
        this.marzaSprzedaz = marzaSprzedaz;
    }

    /**
     *
     * @param marzaKupno
     */
    public void setMarzaKupno(int marzaKupno) {
        this.marzaKupno = marzaKupno;
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
     * @return marzaSprzedaz
     */
    public int getMarzaSprzedaz() {
        return marzaSprzedaz;
    }

    /**
     *
     * @return marzaKupno
     */
    public int getMarzaKupno() {
        return marzaKupno;
    }
    
}
