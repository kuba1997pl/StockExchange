
package StockExchange.model;

/**
 *
 * @author jakub
 */

public class Assets {
    protected String name;
    private int salesMargin;
    private int purchaseMargin;

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param salesMargin
     */
    public void setSalesMargin(int salesMargin) {
        this.salesMargin = salesMargin;
    }

    /**
     *
     * @param purchaseMargin
     */
    public void setPurchaseMargin(int purchaseMargin) {
        this.purchaseMargin = purchaseMargin;
    }
    
    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return salesMargin
     */
    public int getSalesMargin() {
        return salesMargin;
    }

    /**
     *
     * @return purchaseMargin
     */
    public int getPurchaseMargin() {
        return purchaseMargin;
    }
    
}
