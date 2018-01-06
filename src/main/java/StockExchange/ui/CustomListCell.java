package StockExchange.ui;

import javafx.scene.control.ListCell;

public class CustomListCell<T extends DisplayableListItem> extends ListCell<T>{
    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        setText(item == null ? "" : item.getDisplayName());
    }
}
