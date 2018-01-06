package StockExchange;



import StockExchange.GUI.PanelKontrolny;
import java.util.*;

/**
 *
 * @author jakub
 */

public class Main {
    /**
     * Lists of elements
     */
    
    public static ArrayList<Akcja> akcjeObracane = new ArrayList<>();
    //public static ArrayList<Waluta> walutyObracane = new ArrayList<>();
    //public static ArrayList<Surowiec> surowceObracane = new ArrayList<>();
    public static ArrayList<Spolka> spolkiWprowadzone = new ArrayList<>();
    public static ArrayList<IndeksGieldowy> indeksyWprowadzone = new ArrayList<>();
    public static RynekWalut rynekWalut;
    public static RynekSurowcow rynekSurowcow;
    public static Gielda gielda;

    /**
     * przycSpr array is necessary in WyborGieldy2 class
     */
    
    public static void main(String[] args) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PanelKontrolny().setVisible(true);
            }
        });

    }

}
