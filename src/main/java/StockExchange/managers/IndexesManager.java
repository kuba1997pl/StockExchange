package StockExchange.managers;

import StockExchange.ApplicationExecutor;
import StockExchange.model.ApplicationModel;
import StockExchange.model.Company;
import StockExchange.model.Index;
import javafx.collections.ObservableList;

public class IndexesManager {

    private IndexesManager() {
        ApplicationExecutor.getInstance().getBackgroundThreadPool().execute(this::calculateIndexesValues);
    }

    private void calculateIndexesValues() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                ObservableList<Index> indexes = ApplicationModel.getInstance().getIndexes();
                for(Index index: indexes) {
                    double combinedSharesPrice = index.getCompaniesList().stream().mapToDouble(Company::getCurrentPrice).sum();
                    index.setValue(combinedSharesPrice/1000);
                }
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
                // no trudno
            }
        }
    }

    public static void start() {
        new IndexesManager();
    }
}
