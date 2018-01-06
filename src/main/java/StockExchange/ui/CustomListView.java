package StockExchange.ui;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomListView<T extends DisplayableListItem> extends VBox implements Initializable{

    private OnCustomListViewButtonClickListener clickListener;

    @FXML
    Label label;

    @FXML
    Button button;

    @FXML
    ListView<T> listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setCellFactory(param -> new CustomListCell<>());
    }

    @FXML
    private void onAddItemClick(ActionEvent event) {
        if(clickListener != null) {
            clickListener.onCustomListViewButtonClicked();
        }
    }

    public CustomListView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/StockExchange/view/CustomListView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setItemList(ObservableList<T> itemList) {
        listView.setItems(itemList);
    }

    public void setLabelText(String text) {
        labelTextProperty().set(text);
    }

    public String getLabelText() {
        return labelTextProperty().get();
    }

    public void setButtonText(String text) {
        buttonTextProperty().set(text);
    }

    public String getButtonText() {
        return buttonTextProperty().get();
    }

    public StringProperty buttonTextProperty() {
        return button.textProperty();
    }

    public StringProperty labelTextProperty() {
        return label.textProperty();
    }

    public void setClickListener(OnCustomListViewButtonClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
