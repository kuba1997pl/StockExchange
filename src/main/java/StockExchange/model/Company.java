
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
import java.util.List;
import java.util.Random;

import StockExchange.util.RandomString;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
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

    private long firstMeasurementTime = 0;
    private ObservableList<XYChart.Series<Number, Number>> chartData;
    private ObservableList<XYChart.Data<Number, Number>> currentPriceSeries;

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(name);
        out.writeObject(firstPricingDate);
        out.writeDouble(minPrice);
        out.writeDouble(maxPrice);
        out.writeDouble(currentPrice);
        out.writeDouble(openingPrice);
        out.writeInt(sharesCount);
        out.writeDouble(profit);
        out.writeDouble(income);
        out.writeDouble(equityCapital);
        out.writeDouble(shareCapital);
        out.writeInt(volume);
        out.writeDouble(sales);
    }

    @SuppressWarnings("unchecked")
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        firstPricingDate = (LocalDate) in.readObject();
        minPrice = in.readDouble();
        maxPrice = in.readDouble();
        currentPrice = in.readDouble();
        openingPrice = in.readDouble();
        sharesCount = in.readInt();
        profit = in.readDouble();
        income = in.readDouble();
        equityCapital = in.readDouble();
        shareCapital = in.readDouble();
        volume = in.readInt();
        sales = in.readDouble();
    }

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
        Random generator = new Random();
        if (namesList.size() > 0) {
            name = namesList.get(generator.nextInt(namesList.size()));
            namesList.remove(name);
        } else {
            name = RandomString.nextString(10);
        }
        sharesCount = generator.nextInt(1000);
        minPrice = generator.nextDouble() * 10;
        maxPrice = minPrice + generator.nextDouble() * 20;
        openingPrice = (minPrice + maxPrice) / 2;
        currentPrice = this.openingPrice;
        shareCapital = generator.nextDouble() * 1000000;
        equityCapital = shareCapital + generator.nextDouble() * 1000000;
        profit = this.equityCapital * 100;
        income = this.profit * 12;
        sales = generator.nextDouble() * 1000000;

        //date generator
        int minDay = (int) LocalDate.of(1900, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2015, 1, 1).toEpochDay();
        long losDz = minDay + generator.nextInt(maxDay - minDay);
        this.firstPricingDate = LocalDate.ofEpochDay(losDz);
        this.volume = generator.nextInt(1000);
        chartData = FXCollections.observableArrayList();
        currentPriceSeries = FXCollections.observableArrayList();
        chartData.add(new XYChart.Series<>("Aktualna cena", currentPriceSeries));
        startDataMiner();
        ApplicationExecutor.getInstance().getBackgroundThreadPool().execute(this::companyOperations);
    }

    private void startDataMiner() {
        ApplicationExecutor.getInstance().getBackgroundThreadPool().execute(() -> {
            while(!Thread.currentThread().isInterrupted()) {
                try {
                    if(firstMeasurementTime == 0) {
                        firstMeasurementTime = System.currentTimeMillis()/1000;
                    }
                    long currentTime = (System.currentTimeMillis()/1000) - firstMeasurementTime;
                    Platform.runLater(() -> currentPriceSeries.add(new XYChart.Data<>(currentTime, currentPrice)));
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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

    private synchronized void companyOperations(){
        while (!Thread.currentThread().isInterrupted()) {
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
        sharesCount += byHowMuch;
    }
    public void incrementCurrentPrice(){
        currentPrice = currentPrice*1.01;
        if(currentPrice > maxPrice)
            maxPrice = currentPrice;
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

    /**
     *
     * @return chartData
     */
    public ObservableList<XYChart.Series<Number, Number>> getChartData() {
        return chartData;
    }
}
