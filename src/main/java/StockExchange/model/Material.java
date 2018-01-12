
package StockExchange.model;

import StockExchange.ui.DisplayableListItem;
import StockExchange.util.RandomString;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.text.DecimalFormat;
import java.util.*;

/**
 *
 * @author jakub
 */
public class Material extends Assets implements DisplayableListItem  {
    private Currency currency;
    private String tradeUnit; //jednostka handlowa
    private double currentValue;
    private double minValue;
    private double maxValue;

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
        this.minValue = generator.nextDouble() * 12000;
        this.maxValue = minValue + generator.nextDouble() * 500;
        this.currentValue = minValue;
        this.currency = cModel.get(generator.nextInt(cModel.size()));
        this.tradeUnit = UNITS[generator.nextInt(UNITS.length)];
    }

    public double buyMaterial(double amount) {
        double price = amount * currentValue;
        currentValue *= 1.01;
        if(currentValue > maxValue) {
            maxValue = currentValue;
        }
        return price;
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


}
