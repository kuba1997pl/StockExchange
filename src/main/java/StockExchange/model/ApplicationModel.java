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


    public ObservableList<StockMarket> getStockMarkets() {
        return stockMarkets;
    }

    public ObservableList<Index> getIndexListModels() {
        return indexListModels;
    }

    public ObservableList<Material> getMaterialListModels() {
        return materialListModels;
    }

    public ObservableList<Currency> getCurrencyListModels() {
        return currencyListModels;
    }

    public ObservableList<Company> getCompanyListModels() {
        return companyListModels;
    }

    public ObservableList<CurrencyMarket> getCurrencyMarketListModels() {
        return currencyMarketListModels;
    }

    public ObservableList<MaterialMarket> getMaterialMarketListModels() {
        return materialMarketListModels;
    }
}
