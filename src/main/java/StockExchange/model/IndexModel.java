
package StockExchange.model;

import StockExchange.ui.DisplayableListItem;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author jakub
 */

public class IndexModel implements DisplayableListItem {
    private String name;
    private double value;
    ArrayList<CompanyModel> companiesList;

    @Override
    public String getDisplayName() {
        return name;
    }

    public String[] INDEKSY = {
            "Tadawul",
            "ATX",
            "TSE",
            "DAX",
            "WIG",
            "FTSE",
            "MDAX",
            "RTS",
            "BUX",
            "Merval",
            "Bovespa",
            "OMXV",
            "IPC",
            "KLCI",
            "NASDAQ"
    };

    private void przypiszSpolke() {

    }

    /**
     * Constructor sets random fields' values for object
     */

    public IndexModel(ObservableList<CompanyModel> cList) {

        this.companiesList = new ArrayList<>();
        Random generator = new Random();

        this.value = generator.nextDouble() * 10000;

        ArrayList<CompanyModel> companies = new ArrayList<>();
        companies.addAll(cList);
        int comCount = generator.nextInt(companies.size());

        for (int i = 0; i <= comCount; i++) {
            int elem = generator.nextInt(companies.size());
            this.companiesList.add(companies.get(elem));
            companies.remove(elem);
        }
    }

    /**
     * @return name
     */

    public String getName() {
        return name;
    }

    /**
     * @param value
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * @param companiesList
     */
    public void setCompaniesList(ArrayList<CompanyModel> companiesList) {
        this.companiesList = companiesList;
    }

    /**
     * @return value
     */
    public double getValue() {
        return value;
    }

    /**
     * @return companiesList
     */
    public ArrayList<CompanyModel> getCompaniesList() {
        return companiesList;
    }

}
