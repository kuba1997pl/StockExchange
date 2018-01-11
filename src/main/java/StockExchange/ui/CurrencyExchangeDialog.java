package StockExchange.ui;

import StockExchange.model.Currency;
import StockExchange.model.CurrencyMarket;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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
        getDialogPane().getScene().getWindow().sizeToScene();
    }

    private HBox getCurrenciesAdder() {
        HBox pane = new HBox();
        pane.setSpacing(20);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20, 0, 20, 0));

        ComboBox<Currency> firstCurrency = new ComboBox<>();
        firstCurrency.setButtonCell(new CustomListCell<>());
        firstCurrency.setCellFactory(param -> new CustomListCell<>());
        firstCurrency.setItems(currencyList);

        HBox.setHgrow(firstCurrency, Priority.ALWAYS);
        ImageView arrow = new ImageView(new Image(getClass().getResourceAsStream("/StockExchange/images/arrow.png"), 20, 20, true, true));


        HBox.setHgrow(arrow, Priority.ALWAYS);

        ComboBox<Currency> secondCurrency = new ComboBox<>();
        secondCurrency.setButtonCell(new CustomListCell<>());
        secondCurrency.setCellFactory(param -> new CustomListCell<>());
        ObservableList<Currency> secondCurrencyList = FXCollections.observableArrayList();
        secondCurrency.setItems(secondCurrencyList);

        HBox.setHgrow(secondCurrency, Priority.ALWAYS);
        firstCurrency.valueProperty().addListener((observable, oldValue, newValue) -> {
            secondCurrencyList.clear();
            ObservableList<Currency> tmp = FXCollections.observableArrayList(currencyList);
            tmp.remove(newValue);
            secondCurrencyList.addAll(tmp);
        });

        pane.getChildren().addAll(firstCurrency, arrow, secondCurrency);
        return pane;
    }

    private CurrencyMarket convertResult(ButtonType buttonType) {
        if(buttonType == saveButtonType) {
            return new CurrencyMarket(name.get());
        }
        return null;
    }
}
