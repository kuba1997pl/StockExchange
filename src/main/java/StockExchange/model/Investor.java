
package StockExchange.model;

import StockExchange.ApplicationExecutor;
import javafx.collections.ObservableList;

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
                //indeks gieldy
                ObservableList<StockMarket> sMarkets = ApplicationModel.getInstance().getStockMarkets();
                int stockNo = generator.nextInt(sMarkets.size()); //random stock market index
                int indexNo = generator.nextInt(sMarkets.get(stockNo).getIndexList().size()); //random market index index
                int comNo = generator.nextInt(sMarkets.get(stockNo).getIndexList().get(indexNo).getCompaniesList().size()); //random company from the index index
                int sharesNo = sMarkets.get(stockNo).getIndexList().get(indexNo).getCompaniesList().get(comNo).getSharesCount(); //shares amount
                if (sharesNo > 0) {
                    double sharesPurPr = sMarkets.get(stockNo).getIndexList().get(indexNo).getCompaniesList().get(comNo).getCurrentPrice();
                    int sharesPurNo;
                    //random shares number;
                    if (budget > (sharesNo * sharesPurPr))
                        sharesPurNo = generator.nextInt(sharesNo);
                    else
                        //number of shares times their price must be less than budget
                        sharesPurNo = generator.nextInt((int) (budget / sharesPurPr));
                    //decreasing number of shares amount
                    sMarkets.get(stockNo).getIndexList().get(indexNo).getCompaniesList().get(comNo).setSharesCount(sharesNo - sharesPurNo);
                    // decreasing budget with also paying a margin
                    budget -= (double) sharesPurNo * sharesPurPr;
                    budget -= sMarkets.get(stockNo).getMargin() * (double) sharesPurNo * sharesPurPr;
//                    buying shares makes their price higher - setting new price
                    double priceChanged = 1.01 * sharesPurPr;
                    sMarkets.get(stockNo).getIndexList().get(indexNo).getCompaniesList().get(comNo).setCurrentPrice(priceChanged);
//                    checking if the new price isn't higher than maximum so far
                    if (priceChanged > sMarkets.get(stockNo).getIndexList().get(indexNo).getCompaniesList().get(comNo).getMaxPrice())
                        sMarkets.get(stockNo).getIndexList().get(indexNo).getCompaniesList().get(comNo).setMaxPrice(priceChanged);
                    //updating value of main list
                    ApplicationModel.getInstance().setStockMarkets(sMarkets);

                    //checking if some company has already appeared on the list
                    //and adding an element
                    boolean checker = true;
                    String stockName = sMarkets.get(stockNo).getName();
                    String companyName = sMarkets.get(stockNo).getIndexList().get(indexNo).getCompaniesList().get(comNo).getName();
                    for (ShareInWallet share : sharesPurchased) {
                        if (share.getName() == companyName) {
                            checker = false;
                            //increasing number of already existing at list company
                            share.setAmount(share.getAmount() + sharesPurNo);
                            break;
                        }
                    }
                    if (checker) {
                        ShareInWallet newShare = new ShareInWallet(companyName, sharesPurNo);
                        newShare.setStockName(stockName);
                        sharesPurchased.add(newShare);
                    }
                }

            case 1:
                ObservableList<MaterialMarket> mMarkets = ApplicationModel.getInstance().getMaterialMarketListModels();
                int mMarketNo = generator.nextInt(mMarkets.size()); //random material market index
                int mMaterialNo = generator.nextInt(mMarkets.get(mMarketNo).getMaterialList().size());
                double materialCurVal = mMarkets.get(mMarketNo).getMaterialList().get(mMaterialNo).getCurrentValue();

                double materialAmount = generator.nextDouble() * (budget / materialCurVal);//number of material units times their price must be less than budget;

                // decreasing budget
                budget -= (double) materialCurVal * materialAmount;
                budget -= mMarkets.get(mMarketNo).getMargin() * materialCurVal * (double) materialAmount;
//              buying materials makes their price higher - setting new price
                double priceChanged2 = 1.01 * materialCurVal;
                mMarkets.get(mMarketNo).getMaterialList().get(mMaterialNo).setCurrentValue(priceChanged2);
//              checking if the new price isn't higher than maximum so far
                if (priceChanged2 > mMarkets.get(mMarketNo).getMaterialList().get(mMaterialNo).getMaxValue())
                    mMarkets.get(mMarketNo).getMaterialList().get(mMaterialNo).setMaxValue(priceChanged2);
                //updating value of main list
                ApplicationModel.getInstance().setMaterialMarketListModels(mMarkets);
                //checking if the material has already appeared on the list
                //and adding an element
                boolean checker = true;
                String materialName = mMarkets.get(mMarketNo).getMaterialList().get(mMaterialNo).getName();
                for (MaterialInWallet material : materialsPurchased) {
                    if (material.getName() == materialName) {
                        checker = false;
                        //increasing number of already existing at list company
                        material.setAmount(material.getAmount() + materialAmount);
                        break;
                    }
                }
                if (checker) {
                    MaterialInWallet newMaterial = new MaterialInWallet();
                    newMaterial.setName(materialName);
                    materialsPurchased.add(newMaterial);
                }

            default:
                //OGARNĄĆ POTEM!!!
                ;
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
                ;
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
