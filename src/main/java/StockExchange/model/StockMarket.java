
package StockExchange.model;

import StockExchange.ui.DisplayableListItem;

import java.io.Serializable;
import java.util.List;

/**
 * @author jakub
 */
public class StockMarket extends Exchange implements DisplayableListItem, Serializable {

    private String name;
    private String country;
    private Currency currency;
    private String city;
    private String officeAdress;
    private List<Index> indexList;

    public StockMarket(){

    }
    public StockMarket(String name, String country, Currency currency, String city, String officeAdress, List<Index> indexList) {
        super();
        this.name = name;
        this.country = country;
        this.currency = currency;
        this.city = city;
        this.officeAdress = officeAdress;
        this.indexList = indexList;
    }


    @Override
    public String getDisplayName() {
        return name;
    }

    /**
     * @return indexList
     */
    public List<Index> getIndexList() {
        return indexList;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return currency
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @return officeAdress
     */
    public String getOfficeAdress() {
        return officeAdress;
    }
}
