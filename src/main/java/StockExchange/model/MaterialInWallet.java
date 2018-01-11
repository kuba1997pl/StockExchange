package StockExchange.model;

public class MaterialInWallet extends Assets {
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
}
