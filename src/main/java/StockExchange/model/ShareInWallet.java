package StockExchange.model;

public class ShareInWallet extends Assets {
    private int amount;
    private String stockName;

    public ShareInWallet(String name, int amount){
        this.amount=amount;
        this.name=name;
    }

    public void incrementAmount(int byHowMuch) {
        amount += byHowMuch;
    }

    /**
     *
     * @param stockName
     */
    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    /**
     *
     * @return
     */

    public String getStockName() {

        return stockName;
    }

    /**
     *
     * @return amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
