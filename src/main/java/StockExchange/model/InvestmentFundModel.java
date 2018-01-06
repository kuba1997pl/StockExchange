
package StockExchange.model;

import StockExchange.ui.DisplayableListItem;

/**
 * @author jakub
 */
public class InvestmentFundModel extends Customer implements DisplayableListItem{
    private String name;
    private String managerFirstName;
    private String managerLastName;
    public static String[] NAMES = {
            "Rób Pieniążki Zawodowo",
            "Śpij na pieniądzach!",
            "Dreams fulfiller",
            "Du hast was du willst",
            "Może nie najtaniej, ale jako tako",
            "Agresywny",
            "Stabilność przede wszystkim"
    };
    public static String[] FIRSTNAMES = {
            "Zdzisław",
            "Piotr",
            "Andrzej",
            "Janusz",
            "Helmut",
            "Bożydar",
            "James"
    };
    public static String[] LASTNAMES = {
            "Masztalerz",
            "Wąsik",
            "Boligłowa",
            "Dusigrosz",
            "Rozrzutny",
            "Chytry",
            "Szkodnik"

    };



    @Override
    public String getDisplayName() {
        return name;
    }


    public void buyAssets() {

    }

    public void sellAssets() {
    }


    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @return managerFirstName
     */
    public String getManagerFirstName() {
        return managerFirstName;
    }

    /**
     * @return managerLastName
     */
    public String getManagerLastName() {
        return managerLastName;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param managerFirstName
     */
    public void setManagerFirstName(String managerFirstName) {
        this.managerFirstName = managerFirstName;
    }

    /**
     * @param managerLastName
     */
    public void setManagerLastName(String managerLastName) {
        this.managerLastName = managerLastName;
    }

}