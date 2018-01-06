package StockExchange.controller;

import StockExchange.model.StockExchangeModel;
import StockExchange.ui.StockExchangeListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class StockExchangeListViewController implements Initializable {

    private ObservableList<StockExchangeModel> stocksList;

    @FXML
    ListView<StockExchangeModel> listView;

    @FXML
    Button addStockButton;

    @FXML
    private void onAddStockButtonClick(ActionEvent event) {
        stocksList.add(new StockExchangeModel());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stocksList = FXCollections.observableArrayList();
        listView.setItems(stocksList);
        listView.setCellFactory(param -> new StockExchangeListCell());
    }
}
