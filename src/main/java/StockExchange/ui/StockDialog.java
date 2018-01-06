package StockExchange.ui;

import StockExchange.model.StockExchangeModel;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class StockDialog extends Dialog<StockExchangeModel> implements Callback<ButtonType, StockExchangeModel>{

    private static ButtonType saveButtonType = new ButtonType("Zapisz", ButtonBar.ButtonData.OK_DONE);
    private static ButtonType cancelButtonType = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);


    private StockDialog() {
        super();
        setTitle("Dodaj giełdę");
        DialogPane dialogPane = getDialogPane();
        dialogPane.getButtonTypes().addAll(saveButtonType, cancelButtonType);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField stockExchangeName = new TextField();
        stockExchangeName.setPromptText("Nazwa");
        TextField country = new TextField();
        country.setPromptText("Państwo");
        TextField city = new TextField();
        city.setPromptText("Miasto");
        TextField address = new TextField();
        address.setPromptText("Adres");

        grid.add(new Label("Nazwa:"), 0, 0);
        grid.add(stockExchangeName, 1, 0);
        grid.add(new Label("Państwo:"), 0, 1);
        grid.add(country, 1, 1);
        grid.add(new Label("Miasto:"), 0, 2);
        grid.add(city, 1, 2);
        grid.add(new Label("Adres:"), 0, 3);
        grid.add(address, 1, 3);

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


    private TextField createTextField(String prompt) {
        TextField textField = new TextField();
        textField.setPromptText(prompt);
        return textField;
    }

    @Override
    public StockExchangeModel call(ButtonType param) {
        return null;
    }
}
