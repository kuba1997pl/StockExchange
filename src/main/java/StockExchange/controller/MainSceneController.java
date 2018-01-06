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
import java.util.function.Consumer;

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
        stockExchangeListView.setOnClickListener(() -> {
            Optional<StockExchangeModel> newModel = new StockDialog().showAndWait();
            newModel.ifPresent( stockExchangeModels::add);
        });
        indexListView.setOnClickListener(() -> {
            indexListModels.add(new IndexModel(companyListModels));
        });
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
