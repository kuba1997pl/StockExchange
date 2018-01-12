package StockExchange.ui.previewDialogs;

import StockExchange.model.Currency;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class CurrencyPreviewDialog extends PreviewDialog<Currency> {

    public CurrencyPreviewDialog(Currency currency) {
        super(getLabels(currency));
        GridPane grid = getContentGrid();
        grid.add(new Label("Lista krajów w których waluta jest akceptowana:"), 0, 1, 2, 1);
        ListView<String> countries = new ListView<>();
        countries.setItems(FXCollections.observableArrayList(currency.getCountriesList()));
        grid.add(countries, 0, 2, 2, 1);
        setTitle("Szczegóły waluty");
    }

    private static List<Pair<String, String>> getLabels(Currency currency) {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("Nazwa:", currency.getDisplayName()));
        return list;
    }

}
