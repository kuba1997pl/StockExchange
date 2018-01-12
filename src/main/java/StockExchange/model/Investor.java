
package StockExchange.model;

import StockExchange.ApplicationExecutor;
import StockExchange.ui.DisplayableListItem;
import javafx.collections.ObservableList;

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
        Random generator = new Random();
        ObservableList<MaterialMarket> materialMarkets = ApplicationModel.getInstance().getMaterialMarkets();
        ObservableList<Currency> currencies = ApplicationModel.getInstance().getCurrencies();
        MaterialMarket market = materialMarkets.get(generator.nextInt(materialMarkets.size()));
        Material material = market.getMaterialList().get(generator.nextInt(market.getMaterialList().size()));
        //Value of currency is changing so each time I buy material I have to check the current value of its currency
        Currency currencyOfMaterial = material.getCurrency();
        for (Currency elem : currencies) {
            if (elem.getName().equals(currencyOfMaterial.getName()))
                currencyOfMaterial = elem;
        }


        //DOKOŃCZYĆ!!!
        //!!!
        //!!!
        //!!!
        // UJĄĆ WALUTĘ!!!


        double materialAmount = generator.nextDouble() * (budget / material.getCurrentValue());
        budget -= market.getMargin() * material.buyMaterial(materialAmount);

        Optional<MaterialInWallet> optionalMaterial = materialsPurchased.stream().filter(inWallet -> inWallet.name.equals(material.name)).findFirst();
        if (optionalMaterial.isPresent()) {
            optionalMaterial.get().incrementAmount(materialAmount);
        } else {
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

        Optional<ShareInWallet> optionalShare = sharesPurchased.stream().filter(shareInWallet -> shareInWallet.name.equals(market.getName())).findFirst();
        if (optionalShare.isPresent()) {
            optionalShare.get().incrementAmount(sharesPurchaseAmount);
        } else {
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

        //WALUTĘ UJĄĆ!!!
        //
        //!!!

        //for(Currency elem : currencies){
        //    if(materialCurrency.getName().equals(elem.getName()))
        //}

        double materialPrice = material.getCurrentValue();
        double minPriceSoFar = material.getMinValue();
        double margin = materialMarket.getMargin();
        double currencyValue = 0;
        Currency materialCurrency = null;

        //changing the budget
        budget += materialPrice * materialAmount; // x kursSprzedażyWaluty;
        budget -= materialPrice * materialAmount * margin; // x kursSprzedażyWaluty

        //decrementing amount of material in wallet
        materialsPurchased.get(materialNumber).decrementAmount(materialAmount);

        //decrementing price of material
        material.decrementCurrentValue();
    }

    private void sellCurrencies() {
    }

    /**
     * buying assets with investment fund
     */
    private void buyWithInvFuM() {

    }

    /**
     * selling assets with investment fund
     */
    private void sellWithInfFuM() {

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
        return firstName + lastName;
    }
}
