
package StockExchange.model;

import StockExchange.ApplicationExecutor;
import StockExchange.ui.DisplayableListItem;
import StockExchange.util.RandomString;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.util.Pair;

import java.text.DecimalFormat;
import java.util.*;

/**
 *
 * @author jakub
 */
public class Material extends Assets implements DisplayableListItem  {
    private Currency currency;
    private String tradeUnit; //jednostka handlowa
    private double initialValue;
    private long firstMeasurementTime = 0;
    private double currentValue;
    private double minValue;
    private double maxValue;
    private ObservableList<XYChart.Series<Number, Number>> chartData;
    private ObservableList<XYChart.Data<Number, Number>> chartDataList;

    public static String[] MATERIALS = {
            "Kakao",
            "Herbata",
            "Kawa",
            "Ropa",
            "Złoto",
            "Platyna",
            "Pallad",
            "Wieprzowina",
            "Kauczuk",
            "Olej Opałowy",
            "Miedź",
            "Cynk",
            "Srebro",
            "Cukier",
            "Kukurydza",
            "Sok pomarańczowy",
            "Śruta sojowa"
    };

    public static String[] UNITS = {
            "baryłka",
            "uncja",
            "tona",
            "funt",
            "galon",
            "kilogram",
            "korzec"
    };

    private static ArrayList<String> materials = new ArrayList<>(Arrays.asList(MATERIALS));

    @Override
    public String getDisplayName() {
        return name;
    }

    public Material(ObservableList<Currency> cModel){
        Random generator = new Random();
        if (materials.size() > 0) {
            name = materials.get(generator.nextInt(materials.size()));
            materials.remove(name);
        } else {
            String str = RandomString.nextString(10);
            name = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        }
        minValue = generator.nextDouble() * 12000;
        maxValue = minValue + generator.nextDouble() * 500;
        currentValue = minValue;
        initialValue = currentValue;
        currency = cModel.get(generator.nextInt(cModel.size()));
        tradeUnit = UNITS[generator.nextInt(UNITS.length)];
        chartData = FXCollections.observableArrayList();
        chartDataList = FXCollections.observableArrayList();
        startDataMiner();
    }

    private void startDataMiner() {
        ApplicationExecutor.getInstance().getBackgroundThreadPool().execute(() -> {
            chartData.add(new XYChart.Series<>(name, chartDataList));
            while(!Thread.currentThread().isInterrupted()) {
                try {
                    if(firstMeasurementTime == 0) {
                        firstMeasurementTime = System.currentTimeMillis()/1000;
                    }
                    long currentTime = (System.currentTimeMillis()/1000) - firstMeasurementTime;
                    Platform.runLater(() -> chartDataList.add(new XYChart.Data<>(currentTime, currentValue)));
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public double buyMaterial(double amount) {
        double price = amount * currentValue;
        currentValue *= 1.01;
        if(currentValue > maxValue) {
            maxValue = currentValue;
        }
        return price;
    }

    public void incrementCurrentValue(){
        currentValue *= 1.01;
        if(currentValue>maxValue)
            maxValue = currentValue;
    }
    public void decrementCurrentValue(){
        currentValue *= 0.99;
        if (currentValue < minValue)
            minValue = currentValue;
    }
    /**
     *
     * @param currency
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     *
     * @param tradeUnit
     */
    public void setTradeUnit(String tradeUnit) {
        this.tradeUnit = tradeUnit;
    }

    /**
     *
     * @param currentValue
     */
    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    /**
     *
     * @param minValue
     */
    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    /**
     *
     * @param maxValue
     */
    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    /**
     *
     * @return currency
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     *
     * @return tradeUnit
     */
    public String getTradeUnit() {
        return tradeUnit;
    }

    /**
     * 
     * @return currentValue
     */
    public double getCurrentValue() {
        return currentValue;
    }

    /**
     *
     * @return minValue
     */
    public double getMinValue() {
        return minValue;
    }

    /**
     *
     * @return maxValue
     */
    public double getMaxValue() {
        return maxValue;
    }

    /**
     *
     * @return
     */
    public ObservableList<XYChart.Series<Number, Number>> getChartData() {
        return chartData;
    }
}
