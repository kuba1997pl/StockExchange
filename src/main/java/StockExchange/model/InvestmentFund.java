
package StockExchange.model;

import StockExchange.ApplicationExecutor;
import StockExchange.ui.DisplayableListItem;
import StockExchange.util.RandomString;
import javafx.collections.ObservableList;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

/**
 * @author jakub
 */
public class InvestmentFund extends Customer implements DisplayableListItem {
    private String name;
    private String managerFirstName;
    private String managerLastName;


   private static String[] NAMES = {
            "Rób Pieniążki Zawodowo",
            "Śpij na pieniądzach!",
            "Dreams fulfiller",
            "Du hast was du willst",
            "Może nie najtaniej, ale jako tako",
            "Agresywny",
            "Stabilność przede wszystkim"
    };
    private static String[] FIRSTNAMES = {
            "Zdzisław",
            "Piotr",
            "Andrzej",
            "Janusz",
            "Helmut",
            "Bożydar",
            "James"
    };
    private static String[] LASTNAMES = {
            "Masztalerz",
            "Wąsik",
            "Boligłowa",
            "Dusigrosz",
            "Rozrzutny",
            "Chytry",
            "Szkodnik"
    };

    private static ArrayList<String> namesList = new ArrayList<>(Arrays.asList(NAMES));

    public InvestmentFund() {
        currenciesPurchased = new ArrayList<>();
        sharesPurchased = new ArrayList<>();
        materialsPurchased = new ArrayList<>();
        Random generator = new Random();
        if (namesList.size() > 0) {
            name = namesList.get(generator.nextInt(namesList.size()));
            namesList.remove(name);
        } else {
            name = RandomString.nextString(10);
        }
        budget = generator.nextDouble() * 100000;
        managerFirstName = FIRSTNAMES[generator.nextInt(FIRSTNAMES.length)];
        managerLastName = LASTNAMES[generator.nextInt(LASTNAMES.length)];
    }

    public synchronized void investmentFundOperations() {
        ApplicationExecutor.getInstance().getBackgroundThreadPool().execute(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                Random generator = new Random();
                int cases = generator.nextInt(2);
                switch (cases) {
                    case 0:
                        buyAssetsOnYourOwn();
                    default:
                        sellAssetsOnYourOwn();
                }
                //company's thread sleep ten times longer than infestor's thread
                try {
                    sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void increaseBudget(double byHowMuch){
        budget+=byHowMuch;
    }
    public void decreaseBudget(double byHowMuch) {budget -= byHowMuch;}

    @Override
    public String getDisplayName() {
        return name;
    }


    /**
     * @return list of currencies purchased by IF
     */
    public List<CurrencyInWallet> getCurrenciesPurchased() {
        return currenciesPurchased;
    }

    /**
     * @return list of materials purchased by IF
     */
    public List<MaterialInWallet> getMaterialsPurchased() {
        return materialsPurchased;
    }

    /**
     * @return list of shares purchased by IF
     */
    public List<ShareInWallet> getSharesPurchased() {
        return sharesPurchased;
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
