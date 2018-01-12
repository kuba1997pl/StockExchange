
package StockExchange.model;

import StockExchange.ApplicationExecutor;
import StockExchange.ui.DisplayableListItem;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import StockExchange.util.RandomString;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;

import static java.lang.Thread.sleep;

/**
 * @author jakub
 */

public class Company implements DisplayableListItem, Serializable {
    private String name;
    private LocalDate firstPricingDate;
    private double minPrice;
    private double maxPrice;
    private double currentPrice;
    private double openingPrice;
    private int sharesCount;
    private double profit; // dochód = przychód - koszta
    private double income;
    private double equityCapital; // kapitał własny
    private double shareCapital; // kapitał zakładowy
    private int volume; //ilość akcji, które zmieniły właściciela- wolumen
    private double sales; //przychód - podatek, obroty POPRAW!

    private static ArrayList<String> namesList = new ArrayList<>();

    static {
        InputStream inputStream = Company.class.getResourceAsStream("/StockExchange/dataSamples/companiesSample.json");
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

    @Override
    public String getDisplayName() {
        return this.name;
    }

    public Company() {
        DecimalFormat two = new DecimalFormat("#0.00");
        //String strForm = two.format(number);
        //number = Double.parseDouble(strForm.replace(",", "."));
        Random generator = new Random();
        if (namesList.size() > 0) {
            name = namesList.get(generator.nextInt(namesList.size()));
            namesList.remove(name);
        } else {
            name = RandomString.nextString(10);
        }
        double min = generator.nextDouble() * 10;
        double max = min + generator.nextDouble() * 20;
        this.sharesCount = generator.nextInt(1000);
        this.minPrice = Double.parseDouble(two.format(min).replace(",", "."));
        this.maxPrice = Double.parseDouble(two.format(max).replace(",", "."));
        this.openingPrice = Double.parseDouble(two.format((min + max) / 2.0).replace(",", "."));
        this.currentPrice = this.openingPrice;
        this.shareCapital = Double.parseDouble(two.format(generator.nextDouble() * 1000000).replace(",", "."));
        this.equityCapital = Double.parseDouble(two.format(shareCapital + generator.nextDouble() * 1000000).replace(",", "."));
        this.profit = this.equityCapital * 100;
        this.income = this.profit * 12;
        this.sales = Double.parseDouble(two.format(generator.nextDouble() * 1000000).replace(",", "."));

        //date generator
        int minDay = (int) LocalDate.of(1900, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2015, 1, 1).toEpochDay();
        long losDz = minDay + generator.nextInt(maxDay - minDay);
        this.firstPricingDate = LocalDate.ofEpochDay(losDz);
        this.volume = generator.nextInt(1000);
        //System.out.println(this.firstPricingDate+"\n"+this.volume+"\n"+this.maxPrice+"\n"+this.minPrice);
    }

    /**
     * @param amount amount of shares to buy
     * @return price of bought shares
     */

    public double buyShares(int amount) {
        if (sharesCount >= amount) {
            sharesCount -= amount;
            double price = amount * currentPrice;
            sales += price; //income
            currentPrice *= 1.01;
            if (currentPrice > maxPrice)
                maxPrice = currentPrice;
            volume += amount;
            return price;
        }
        return -1;
    }

    public synchronized void companyOperations(){
        ApplicationExecutor.getInstance().getBackgroundThreadPool().execute(() -> {
            while (true) {
                Random generator = new Random();
                int cases = generator.nextInt(2);
                switch (cases) {
                    case 0:
                        increaseProfit();
                    default:
                        releaseShares();
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

    private void releaseShares() {
        Random generator = new Random();
        sharesCount += generator.nextInt(100);
    }

    //Profit is dependent on income
    //it increases the same time

    public void increaseProfit() {
        profit += increaseIncome() * 0.75;
    }

    private double increaseIncome() {
        Random generator = new Random();
        double incomeGrowth = generator.nextDouble() * 100000;
        income += incomeGrowth;
        return incomeGrowth;
    }

    private void buyAllYourShares(){

    }

    public void incrementSharesCount(int byHowMuch){
        sharesCount+=byHowMuch;
    }
    public void decrementCurrentPrice(){
        currentPrice = currentPrice*0.99;
        if(currentPrice < minPrice)
            minPrice = currentPrice;
    }
    public void changeVolume(int byHowMuch){
        volume += byHowMuch;
    }
    public void increaseSales(double byHowMuch){
        sales += byHowMuch;
    }

    /**
     * @param currentPrice
     */

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    /**
     * @return currentPrice
     */

    public double getCurrentPrice() {
        return currentPrice;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param dataIwyceny
     */
    public void setFirstPricingDate(LocalDate dataIwyceny) {
        this.firstPricingDate = firstPricingDate;
    }

    /**
     * @param minPrice
     */
    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * @param maxPrice
     */
    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }


    /**
     * @param openingPrice
     */
    public void setOpeningPrice(double openingPrice) {
        this.openingPrice = openingPrice;
    }

    /**
     * @param sharesCount
     */
    public void setSharesCount(int sharesCount) {
        this.sharesCount = sharesCount;
    }

    /**
     * @param profit
     */
    public void setProfit(int profit) {
        this.profit = profit;
    }

    /**
     * @param income
     */
    public void setIncome(int income) {
        this.income = income;
    }

    /**
     * @param equityCapital
     */
    public void setEquityCapital(int equityCapital) {
        this.equityCapital = equityCapital;
    }

    /**
     * @param shareCapital
     */
    public void setShareCapital(int shareCapital) {
        this.shareCapital = shareCapital;
    }

    /**
     * @param volume
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * @param sales
     */
    public void setSales(int sales) {
        this.sales = sales;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @return dataIwyceny
     */
    public LocalDate getFirstPricingDate() {
        return firstPricingDate;
    }

    /**
     * @return minPrice
     */
    public double getMinPrice() {
        return minPrice;
    }

    /**
     * @return maxPrice
     */
    public double getMaxPrice() {
        return maxPrice;
    }


    /**
     * @return openingPrice
     */
    public double getOpeningPrice() {

        return openingPrice;
    }

    /**
     * @return sharesCount
     */
    public int getSharesCount() {
        return sharesCount;
    }

    /**
     * @return profit
     */
    public double getProfit() {
        return profit;
    }

    /**
     * @return income
     */
    public double getIncome() {
        return income;
    }

    /**
     * @return equityCapital
     */
    public double getEquityCapital() {
        return equityCapital;
    }

    /**
     * @return shareCapital
     */
    public double getShareCapital() {
        return shareCapital;
    }

    /**
     * @return volume
     */
    public int getVolume() {
        return volume;
    }

    /**
     * @return sales
     */
    public double getSales() {
        return sales;
    }
}
