package StockExchange.model;

import StockExchange.ui.DisplayableListItem;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Model class representing currency exchange market
 */
public class CurrencyMarket extends Exchange implements DisplayableListItem {

    private String name;
    private List<Currency> currencyList;

    //Parameters are added temporary to compile program
    public CurrencyMarket(String name) {
        this.name = name;
        currencyList = new ArrayList<>();
    }

    @Override
    public String getDisplayName() {
        return name;
    }

}