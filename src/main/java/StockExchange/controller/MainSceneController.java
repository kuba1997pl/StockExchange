package StockExchange.controller;

import StockExchange.model.*;
import StockExchange.ui.CustomListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Currency;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stockExchangeListView.setClickListener(() -> stockExchangeModels.add(new StockExchangeModel()));
        stockExchangeModels = FXCollections.observableArrayList();
        stockExchangeListView.setItemList(stockExchangeModels);
    }

    private void initializeModels() {

    }
}
