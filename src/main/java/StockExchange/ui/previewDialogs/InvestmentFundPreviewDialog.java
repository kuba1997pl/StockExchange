package StockExchange.ui.previewDialogs;

import StockExchange.model.*;
import StockExchange.ui.CustomListCell;
import javafx.collections.FXCollections;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class InvestmentFundPreviewDialog extends PreviewDialog<InvestmentFund> {

    private static ButtonType deleteButtonType = new ButtonType("Usuń spółkę", ButtonBar.ButtonData.OK_DONE);
    private InvestmentFund investmentFund;

    public InvestmentFundPreviewDialog(InvestmentFund investmendFund) {
        super(getLabels(investmendFund));
        this.investmentFund = investmendFund;
        setTitle("Szczegóły funduszu inwestycyjnego");
        GridPane pane = getContentGrid();
        ListView<CurrencyInWallet> currenciesPurchased = new ListView<>();
        currenciesPurchased.setItems(FXCollections.observableArrayList(investmendFund.getCurrenciesPurchased()));
        currenciesPurchased.setCellFactory(param -> new CustomListCell<>());
        int rowCount = pane.impl_getRowCount();
        pane.add(currenciesPurchased, 0, rowCount);
        ListView<MaterialInWallet> materialsPurchased = new ListView<>();
        materialsPurchased.setItems(FXCollections.observableArrayList(investmendFund.getMaterialsPurchased()));
        materialsPurchased.setCellFactory(param -> new CustomListCell<>());
        pane.add(materialsPurchased, 1, rowCount);
        ListView<ShareInWallet> sharesPurchased = new ListView<>();
        sharesPurchased.setItems(FXCollections.observableArrayList(investmendFund.getSharesPurchased()));
        sharesPurchased.setCellFactory(param -> new CustomListCell<>());
        pane.add(sharesPurchased, 2, rowCount);
        getDialogPane().getButtonTypes().add(deleteButtonType);
        setResultConverter(this::close);
    }

    private InvestmentFund close(ButtonType param) {
        if(param == deleteButtonType) {
            ApplicationModel.getInstance().getInvestmentFunds().remove(investmentFund);
        }
        return null;
    }

    private static List<Pair<String, String>> getLabels(InvestmentFund investmentFund) {
        List<Pair<String, String>> labels = new ArrayList<>();
        labels.add(new Pair<>("Nazwa:", investmentFund.getDisplayName()));
        labels.add(new Pair<>("Imię menedżera:", investmentFund.getManagerFirstName()));
        labels.add(new Pair<>("Nazwisko menedżera:", investmentFund.getManagerLastName()));
        return labels;
    }
}
