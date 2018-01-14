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

public class InvestorPreviewDialog extends PreviewDialog<Investor> {

    private static ButtonType deleteButtonType = new ButtonType("Usuń spółkę", ButtonBar.ButtonData.OK_DONE);
    private Investor investor;

    public InvestorPreviewDialog(Investor investor) {
        super(getLabels(investor));
        this.investor = investor;
        setTitle("Szczegóły inwestora");
        GridPane pane = getContentGrid();
        ListView<CurrencyInWallet> currenciesPurchased = new ListView<>();
        currenciesPurchased.setItems(FXCollections.observableArrayList(investor.getCurrenciesPurchased()));
        currenciesPurchased.setCellFactory(param -> new CustomListCell<>());
        int rowCount = pane.impl_getRowCount();
        pane.add(currenciesPurchased, 0, rowCount);
        ListView<MaterialInWallet> materialsPurchased = new ListView<>();
        materialsPurchased.setItems(FXCollections.observableArrayList(investor.getMaterialsPurchased()));
        materialsPurchased.setCellFactory(param -> new CustomListCell<>());
        pane.add(materialsPurchased, 1, rowCount);
        ListView<ShareInWallet> sharesPurchased = new ListView<>();
        sharesPurchased.setItems(FXCollections.observableArrayList(investor.getSharesPurchased()));
        sharesPurchased.setCellFactory(param -> new CustomListCell<>());
        pane.add(sharesPurchased, 2, rowCount);
        getDialogPane().getButtonTypes().add(deleteButtonType);
        setResultConverter(this::close);
    }

    private Investor close(ButtonType param) {
        if(param == deleteButtonType) {
            ApplicationModel.getInstance().getInvestors().remove(investor);
        }
        return null;
    }

    private static List<Pair<String, String>> getLabels(Investor investor) {
        List<Pair<String, String>> labels = new ArrayList<>();
        labels.add(new Pair<>("Imię i nazwisko:", investor.getDisplayName()));
        labels.add(new Pair<>("PRECEL:", investor.getPESEL()));
        labels.add(new Pair<>("Budżet:", Double.toString(investor.getBudget())));
        return labels;
    }
}
