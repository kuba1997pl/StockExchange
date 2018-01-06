
package StockExchange.model;

import StockExchange.ui.DisplayableListItem;

import java.util.Random;

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

    public static String[] UNITS = {
            "baryłka",
            "uncja",
            "tona",
            "funt",
            "galon",
            "kilogram",
            "korzec"
    };

    public MaterialModel(){
        Random generator = new Random();
        double min = generator.nextDouble() * 12000;
        double max = min + generator.nextDouble() * 500;
        double akt = (max + min) / 2 + generator.nextDouble();
        this.maxValue = max;
        this.minValue =min;
        this.currentValue = akt;

        //int rozm = Main.currencyExchangeModel.getCurrencyList().size();
        //int elem = generator.nextInt(rozm); //generating random index
        //this.currencyModel = Main.currencyExchangeModel.getCurrencyList().get(elem);//Setting CurrencyModel value

        this.tradeUnit = UNITS[generator.nextInt(UNITS.length)];
        System.out.println(this.tradeUnit +"\n"+this.currentValue +"\n"+this.minValue +"\n"+this.maxValue);
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

    @Override
    public String getDisplayName() {
        return null;
    }
}
