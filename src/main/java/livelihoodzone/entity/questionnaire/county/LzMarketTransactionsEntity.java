package livelihoodzone.entity.questionnaire.county;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "lz_market_trade_responses")
public class LzMarketTransactionsEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LzMarketTradeResponseId")
    private int lzMarketTradeResponseId;

    @Column(name = "LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @Column(name = "MarketId")
    private int marketId;

    @Column(name = "LivestockTrade")
    private int livestockTrade;

    @Column(name = "PoultryTrade")
    private int poultryTrade;

    @Column(name = "FarmProduceTrade")
    private int farmProduceTrade;

    @Column(name = "RetailFarmInput")
    private int retailFarmInput;

    @Column(name = "LabourExchange")
    private int labourExchange;

    public LzMarketTransactionsEntity() {
    }

    public LzMarketTransactionsEntity(int lzQuestionnaireSessionId, int marketId, int livestockTrade, int poultryTrade, int farmProduceTrade, int retailFarmInput, int labourExchange) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
        this.marketId = marketId;
        this.livestockTrade = livestockTrade;
        this.poultryTrade = poultryTrade;
        this.farmProduceTrade = farmProduceTrade;
        this.retailFarmInput = retailFarmInput;
        this.labourExchange = labourExchange;
    }

    public int getLzMarketTradeResponseId() {
        return lzMarketTradeResponseId;
    }

    public void setLzMarketTradeResponseId(int lzMarketTradeResponseId) {
        this.lzMarketTradeResponseId = lzMarketTradeResponseId;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public int getLzQuestionnaireSessionId() {
        return lzQuestionnaireSessionId;
    }

    public void setLzQuestionnaireSessionId(int lzQuestionnaireSessionId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
    }

    public int getLivestockTrade() {
        return livestockTrade;
    }

    public void setLivestockTrade(int livestockTrade) {
        this.livestockTrade = livestockTrade;
    }

    public int getPoultryTrade() {
        return poultryTrade;
    }

    public void setPoultryTrade(int poultryTrade) {
        this.poultryTrade = poultryTrade;
    }

    public int getFarmProduceTrade() {
        return farmProduceTrade;
    }

    public void setFarmProduceTrade(int farmProduceTrade) {
        this.farmProduceTrade = farmProduceTrade;
    }

    public int getRetailFarmInput() {
        return retailFarmInput;
    }

    public void setRetailFarmInput(int retailFarmInput) {
        this.retailFarmInput = retailFarmInput;
    }

    public int getLabourExchange() {
        return labourExchange;
    }

    public void setLabourExchange(int labourExchange) {
        this.labourExchange = labourExchange;
    }
}
