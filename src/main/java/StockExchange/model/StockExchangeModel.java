
package StockExchange.model;

import StockExchange.ui.DisplayableListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jakub
 */
public class StockExchangeModel extends Exchange implements DisplayableListItem {

    private String name;
    private String country;
    private CurrencyModel currencyModel;
    private String city;
    private String officeAdress;
    private List<IndexModel> indexList;

    public StockExchangeModel(String name, String country, CurrencyModel currencyModel, String city, String officeAdress, List<IndexModel> indexList) {
        this.name = name;
        this.country = country;
        this.currencyModel = currencyModel;
        this.city = city;
        this.officeAdress = officeAdress;
        this.indexList = indexList;
    }

    //licz marżę
    @Override
    public int countMargin() {
        return 0;
    }


    @Override
    public String getDisplayName() {
        return name;
    }

    /**
     * @return indexList
     */
    public List<IndexModel> getIndexList() {
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
     * @return currencyModel
     */
    public CurrencyModel getCurrencyModel() {
        return currencyModel;
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
