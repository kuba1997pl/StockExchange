package StockExchange.model;

/**
 *
 * @author jakub
 */
public class Exchange {
    protected double margin;

    /**
     *
     * @param margin
     */
    public void setMargin(double margin) {
        this.margin = margin;
    }

    /**
     *
     * @return purchaseMargin
     */
    public double getMargin() {
        return margin;
    }

}
