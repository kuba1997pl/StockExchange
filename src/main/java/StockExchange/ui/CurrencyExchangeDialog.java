package StockExchange.ui;

import StockExchange.model.Currency;
import StockExchange.model.CurrencyMarket;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CurrencyExchangeDialog extends Dialog<CurrencyMarket> {

    private static ButtonType saveButtonType = new ButtonType("Zapisz", ButtonBar.ButtonData.OK_DONE);
    private static ButtonType cancelButtonType = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);

    private ObservableList<Currency> currencyList;

    private StringProperty name = new SimpleStringProperty();
    private VBox currenciesPane;

    public CurrencyExchangeDialog(ObservableList<Currency> currencyList) {
        super();
        this.currencyList = FXCollections.observableArrayList(currencyList);
        setTitle("Utwórz rynek walut");
        DialogPane dialogPane = getDialogPane();
        dialogPane.getButtonTypes().addAll(saveButtonType, cancelButtonType);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));

        TextField nameField = new TextField();
        nameField.setPromptText("Nazwa");
        nameField.textProperty().bindBidirectional(name);

        grid.add(new Label("Nazwa:"), 0, 0);
        grid.add(nameField, 1, 0);

        currenciesPane = new VBox();
        Button addCurrency = new Button();
        addCurrency.setText("Dodaj stawkę walutową");
        addCurrency.setOnAction(this::addCurrency);
        currenciesPane.getChildren().add(addCurrency);

        grid.add(currenciesPane, 0, 1);

        dialogPane.setContent(grid);
        setResultConverter(this::convertResult);
    }

    private void addCurrency(ActionEvent event) {
        currenciesPane.getChildren().add(getCurrenciesAdder());
    }

    private HBox getCurrenciesAdder() {
        HBox pane = new HBox();
        ComboBox<Currency> firstCurrency = new ComboBox<>();
        firstCurrency.setButtonCell(new CustomListCell<>());
        firstCurrency.setCellFactory(param -> new CustomListCell<>());
        firstCurrency.setItems(currencyList);
        pane.getChildren().add(firstCurrency);
        return pane;
    }

    private CurrencyMarket convertResult(ButtonType buttonType) {
        if(buttonType == saveButtonType) {
            return new CurrencyMarket(name.get());
        }
        return null;
    }
}
