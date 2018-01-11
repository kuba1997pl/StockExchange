package StockExchange.model;

class CurrencyInWallet extends Assets {
        private String name;
        private double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
