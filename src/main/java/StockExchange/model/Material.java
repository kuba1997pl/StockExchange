
package StockExchange.model;

import StockExchange.ui.DisplayableListItem;
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
        name = materials.get(generator.nextInt(materials.size()));
        materials.remove(name);
        double min = generator.nextDouble() * 12000;
        double max = min + generator.nextDouble() * 500;
        double akt = (max + min) / 2 + generator.nextDouble();
        DecimalFormat two = new DecimalFormat("#0.00");
        this.maxValue = Double.parseDouble(two.format(max).replace(",", "."));
        this.minValue =Double.parseDouble(two.format(min).replace(",", "."));
        this.currentValue = Double.parseDouble(two.format(Double.parseDouble(two.format(min).replace(",", "."))).replace(",", "."));
        this.currency = cModel.get(generator.nextInt(cModel.size()));
        this.tradeUnit = UNITS[generator.nextInt(UNITS.length)];
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
