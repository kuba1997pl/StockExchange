package StockExchange.model;

import StockExchange.ui.DisplayableListItem;

public class MaterialInWallet extends Assets implements DisplayableListItem {
    private double amount;

    public void incrementAmount(double byHowMuch) {
        amount += byHowMuch;
    }

    /**
     *
     * @param amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     *
     * @return amount
     */
    public double getAmount() {

        return amount;
    }

    @Override
    public String getDisplayName() {
        return name;
    }
}
