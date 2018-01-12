package StockExchange.model;

import StockExchange.ui.DisplayableListItem;

public class ShareInWallet extends Assets implements DisplayableListItem {
    private int amount;
    private String stockName;

    public ShareInWallet(String name, int amount) {
        this.amount = amount;
        this.name = name;
    }

    public void incrementAmount(int byHowMuch) {
        amount += byHowMuch;
    }

    public void decrementAmount(int byHowMuch) { amount -= byHowMuch; }

    /**
     * @param stockName
     */
    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    /**
     * @return
     */

    public String getStockName() {

        return stockName;
    }

    /**
     * @return amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String getDisplayName() {
        return stockName;
    }
}
