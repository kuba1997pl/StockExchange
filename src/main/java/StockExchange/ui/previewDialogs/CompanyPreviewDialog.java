package StockExchange.ui.previewDialogs;

import StockExchange.model.Company;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class CompanyPreviewDialog extends PreviewDialog<Company> {

    public CompanyPreviewDialog(Company company) {
        super(getLabels(company));
    }

    private static List<Pair<String, String>> getLabels(Company company) {
        List<Pair<String, String>> labels = new ArrayList<>();
        labels.add(new Pair<>("Nazwa:", company.getDisplayName()));
        labels.add(new Pair<>("Data pierwszej wyceny:", company.getFirstPricingDate().toString()));
        labels.add(new Pair<>("Cena minimalna:", Double.toString(company.getMinPrice())));
        labels.add(new Pair<>("Cena maksymalna:", Double.toString(company.getMaxPrice())));
        labels.add(new Pair<>("Cena aktualna:", Double.toString(company.getCurrentPrice())));
        labels.add(new Pair<>("Cena otwarcia:", Double.toString(company.getOpeningPrice())));
        labels.add(new Pair<>("Liczba udziałów:", Integer.toString(company.getSharesCount())));
        labels.add(new Pair<>("Zysk:", Double.toString(company.getProfit())));
        labels.add(new Pair<>("Przychód:", Double.toString(company.getIncome())));
        labels.add(new Pair<>("Obroty:", Double.toString(company.getSales())));
        labels.add(new Pair<>("Kapitał własny:", Double.toString(company.getEquityCapital())));
        labels.add(new Pair<>("Kapitał zakładowy:", Double.toString(company.getShareCapital())));
        labels.add(new Pair<>("Wolumen:", Integer.toString(company.getVolume())));
        return labels;
    }

}
