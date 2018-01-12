package StockExchange.ui;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

/**
 * Custom view for showing listView with a headline and an action button
 * @param <T> type of displayed item in a listView. Must implement DisplayableListItem interface.
 */
public class CustomListView<T extends DisplayableListItem> extends VBox implements Initializable, EventHandler<ActionEvent>{

    private OnCustomListViewButtonClickListener onClickListener;

    @FXML
    private Label label;

    @FXML
    private Button button;

    @FXML
    private ListView<T> listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setCellFactory(param -> new CustomListCell<>());
        button.setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
        if(onClickListener != null) {
            onClickListener.onCustomListViewButtonClicked();
        }
    }

    public CustomListView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/StockExchange/view/CustomListView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setButtonDisabled(ObservableValue<Boolean> disabled) {
        button.setDisable(disabled.getValue());
        disabled.addListener((observable, oldValue, newValue) -> button.setDisable(newValue));
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

    public boolean getButtonVisibility() {
        return button.isVisible();
    }

    public void setButtonVisibility(boolean visibility) {
        button.setVisible(visibility);
    }

    private StringProperty buttonTextProperty() {
        return button.textProperty();
    }

    private StringProperty labelTextProperty() {
        return label.textProperty();
    }

    public void setOnClickListener(OnCustomListViewButtonClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void addOnDoubleClickItemListener(Consumer<T> function) {
        listView.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount() == 2) {
                T item = listView.getSelectionModel().getSelectedItem();
                function.accept(item);
            }
        });
    }

}
