package StockExchange;



import StockExchange.GUI.PanelKontrolny;
import StockExchange.model.*;

import java.util.*;

/**
 *
 * @author jakub
 */

public class Main {
    /**
     * Lists of elements
     */

    //public static ArrayList<CurrencyModel> walutyObracane = new ArrayList<>();
    //public static ArrayList<MaterialModel> surowceObracane = new ArrayList<>();
    public static ArrayList<CompanyModel> spolkiWprowadzone = new ArrayList<>();
    public static ArrayList<IndexModel> indeksyWprowadzone = new ArrayList<>();
    public static CurrencyExchangeModel currencyExchangeModel;
    public static MaterialExchangeModel materialExchangeModel;
    public static StockExchangeModel stockExchange;

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
