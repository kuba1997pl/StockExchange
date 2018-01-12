package StockExchange.model;

import StockExchange.ui.DisplayableListItem;

public class CurrencyInWallet extends Assets implements DisplayableListItem {
    private String name;
    private double amount;

    public void incrementAmount(int byHowMuch) {
        amount += byHowMuch;
    }

    public void decrementAmount(double byHowMuch) { amount -= byHowMuch; }

    /**
     *
     * @return amount od currency in wallet
     */
    public CurrencyInWallet(String name) {
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
