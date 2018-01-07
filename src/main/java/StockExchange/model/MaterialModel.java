
package StockExchange.model;

import StockExchange.controller.MainSceneController;
import StockExchange.ui.DisplayableListItem;
import javafx.collections.ObservableList;

import java.util.*;

/**
 *
 * @author jakub
 */
public class MaterialModel extends Assets implements DisplayableListItem  {
    private CurrencyModel currencyModel;
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

    public MaterialModel(ObservableList<CurrencyModel> cModel){
        Random generator = new Random();
        name = materials.get(generator.nextInt(materials.size()));
        materials.remove(name);
        double min = generator.nextDouble() * 12000;
        double max = min + generator.nextDouble() * 500;
        double akt = (max + min) / 2 + generator.nextDouble();
        this.maxValue = max;
        this.minValue =min;
        this.currentValue = akt;
        this.currencyModel = cModel.get(generator.nextInt(cModel.size()));
        this.tradeUnit = UNITS[generator.nextInt(UNITS.length)];
    }

    /**
     *
     * @param currencyModel
     */
    public void setCurrencyModel(CurrencyModel currencyModel) {
        this.currencyModel = currencyModel;
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
     * @return currencyModel
     */
    public CurrencyModel getCurrencyModel() {
        return currencyModel;
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
