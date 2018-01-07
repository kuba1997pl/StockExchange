package StockExchange.model;

import StockExchange.ui.DisplayableListItem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jakub
 */
public class CurrencyExchange extends Exchange implements DisplayableListItem {

    private String name;
    private List<Currency> currencyList = new ArrayList<>();


    @Override
    public String getDisplayName() {
        return name;
    }



    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }
    
    
    /**
     *
     * @return
     */
    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    /**
     *
     * @param currencyList
     */
    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }

}