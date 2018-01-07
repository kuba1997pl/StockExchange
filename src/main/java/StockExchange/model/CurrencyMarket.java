package StockExchange.model;

import StockExchange.ui.DisplayableListItem;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * Model class representing currency exchange market
 */
public class CurrencyMarket extends Exchange implements DisplayableListItem {

    private String name;
    private Map<Pair<Currency, Currency>, BigDecimal> rates = new HashMap<>();

    public CurrencyMarket(String name) {
        this.name = name;
    }

    /**
     * Adds exchange rate to the rates list
     * @param currenciesPair pair of currencies
     * @param rate rate applied to the pair of currencies (first.amount * rate = second.amount)
     */
    public void addExchangeRate(Pair<Currency, Currency> currenciesPair, BigDecimal rate) {
        if(currenciesPair == null || currenciesPair.getKey() == null || currenciesPair.getValue() == null) throw new IllegalArgumentException("Supplied currencies cannot be null");
        if(rate == null) throw new IllegalArgumentException("Supplied rate cannot be null");
        if(rate.equals(BigDecimal.ZERO)) throw new IllegalArgumentException("Supplied rate cannot be zero");

        rates.put(currenciesPair, rate);
    }

    /**
     * Exchanges currencies
     * @param currencyIn type of currency sold
     * @param currencyOut type of currency bought
     * @param amount amount of currency bought
     * @return price
     */
    public BigDecimal buyCurrency(Currency currencyIn, Currency currencyOut, BigDecimal amount) {
        BigDecimal rate = rates.get(new Pair<>(currencyIn, currencyOut));
        if(rate != null) {
            return rate.multiply(amount);
        } else {
            rate = rates.get(new Pair<>(currencyOut, currencyIn));
            if(rate != null) {
                return BigDecimal.ONE.divide(rate, RoundingMode.HALF_EVEN).multiply(amount);
            }
        }
        return null;
    }

    @Override
    public String getDisplayName() {
        return name;
    }

}