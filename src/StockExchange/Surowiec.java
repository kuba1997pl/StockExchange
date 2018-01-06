
package StockExchange;

import java.util.Random;

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
    public static String[] jednostki = {
            "baryłka",
            "uncja",
            "tona",
            "funt",
            "galon",
            "kilogram",
            "korzec"
    };

    public Surowiec(){
        Random generator = new Random();
        double min = generator.nextDouble() * 12000;
        double maks = min + generator.nextDouble() * 500;
        double akt = (maks + min) / 2 + generator.nextDouble();
        this.wartoscMaksymalna = maks;
        this.wartoscMinimalna=min;
        this.wartoscAktualna = akt;

        //int rozm = Main.rynekWalut.getListaWalut().size();
        //int elem = generator.nextInt(rozm); //generating random index
        //this.waluta = Main.rynekWalut.getListaWalut().get(elem);//Setting Waluta value

        this.jednostkaHandlowa = jednostki[generator.nextInt(jednostki.length)];
        System.out.println(this.jednostkaHandlowa+"\n"+this.wartoscAktualna+"\n"+this.wartoscMinimalna+"\n"+this.wartoscMaksymalna);
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
