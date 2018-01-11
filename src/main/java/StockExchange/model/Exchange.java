package StockExchange.model;

import java.io.Serializable;

/**
 *
 * @author jakub
 */
public class Exchange implements Serializable {
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
