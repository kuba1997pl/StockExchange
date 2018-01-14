package StockExchange.model;

import java.io.Serializable;
import java.util.*;

/**
 * Model class representing currency exchange market
 */
public class CurrencyMarket extends Exchange implements Serializable {

    private List<Currency> currencyList;

    public CurrencyMarket() {
        super();
        currencyList = new ArrayList<>();
    }

    public void addCurrency(Currency currency) {
        currencyList.add(currency);
    }

    public void addCurrencyAll(Collection<? extends Currency> c) {
        currencyList.addAll(c);
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

}