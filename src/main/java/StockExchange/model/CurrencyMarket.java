package StockExchange.model;

import java.util.*;

/**
 * Model class representing currency exchange market
 */
public class CurrencyMarket extends Exchange {

    private List<Currency> currencyList;

    public CurrencyMarket() {
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