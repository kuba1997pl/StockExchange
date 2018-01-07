package StockExchange.controller;

import StockExchange.model.*;
import StockExchange.ui.CustomListView;
import StockExchange.ui.StockDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {

    @FXML
    private CustomListView<StockExchangeModel> stockExchangeListView;
    @FXML
    private CustomListView<IndexModel> indexListView;
    @FXML
    private CustomListView<MaterialModel> materialListView;
    @FXML
    private CustomListView<CurrencyModel> currencyListView;
    @FXML
    private CustomListView<CompanyModel> companyListView;
    @FXML
    private CustomListView<CurrencyExchangeModel> currencyExchangeListView;
    @FXML
    private CustomListView<MaterialExchangeModel> materialExchangeListView;

    private ObservableList<StockExchangeModel> stockExchangeModels;
    private ObservableList<IndexModel> indexListModels;
    private ObservableList<MaterialModel> materialListModels;
    private ObservableList<CurrencyModel> currencyListModels;
    private ObservableList<CompanyModel> companyListModels;
    private ObservableList<CurrencyExchangeModel> currencyExchangeListModels;
    private ObservableList<MaterialExchangeModel> materialExchangeListModels;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeModels();
        setItemLists();
        initializeOnClickListeners();
    }

    private void addStockExchange() {
        Optional<StockExchangeModel> newModel = new StockDialog(currencyListModels, indexListModels).showAndWait();
        newModel.ifPresent(stockExchangeModels::add);
    }

    private void addIndex() {
        indexListModels.add(new IndexModel(companyListModels));
    }

    private void addMaterial() {
        materialListModels.add(new MaterialModel());
    }

    private void addCurrency() {
        currencyListModels.add(new CurrencyModel());
    }

    private void addCompany() {
        companyListModels.add(new CompanyModel());
    }

    private void addCurrencyExchange() {
        currencyExchangeListModels.add(new CurrencyExchangeModel());
    }

    private void addMaterialExchange() {
        materialExchangeListModels.add(new MaterialExchangeModel());
    }

    private void initializeOnClickListeners() {
        stockExchangeListView.setOnClickListener(this::addStockExchange);
        indexListView.setOnClickListener(this::addIndex);
        materialListView.setOnClickListener(this::addMaterial);
        currencyListView.setOnClickListener(this::addCurrency);
        companyListView.setOnClickListener(this::addCompany);
        currencyExchangeListView.setOnClickListener(this::addCurrencyExchange);
        materialExchangeListView.setOnClickListener(this::addMaterialExchange);
    }

    private void initializeModels() {
        stockExchangeModels = FXCollections.observableArrayList();
        indexListModels = FXCollections.observableArrayList();
        materialListModels = FXCollections.observableArrayList();
        currencyListModels = FXCollections.observableArrayList();
        companyListModels = FXCollections.observableArrayList();
        currencyExchangeListModels = FXCollections.observableArrayList();
        materialExchangeListModels = FXCollections.observableArrayList();
    }

    private void setItemLists() {
        stockExchangeListView.setItemList(stockExchangeModels);
        indexListView.setItemList(indexListModels);
        materialListView.setItemList(materialListModels);
        currencyListView.setItemList(currencyListModels);
        companyListView.setItemList(companyListModels);
        currencyExchangeListView.setItemList(currencyExchangeListModels);
        materialExchangeListView.setItemList(materialExchangeListModels);
    }
}
