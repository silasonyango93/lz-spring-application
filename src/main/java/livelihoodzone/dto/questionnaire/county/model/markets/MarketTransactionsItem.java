package livelihoodzone.dto.questionnaire.county.model.markets;

public class MarketTransactionsItem {
    private String marketUniqueId;
    private String marketName;
    private boolean livestockTrade;
    private boolean poultryTrade;
    private boolean farmProduceTrade;
    private boolean retailFarmInput;
    private boolean labourExchange;

    public String getMarketUniqueId() {
        return marketUniqueId;
    }

    public void setMarketUniqueId(String marketUniqueId) {
        this.marketUniqueId = marketUniqueId;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public boolean isLivestockTrade() {
        return livestockTrade;
    }

    public void setLivestockTrade(boolean livestockTrade) {
        this.livestockTrade = livestockTrade;
    }

    public boolean isPoultryTrade() {
        return poultryTrade;
    }

    public void setPoultryTrade(boolean poultryTrade) {
        this.poultryTrade = poultryTrade;
    }

    public boolean isFarmProduceTrade() {
        return farmProduceTrade;
    }

    public void setFarmProduceTrade(boolean farmProduceTrade) {
        this.farmProduceTrade = farmProduceTrade;
    }

    public boolean isRetailFarmInput() {
        return retailFarmInput;
    }

    public void setRetailFarmInput(boolean retailFarmInput) {
        this.retailFarmInput = retailFarmInput;
    }

    public boolean isLabourExchange() {
        return labourExchange;
    }

    public void setLabourExchange(boolean labourExchange) {
        this.labourExchange = labourExchange;
    }
}
