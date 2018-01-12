
package StockExchange.model;

import StockExchange.ui.DisplayableListItem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author jakub
 */
public class InvestmentFund extends Customer implements DisplayableListItem {
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

    private static ArrayList<String> namesList = new ArrayList<>(Arrays.asList(NAMES));

    public InvestmentFund(){
        Random generator = new Random();
        name = NAMES[generator.nextInt(namesList.size())];
        namesList.remove(name);
        managerFirstName = FIRSTNAMES[generator.nextInt(FIRSTNAMES.length)];
        managerLastName = LASTNAMES[generator.nextInt(LASTNAMES.length)];
    }

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
}
