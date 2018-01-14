package StockExchange.model;

import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jakub
 */
public class Customer implements Serializable {
    private String name;
    protected double budget;
    protected List<CurrencyInWallet> currenciesPurchased = new ArrayList<>();
    protected List<MaterialInWallet> materialsPurchased = new ArrayList<>();
    protected List<ShareInWallet> sharesPurchased = new ArrayList<>();

    /**
     * buying Assets on your own
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
                buyCurrencies();
                //OGARNĄĆ POTEM!!!
        }
    }

    private void buyMaterials() {
        if (ApplicationModel.getInstance().getMaterialMarkets().size() > 0) {
            Random generator = new Random();
            ObservableList<MaterialMarket> materialMarkets = ApplicationModel.getInstance().getMaterialMarkets();
            ObservableList<Currency> currencies = ApplicationModel.getInstance().getCurrencies();
            MaterialMarket market = materialMarkets.get(generator.nextInt(materialMarkets.size()));
            List<Material> materialList = market.getMaterialList();
            if (materialList.size() > 0) {
                Material material = materialList.get(generator.nextInt(materialList.size()));
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
                //incrementing current Price
                material.incrementCurrentValue();

                boolean checker = true;
                for (MaterialInWallet element : materialsPurchased) {
                    if (material.getName().equals(element.getName())) {
                        checker = false;
                        element.incrementAmount(materialAmount);
                    }

                }
                if (checker) {
                    MaterialInWallet newMaterial = new MaterialInWallet(materialAmount, market.getName(), material.getName());
                    materialsPurchased.add(newMaterial);
                }
            }
        }
    }

    private synchronized void buyShares() {
        Random generator = new Random();
        List<StockMarket> stockMarkets = ApplicationModel.getInstance().getStockMarkets();
        if(stockMarkets.size() > 0) {
            StockMarket market = stockMarkets.get(generator.nextInt(stockMarkets.size()));
            List<Index> indexList = market.getIndexList();
            if(indexList.size() > 0) {
                Index index = indexList.get(generator.nextInt(indexList.size()));
                List<Company> companies = index.getCompaniesList();
                if(companies.size() > 0) {
                    Company company = companies.get(generator.nextInt(companies.size()));
                    if(company.getSharesCount() > 0) {
                        int bound = Math.min(company.getSharesCount(), (int) Math.floor(budget / company.getCurrentPrice()));
                        if (bound > 0) {
                            int sharesPurchaseAmount = generator.nextInt(bound);

                            double boughtSharesPrice = company.buyShares(sharesPurchaseAmount);
                            if (boughtSharesPrice >= 0) {
                                budget -= (boughtSharesPrice + market.getMargin() * boughtSharesPrice);
                            }

                            company.incrementCurrentPrice();

                            boolean checker = true;
                            for (ShareInWallet elem : sharesPurchased) {
                                if (company.getName().equals(elem.getName())) {
                                    elem.incrementAmount(sharesPurchaseAmount);
                                    checker = false;
                                }
                            }
                            if (checker) {
                                ShareInWallet newShare = new ShareInWallet(company.getName(), sharesPurchaseAmount);
                                newShare.setStockName(market.getName());
                                sharesPurchased.add(newShare);
                            }
                        }
                    }
                }
            }
        }
    }

    private void buyCurrencies() {
        if (ApplicationModel.getInstance().getCurrencyMarket().getCurrencyList().size() > 0) {
            Random generator = new Random();
            CurrencyMarket currencyMarket = ApplicationModel.getInstance().getCurrencyMarket();
            List<Currency> currencies = currencyMarket.getCurrencyList();
            Currency currency = currencies.get(generator.nextInt(currencies.size()));
            double currencyAmount = generator.nextDouble() * (budget / currency.getPurchasePrice());
            boolean checker = true;

            for (CurrencyInWallet elem : currenciesPurchased) {
                if (elem.getName().equals(currency.getName())) {
                    checker = false;
                    elem.incrementAmount(currencyAmount);
                }
            }
            if (checker) {
                CurrencyInWallet newCurrency = new CurrencyInWallet(currencyAmount, currency.getName());
                currenciesPurchased.add(newCurrency);
            }

            double currencyPurchasePrice = currency.getPurchasePrice();
            double margin = currencyMarket.getMargin();
            budget -= currencyAmount * currencyPurchasePrice * (1 + margin);

            currency.incrementPrice();
        }
    }

    /**
     * selling Assets on your own
     */
    public void sellAssetsOnYourOwn() {

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
        if (sharesPurchased.size() > 0) {
            Random generator = new Random();
            ObservableList<StockMarket> stockMarkets = ApplicationModel.getInstance().getStockMarkets();
            ObservableList<Company> companies = ApplicationModel.getInstance().getCompanies();
            if (stockMarkets.size() > 0 && companies.size() > 0) {
                ShareInWallet share = sharesPurchased.get(generator.nextInt(sharesPurchased.size()));
                String stockName = share.getStockName();
                String companyName = share.getName();

                //looking for a stock where we can sell shares of chosen company
                StockMarket stockToSell = null;
                for (StockMarket elem : stockMarkets) {
                    if (stockName.equals(elem.getName())) {
                        stockToSell = elem;
                        break;
                    }
                }
                //looking for a proper company
                Company company = null;
                for (Company elem : companies) {
                    if (companyName.equals(elem.getName())) {
                        company = elem;
                        break;
                    }
                }

                if (stockToSell != null && company != null && share.getAmount() > 0) {
                    int sharesCount = generator.nextInt(share.getAmount());
                    double sellPrice = company.getCurrentPrice();

                    //increasing budget;
                    budget += sharesCount * sellPrice;
                    //decreasing budget because of paying a margine
                    budget -= sharesCount * sellPrice * stockToSell.getMargin();

                    //decreasing number of Investor's shares
                    int currentSharesCount = generator.nextInt(share.getAmount());
                    share.setAmount(currentSharesCount - sharesCount);

                    //increasing number of Company's shares
                    company.incrementSharesCount(sharesCount);

                    //1decreasing share price
                    company.decrementCurrentPrice();

                    // increasing volume
                    company.changeVolume(sharesCount);

                    //increasing shares
                    company.increaseSales(sharesCount * sellPrice);
                }
            }
        }
    }

