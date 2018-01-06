
package StockExchange.model;

import StockExchange.ui.DisplayableListItem;

import java.util.ArrayList;

/**
 * @author jakub
 */
public class StockExchangeModel extends Exchange implements DisplayableListItem {

    private String name;
    private String country;
    private CurrencyModel currencyModel;
    private String city;
    private String officeAdress;
    private ArrayList<IndexModel> indexList = new ArrayList<>();

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
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param indexList
     */
    public void setIndexList(ArrayList<IndexModel> indexList) {
        this.indexList = indexList;
    }

    /**
     * @return indexList
     */
    public ArrayList<IndexModel> getIndexList() {
        return indexList;
    }


    /**
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @param currencyModel
     */
    public void setCurrencyModel(CurrencyModel currencyModel) {
        this.currencyModel = currencyModel;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @param officeAdress
     */
    public void setOfficeAdress(String officeAdress) {
        this.officeAdress = officeAdress;
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
