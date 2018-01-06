package StockExchange.controller;

import StockExchange.model.StockIndex;
import StockExchange.ui.IndexesListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class IndexesListViewController implements Initializable {

    private ObservableList<StockIndex> indexesList;

    @FXML
    ListView<StockIndex> listView;

    @FXML
    private void onAddIndexButtonClick(ActionEvent event) {
        indexesList.add(new StockIndex());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        indexesList = FXCollections.observableArrayList();
        listView.setItems(indexesList);
        listView.setCellFactory(param -> new IndexesListCell());
    }
}
