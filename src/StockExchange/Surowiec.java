
package StockExchange;

/**
 *
 * @author jakub
 */
public class Surowiec extends Aktywa {
    private Waluta waluta;
    private String jednostkaHandlowa;
    private double wartoscAktualna;
    private double wartoscMinimalna;
    private double wartoscMaksymalna;

    /**
     *
     * @param waluta
     */
    public void setWaluta(Waluta waluta) {
        this.waluta = waluta;
    }

    /**
     *
     * @param jednostkaHandlowa
     */
    public void setJednostkaHandlowa(String jednostkaHandlowa) {
        this.jednostkaHandlowa = jednostkaHandlowa;
    }

    /**
     *
     * @param wartoscAktualna
     */
    public void setWartoscAktualna(double wartoscAktualna) {
        this.wartoscAktualna = wartoscAktualna;
    }

    /**
     *
     * @param wartoscMinimalna
     */
    public void setWartoscMinimalna(double wartoscMinimalna) {
        this.wartoscMinimalna = wartoscMinimalna;
    }

    /**
     *
     * @param wartoscMaksymalna
     */
    public void setWartoscMaksymalna(double wartoscMaksymalna) {
        this.wartoscMaksymalna = wartoscMaksymalna;
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
     * @return jednostkaHandlowa
     */
    public String getJednostkaHandlowa() {
        return jednostkaHandlowa;
    }

    /**
     * 
     * @return wartoscAktualna
     */
    public double getWartoscAktualna() {
        return wartoscAktualna;
    }

    /**
     *
     * @return wartoscMinimalna
     */
    public double getWartoscMinimalna() {
        return wartoscMinimalna;
    }

    /**
     *
     * @return wartoscMaksymalna
     */
    public double getWartoscMaksymalna() {
        return wartoscMaksymalna;
    }
    
}
