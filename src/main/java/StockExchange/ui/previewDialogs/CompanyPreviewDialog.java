package StockExchange.ui.previewDialogs;

import StockExchange.model.ApplicationModel;
import StockExchange.model.Company;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class CompanyPreviewDialog extends PreviewDialog<Company> {

    private static ButtonType deleteButtonType = new ButtonType("Usuń spółkę", ButtonBar.ButtonData.OK_DONE);
    private Company company;

    public CompanyPreviewDialog(Company company) {
        super(getLabels(company));
        this.company = company;
        setTitle("Szczegóły spółki");
        NumberAxis valuesAxis = new NumberAxis();
        valuesAxis.setLabel("Wartość");
        NumberAxis timeAxis = new NumberAxis();
        timeAxis.setLabel("Czas");
        LineChart<Number, Number> chart = new LineChart<>(timeAxis, valuesAxis, company.getChartData());
        chart.setTitle("Wykres ceny kupna/sprzedaży waluty w czasie");
        chart.setCreateSymbols(false);
        getContentGrid().add(chart, 0, getContentGrid().impl_getRowCount(), getContentGrid().impl_getColumnCount(), 1);
        getDialogPane().getButtonTypes().add(deleteButtonType);
        setResultConverter(this::close);
    }

    private Company close(ButtonType param) {
        if(param == deleteButtonType) {
            ApplicationModel.getInstance().getCompanies().remove(company);
        }
        return null;
    }



    private static List<Pair<String, String>> getLabels(Company company) {
        List<Pair<String, String>> labels = new ArrayList<>();
        labels.add(new Pair<>("Nazwa:", company.getDisplayName()));
        labels.add(new Pair<>("Data pierwszej wyceny:", company.getFirstPricingDate().toString()));
        labels.add(new Pair<>("Cena minimalna:", String.format("%10.2f", company.getMinPrice())));
        labels.add(new Pair<>("Cena maksymalna:", String.format("%10.2f", company.getMaxPrice())));
        labels.add(new Pair<>("Cena aktualna:", String.format("%10.2f", company.getCurrentPrice())));
        labels.add(new Pair<>("Cena otwarcia:", String.format("%10.2f", company.getOpeningPrice())));
        labels.add(new Pair<>("Liczba udziałów:", Integer.toString(company.getSharesCount())));
        labels.add(new Pair<>("Zysk:", String.format("%10.2f", company.getProfit())));
        labels.add(new Pair<>("Przychód:", String.format("%10.2f", company.getIncome())));
        labels.add(new Pair<>("Obroty:", String.format("%10.2f", company.getSales())));
        labels.add(new Pair<>("Kapitał własny:", String.format("%10.2f", company.getEquityCapital())));
        labels.add(new Pair<>("Kapitał zakładowy:", String.format("%10.2f", company.getShareCapital())));
        labels.add(new Pair<>("Wolumen:", Integer.toString(company.getVolume())));
        return labels;
    }

}
