package StockExchange.controller;

import StockExchange.bindings.ListChangeBooleanBinding;
import StockExchange.managers.IndexesManager;
import StockExchange.managers.InvestorsManager;
import StockExchange.model.*;
import StockExchange.ui.*;
import StockExchange.ui.previewDialogs.*;
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
    @FXML
    private CustomListView<Investor> investorsPreviewList;
    @FXML
    private CustomListView<InvestmentFund> fundsPreviewList;

    private ApplicationModel applicationModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        applicationModel = ApplicationModel.getInstance();
        setItemLists();
        initializeOnClickListeners();
        addButtonVisibilityModifiers();
        InvestorsManager.start();
        IndexesManager.start();
    }

    private void addStockExchange() {
        if(isListEmpty(applicationModel.getCurrencies()) || isListEmpty(applicationModel.getIndexes())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nie można utworzyć giełdy");
            alert.setContentText("Nie można utworzyć giełdy. Najpierw stwórz walutę, oraz index");
            alert.show();
        } else {
            Optional<StockMarket> newModel = new StockDialog(applicationModel.getCurrencies(), applicationModel.getIndexes()).showAndWait();
            newModel.ifPresent(applicationModel.getStockMarkets()::add);
        }
    }

    private void addIndex() {
        if(isListEmpty(applicationModel.getCompanies())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nie można utworzyć indeksu");
            alert.setContentText("Nie można utworzyć indeksu. Najpierw stwórz spółkę");
            alert.show();
        } else {
            TextInputDialog dialog = new TextInputDialog();
            dialog.showAndWait().ifPresent(name -> applicationModel.getIndexes().add(new Index(name, applicationModel.getCompanies())));
        }
    }

    private void addMaterial() {
        if(isListEmpty(applicationModel.getCurrencies())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nie można utworzyć surowca");
            alert.setContentText("Nie można utworzyć surowca. Najpierw stwórz walutę");
            alert.show();
        } else {
            applicationModel.getMaterials().add(new Material(applicationModel.getCurrencies()));
        }
    }

    private void addCurrency() {
        applicationModel.getCurrencies().add(new Currency());
    }

    private void addCompany() {
        applicationModel.getCompanies().add(new Company());
    }

    private void addCurrencyExchange() {
        new CurrencyExchangeDialog(applicationModel.getCurrencies()).showAndWait().ifPresent(applicationModel.getCurrencyMarkets()::add);
    }

    private void addMaterialExchange() {
        applicationModel.getMaterialMarkets().add(new MaterialMarket());
    }

    private void initializeOnClickListeners() {
        stockExchangeListView.setOnClickListener(this::addStockExchange);
        indexListView.setOnClickListener(this::addIndex);
        materialListView.setOnClickListener(this::addMaterial);
        currencyListView.setOnClickListener(this::addCurrency);
        companyListView.setOnClickListener(this::addCompany);
        currencyExchangeListView.setOnClickListener(this::addCurrencyExchange);
        materialExchangeListView.setOnClickListener(this::addMaterialExchange);
        stockExchangeListView.addOnDoubleClickItemListener(stockMarket -> new StockMarketPreviewDialog(stockMarket).show());
        currencyListView.addOnDoubleClickItemListener(currency -> new CurrencyPreviewDialog(currency).show());
        materialListView.addOnDoubleClickItemListener(material -> new MaterialPreviewDialog(material).show());
        companyListView.addOnDoubleClickItemListener(company -> new CompanyPreviewDialog(company).show());
        indexListView.addOnDoubleClickItemListener(index -> new IndexPreviewDialog(index).show());
        investorsPreviewList.addOnDoubleClickItemListener(investor -> new InvestorPreviewDialog(investor).show());
        fundsPreviewList.addOnDoubleClickItemListener(investmentFund -> new InvestmentFundPreviewDialog(investmentFund).show());
    }

    private void setItemLists() {
        stockExchangeListView.setItemList(applicationModel.getStockMarkets());
        indexListView.setItemList(applicationModel.getIndexes());
        materialListView.setItemList(applicationModel.getMaterials());
        currencyListView.setItemList(applicationModel.getCurrencies());
        companyListView.setItemList(applicationModel.getCompanies());
        currencyExchangeListView.setItemList(applicationModel.getCurrencyMarkets());
        materialExchangeListView.setItemList(applicationModel.getMaterialMarkets());
        investorsPreviewList.setItemList(applicationModel.getInvestors());
        fundsPreviewList.setItemList(applicationModel.getInvestmentFunds());
    }

    private void addButtonVisibilityModifiers() {
        ObservableList<Currency> currencyListModels = applicationModel.getCurrencies();
        ObservableList<Index> indexListModels = applicationModel.getIndexes();
        ObservableList<Company> companyListModels = applicationModel.getCompanies();
        BooleanBinding stockListViewBinding = new ListChangeBooleanBinding(() -> isListEmpty(currencyListModels) || isListEmpty(indexListModels), indexListModels, currencyListModels);
        stockExchangeListView.setButtonDisabled(stockListViewBinding);
        BooleanBinding indexListViewBinding = new ListChangeBooleanBinding(() -> isListEmpty(companyListModels) , companyListModels);
        indexListView.setButtonDisabled(indexListViewBinding);
        BooleanBinding materialListViewBinding = new ListChangeBooleanBinding(() -> isListEmpty(currencyListModels), currencyListModels);
        materialListView.setButtonDisabled(materialListViewBinding);
        BooleanBinding currencyMarketListViewBinding = new ListChangeBooleanBinding(() -> isListEmpty(currencyListModels), currencyListModels);
        currencyExchangeListView.setButtonDisabled(currencyMarketListViewBinding);
    }

    private boolean isListEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }
}
