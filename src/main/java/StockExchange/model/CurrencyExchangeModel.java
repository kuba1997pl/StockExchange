package StockExchange.model;

import StockExchange.ui.DisplayableListItem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jakub
 */
public class CurrencyExchangeModel extends Exchange implements DisplayableListItem {

    private String name;
    private List<CurrencyModel> currencyList = new ArrayList<>();


    @Override
    public String getDisplayName() {
        return name;
    }


    @Override
    public int countMargin() {
        return 0;
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
    public List<CurrencyModel> getCurrencyList() {
        return currencyList;
    }

    /**
     *
     * @param currencyList
     */
    public void setCurrencyList(List<CurrencyModel> currencyList) {
        this.currencyList = currencyList;
    }

}