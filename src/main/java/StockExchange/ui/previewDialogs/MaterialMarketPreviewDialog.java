package StockExchange.ui.previewDialogs;

import StockExchange.model.ApplicationModel;
import StockExchange.model.Material;
import StockExchange.model.MaterialMarket;
import StockExchange.ui.CustomListCell;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class MaterialMarketPreviewDialog extends PreviewDialog<MaterialMarket> {


    public MaterialMarketPreviewDialog (MaterialMarket materialMarket) {
        super(getLabels(materialMarket));
        setTitle("Szczegóły rynku surowców");
        GridPane pane = getContentGrid();
        ListView<Material> currenciesPurchased = new ListView<>();
        currenciesPurchased.setItems(FXCollections.observableArrayList(materialMarket.getMaterialList()));
        currenciesPurchased.setCellFactory(param -> new CustomListCell<>());
        int rowCount = pane.impl_getRowCount();
        pane.add(currenciesPurchased, 0, rowCount, pane.impl_getColumnCount(), 1);
    }

    private static List<Pair<String, String>> getLabels(MaterialMarket materialMarket) {
        List<Pair<String, String>> labels = new ArrayList<>();
        labels.add(new Pair<>("Nazwa:", materialMarket.getName()));
        return labels;
    }

}
