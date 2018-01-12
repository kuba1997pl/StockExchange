
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
    private List<CurrencyInWallet> currenciesPurchased;
    private List<MaterialInWallet> materialsPurchased;
    private List<ShareInWallet> sharesPurchased;
    private double budget;


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
        if(namesList.size() > 0) {
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
                        buyAssets();
                    default:
                        sellAssets();
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


    /**
     * buying Assets on your own
     */
    public void buyAssets() {
        Random generator = new Random();
        int choice = generator.nextInt(3);
        switch (choice) {
            case 0:
                buyShares();
            case 1:
                buyMaterials();
            default:
                buyCurrencies();
                //OGARNĄĆ POTEM!!!
        }
    }

    private void buyMaterials() {
        Random generator = new Random();
        ObservableList<MaterialMarket> materialMarkets = ApplicationModel.getInstance().getMaterialMarkets();
        ObservableList<Currency> currencies = ApplicationModel.getInstance().getCurrencies();
        MaterialMarket market = materialMarkets.get(generator.nextInt(materialMarkets.size()));
        Material material = market.getMaterialList().get(generator.nextInt(market.getMaterialList().size()));
        //Value of currency is changing so each time I buy material I have to check the current value of its currency
        String currencyName = material.getCurrency().getName();
        int indexOfCurrency = 0;
        for (Currency elem : currencies) {
            if (elem.getName().equals(currencyName))
                indexOfCurrency = currencies.indexOf(elem);
        }
        Currency currencyOfMaterial = currencies.get(indexOfCurrency);

        double materialAmount = generator.nextDouble() * (budget / (material.getCurrentValue() * currencyOfMaterial.getPurchasePrice()));
        budget -= market.getMargin() * material.buyMaterial(materialAmount) * currencyOfMaterial.getPurchasePrice();

        boolean checker = true;
        for (MaterialInWallet element : materialsPurchased) {
            if (material.getName().equals(element.getName())) {
                checker = false;
                element.incrementAmount(materialAmount);
            }
        }
        if (checker) {
            MaterialInWallet newMaterial = new MaterialInWallet(materialAmount, market.getName());
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

        double boughtSharesPrice = company.buyShares(sharesPurchaseAmount);
        if (boughtSharesPrice >= 0) {
            budget -= (boughtSharesPrice + market.getMargin() * boughtSharesPrice);
        }

        boolean checker = true;
        for (ShareInWallet elem : sharesPurchased) {
            if (company.getName().equals(elem.getName())) {
                elem.incrementAmount(sharesPurchaseAmount);
                checker = false;
            }
        }

        if(checker){
            ShareInWallet newShare = new ShareInWallet(company.getName(), sharesPurchaseAmount);
            newShare.setStockName(market.getName());
            sharesPurchased.add(newShare);
        }
    }

    private void buyCurrencies() {
    }

    /**
     * selling Assets on your own
     */
    public void sellAssets() {

        Random generator = new Random();
        int choice = generator.nextInt(3);
        switch (choice) {
            case 0:
                sellShares();
            case 1:
                sellMaterials();
            default:
                sellCurrencies();
                // WALUTY OGARNĄĆ!!!
        }
    }

    private void sellShares() {
        Random generator = new Random();
        ObservableList<StockMarket> stockMarkets = ApplicationModel.getInstance().getStockMarkets();
        ObservableList<Company> companies = ApplicationModel.getInstance().getCompanies();
        int companyNumber = generator.nextInt(sharesPurchased.size());
        String stockName = sharesPurchased.get(companyNumber).getStockName();
        String companyName = sharesPurchased.get(companyNumber).getName();

        //looking for a stock where we can sell shares of chosen company
        int indexOfStock = 0;
        Optional<StockMarket> stockMarket = stockMarkets.stream().filter(market -> market.getName().equals(stockName)).findFirst();
        for (StockMarket elem : stockMarkets) {
            if (stockName.equals(elem.getName())) {
                indexOfStock = stockMarkets.indexOf(elem);
            }
        }
        StockMarket stockToSell = stockMarkets.get(indexOfStock);
        //looking for a proper company
        int indexOfCompany = 0;
        for (Company elem : companies) {
            if (companyName.equals(elem.getName())) {
                indexOfCompany = companies.indexOf(elem);
            }
        }
        Company company = companies.get(indexOfCompany);

        int sharesCount = generator.nextInt(sharesPurchased.get(companyNumber).getAmount());
        double sellPrice = company.getCurrentPrice();

        //increasing budget;
        budget += sharesCount * sellPrice;
        //decreasing budget because of paying a margine
        budget -= sharesCount * sellPrice * stockToSell.getMargin();

        //decreasing number of Investor's shares
        int currentSharesCount = generator.nextInt(sharesPurchased.get(companyNumber).getAmount());
        sharesPurchased.get(companyNumber).setAmount(currentSharesCount - sharesCount);

        //increasing number of Company's shares
        company.incrementSharesCount(sharesCount);

        //1decreasing share price
        company.decrementCurrentPrice();

        // increasing volume
        company.changeVolume(sharesCount);

        //increasing shares
        company.increaseSales(sharesCount * sellPrice);
    }

    private void sellMaterials() {
        Random generator = new Random();
        ObservableList<MaterialMarket> materialMarkets = ApplicationModel.getInstance().getMaterialMarkets();
        ObservableList<Material> materials = ApplicationModel.getInstance().getMaterials();
        ObservableList<Currency> currencies = ApplicationModel.getInstance().getCurrencies();

        int materialNumber = generator.nextInt(materialsPurchased.size());
        //amount of material we have in wallet is maximum we can sell
        double materialAmount = generator.nextDouble() * materialsPurchased.get(materialNumber).getAmount();
        String marketName = materialsPurchased.get(materialNumber).getMarketName();
        String materialName = materialsPurchased.get(materialNumber).getName();

        int indexOfMaterial = 0;
        for (Material elem : materials) {
            if (materialName.equals(elem.getName())) {
                indexOfMaterial = materials.indexOf(elem);
            }
        }
        Material material = materials.get(indexOfMaterial);

        int indexOfMaterialMarket = 0;
        for (MaterialMarket elem : materialMarkets) {
            if (marketName.equals(elem.getName())) {
                indexOfMaterialMarket = materialMarkets.indexOf(elem);
            }
        }
        MaterialMarket materialMarket = materialMarkets.get(indexOfMaterialMarket);

        String currencyName = material.getCurrency().getName();
        int indexOfCurrency = 0;
        for (Currency elem : currencies) {
            if (elem.getName().equals(currencyName))
                indexOfCurrency = currencies.indexOf(elem);
        }
        Currency currencyOfMaterial = currencies.get(indexOfCurrency);

        double materialPrice = material.getCurrentValue();
        double minPriceSoFar = material.getMinValue();
        double margin = materialMarket.getMargin();
        double currencyValue = currencyOfMaterial.getSellPrice();

        //changing the budget
        budget += materialPrice * materialAmount * currencyValue; // x kursSprzedażyWaluty;
        budget -= materialPrice * materialAmount * currencyValue * margin; // x kursSprzedażyWaluty

        //decrementing amount of material in wallet
        materialsPurchased.get(materialNumber).decrementAmount(materialAmount);

        //decrementing price of material
        material.decrementCurrentValue();
    }

    private void sellCurrencies() {
        Random generator = new Random();
        /* After changes in ApplicationModel it will work
        ObservableList<CurrencyMarket> currencyMarket = ApplicationModel.getInstance().getCurrencyMarket();
        */
        int currencyNumber = generator.nextInt(currenciesPurchased.size());
        //String currencyName = currencyMarket.get(currencyNumber).getName();
        int indexOfCurrency = 0;
        /*
        for (Currency elem : currencyMarket) {
            if (stockName.equals(elem.getName())) {
                indexOfStock = stockMarkets.indexOf(elem);
            }
        }
        */
        //Currency currency = currencyMarket.get(indexOfCurrency);
        double currencyAmount = generator.nextDouble() * currenciesPurchased.get(currencyNumber).getAmount();
        //double currencySellPrice = currency.getSellPrice();
        //double margin = currencyMarket.getMargin();

        //budget+= currencyAmount*currencyAmount*(1- margin);

        currenciesPurchased.get(currencyNumber).decrementAmount(currencyAmount);

        //currency.decrementPrice();
    }

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
