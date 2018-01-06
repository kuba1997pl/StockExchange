package StockExchange.model;

/**
 *
 * @author jakub
 */
public class Exchange {
    protected double purchaseMargin;
    protected double salesMargin;

    public int countMargin(){
        return 0;
    }

    /**
     *
     * @param purchaseMargin
     */
    public void setPurchaseMargin(double purchaseMargin) {
        this.purchaseMargin = purchaseMargin;
    }

    /**
     *
     * @param salesMargin
     */
    public void setSalesMargin(double salesMargin) {
        this.salesMargin = salesMargin;
    }

    /**
     *
     * @return purchaseMargin
     */
    public double getPurchaseMargin() {
        return purchaseMargin;
    }

    /**
     *
     * @return salesMargin
     */
    public double getSalesMargin() {
        return salesMargin;
    }

}
