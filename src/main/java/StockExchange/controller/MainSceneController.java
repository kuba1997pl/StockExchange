package StockExchange.controller;

import StockExchange.bindings.ListChangeBooleanBinding;
import StockExchange.managers.IndexesManager;
import StockExchange.managers.InvestorsManager;
import StockExchange.model.*;
import StockExchange.ui.*;
import StockExchange.ui.previewDialogs.*;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private CustomListView<MaterialMarket> materialExchangeListView;
    @FXML
    private CustomListView<Investor> investorsPreviewList;
    @FXML
    private CustomListView<InvestmentFund> fundsPreviewList;

    private ApplicationModel applicationModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initialize();
    }

    private void initialize() {
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

    private void addMaterialExchange() {
        applicationModel.getMaterialMarkets().add(new MaterialMarket());
    }

    private void initializeOnClickListeners() {
        stockExchangeListView.setOnClickListener(this::addStockExchange);
        indexListView.setOnClickListener(this::addIndex);
        materialListView.setOnClickListener(this::addMaterial);
        currencyListView.setOnClickListener(this::addCurrency);
        companyListView.setOnClickListener(this::addCompany);
        materialExchangeListView.setOnClickListener(this::addMaterialExchange);
        stockExchangeListView.addOnDoubleClickItemListener(stockMarket -> new StockMarketPreviewDialog(stockMarket).show());
        currencyListView.addOnDoubleClickItemListener(currency -> new CurrencyPreviewDialog(currency).show());
        materialListView.addOnDoubleClickItemListener(material -> new MaterialPreviewDialog(material).show());
        companyListView.addOnDoubleClickItemListener(company -> new CompanyPreviewDialog(company).show());
        indexListView.addOnDoubleClickItemListener(index -> new IndexPreviewDialog(index).show());
        investorsPreviewList.addOnDoubleClickItemListener(investor -> new InvestorPreviewDialog(investor).show());
        fundsPreviewList.addOnDoubleClickItemListener(investmentFund -> new InvestmentFundPreviewDialog(investmentFund).show());
        materialExchangeListView.addOnDoubleClickItemListener(materialMarket -> new MaterialMarketPreviewDialog(materialMarket).show());
    }

    @FXML
    private void saveState(ActionEvent event) {
        try {
            FileOutputStream fileOut = new FileOutputStream("state.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(ApplicationModel.getInstance());
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    @FXML
    private void loadState(ActionEvent event) {
        try {
            Path state = Paths.get("state.ser");
            InputStream in = Files.newInputStream(state);
            ObjectInputStream ois = new ObjectInputStream(in);
            ApplicationModel.setInstance((ApplicationModel) ois.readObject());
            initialize();
        } catch (NoSuchFileException e) {
            Alert noSuchFileAlert = new Alert(Alert.AlertType.ERROR);
            noSuchFileAlert.setTitle("Nie ma z czego załadować");
            noSuchFileAlert.setContentText("Przed załadowaniem stanu aplikacji proszę go najpierw zapisać");
            noSuchFileAlert.show();
        } catch(ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private void setItemLists() {
        stockExchangeListView.setItemList(applicationModel.getStockMarkets());
        indexListView.setItemList(applicationModel.getIndexes());
        materialListView.setItemList(applicationModel.getMaterials());
        currencyListView.setItemList(applicationModel.getCurrencies());
        companyListView.setItemList(applicationModel.getCompanies());
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
    }

    private boolean isListEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }
}
