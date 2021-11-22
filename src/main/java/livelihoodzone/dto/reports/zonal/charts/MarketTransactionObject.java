package livelihoodzone.dto.reports.zonal.charts;

import livelihoodzone.dto.questionnaire.county.model.markets.MarketTransactionsItem;

import java.util.ArrayList;
import java.util.List;

public class MarketTransactionObject {
    private List<MarketTransactionsItem> marketTransactionItems;

    public MarketTransactionObject() {
    }

    public MarketTransactionObject(boolean instantiate) {
        if (instantiate) {
            marketTransactionItems = new ArrayList<>();
        }
    }

    public List<MarketTransactionsItem> getMarketTransactionItems() {
        return marketTransactionItems;
    }

    public void setMarketTransactionItems(List<MarketTransactionsItem> marketTransactionItems) {
        this.marketTransactionItems = marketTransactionItems;
    }
}
