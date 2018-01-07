package StockExchange.ui;

import StockExchange.model.Currency;
import StockExchange.model.Index;
import StockExchange.model.StockExchange;
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

public class StockDialog extends Dialog<StockExchange> implements Callback<ButtonType, StockExchange>, ChangeListener<String>{

    private static ButtonType saveButtonType = new ButtonType("Zapisz", ButtonBar.ButtonData.OK_DONE);
    private static ButtonType cancelButtonType = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);

    private StringProperty stockName = new SimpleStringProperty();
    private StringProperty countryName = new SimpleStringProperty();
    private StringProperty city = new SimpleStringProperty();
    private StringProperty address = new SimpleStringProperty();
    private ObservableList<Index> chosenIndexesList = FXCollections.observableArrayList();
    private ObservableList<Index> suppliedIndexesList;
    private ObjectProperty<MultipleSelectionModel<Index>> chosenSelectedIndices = new SimpleObjectProperty<>();
    private ObjectProperty<MultipleSelectionModel<Index>> suppliedSelectedIndices = new SimpleObjectProperty<>();

    private Button okButton;

    public StockDialog(ObservableList<Currency> currencies, ObservableList<Index> indexes) {
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

        ComboBox<Currency> currencyComboBox = new ComboBox<>();
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

        ListView<Index> chosenIndexesListView = new ListView<>();
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

        ListView<Index> indexListView = new ListView<>();
        indexListView.setItems(suppliedIndexesList);
        indexListView.setCellFactory(param -> new CustomListCell<>());
        suppliedSelectedIndices.bind(indexListView.selectionModelProperty());

        hBox.getChildren().add(indexListView);

        vBox.getChildren().add(hBox);

        getDialogPane().setContent(vBox);
        setResultConverter(this);
    }

    private void addSelectedIndex(ActionEvent event) {
        ArrayList<Index> list = new ArrayList<>(suppliedSelectedIndices.get().getSelectedItems());
        chosenIndexesList.addAll(list);
        suppliedIndexesList.removeAll(list);
    }

    private void removeSelectedIndex(ActionEvent event) {
        ArrayList<Index> list = new ArrayList<>(chosenSelectedIndices.get().getSelectedItems());
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
    public StockExchange call(ButtonType param) {
        if(param == saveButtonType) {
            return new StockExchange(stockName.get(), countryName.get(), new Currency(), city.get(), address.get(), new ArrayList<>());
        }
        return null;
    }

    public void currencyChanged(ObservableValue<? extends Currency> observable, Currency oldValue, Currency newValue) {

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
