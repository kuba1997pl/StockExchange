package StockExchange.ui.previewDialogs;

import StockExchange.model.CurrencyInWallet;
import StockExchange.model.Investor;
import StockExchange.model.MaterialInWallet;
import StockExchange.model.ShareInWallet;
import StockExchange.ui.CustomListCell;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class InvestorPreviewDialog extends PreviewDialog<Investor> {

    public InvestorPreviewDialog(Investor investor) {
        super(getLabels(investor));
        GridPane pane = getContentGrid();
        ListView<CurrencyInWallet> currenciesPurchased = new ListView<>();
        currenciesPurchased.setItems(FXCollections.observableArrayList(investor.getCurrenciesPurchased()));
        currenciesPurchased.setCellFactory(param -> new CustomListCell<>());
        int rowCount = pane.impl_getRowCount();
        pane.add(currenciesPurchased, 0, rowCount++);
        ListView<MaterialInWallet> materialsPurchased = new ListView<>();
        materialsPurchased.setItems(FXCollections.observableArrayList(investor.getMaterialsPurchased()));
        materialsPurchased.setCellFactory(param -> new CustomListCell<>());
        pane.add(materialsPurchased, 1, rowCount++);
        ListView<ShareInWallet> sharesPurchased = new ListView<>();
        sharesPurchased.setItems(FXCollections.observableArrayList(investor.getSharesPurchased()));
        sharesPurchased.setCellFactory(param -> new CustomListCell<>());
        pane.add(sharesPurchased, 2, rowCount);
    }

    private static List<Pair<String, String>> getLabels(Investor investor) {
        List<Pair<String, String>> labels = new ArrayList<>();
        labels.add(new Pair<>("Imię i nazwisko:", investor.getDisplayName()));
        labels.add(new Pair<>("PRECEL:", investor.getPESEL()));
        labels.add(new Pair<>("Budżet:", Double.toString(investor.getBudget())));
        return labels;
    }
}
