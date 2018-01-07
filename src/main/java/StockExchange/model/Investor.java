
package StockExchange.model;

import StockExchange.controller.MainSceneController;
import javafx.collections.ObservableList;

import java.lang.reflect.Array;
import StockExchange.ApplicationExecutor;
import java.util.*;
import static java.lang.Thread.sleep;

/**
 * @author jakub
 */
public class Investor extends Customer {
    private String firstName;
    private String lastName;
    private String PESEL;
    private double budget;
    private List<Currency> currenciesPurchased;
    private List<Material> materialsPurchased;
    private List<Share> sharesPurchased;

    public static String[] FIRSTNAMES = {
            "Christian",
            "Bill",
            "Tony",
            "Lucjusz",
            "Zdzisław",
            "Jarosław",
            "Marian",
            "Gordon",
            "Bruce",
            "Jordan",
            "Donald",
            "Dariusz",
    };
    public static String[] LASTNAMES = {
            "Grey",
            "Gates",
            "Stark",
            "Malfoy",
            "Majchrzak",
            "Kowalski",
            "Szczygieł",
            "Gekko",
            "Wayne",
            "Belfort",
            "Trump",
            "Brzezinski"
    };

    private static ArrayList<String> firstnames = new ArrayList<>(Arrays.asList(FIRSTNAMES));
    private static ArrayList<String> lastnames = new ArrayList<>(Arrays.asList(LASTNAMES));

    public Investor() {
        materialsPurchased = new ArrayList<>();
        currenciesPurchased = new ArrayList<>();
        sharesPurchased = new ArrayList<>();
        Random generator = new Random();
        firstName = firstnames.get(generator.nextInt(firstnames.size()));
        firstnames.remove(firstName);
        lastName = lastnames.get(generator.nextInt(lastnames.size()));
        lastnames.remove(lastName);
        StringBuilder builder = new StringBuilder();
        this.PESEL = builder
                .append(getTwoDigitNumberString(generator.nextInt(100))) //rok
                .append(getTwoDigitNumberString(generator.nextInt(12) + 1)) //miesiac
                .append(getTwoDigitNumberString(generator.nextInt(28) + 1)) // dzien
                .append(getTwoDigitNumberString(generator.nextInt(100000))).toString();
        this.budget = generator.nextDouble() * 1000000.0;
    }

    private String getTwoDigitNumberString(int i) {
        if (i < 10) {
            return String.valueOf(0) + i;
        } else {
            return String.valueOf(i);
        }
    }

    public void investorOperations() {
        ApplicationExecutor.getInstance().getBackgroundThreadPool().execute(() -> {
            while (true) {
                Random generator = new Random();
                int cases = generator.nextInt(4) + 1;
                switch (cases) {
                    case 0:
                        buyAssetsOnYourOwn();
                    case 1:
                        sellAssetsOnYourOwn();
                    case 2:
                        buyWithInvFuM();
                    case 3:
                        sellWithInfFuM();
                    default:
                        riseBudget();
                }
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void buyAssetsOnYourOwn() {
        Random generator = new Random();
        int choice = generator.nextInt(3);
        switch (choice) {
            case 0:
                //indeks gieldy
                List<StockExchange> exchanges = new ArrayList<>(ApplicationModel.getInstance().getStockExchanges());
                int stockNo = generator.nextInt(exchanges.size()); //indeks losowej gieldy
                int indexNo = generator.nextInt(exchanges.get(stockNo).getIndexList().size()); //indeks losowego indeksu gieldy
                int comNo = generator.nextInt(exchanges.get(stockNo).getIndexList().get(indexNo).getCompaniesList().size()); //indeks losowej spolki na indeksie
                int sharesNo = exchanges.get(stockNo).getIndexList().get(indexNo).getCompaniesList().get(comNo).getSharesCount(); //liczba akcji
                if (sharesNo > 0) {
                        double sharesPurPr = exchanges.get(stockNo).getIndexList().get(indexNo).getCompaniesList().get(comNo).getCurrentPrice();

                }


            case 1:
                ;
            default:
                ;
        }
    }

    public void sellAssetsOnYourOwn() {

    }

    public void buyWithInvFuM() {

    }

    public void sellWithInfFuM() {

    }

    public void riseBudget() {
        Random generator = new Random();
        this.budget *= (generator.nextDouble() + 1.0);
    }


    /**
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return PESEL
     */
    public String getPESEL() {
        return PESEL;
    }

    /**
     * @return budget
     */
    public double getBudget() {
        return budget;
    }

    /**
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @param PESEL
     */
    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    /**
     * @param budget
     */
    public void setBudget(double budget) {
        this.budget = budget;
    }
}
