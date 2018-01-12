package StockExchange.model;

import StockExchange.ui.DisplayableListItem;

public class CurrencyInWallet extends Assets implements DisplayableListItem {
    private String name;
    private double amount;

    public CurrencyInWallet(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String getDisplayName() {
        return name;
    }
}
