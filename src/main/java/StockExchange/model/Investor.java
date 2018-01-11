
package StockExchange.model;

import StockExchange.ApplicationExecutor;
import javafx.collections.ObservableList;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

/**
 * @author jakub
 */
public class Investor extends Customer {
    private String firstName;
    private String lastName;
    private String PESEL;
    private double budget;
    private List<CurrencyInWallet> currenciesPurchased;
    private List<MaterialInWallet> materialsPurchased;
    private List<ShareInWallet> sharesPurchased;

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


    /**
     * buying Assets on your Own
     */
    public void buyAssetsOnYourOwn() {
        Random generator = new Random();
        int choice = generator.nextInt(3);
        switch (choice) {
            case 0:
                buyShares();
            case 1:
                buyMaterials();
            default:
                //OGARNĄĆ POTEM!!!
        }
    }

    private void buyMaterials() {
        Random generator = new Random();
        ObservableList<MaterialMarket> materialMarkets = ApplicationModel.getInstance().getMaterialMarketListModels();
        MaterialMarket market = materialMarkets.get(generator.nextInt(materialMarkets.size()));
        Material material = market.getMaterialList().get(generator.nextInt(market.getMaterialList().size()));
        double materialAmount = generator.nextDouble() * (budget / material.getCurrentValue());
        budget -= market.getMargin() * material.buyMaterial(materialAmount);
        Optional<MaterialInWallet> optionalMaterial = materialsPurchased.stream().filter(inWallet -> inWallet.name.equals(material.name)).findFirst();
        if(optionalMaterial.isPresent()) {
            optionalMaterial.get().incrementAmount(materialAmount);
        } else {
            MaterialInWallet newMaterial = new MaterialInWallet();
            newMaterial.setName(material.name);
            materialsPurchased.add(newMaterial);
        }
    }

    private void buyShares() {
        Random generator = new Random();
        ObservableList<StockMarket> stockMarkets = ApplicationModel.getInstance().getStockMarkets();
        StockMarket market = stockMarkets.get(generator.nextInt(stockMarkets.size()));
        List<Company> companies = market.getIndexList()
                .stream()
                .map(Index::getCompaniesList)
                .flatMap(Collection::stream)
                .filter(company -> company.getSharesCount() > 0)
                .collect(Collectors.toList());

        Company company = companies.get(generator.nextInt(companies.size()));

        int sharesPurchaseAmount = Math.min(company.getSharesCount(), (int) Math.floor(budget / company.getCurrentPrice()));

        int boughtSharesPrice = company.buyShares(sharesPurchaseAmount);
        if (boughtSharesPrice >= 0) {
            budget -= (boughtSharesPrice + market.getMargin() * boughtSharesPrice);
        }

        Optional<ShareInWallet> optionalShare = sharesPurchased.stream().filter(shareInWallet -> shareInWallet.name.equals(market.getName())).findFirst();
        if(optionalShare.isPresent()) {
            optionalShare.get().incrementAmount(sharesPurchaseAmount);
        } else {
            ShareInWallet newShare = new ShareInWallet(company.getName(), sharesPurchaseAmount);
            newShare.setStockName(market.getName());
            sharesPurchased.add(newShare);
        }
    }

    public void sellAssetsOnYourOwn() {

        Random generator = new Random();
        int choice = generator.nextInt(3);
        switch (choice) {
            case 0:
                int shareNo = generator.nextInt(sharesPurchased.size());

            case 1:

            default:
                //OGARNĄĆ POTEM!!!
        }
    }

    public void buyWithInvFuM() {

    }

    public void sellWithInfFuM() {

    }

    public void riseBudget() {
        Random generator = new Random();
        if (budget < 0) {
            //giving a chance to a bankrupt
            budget += -budget + generator.nextDouble() * 100;
        } else
            budget = (generator.nextDouble() + 1.0);
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
