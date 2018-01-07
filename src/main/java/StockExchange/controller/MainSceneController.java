package StockExchange.controller;

import StockExchange.model.*;
import StockExchange.ui.CustomListView;
import StockExchange.ui.StockDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {

    @FXML
    private CustomListView<StockExchange> stockExchangeListView;
    @FXML
    private CustomListView<Index> indexListView;
    @FXML
    private CustomListView<Material> materialListView;
    @FXML
    private CustomListView<Currency> currencyListView;
    @FXML
    private CustomListView<Company> companyListView;
    @FXML
    private CustomListView<CurrencyExchange> currencyExchangeListView;
    @FXML
    private CustomListView<MaterialExchange> materialExchangeListView;

    private ApplicationModel applicationModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        applicationModel = ApplicationModel.getInstance();
        setItemLists();
        initializeOnClickListeners();
    }

    private void addStockExchange() {
        if(isListEmpty(applicationModel.getCurrencyListModels()) || isListEmpty(applicationModel.getIndexListModels())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nie można utworzyć giełdy");
            alert.setContentText("Nie można utworzyć giełdy. Najpierw stwórz walutę, oraz index");
            alert.show();
        } else {
            Optional<StockExchange> newModel = new StockDialog(applicationModel.getCurrencyListModels(), applicationModel.getIndexListModels()).showAndWait();
            newModel.ifPresent(applicationModel.getStockExchanges()::add);
        }
    }

    private void addIndex() {
        if(isListEmpty(applicationModel.getCompanyListModels())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nie można utworzyć indeksu");
            alert.setContentText("Nie można utworzyć indeksu. Najpierw stwórz spółkę");
            alert.show();
        } else {
            TextInputDialog dialog = new TextInputDialog();
            dialog.showAndWait().ifPresent(name -> {
                applicationModel.getIndexListModels().add(new Index(name, applicationModel.getCompanyListModels()));
            });
        }
    }

    private void addMaterial() {
        if(isListEmpty(applicationModel.getCurrencyListModels())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nie można utworzyć surowca");
            alert.setContentText("Nie można utworzyć surowca. Najpierw stwórz walutę");
            alert.show();
        } else {
            applicationModel.getMaterialListModels().add(new Material(applicationModel.getCurrencyListModels()));
        }
    }

    private void addCurrency() {
        applicationModel.getCurrencyListModels().add(new Currency());
    }

    private void addCompany() {
        applicationModel.getCompanyListModels().add(new Company());
    }

    private void addCurrencyExchange() {
        applicationModel.getCurrencyExchangeListModels().add(new CurrencyExchange());
    }

    private void addMaterialExchange() {
        applicationModel.getMaterialExchangeListModels().add(new MaterialExchange());
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

    private void setItemLists() {
        stockExchangeListView.setItemList(applicationModel.getStockExchanges());
        indexListView.setItemList(applicationModel.getIndexListModels());
        materialListView.setItemList(applicationModel.getMaterialListModels());
        currencyListView.setItemList(applicationModel.getCurrencyListModels());
        companyListView.setItemList(applicationModel.getCompanyListModels());
        currencyExchangeListView.setItemList(applicationModel.getCurrencyExchangeListModels());
        materialExchangeListView.setItemList(applicationModel.getMaterialExchangeListModels());
    }

    private boolean isListEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }
}
