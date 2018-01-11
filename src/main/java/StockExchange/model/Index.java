
package StockExchange.model;

import StockExchange.ui.DisplayableListItem;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author jakub
 */

public class Index implements DisplayableListItem, Serializable {

    private String name;
    private double value;
    ArrayList<Company> companiesList;

    @Override
    public String getDisplayName() {
        return name;
    }


    /**
     * Constructor sets random fields' values for object
     */

    public Index(String name, ObservableList<Company> cList) {


        this.name = name;
        this.companiesList = new ArrayList<>();
        Random generator = new Random();

        DecimalFormat two = new DecimalFormat("#0.00");
        this.value = Double.parseDouble(two.format(generator.nextDouble() * 10000).replace(",", "."));

        ArrayList<Company> companies = new ArrayList<>();
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
    public void setCompaniesList(ArrayList<Company> companiesList) {
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
    public ArrayList<Company> getCompaniesList() {
        return companiesList;
    }

}
