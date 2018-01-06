package StockExchange.model;

import StockExchange.ui.DisplayableListItem;

public class StockExchangeModel extends DisplayableListItem{

    private String name;

    public StockExchangeModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDisplayName() {
        return name;
    }
}
