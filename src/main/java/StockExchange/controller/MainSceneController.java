package StockExchange.controller;

import StockExchange.bindings.ListChangeBooleanBinding;
import StockExchange.model.*;
import StockExchange.ui.CurrencyExchangeDialog;
import StockExchange.ui.CustomListView;
import StockExchange.ui.StockDialog;
import javafx.beans.binding.BooleanBinding;
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
    private CustomListView<StockMarket> stockExchangeListView;
    @FXML
    private CustomListView<Index> indexListView;
    @FXML
    private CustomListView<Material> materialListView;
    @FXML
    private CustomListView<Currency> currencyListView;
    @FXML
    private CustomListView<Company> companyListView;
    @FXML
    private CustomListView<CurrencyMarket> currencyExchangeListView;
    @FXML
    private CustomListView<MaterialMarket> materialExchangeListView;

    private ApplicationModel applicationModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        applicationModel = ApplicationModel.getInstance();
        setItemLists();
        initializeOnClickListeners();
        addButtonVisibilityModifiers();
    }

    private void addStockExchange() {
        if(isListEmpty(applicationModel.getCurrencyListModels()) || isListEmpty(applicationModel.getIndexListModels())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nie można utworzyć giełdy");
            alert.setContentText("Nie można utworzyć giełdy. Najpierw stwórz walutę, oraz index");
            alert.show();
        } else {
            Optional<StockMarket> newModel = new StockDialog(applicationModel.getCurrencyListModels(), applicationModel.getIndexListModels()).showAndWait();
            newModel.ifPresent(applicationModel.getStockMarkets()::add);
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
            dialog.showAndWait().ifPresent(name -> applicationModel.getIndexListModels().add(new Index(name, applicationModel.getCompanyListModels())));
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
        new CurrencyExchangeDialog(applicationModel.getCurrencyListModels()).showAndWait().ifPresent(applicationModel.getCurrencyMarketListModels()::add);
    }

    private void addMaterialExchange() {
        applicationModel.getMaterialMarketListModels().add(new MaterialMarket());
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
        stockExchangeListView.setItemList(applicationModel.getStockMarkets());
        indexListView.setItemList(applicationModel.getIndexListModels());
        materialListView.setItemList(applicationModel.getMaterialListModels());
        currencyListView.setItemList(applicationModel.getCurrencyListModels());
        companyListView.setItemList(applicationModel.getCompanyListModels());
        currencyExchangeListView.setItemList(applicationModel.getCurrencyMarketListModels());
        materialExchangeListView.setItemList(applicationModel.getMaterialMarketListModels());
    }

    private void addButtonVisibilityModifiers() {
        ObservableList<Currency> currencyListModels = applicationModel.getCurrencyListModels();
        ObservableList<Index> indexListModels = applicationModel.getIndexListModels();
        ObservableList<Company> companyListModels = applicationModel.getCompanyListModels();
        BooleanBinding stockListViewBinding = new ListChangeBooleanBinding(() -> isListEmpty(currencyListModels) || isListEmpty(indexListModels), indexListModels, currencyListModels);
        stockExchangeListView.setButtonDisabled(stockListViewBinding);
        BooleanBinding indexListViewBinding = new ListChangeBooleanBinding(() -> isListEmpty(companyListModels) , companyListModels);
        indexListView.setButtonDisabled(indexListViewBinding);
        BooleanBinding materialListViewBinding = new ListChangeBooleanBinding(() -> isListEmpty(currencyListModels), currencyListModels);
        materialListView.setButtonDisabled(materialListViewBinding);
    }

    private boolean isListEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }
}
