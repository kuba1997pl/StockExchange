package StockExchange.model;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ApplicationModel implements Serializable {

    private ObservableList<StockMarket> stockMarkets;
    private ObservableList<Index> indexes;
    private ObservableList<Material> materials;
    private ObservableList<Currency> currencies;
    private ObservableList<Company> companies;
    private ObservableList<MaterialMarket> materialMarkets;
    private ObservableList<Investor> investors;
    private ObservableList<InvestmentFund> investmentFunds;
    private CurrencyMarket currencyMarket;

    private static ApplicationModel instance;

    private ApplicationModel() {
        initializeModels();
    }

    private void initializeModels() {
        stockMarkets = FXCollections.observableArrayList();
        indexes = FXCollections.observableArrayList();
        materials = FXCollections.observableArrayList();
        currencies = FXCollections.observableArrayList();
        companies = FXCollections.observableArrayList();
        currencyMarket = new CurrencyMarket();
        materialMarkets = FXCollections.observableArrayList();
        investors = FXCollections.observableArrayList();
        investmentFunds = FXCollections.observableArrayList();

        currencies.addListener(this::onCurrencyAdded);
    }

    private void onCurrencyAdded(ListChangeListener.Change<? extends Currency> c) {
        if(c.next()) {
            for (Currency currency : c.getAddedSubList()) {
                currencyMarket.addCurrency(currency);
            }
        }
    }

    public static void setInstance(ApplicationModel instance) {
        ApplicationModel.instance = instance;
    }

    public static ApplicationModel getInstance() {
        if (instance == null) {
            instance = new ApplicationModel();
        }
        return instance;
    }

    /**
     *
     * @return
     */
    public ObservableList<StockMarket> getStockMarkets() {
        return stockMarkets;
    }

    /**
     *
     * @return
     */
    public ObservableList<Index> getIndexes() {
        return indexes;
    }

    /**
     *
     * @return
     */
    public ObservableList<Material> getMaterials() {
        return materials;
    }

    public ObservableList<Currency> getCurrencies() {
        return currencies;
    }

    /**
     *
     * @return
     */
    public ObservableList<Company> getCompanies() {
        return companies;
    }

    /**
     *
     * @return
     */
    public ObservableList<MaterialMarket> getMaterialMarkets() { return materialMarkets; }

    /**
     *
     * @return
     */
    public ObservableList<Investor> getInvestors() {
        return investors;
    }

    /**
     *
     * @return
     */
    public ObservableList<InvestmentFund> getInvestmentFunds() {
        return investmentFunds;
    }

    /**
     *
     * @return
     */
    public CurrencyMarket getCurrencyMarket() {
        return currencyMarket;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(new ArrayList<>(stockMarkets));
        out.writeObject(new ArrayList<>(indexes));
        out.writeObject(new ArrayList<>(materials));
        out.writeObject(new ArrayList<>(currencies));
        out.writeObject(new ArrayList<>(companies));
        out.writeObject(new ArrayList<>(materialMarkets));
        out.writeObject(new ArrayList<>(investors));
        out.writeObject(new ArrayList<>(investmentFunds));
        out.writeObject(currencyMarket);
    }

    @SuppressWarnings("unchecked")
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        stockMarkets = FXCollections.observableArrayList((List<StockMarket>) in.readObject());
        indexes = FXCollections.observableArrayList((List<Index>) in.readObject());
        materials = FXCollections.observableArrayList((List<Material>) in.readObject());
        currencies = FXCollections.observableArrayList((List<Currency>) in.readObject());
        companies = FXCollections.observableArrayList((List<Company>) in.readObject());
        materialMarkets = FXCollections.observableArrayList((List<MaterialMarket>) in.readObject());
        investors = FXCollections.observableArrayList((List<Investor>) in.readObject());
        investmentFunds = FXCollections.observableArrayList((List<InvestmentFund>) in.readObject());
        currencyMarket = (CurrencyMarket) in.readObject();
    }
}
