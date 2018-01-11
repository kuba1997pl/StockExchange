package StockExchange.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ApplicationModel {

    private ObservableList<StockMarket> stockMarkets;
    private ObservableList<Index> indexListModels;
    private ObservableList<Material> materialListModels;
    private ObservableList<Currency> currencyListModels;
    private ObservableList<Company> companyListModels;
    private ObservableList<CurrencyMarket> currencyMarketListModels;
    private ObservableList<MaterialMarket> materialMarketListModels;
    private static ApplicationModel instance;


    private ApplicationModel() {
        initializeModels();
    }

    private void initializeModels() {
        stockMarkets = FXCollections.observableArrayList();
        indexListModels = FXCollections.observableArrayList();
        materialListModels = FXCollections.observableArrayList();
        currencyListModels = FXCollections.observableArrayList();
        companyListModels = FXCollections.observableArrayList();
        currencyMarketListModels = FXCollections.observableArrayList();
        materialMarketListModels = FXCollections.observableArrayList();
    }

    public static ApplicationModel getInstance() {
        if (instance == null) {
            instance = new ApplicationModel();
        }
        return instance;
    }

    /**
     *
     * @return
     */
    public ObservableList<StockMarket> getStockMarkets() {
        return stockMarkets;
    }

    /**
     *
     * @return
     */
    public ObservableList<Index> getIndexListModels() {
        return indexListModels;
    }

    /**
     *
     * @return
     */
    public ObservableList<Material> getMaterialListModels() {
        return materialListModels;
    }

    public ObservableList<Currency> getCurrencyListModels() {
        return currencyListModels;
    }

    /**
     *
     * @return
     */
    public ObservableList<Company> getCompanyListModels() {
        return companyListModels;
    }

    /**
     *
     * @return
     */
    public ObservableList<CurrencyMarket> getCurrencyMarketListModels() {
        return currencyMarketListModels;
    }

    /**
     *
     * @return
     */
    public ObservableList<MaterialMarket> getMaterialMarketListModels() { return materialMarketListModels; }

    /**
     *
     * @param stockMarkets
     */
    public void setStockMarkets(ObservableList<StockMarket> stockMarkets) {
        this.stockMarkets = stockMarkets;
    }

    /**
     *
     * @param indexListModels
     */
    public void setIndexListModels(ObservableList<Index> indexListModels) {
        this.indexListModels = indexListModels;
    }

    /**
     *
     * @param materialListModels
     */
    public void setMaterialListModels(ObservableList<Material> materialListModels) {
        this.materialListModels = materialListModels;
    }

    /**
     *
     * @param currencyListModels
     */
    public void setCurrencyListModels(ObservableList<Currency> currencyListModels) {
        this.currencyListModels = currencyListModels;
    }

    /**
     *
     * @param companyListModels
     */
    public void setCompanyListModels(ObservableList<Company> companyListModels) {
        this.companyListModels = companyListModels;
    }

    /**
     *
     * @param currencyMarketListModels
     */
    public void setCurrencyMarketListModels(ObservableList<CurrencyMarket> currencyMarketListModels) {
        this.currencyMarketListModels = currencyMarketListModels;
    }

    /**
     *
     * @param materialMarketListModels
     */
    public void setMaterialMarketListModels(ObservableList<MaterialMarket> materialMarketListModels) {
        this.materialMarketListModels = materialMarketListModels;
    }

    /**
     *
     * @param instance
     */
    public static void setInstance(ApplicationModel instance) {
        ApplicationModel.instance = instance;
    }
}
