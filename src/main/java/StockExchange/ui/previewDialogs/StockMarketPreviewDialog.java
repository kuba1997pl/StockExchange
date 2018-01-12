package StockExchange.ui.previewDialogs;

import StockExchange.model.Index;
import StockExchange.model.StockMarket;
import StockExchange.ui.CustomListView;
import javafx.collections.FXCollections;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class StockMarketPreviewDialog extends PreviewDialog<StockMarket> {

    public StockMarketPreviewDialog(StockMarket stockMarket) {
        super(getLabels(stockMarket));
        setTitle("Szczegóły giełdy");
        GridPane pane = getContentGrid();
        CustomListView<Index> indexesList = new CustomListView<>();
        indexesList.setItemList(FXCollections.observableArrayList(stockMarket.getIndexList()));
        indexesList.setButtonVisibile(false);
        indexesList.setLabelText("Indeksy");
        indexesList.addOnDoubleClickItemListener(index -> new IndexPreviewDialog(index).show());
        pane.add(indexesList, 0, pane.impl_getRowCount(), pane.impl_getColumnCount(), 1);
    }

    private static List<Pair<String, String>> getLabels(StockMarket stockMarket) {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("Name:", stockMarket.getName()));
        list.add(new Pair<>("Country:", stockMarket.getCountry()));
        list.add(new Pair<>("Currency:", stockMarket.getCurrency().getDisplayName()));
        list.add(new Pair<>("City:", stockMarket.getCity()));
        list.add(new Pair<>("Office address:", stockMarket.getOfficeAdress()));
        return list;
    }
}
