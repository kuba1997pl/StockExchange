package StockExchange.controller;

import StockExchange.model.StockExchangeModel;
import StockExchange.ui.CustomListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {

    @FXML
    private CustomListView<StockExchangeModel> stockExchangeListView;
    @FXML
    private CustomListView<StockExchangeModel> indexListView;
    @FXML
    private CustomListView<StockExchangeModel> materialListView;
    @FXML
    private CustomListView<StockExchangeModel> currencyListView;
    @FXML
    private CustomListView<StockExchangeModel> companyListView;
    @FXML
    private CustomListView<StockExchangeModel> currencyExchangeListView;
    @FXML
    private CustomListView<StockExchangeModel> materialExchangeListView;


    private ObservableList<StockExchangeModel> stockExchangeModels;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stockExchangeListView.setClickListener(() -> stockExchangeModels.add(new StockExchangeModel("chujas")));
        stockExchangeModels = FXCollections.observableArrayList();
        stockExchangeListView.setItemList(stockExchangeModels);
    }

    private void initializeModels() {

    }
}