    private void sellMaterials() {
        if (materialsPurchased.size() > 0) {
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
    }


    private synchronized void sellCurrencies() {
        if (currenciesPurchased.size() > 0) {
            Random generator = new Random();
            // After changes in ApplicationModel it will work
            CurrencyMarket currencyMarket = ApplicationModel.getInstance().getCurrencyMarket();
            List<Currency> currencyList = currencyMarket.getCurrencyList();
            if (currencyList.size() > 0) {
                CurrencyInWallet currencyInWallet = currenciesPurchased.get(generator.nextInt(currenciesPurchased.size()));
                Currency currency = null;
                // find our currency on a global currencies list
                for (Currency elem : currencyList) {
                    if (currencyInWallet.getName().equals(elem.getName())) {
                        currency = elem;
                    }
                }
                if (currency != null) {
                    double currencyAmount = generator.nextDouble() * currencyInWallet.getAmount();
                    double currencySellPrice = currency.getSellPrice();
                    double margin = currencyMarket.getMargin();

                    budget += currencyAmount * currencySellPrice * (1 - margin);

                    currencyInWallet.decrementAmount(currencyAmount);

                    currency.decrementPrice();
                }
            }
        }
    }

    /**
     * @return budget
     */
    public double getBudget() {
        return budget;
    }

    /**
     * @return currenciesPurchased of customer
     */
    public List<CurrencyInWallet> getCurrenciesPurchased() {
        return currenciesPurchased;
    }

    /**
     * @return materialsPurchased of customer
     */
    public List<MaterialInWallet> getMaterialsPurchased() {
        return materialsPurchased;
    }

    /**
     * @return sharesPurchased of customer
     */
    public List<ShareInWallet> getSharesPurchased() {
        return sharesPurchased;
    }
}
