package StockExchange.ui.previewDialogs;

import StockExchange.model.InvestmentFund;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class InvestmentFundPreviewDialog extends PreviewDialog<InvestmentFund> {

    public InvestmentFundPreviewDialog(InvestmentFund investmendFund) {
        super(getLabels(investmendFund));
        setTitle("Szczegóły funduszu inwestycyjnego");
    }

    private static List<Pair<String, String>> getLabels(InvestmentFund investmentFund) {
        List<Pair<String, String>> labels = new ArrayList<>();
        labels.add(new Pair<>("Nazwa:", investmentFund.getDisplayName()));
        labels.add(new Pair<>("Imię menedżera:", investmentFund.getManagerFirstName()));
        labels.add(new Pair<>("Nazwisko menedżera:", investmentFund.getManagerLastName()));
        return labels;
    }
}
