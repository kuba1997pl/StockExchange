
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
        this.budget = generator.nextDouble() * 1000000 + 1000000;
        ApplicationExecutor.getInstance().getBackgroundThreadPool().execute(this::investorOperations);
    }

    private String getTwoDigitNumberString(int i) {
        if (i < 10) {
            return String.valueOf(0) + i;
        } else {
            return String.valueOf(i);
        }
    }

    private synchronized void investorOperations() {
        while (!Thread.currentThread().isInterrupted()) {
            Random generator = new Random();
            int cases = generator.nextInt(5);
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
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
        if (ApplicationModel.getInstance().getInvestmentFunds().size() > 0) {
            Random generator = new Random();
            ObservableList<InvestmentFund> investmentFunds = ApplicationModel.getInstance().getInvestmentFunds();
            ObservableList<Company> companies = ApplicationModel.getInstance().getCompanies();
            int investmentFundNumber = generator.nextInt(investmentFunds.size());
            List<ShareInWallet> sharesToBuy = investmentFunds.get(investmentFundNumber).getSharesPurchased();
            if (sharesToBuy.size() > 0) {
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
                int sharesAmount = generator.nextInt(Math.min(sharesToBuy.get(shareNumber).getAmount(), (int) Math.floor(budget / company.getCurrentPrice())));
                //decreasing budget of investor
                budget -= sharePrice * sharesAmount;
                //increasing budget of IF
                investmentFunds.get(investmentFundNumber).increaseBudget(sharePrice * sharesAmount);
                //decreasing amount of IF shares
                sharesToBuy.get(shareNumber).decrementAmount(sharesAmount);

                boolean checker = true;
                for (ShareInWallet elem : sharesPurchased) {
                    if (company.getName().equals(elem.getName())) {
                        elem.incrementAmount(sharesAmount);
                        checker = false;
                    }
                }
                if (checker) {
                    ShareInWallet newShare = new ShareInWallet(company.getName(), sharesAmount);
                    newShare.setStockName(sharesToBuy.get(shareNumber).getStockName());
                    sharesPurchased.add(newShare);
                }
            }
        }
    }

    private void buyMaterialsWithIF() {

        Random generator = new Random();
        ObservableList<InvestmentFund> investmentFunds = ApplicationModel.getInstance().getInvestmentFunds();
        ObservableList<Currency> currencies = ApplicationModel.getInstance().getCurrencies();
        ObservableList<Material> materials = ApplicationModel.getInstance().getMaterials();

        if (investmentFunds.size() > 0 && currencies.size() > 0 && materials.size() > 0) {
            int investmentFundNumber = generator.nextInt(investmentFunds.size());
            List<MaterialInWallet> materialsToBuy = investmentFunds.get(investmentFundNumber).getMaterialsPurchased();
            if (materialsToBuy.size() > 0) {
                int materialNumber = generator.nextInt(materialsToBuy.size());
                String materialName = materialsToBuy.get(materialNumber).getName();

                int indexOfMaterial = 0;
                for (Material elem : materials) {
                    if (materialName.equals(elem.getName())) {
                        indexOfMaterial = materials.indexOf(elem);
                    }
                }
                Material material = materials.get(indexOfMaterial);

                String currencyName = material.getCurrency().getName();
                int indexOfCurrency = 0;
                for (Currency elem : currencies) {
                    if (elem.getName().equals(currencyName))
                        indexOfCurrency = currencies.indexOf(elem);
                }
                Currency currencyOfMaterial = currencies.get(indexOfCurrency);

                double materialPrice = material.getCurrentValue() * currencyOfMaterial.getPurchasePrice();

                double materialAmount = generator.nextDouble() * Math.min(materialsToBuy.get(materialNumber).getAmount(), (int) Math.floor(budget / materialPrice));

                budget -= materialPrice * materialAmount;
                //increasing budget of IF
                investmentFunds.get(investmentFundNumber).increaseBudget(materialPrice * materialAmount);
                //decreasing amount of IF materials
                materialsToBuy.get(materialNumber).decrementAmount(materialAmount);

                boolean checker = true;
                for (MaterialInWallet elem : materialsPurchased) {
                    if (material.getName().equals(elem.getName())) {
                        elem.incrementAmount(materialAmount);
                        checker = false;
                    }
                }
                if (checker) {
                    MaterialInWallet newMaterial = new MaterialInWallet(materialAmount, material.getName(), materialsToBuy.get(materialNumber).getMarketName());
                    materialsPurchased.add(newMaterial);
                }
            }
        }

    }

    private void buyCurrenciesWitfIF() {
        Random generator = new Random();
        ObservableList<InvestmentFund> investmentFunds = ApplicationModel.getInstance().getInvestmentFunds();
        if (investmentFunds.size() > 0) {
            ObservableList<Currency> currencies = ApplicationModel.getInstance().getCurrencies();
            int investmentFundNumber = generator.nextInt(investmentFunds.size());
            List<CurrencyInWallet> currenciesToBuy = investmentFunds.get(investmentFundNumber).getCurrenciesPurchased();
            if (currenciesToBuy.size() > 0) {
                int currencyNumber = generator.nextInt(currenciesToBuy.size());
                String currencyName = currenciesToBuy.get(currencyNumber).getName();

                int indexOfCurrency = 0;
                for (Currency elem : currencies) {
                    if (currencyName.equals(elem.getName())) {
                        indexOfCurrency = currencies.indexOf(elem);
                    }
                }
                Currency currency = currencies.get(indexOfCurrency);

                double currencyPurchasePrice = currency.getPurchasePrice();

                double currencyAmount = generator.nextDouble() * Math.min(currenciesToBuy.get(currencyNumber).getAmount(), (int) Math.floor(budget / currencyPurchasePrice));

                //decreasing investor's budget
                budget -= currencyPurchasePrice * currencyAmount;
                //increasing budget of IF
                investmentFunds.get(investmentFundNumber).increaseBudget(currencyPurchasePrice * currencyAmount);
                //decreasing amount of IF currencies
                currenciesToBuy.get(currencyNumber).decrementAmount(currencyAmount);

                boolean checker = true;
                for (CurrencyInWallet elem : currenciesPurchased) {
                    if (currency.getName().equals(elem.getName())) {
                        elem.incrementAmount(currencyAmount);
                        checker = false;
                    }
                }
                if (checker) {
                    CurrencyInWallet newCurrency = new CurrencyInWallet(currencyAmount, currencyName);
                    currenciesPurchased.add(newCurrency);
                }
            }
        }
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

    private void sellSharesWithIF() {

        Random generator = new Random();

        ObservableList<InvestmentFund> investmentFunds = ApplicationModel.getInstance().getInvestmentFunds();
        ObservableList<Company> companies = ApplicationModel.getInstance().getCompanies();
        if (investmentFunds.size() > 0 && companies.size() > 0 && sharesPurchased.size() > 0) {

            int shareNumber = generator.nextInt(sharesPurchased.size());
            String companyName = sharesPurchased.get(shareNumber).getName();
            int shareMaxCount = sharesPurchased.get(shareNumber).getAmount();

            int investmentFundNumber = generator.nextInt(investmentFunds.size());
            double investmentFundBudget = investmentFunds.get(investmentFundNumber).getBudget();
            List<ShareInWallet> investmentFundShares = investmentFunds.get(investmentFundNumber).getSharesPurchased();

            int indexOfCompany = 0;
            for (Company elem : companies) {
                if (elem.getName().equals(companyName))
                    indexOfCompany = companies.indexOf(elem);
            }
            Company company = companies.get(indexOfCompany);
            double sharePrice = company.getCurrentPrice();
            int shareAmount = generator.nextInt(Math.min(shareMaxCount, (int) Math.floor(investmentFundBudget / company.getCurrentPrice())));

            budget += shareAmount * sharePrice;
            investmentFunds.get(investmentFundNumber).decreaseBudget(shareAmount * sharePrice);
            sharesPurchased.get(shareNumber).decrementAmount(shareAmount);

            boolean checker = true;
            for (ShareInWallet elem : investmentFundShares) {
                if (company.getName().equals(elem.getName())) {
                    elem.incrementAmount(shareAmount);
                    checker = false;
                }
            }
            if (checker) {
                ShareInWallet newShare = new ShareInWallet(company.getName(), shareAmount);
                newShare.setStockName(sharesPurchased.get(shareNumber).getStockName());
                investmentFundShares.add(newShare);
            }
        }
    }

    private void sellMaterialsWithIF() {
        Random generator = new Random();

        ObservableList<InvestmentFund> investmentFunds = ApplicationModel.getInstance().getInvestmentFunds();
        ObservableList<Material> materials = ApplicationModel.getInstance().getMaterials();
        ObservableList<Currency> currencies = ApplicationModel.getInstance().getCurrencies();
        if (investmentFunds.size() > 0 && currencies.size() > 0 && materials.size() > 0 && materialsPurchased.size() > 0) {

            int materialNumber = generator.nextInt(materialsPurchased.size());
            String materialName = materialsPurchased.get(materialNumber).getName();
            double materialMaxCount = materialsPurchased.get(materialNumber).getAmount();
            String materialMarket = materialsPurchased.get(materialNumber).getMarketName();

            int investmentFundNumber = generator.nextInt(investmentFunds.size());
            double investmentFundBudget = investmentFunds.get(investmentFundNumber).getBudget();
            List<MaterialInWallet> investmentFundMaterials = investmentFunds.get(investmentFundNumber).getMaterialsPurchased();

            int indexOfMaterial = 0;
            for (Material elem : materials) {
                if (elem.getName().equals(materialName))
                    indexOfMaterial = materials.indexOf(elem);
            }

            Material material = materials.get(indexOfMaterial);
            double materialPrice = material.getCurrentValue();
            double materialAmount = generator.nextDouble() * Math.min(materialMaxCount, (int) Math.floor(investmentFundBudget / materialPrice));

            budget += materialAmount * materialPrice;
            investmentFunds.get(investmentFundNumber).decreaseBudget(materialAmount * materialPrice);
            materialsPurchased.get(materialNumber).decrementAmount(materialAmount);

            boolean checker = true;
            for (MaterialInWallet elem : investmentFundMaterials) {
                if (materialName.equals(elem.getName())) {
                    elem.incrementAmount(materialAmount);
                    checker = false;
                }
            }
            if (checker) {
                MaterialInWallet newMaterial = new MaterialInWallet(materialAmount, materialMarket, materialName);
                investmentFundMaterials.add(newMaterial);
            }
        }
    }


    private void sellCurrenciesWithIF() {

        Random generator = new Random();

        ObservableList<InvestmentFund> investmentFunds = ApplicationModel.getInstance().getInvestmentFunds();
        ObservableList<Currency> currencies = ApplicationModel.getInstance().getCurrencies();
        if (investmentFunds.size() > 0 && currencies.size() > 0 && currenciesPurchased.size() > 0) {

            int currencyNumber = generator.nextInt(currenciesPurchased.size());
            String currencyName = currenciesPurchased.get(currencyNumber).getName();
            double currencyMaxCount = currenciesPurchased.get(currencyNumber).getAmount();

            int investmentFundNumber = generator.nextInt(investmentFunds.size());
            double investmentFundBudget = investmentFunds.get(investmentFundNumber).getBudget();
            List<CurrencyInWallet> investmentFundCurrencies = investmentFunds.get(investmentFundNumber).getCurrenciesPurchased();

            int indexOfCurrency = 0;
            for (Currency elem : currencies) {
                if (elem.getName().equals(currencyName))
                    indexOfCurrency = currencies.indexOf(elem);
            }

            Currency currency = currencies.get(indexOfCurrency);
            double currencySellPrice = currency.getSellPrice();
            double currencyAmount = generator.nextDouble() * Math.min(currencyMaxCount, (int) Math.floor(investmentFundBudget / currencySellPrice));

            budget += currencyAmount * currencySellPrice;
            investmentFunds.get(investmentFundNumber).decreaseBudget(currencyAmount * currencySellPrice);
            currenciesPurchased.get(currencyNumber).decrementAmount(currencyAmount);

            boolean checker = true;
            for (CurrencyInWallet elem : investmentFundCurrencies) {
                if (currencyName.equals(elem.getName())) {
                    elem.incrementAmount(currencyAmount);
                    checker = false;
                }
            }
            if (checker) {
                CurrencyInWallet newCurrency = new CurrencyInWallet(currencyAmount, currencyName);
                investmentFundCurrencies.add(newCurrency);
            }
        }
    }

    /**
     * possibility to change budget
     */
    public void riseBudget() {
        Random generator = new Random();
        if (budget < 0) {
            //giving a chance to a bankrupt
            budget += -budget + generator.nextDouble() * 100;
        } else
            budget += (generator.nextDouble() * 10000);
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
