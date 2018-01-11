package StockExchange.model;

public class MaterialInWallet extends Assets {
    private double amount;

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
}
