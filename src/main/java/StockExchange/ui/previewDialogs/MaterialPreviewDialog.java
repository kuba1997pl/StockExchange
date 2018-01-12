package StockExchange.ui.previewDialogs;

import StockExchange.model.Material;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class MaterialPreviewDialog extends PreviewDialog<Material> {

    public MaterialPreviewDialog(Material material) {
        super(getLabels(material));
    }

    private static List<Pair<String, String>> getLabels(Material material) {
        List<Pair<String, String>> labels = new ArrayList<>();
        labels.add(new Pair<>("Nazwa:", material.getDisplayName()));
        labels.add(new Pair<>("Waluta:", material.getCurrency().getDisplayName()));
        labels.add(new Pair<>("Jednostka handlowa:", material.getTradeUnit()));
        labels.add(new Pair<>("Cena aktualna:", Double.toString(material.getCurrentValue())));
        labels.add(new Pair<>("Cena minimalna:", Double.toString(material.getMinValue())));
        labels.add(new Pair<>("Cena maksymalna:", Double.toString(material.getMaxValue())));
        return labels;
    }

}
