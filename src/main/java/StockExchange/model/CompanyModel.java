
package StockExchange.model;

import StockExchange.ui.DisplayableListItem;

import java.time.LocalDate;
import java.util.Random;

/**
 *
 * @author jakub
 */

public class CompanyModel implements DisplayableListItem {
    private String name;
    private LocalDate firstPricingDate;
    private double minPrice;
    private double maxPrice;
    private double currentPrice;
    private int sharesCount;
    private double profit; // dochód = przychód - koszta  POPRAW!
    private double income; //POPRAW!
    private double equityCapital; // kapitał własny POPRAW!
    private double shareCapital; // kapitał zakładowy  POPRAW!
    private int volume; //ilość akcji, które zmieniły właściciela- wolumen
    private double sales; //przychód - podatek, obroty POPRAW!

    @Override
    public String getDisplayName() {
        return this.name;
    }
    
    public CompanyModel(){
        Random generator = new Random();
        double min = generator.nextDouble() * 10;
        double max = min + generator.nextDouble() * 20;
        int akcyje = generator.nextInt(1000);
        this.minPrice = min;
        this.maxPrice = max;
        this.currentPrice = (min+max)/2;
        this.sharesCount = akcyje;

        //date generator
        int minDz = (int) LocalDate.of(1900, 1, 1).toEpochDay();
        int maxDz = (int) LocalDate.of(2015, 1, 1).toEpochDay();
        long losDz = minDz + generator.nextInt(maxDz - minDz);
        this.firstPricingDate = LocalDate.ofEpochDay(losDz);
        this.volume = generator.nextInt(1000);
        //System.out.println(this.firstPricingDate+"\n"+this.volume+"\n"+this.maxPrice+"\n"+this.minPrice);
    }



    /**
     *
     * @param currentPrice
     */

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    /**
     *
     * @return currentPrice
     */

    public double getCurrentPrice() {

        return currentPrice;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param dataIwyceny
     */
    public void setFirstPricingDate(LocalDate dataIwyceny) {
        this.firstPricingDate = firstPricingDate;
    }

    /**
     *
     * @param minPrice
     */
    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    /**
     *
     * @param maxPrice
     */
    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     *
     * @param sharesCount
     */
    public void setSharesCount(int sharesCount) {
        this.sharesCount = sharesCount;
    }

    /**
     *
     * @param profit
     */
    public void setProfit(int profit) {
        this.profit = profit;
    }

    /**
     *
     * @param income
     */
    public void setIncome(int income) {
        this.income = income;
    }

    /**
     *
     * @param equityCapital
     */
    public void setEquityCapital(int equityCapital) {
        this.equityCapital = equityCapital;
    }

    /**
     *
     * @param shareCapital
     */
    public void setShareCapital(int shareCapital) {
        this.shareCapital = shareCapital;
    }

    /**
     *
     * @param volume
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     *
     * @param sales
     */
    public void setSales(int sales) {
        this.sales = sales;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return dataIwyceny
     */
    public LocalDate getFirstPricingDate() {
        return firstPricingDate;
    }

    /**
     *
     * @return minPrice
     */
    public double getMinPrice() {
        return minPrice;
    }

    /**
     * 
     * @return maxPrice
     */
    public double getMaxPrice() {
        return maxPrice;
    }

    /**
     *
     * @return sharesCount
     */
    public int getSharesCount() {
        return sharesCount;
    }

    /**
     *
     * @return profit
     */
    public double getProfit() {
        return profit;
    }

    /**
     *
     * @return income
     */
    public double getIncome() {
        return income;
    }

    /**
     *
     * @return equityCapital
     */
    public double getEquityCapital() {
        return equityCapital;
    }

    /**
     *
     * @return shareCapital
     */
    public double getShareCapital() {
        return shareCapital;
    }

    /**
     *
     * @return volume
     */
    public int getVolume() {
        return volume;
    }

    /**
     *
     * @return sales
     */
    public double getSales() {
        return sales;
    }
}
