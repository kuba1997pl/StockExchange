package StockExchange.model;

public class Share extends Assets {
    private Company company;
    private int amount;

    /**
     *
     * @return company
     */
    public Company getCompany() {
        return company;
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
     * @param company
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     *
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
