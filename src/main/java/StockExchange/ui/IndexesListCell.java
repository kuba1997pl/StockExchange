package StockExchange.ui;

import StockExchange.model.StockExchangeModel;
import StockExchange.model.StockIndex;
import javafx.scene.control.ListCell;

public class IndexesListCell extends ListCell<StockIndex>{

    public IndexesListCell() {

    }

    @Override
    protected void updateItem(StockIndex item, boolean empty) {
        super.updateItem(item, empty);
        setText(item == null ? "" : "stockIndex");
    }
}
