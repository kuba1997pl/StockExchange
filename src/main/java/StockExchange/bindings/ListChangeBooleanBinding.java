package StockExchange.bindings;

import javafx.beans.binding.BooleanBinding;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.concurrent.Callable;

public class ListChangeBooleanBinding extends BooleanBinding {

    private Callable<Boolean> func;

    public ListChangeBooleanBinding(Callable<Boolean> func, ObservableList<?>... observables) {
        this.func = func;
        bind(observables);
    }

    private void bind(ObservableList<?>... observables) {
        if(observables != null && observables.length > 0) {
            for(ObservableList<?> observable: observables) {
                observable.addListener(this::listChanged);
            }
        }
    }

    private void listChanged(ListChangeListener.Change c) {
        invalidate();
    }

    @Override
    protected boolean computeValue() {
        try {
            return func.call();
        } catch (Exception e) {
            return false;
        }
    }
}
