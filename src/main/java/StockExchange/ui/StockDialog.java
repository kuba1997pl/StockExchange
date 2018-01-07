package StockExchange.ui;

import StockExchange.model.CurrencyModel;
import StockExchange.model.IndexModel;
import StockExchange.model.StockExchangeModel;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    private ObservableList<IndexModel> chosenIndexesList = FXCollections.observableArrayList();
    private ObservableList<IndexModel> suppliedIndexesList;
    private ObjectProperty<MultipleSelectionModel<IndexModel>> chosenSelectedIndices = new SimpleObjectProperty<>();
    private ObjectProperty<MultipleSelectionModel<IndexModel>> suppliedSelectedIndices = new SimpleObjectProperty<>();

    private Button okButton;

    public StockDialog(ObservableList<CurrencyModel> currencies, ObservableList<IndexModel> indexes) {
        super();
        this.suppliedIndexesList = FXCollections.observableArrayList(indexes);
        setTitle("Dodaj giełdę");
        DialogPane dialogPane = getDialogPane();
        dialogPane.getButtonTypes().addAll(saveButtonType, cancelButtonType);
        okButton = (Button) dialogPane.lookupButton(saveButtonType);
        okButton.setDisable(true);
        setListeners();

        // Create the username and password labels and fields.
        VBox vBox = new VBox();
        GridPane grid = new GridPane();
        grid.prefWidthProperty().bind(vBox.prefWidthProperty());
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));

        grid.add(new Label("Nazwa:"), 0, 0);
        grid.add(createTextField("Nazwa", stockName), 1, 0);

        grid.add(new Label("Państwo:"), 0, 1);
        grid.add(createTextField("Państwo", countryName), 1, 1);

        grid.add(new Label("Miasto:"), 0, 2);
        grid.add(createTextField("Miasto", city), 1, 2);

        grid.add(new Label("Adres:"), 0, 3);
        grid.add(createTextField("Adres", address), 1, 3);

        ComboBox<CurrencyModel> currencyComboBox = new ComboBox<>();
        currencyComboBox.setItems(currencies);
        currencyComboBox.setButtonCell(new CustomListCell<>());
        currencyComboBox.setCellFactory(param -> new CustomListCell<>());
        currencyComboBox.valueProperty().addListener(this::currencyChanged);

        grid.add(new Label("Waluta:"), 0, 4);
        grid.add(currencyComboBox, 1, 4);

        vBox.getChildren().add(grid);

        HBox hBox = new HBox();

        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);

        ListView<IndexModel> chosenIndexesListView = new ListView<>();
        chosenIndexesListView.setItems(chosenIndexesList);
        chosenIndexesListView.setCellFactory(param -> new CustomListCell<>());
        chosenSelectedIndices.bind(chosenIndexesListView.selectionModelProperty());

        hBox.getChildren().add(chosenIndexesListView);

        VBox indexAddButtons = new VBox();
        indexAddButtons.setAlignment(Pos.CENTER);
        indexAddButtons.setSpacing(20);
        indexAddButtons.setFillWidth(true);

        Button addIndex = new Button();
        addIndex.setOnAction(this::addSelectedIndex);
        addIndex.setText("+");

        indexAddButtons.getChildren().add(addIndex);

        Button removeIndex = new Button();
        removeIndex.setOnAction(this::removeSelectedIndex);
        removeIndex.setText("-");

        indexAddButtons.getChildren().add(removeIndex);

        hBox.getChildren().add(indexAddButtons);

        ListView<IndexModel> indexListView = new ListView<>();
        indexListView.setItems(suppliedIndexesList);
        indexListView.setCellFactory(param -> new CustomListCell<>());
        suppliedSelectedIndices.bind(indexListView.selectionModelProperty());

        hBox.getChildren().add(indexListView);

        vBox.getChildren().add(hBox);

        getDialogPane().setContent(vBox);
        setResultConverter(this);
    }

    private void addSelectedIndex(ActionEvent event) {
        ArrayList<IndexModel> list = new ArrayList<>(suppliedSelectedIndices.get().getSelectedItems());
        chosenIndexesList.addAll(list);
        suppliedIndexesList.removeAll(list);
    }

    private void removeSelectedIndex(ActionEvent event) {
        ArrayList<IndexModel> list = new ArrayList<>(chosenSelectedIndices.get().getSelectedItems());
        suppliedIndexesList.addAll(list);
        chosenIndexesList.removeAll(list);
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

    public void currencyChanged(ObservableValue<? extends CurrencyModel> observable, CurrencyModel oldValue, CurrencyModel newValue) {

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
