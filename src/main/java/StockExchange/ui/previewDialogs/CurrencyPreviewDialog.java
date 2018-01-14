package StockExchange.ui.previewDialogs;

import StockExchange.model.Currency;
import javafx.collections.FXCollections;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class CurrencyPreviewDialog extends PreviewDialog<Currency> {

    public CurrencyPreviewDialog(Currency currency) {
        super(getLabels(currency));
        GridPane grid = getContentGrid();
        grid.add(new Label("Lista krajów w których waluta jest akceptowana:"), 0, grid.impl_getRowCount(), grid.impl_getColumnCount(), 1);
        ListView<String> countries = new ListView<>();
        countries.setItems(FXCollections.observableArrayList(currency.getCountriesList()));
        grid.add(countries, 0, grid.impl_getRowCount(), grid.impl_getColumnCount(), 1);
        setTitle("Szczegóły waluty");

        NumberAxis valuesAxis = new NumberAxis();
        valuesAxis.setLabel("Wartość");
        NumberAxis timeAxis = new NumberAxis();
        timeAxis.setLabel("Czas");
        LineChart<Number, Number> chart = new LineChart<>(timeAxis, valuesAxis, currency.getChartData());
        chart.setTitle("Wykres ceny kupna/sprzedaży waluty w czasie");
        chart.setCreateSymbols(false);
        getContentGrid().add(chart, 0, getContentGrid().impl_getRowCount(), getContentGrid().impl_getColumnCount(), 1);
    }

    private static List<Pair<String, String>> getLabels(Currency currency) {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("Nazwa:", currency.getDisplayName()));
        list.add(new Pair<>("Cena kupna:", String.format("%10.2f", currency.getPurchasePrice())));
        list.add(new Pair<>("Cena sprzedaży:", String.format("%10.2f", currency.getSellPrice())));
        return list;
    }

}
