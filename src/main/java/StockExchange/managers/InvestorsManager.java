package StockExchange.managers;

import StockExchange.ApplicationExecutor;
import StockExchange.model.ApplicationModel;
import StockExchange.model.Company;
import StockExchange.model.InvestmentFund;
import StockExchange.model.Investor;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

public class InvestorsManager {
    private IntegerProperty totalSharesCount;

    private InvestorsManager() {
        totalSharesCount = new SimpleIntegerProperty();
        totalSharesCount.addListener(this::onTotalSharesAmountChanged);
        ApplicationExecutor.getInstance().getBackgroundThreadPool().execute(this::iterateOverAllCompaniesAndSumSharesCount);
    }

    private void iterateOverAllCompaniesAndSumSharesCount() {
        while(!Thread.currentThread().isInterrupted()) {
            try {
                totalSharesCount.setValue(ApplicationModel.getInstance().getCompanies().stream().mapToInt(Company::getSharesCount).sum());
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
                // no trudno
            }
        }
    }

    private void onTotalSharesAmountChanged(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        Platform.runLater(() -> {
            ObservableList<Investor> investors = ApplicationModel.getInstance().getInvestors();
            ObservableList<InvestmentFund> investmentFunds = ApplicationModel.getInstance().getInvestmentFunds();
            int currentInvestmentFundsCount = investmentFunds.size();
            int investmenFundsLimit = newValue.intValue() / 5000;
            int currentInvestorsCount = investors.size();
            int investorsLimit = newValue.intValue() / 500;
            if(currentInvestmentFundsCount > investmenFundsLimit) {
                investmentFunds.remove(currentInvestmentFundsCount - 1);
            } else {
                for (int i = 0; i < investmenFundsLimit - currentInvestmentFundsCount; i++) {
                    investmentFunds.add(new InvestmentFund());
                }
            }
            if(currentInvestorsCount > investorsLimit) {
                investors.remove(currentInvestorsCount - 1);
            } else {
                for (int i = 0; i < investorsLimit - currentInvestorsCount; i++) {
                    investors.add(new Investor());
                }
            }
        });
    }

    public static void start() {
        new InvestorsManager();
    }
}
