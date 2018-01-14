package StockExchange.ui.previewDialogs;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.List;

class PreviewDialog<T> extends Dialog<T> {

    private static ButtonType closeButtonType = new ButtonType("Zamknij", ButtonBar.ButtonData.CANCEL_CLOSE);
    private GridPane gridPane;

    PreviewDialog(List<Pair<String, String>> labelNames) {
        DialogPane pane = getDialogPane();
        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(30));
        for(int i = 0; i < labelNames.size(); i++) {
            Pair<String, String> pair = labelNames.get(i);
            gridPane.add(new Label(pair.getKey()), 0, i);
            gridPane.add(new Label(pair.getValue()), 1, i);
        }
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gridPane);
        pane.setContent(scrollPane);
        pane.getButtonTypes().addAll(closeButtonType);
    }

    GridPane getContentGrid() {
        return gridPane;
    }

}
