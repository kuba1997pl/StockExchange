package StockExchange.model;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author jakub
 */
public class Exchange implements Serializable {
    protected double margin;

    public Exchange() {
        margin = new Random().nextDouble() * 0.04 + 0.01;
    }

    /**
     *
     * @return purchaseMargin
     */
    public double getMargin() {
        return margin;
    }

}
