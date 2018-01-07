package StockExchange.ui;

import StockExchange.model.CurrencyModel;
import StockExchange.model.IndexModel;
import StockExchange.model.StockExchangeModel;
import com.sun.javafx.binding.SelectBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class StockDialog extends Dialog<StockExchangeModel> implements Callback<ButtonType, StockExchangeModel>, ChangeListener<String>{

    private static ButtonType saveButtonType = new ButtonType("Zapisz", ButtonBar.ButtonData.OK_DONE);
    private static ButtonType cancelButtonType = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);

    private StringProperty stockName = new SimpleStringProperty();
    private StringProperty countryName = new SimpleStringProperty();
    private StringProperty city = new SimpleStringProperty();
    private StringProperty address = new SimpleStringProperty();

    private Button okButton;

    public StockDialog(ObservableList<CurrencyModel> currencies, ObservableList<IndexModel> indexes) {
        super();
        setTitle("Dodaj giełdę");
        DialogPane dialogPane = getDialogPane();
        dialogPane.getButtonTypes().addAll(saveButtonType, cancelButtonType);
        okButton = (Button) dialogPane.lookupButton(saveButtonType);
        okButton.setDisable(true);
        setListeners();

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("Nazwa:"), 0, 0);
        grid.add(createTextField("Nazwa", stockName), 1, 0);

        grid.add(new Label("Państwo:"), 0, 1);
        grid.add(createTextField("Państwo", countryName), 1, 1);

        grid.add(new Label("Miasto:"), 0, 2);
        grid.add(createTextField("Miasto", city), 1, 2);

        grid.add(new Label("Adres:"), 0, 3);
        grid.add(createTextField("Adres", address), 1, 3);

        ComboBox<IndexModel> comboBox = new ComboBox<>();
        comboBox.setItems(indexes);
        comboBox.setCellFactory(param -> new CustomListCell<>());

        grid.add(new Label("Indeks:"), 0, 4);
        grid.add(comboBox, 1, 4);

        /**
         * nazwa - string
         * panstwo - string
         * waluta - currencyModel
         * miasto - string
         * adres - string
         * do wyboru indeksy - IndexModel
         */

        getDialogPane().setContent(grid);
        setResultConverter(this);
    }

    private void setListeners() {
        stockName.addListener(this);
        countryName.addListener(this);
        city.addListener(this);
        address.addListener(this);
    }

    private TextField createTextField(String prompt, Property<String> bindingProperty) {
        TextField textField = new TextField();
        textField.setPromptText(prompt);
        textField.textProperty().bindBidirectional(bindingProperty);
        return textField;
    }

    @Override
    public StockExchangeModel call(ButtonType param) {
        if(param == saveButtonType) {
            return new StockExchangeModel(stockName.get(), countryName.get(), new CurrencyModel(), city.get(), address.get(), new ArrayList<>());
        }
        return null;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        String[] values = { stockName.get(), countryName.get(), city.get(), address.get() };
        boolean disabled = false;
        for(String string : values) {
            if(string != null) {
                if(string.isEmpty()) {
                    disabled = true;
                }
            } else {
                disabled = true;
            }
        }
        okButton.setDisable(disabled);
    }
}
