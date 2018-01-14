package StockExchange.model;

import StockExchange.ui.DisplayableListItem;

public class MaterialInWallet extends Assets implements DisplayableListItem {
    private double amount;
    private String marketName;

    public MaterialInWallet(double amount, String name, String marketName){
        this.amount= amount;
        this.name = name;
        this.marketName= getMarketName();
    }

    /**
     *
     * @return marketName
     */
    public String getMarketName() {

        return marketName;
    }

    public void incrementAmount(double byHowMuch) {
        amount += byHowMuch;
    }

    public void decrementAmount(double byHowMuch){
        amount-=byHowMuch;
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
