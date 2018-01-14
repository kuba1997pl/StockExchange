package StockExchange.ui.previewDialogs;

import StockExchange.model.Material;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class MaterialPreviewDialog extends PreviewDialog<Material> {

    public MaterialPreviewDialog(Material material) {
        super(getLabels(material));
        setTitle("Szczegóły surowca");
        NumberAxis valuesAxis = new NumberAxis();
        valuesAxis.setLabel("Wartość");
        NumberAxis timeAxis = new NumberAxis();
        timeAxis.setLabel("Czas");
        LineChart<Number, Number> chart = new LineChart<>(timeAxis, valuesAxis, material.getChartData());
        chart.setTitle("Wykres wartości materiału w czasie");
        chart.setCreateSymbols(false);
        getContentGrid().add(chart, 0, getContentGrid().impl_getRowCount(), getContentGrid().impl_getColumnCount(), 1);
    }

    private static List<Pair<String, String>> getLabels(Material material) {
        List<Pair<String, String>> labels = new ArrayList<>();
        labels.add(new Pair<>("Nazwa:", material.getDisplayName()));
        labels.add(new Pair<>("Waluta:", material.getCurrency().getDisplayName()));
        labels.add(new Pair<>("Jednostka handlowa:", material.getTradeUnit()));
        labels.add(new Pair<>("Cena aktualna:", String.format("%10.2f", material.getCurrentValue())));
        labels.add(new Pair<>("Cena minimalna:", String.format("%10.2f", material.getMinValue())));
        labels.add(new Pair<>("Cena maksymalna:", String.format("%10.2f", material.getMaxValue())));
        return labels;
    }

}
