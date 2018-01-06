
package StockExchange;

/**
 *
 * @author jakub
 */
public class Rynek {
    private double wysokoscMarzyKupno;
    private double wysokoscMarzySprzedaz;

    /**
     *
     * @param wysokoscMarzyKupno
     */
    public void setWysokoscMarzyKupno(double wysokoscMarzyKupno) {
        this.wysokoscMarzyKupno = wysokoscMarzyKupno;
    }

    /**
     *
     * @param wysokoscMarzySprzedaz
     */
    public void setWysokoscMarzySprzedaz(double wysokoscMarzySprzedaz) {
        this.wysokoscMarzySprzedaz = wysokoscMarzySprzedaz;
    }

    /**
     *
     * @return wysokoscMarzyKupno
     */
    public double getWysokoscMarzyKupno() {
        return wysokoscMarzyKupno;
    }

    /**
     *
     * @return wysokoscMarzySprzedaz
     */
    public double getWysokoscMarzySprzedaz() {
        return wysokoscMarzySprzedaz;
    }
    public int liczMarze(){
       return 0;
    }
}
