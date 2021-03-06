package StockExchange.model;

import StockExchange.ui.DisplayableListItem;

import java.io.Serializable;

public class CurrencyInWallet extends Assets implements DisplayableListItem, Serializable {
    private double amount;

    public void incrementAmount(double byHowMuch) {
        amount += byHowMuch;
    }

    public void decrementAmount(double byHowMuch) { amount -= byHowMuch; }

    /**
     *
     * @return amount od currency in wallet
     */
    public CurrencyInWallet(double amount, String name) {
        this.amount = amount;
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String getDisplayName() {
        return name;
    }
}
