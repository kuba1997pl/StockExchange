package StockExchange.ui.previewDialogs;

import StockExchange.model.Company;
import StockExchange.model.Index;
import StockExchange.ui.CustomListView;
import javafx.collections.FXCollections;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class IndexPreviewDialog extends PreviewDialog<Index> {

    public IndexPreviewDialog(Index index) {
        super(getLabels(index));
        setTitle("Szczegóły indeksu");
        GridPane pane = getContentGrid();
        CustomListView<Company> companies = new CustomListView<>();
        companies.setButtonVisibile(false);
        companies.setLabelText("Spółki");
        companies.setItemList(FXCollections.observableArrayList(index.getCompaniesList()));
        companies.addOnDoubleClickItemListener(company -> new CompanyPreviewDialog(company).show());
        pane.add(companies, 0, pane.impl_getRowCount(), pane.impl_getColumnCount(), 1);
    }

    private static List<Pair<String, String>> getLabels(Index index) {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("Nazwa:", index.getDisplayName()));
        list.add(new Pair<>("Wartość:", String.format("%10.2f", index.getValue())));
        return list;
    }
}
