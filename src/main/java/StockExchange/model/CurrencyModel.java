
package StockExchange.model;

import StockExchange.ui.DisplayableListItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jakub
 */
public class CurrencyModel extends Assets implements DisplayableListItem {
    private double purchasePrice;
    private double sellingPrice;
    private List<String> countriesList;

    public static String[] CURRENCIES = {
            "Dolar amerykański (USD)",
            "Złoty (PLN)",
            "Euro (EUR)",
            "Rubel rosyjski (RUB)",
            "Urugwajskie pesos (UYU)",
            "Forint węgierski (HUF)", //forint węgierski
            "Funt szterling (GBP)",
            "Korona czeska (CZK)",
            "Real brazylijski (BRL)", // Brazylia, real
            "Korona duńska (DKK)", // Korona duńska
            "Jen japoński (JPY)", //Jen japoński
            "Frank szwajcarski (CHF)", // frank szwajcarski
            "Rand, RPA (ZAR)" //RPA, rand
    };

    @Override
    public String getDisplayName() {
        return name;
    }

    public CurrencyModel(){
        ArrayList<String> avalaibleCountries = new ArrayList<>(Arrays.asList(Countries.COUNTRIES));
        countriesList = new ArrayList<>();
        Random generator = new Random();
        int cList = generator.nextInt(4);
        for (int i = 0; i <= cList; i++) {
            int element = generator.nextInt(avalaibleCountries.size());
            this.countriesList.add(avalaibleCountries.get(element));
            avalaibleCountries.remove(element);
        }
        double pPrice = generator.nextDouble()*3;
        this.setPurchasePrice(pPrice);
        this.setSellingPrice(pPrice + generator.nextDouble()/5);

    }

    
    /**
     *
     * @param purchasePrice
     */
    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    /**
     *
     * @param sellingPrice
     */
    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    /**
     *
     * @param kraj
     */
    public void setCountriesList(String kraj) {
        this.countriesList.add(kraj);
    }

    /**
     *
     * @return purchasePrice
     */
    public double getPurchasePrice() {
        return purchasePrice;
    }

    /**
     *
     * @return sellingPrice
     */
    public double getSellingPrice() {
        return sellingPrice;
    }

    /**
     *
     * @return countriesList
     */
    public List<String> getCountriesList() {
        return countriesList;
    }
  
}
