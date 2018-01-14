
package StockExchange.model;

import StockExchange.ApplicationExecutor;
import StockExchange.ui.DisplayableListItem;
import javafx.collections.ObservableList;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

/**
 * @author jakub
 */

public class Investor extends Customer implements DisplayableListItem {
    private String firstName;
    private String lastName;
    private String PESEL;
    private double budget;
    private List<CurrencyInWallet> currenciesPurchased;
    private List<MaterialInWallet> materialsPurchased;
    private List<ShareInWallet> sharesPurchased;

    private static ArrayList<String> namesList = new ArrayList<>();

    static {
        InputStream inputStream = Company.class.getResourceAsStream("/StockExchange/dataSamples/namesSample.json");
        try {
            String jsonString = IOUtils.toString(inputStream);
            inputStream.close();
            JSONArray companies = new JSONArray(jsonString);
            for (int i = 0; i < companies.length(); i++) {
                namesList.add(companies.getString(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Investor() {
        materialsPurchased = new ArrayList<>();
        currenciesPurchased = new ArrayList<>();
        sharesPurchased = new ArrayList<>();
        Random generator = new Random();
        String name = namesList.get(generator.nextInt(namesList.size()));
        namesList.remove(name);
        String[] splitName = name.split("\\s+");
        firstName = splitName[0];
        lastName = splitName[1];
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

    public synchronized void investorOperations() {
        ApplicationExecutor.getInstance().getBackgroundThreadPool().execute(() -> {
            while (true) {
                Random generator = new Random();
                int cases = generator.nextInt(4);
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
     * buying assets with investment fund
     */
    private void buyWithInvFuM() {
        Random generator = new Random();
        int choice = generator.nextInt(3);
        switch (choice) {
            case 0:
                buySharesWithIF();
            case 1:
                buyMaterialsWithIF();
            default:
                buyCurrenciesWitfIF();
        }

    }

    private void buySharesWithIF() {
        Random generator = new Random();
        ObservableList<InvestmentFund> investmentFunds = ApplicationModel.getInstance().getInvestmentFunds();
        ObservableList<Company> companies = ApplicationModel.getInstance().getCompanies();
        int investmentFundNumber = generator.nextInt(investmentFunds.size());
        List<ShareInWallet> sharesToBuy = investmentFunds.get(investmentFundNumber).getSharesPurchased();
        int shareNumber = generator.nextInt(sharesToBuy.size());
        String companyName = sharesToBuy.get(shareNumber).getName();
        int indexOfCompany = 0;
        for (Company elem : companies) {
            if (companyName.equals(elem.getName())) {
                indexOfCompany = companies.indexOf(elem);
            }
        }
        Company company = companies.get(indexOfCompany);
        double sharePrice = company.getCurrentPrice();
        int shareAmount = generator.nextInt((int)(budget/sharePrice));
    }

    private void buyMaterialsWithIF() {

    }

    private void buyCurrenciesWitfIF(){

    }

    /**
     * selling assets with investment fund
     */
    private void sellWithInfFuM() {
        Random generator = new Random();
        int choice = generator.nextInt(3);
        switch (choice) {
            case 0:
                sellSharesWithIF();
            case 1:
                sellMaterialsWithIF();
            default:
                sellCurrenciesWithIF();
        }
    }

    private void sellSharesWithIF(){};
    private void sellMaterialsWithIF(){};
    private void sellCurrenciesWithIF(){};
    /**
     * possibility to change budget
     */
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
     * @return currenciesPurchased
     */
    public List<CurrencyInWallet> getCurrenciesPurchased() {
        return currenciesPurchased;
    }

    /**
     * @return materialsPurchased
     */
    public List<MaterialInWallet> getMaterialsPurchased() {
        return materialsPurchased;
    }

    /**
     * @return sharesPurchased
     */
    public List<ShareInWallet> getSharesPurchased() {
        return sharesPurchased;
    }

    @Override
    public String getDisplayName() {
        return firstName + " " + lastName;
    }
}
