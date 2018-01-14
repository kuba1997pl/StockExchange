
package StockExchange.model;

import StockExchange.ApplicationExecutor;
import StockExchange.ui.DisplayableListItem;
import StockExchange.util.RandomString;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jakub
 */
public class Currency extends Assets implements DisplayableListItem {
    private List<String> countriesList;


    private double purchasePrice;
    private double sellPrice;
    private ObservableList<XYChart.Series<Number, Number>> chartData;
    private ObservableList<XYChart.Data<Number, Number>> purchasePriceSeries;
    private ObservableList<XYChart.Data<Number, Number>> sellPriceSeries;
    private long firstMeasurementTime = 0;

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(countriesList);
        out.writeDouble(purchasePrice);
        out.writeDouble(sellPrice);
    }

    @SuppressWarnings("unchecked")
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        countriesList = (List<String>) in.readObject();
        purchasePrice = in.readDouble();
        sellPrice = in.readDouble();
    }

    private static ArrayList<String> CURRENCIES = new ArrayList<>();

    static {
        CURRENCIES.add("Dolar amerykański (USD)");
        CURRENCIES.add("Złoty (PLN)");
        CURRENCIES.add("Euro (EUR)");
        CURRENCIES.add("Rubel rosyjski (RUB)");
        CURRENCIES.add("Urugwajskie pesos (UYU)");
        CURRENCIES.add("Forint węgierski (HUF)");
        CURRENCIES.add("Funt szterling (GBP)");
        CURRENCIES.add("Korona czeska (CZK)");
        CURRENCIES.add("Real brazylijski (BRL)");
        CURRENCIES.add("Korona duńska (DKK)");
        CURRENCIES.add("Jen japoński (JPY)");
        CURRENCIES.add("Frank szwajcarski (CHF)");
        CURRENCIES.add("Rand, RPA (ZAR)");
    }

    @Override
    public String getDisplayName() {
        return name;
    }

    public Currency(){
        if(CURRENCIES.size() > 0) {
            this.name = CURRENCIES.get(new Random().nextInt(CURRENCIES.size()));
            CURRENCIES.remove(this.name);
        } else {
            this.name = RandomString.nextString(3, true);
        }
        Random generator = new Random();
        sellPrice = ( generator.nextDouble() + 1) * 2.5 + 0.1;
        purchasePrice = sellPrice * ( 1.1 + generator.nextDouble() * 0.5 );
        ArrayList<String> avalaibleCountries = new ArrayList<>(Arrays.asList(Countries.COUNTRIES));
        countriesList = new ArrayList<>();
        if(this.name.equals("Złoty (PLN)")) {
            countriesList.addAll(Arrays.asList(Countries.COUNTRIES));
        } else {
            int cList = generator.nextInt(4) + 1;
            for (int i = 0; i <= cList; i++) {
                int element = generator.nextInt(avalaibleCountries.size());
                this.countriesList.add(avalaibleCountries.get(element));
                avalaibleCountries.remove(element);
            }
        }
        chartData = FXCollections.observableArrayList();
        purchasePriceSeries = FXCollections.observableArrayList();
        sellPriceSeries = FXCollections.observableArrayList();
        chartData.add(new XYChart.Series<>("Cena kupna", purchasePriceSeries));
        chartData.add(new XYChart.Series<>("Cena sprzedaży", sellPriceSeries));
        startDataMiner();
    }

    private void startDataMiner() {
        ApplicationExecutor.getInstance().getBackgroundThreadPool().execute(() -> {
            while(!Thread.currentThread().isInterrupted()) {
                try {
                    if(firstMeasurementTime == 0) {
                        firstMeasurementTime = System.currentTimeMillis()/1000;
                    }
                    long currentTime = (System.currentTimeMillis()/1000) - firstMeasurementTime;
                    Platform.runLater(() -> {
                        purchasePriceSeries.add(new XYChart.Data<>(currentTime, purchasePrice));
                        sellPriceSeries.add(new XYChart.Data<>(currentTime, sellPrice));
                    });
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void decrementPrice(){
        purchasePrice = purchasePrice * 0.99;
        sellPrice = sellPrice * 0.99;
    }
    public void incrementPrice(){
        sellPrice*=1.02;
        purchasePrice*=1.02;
    }

    /**
     *
     * @return purchasePrice of currency
     */
    public double getPurchasePrice() {
        return purchasePrice;
    }

    /**
     *
     * @return sellPrice of currency
     */
    public double getSellPrice() {
        return sellPrice;
    }

    /**
     *
     * @param kraj
     */
    public void setCountriesList(String kraj) {
        this.countriesList.add(kraj);
    }

    /**
     *
     * @return countriesList
     */
    public List<String> getCountriesList() {
        return countriesList;
    }

    /**
     *
     * @return
     */
    public ObservableList<XYChart.Series<Number, Number>> getChartData() {
        return chartData;
    }
}
