package livelihoodzone.dto.questionnaire.wealthgroup.foodconsumption;

public class FoodConsumptionResponseItem {
    private double ownFarm;
    private double marketFoodPurchase;
    private double gifts;

    public double getOwnFarm() {
        return ownFarm;
    }

    public void setOwnFarm(double ownFarm) {
        this.ownFarm = ownFarm;
    }

    public double getMarketFoodPurchase() {
        return marketFoodPurchase;
    }

    public void setMarketFoodPurchase(double marketFoodPurchase) {
        this.marketFoodPurchase = marketFoodPurchase;
    }

    public double getGifts() {
        return gifts;
    }

    public void setGifts(double gifts) {
        this.gifts = gifts;
    }
}
