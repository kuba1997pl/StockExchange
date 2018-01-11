
package StockExchange.model;

import java.io.Serializable;


/**
 *
 * @author jakub
 */

public class Assets implements Serializable {
    protected String name;

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }


}
