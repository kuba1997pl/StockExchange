package StockExchange.ui;

import StockExchange.model.StockExchangeModel;
import javafx.scene.control.ListCell;

public class StockExchangeListCell extends ListCell<StockExchangeModel>{

    public StockExchangeListCell() {

    }

    @Override
    protected void updateItem(StockExchangeModel item, boolean empty) {
        super.updateItem(item, empty);
        setText(item == null ? "" : item.getName());
    }
}
