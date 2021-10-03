package livelihoodzone.dto.questionnaire.wealthgroup.foodconsumption;

public class FoodConsumptionResponseItem {
    private double ownFarm;
    private double marketFoodPurchase;
    private double gifts;
    private double huntingGatheringFishing;

    public FoodConsumptionResponseItem() {
    }

    public FoodConsumptionResponseItem(double ownFarm, double marketFoodPurchase, double gifts, double huntingGatheringFishing) {
        this.ownFarm = ownFarm;
        this.marketFoodPurchase = marketFoodPurchase;
        this.gifts = gifts;
        this.huntingGatheringFishing = huntingGatheringFishing;
    }

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

    public double getHuntingGatheringFishing() {
        return huntingGatheringFishing;
    }

    public void setHuntingGatheringFishing(double huntingGatheringFishing) {
        this.huntingGatheringFishing = huntingGatheringFishing;
    }
}
