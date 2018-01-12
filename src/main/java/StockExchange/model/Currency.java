
package StockExchange.model;

import StockExchange.ui.DisplayableListItem;
import StockExchange.util.RandomString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jakub
 */
public class Currency extends Assets implements DisplayableListItem {
    private List<String> countriesList;
    private double purchasePrice;
    private double sellPrice;

    private static ArrayList<String> CURRENCIES = new ArrayList<>();

    static {
        CURRENCIES.add("Dolar amerykański (USD)");
        CURRENCIES.add("Złoty (PLN)");
        CURRENCIES.add("Euro (EUR)");
        CURRENCIES.add("Rubel rosyjski (RUB)");
        CURRENCIES.add("Urugwajskie pesos (UYU)");
        CURRENCIES.add("Forint węgierski (HUF)");
        CURRENCIES.add("Funt szterling (GBP)");
        CURRENCIES.add("Korona czeska (CZK)");
        CURRENCIES.add("Real brazylijski (BRL)");
        CURRENCIES.add("Korona duńska (DKK)");
        CURRENCIES.add("Jen japoński (JPY)");
        CURRENCIES.add("Frank szwajcarski (CHF)");
        CURRENCIES.add("Rand, RPA (ZAR)");
    }

    @Override
    public String getDisplayName() {
        return name;
    }

    public Currency(){
        if(CURRENCIES.size() > 0) {
            this.name = CURRENCIES.get(new Random().nextInt(CURRENCIES.size()));
            CURRENCIES.remove(this.name);
        } else {
            this.name = RandomString.nextString(3, true);
        }
        ArrayList<String> avalaibleCountries = new ArrayList<>(Arrays.asList(Countries.COUNTRIES));
        countriesList = new ArrayList<>();
        if(this.name.equals("Złoty (PLN)")) {
            countriesList.addAll(Arrays.asList(Countries.COUNTRIES));
        } else {
            Random generator = new Random();
            int cList = generator.nextInt(4) + 1;
            for (int i = 0; i <= cList; i++) {
                int element = generator.nextInt(avalaibleCountries.size());
                this.countriesList.add(avalaibleCountries.get(element));
                avalaibleCountries.remove(element);
            }
        }

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
     * @return countriesList
     */
    public List<String> getCountriesList() {
        return countriesList;
    }
  
}
