package livelihoodzone.service.retrofit.reports.wealthgroup;

import com.google.gson.annotations.SerializedName;

public class WgCropContributionRetrofitModel {
    @SerializedName("CropName")
    private String cropName;

    @SerializedName("CashIncomeRank")
    private int cashIncomeRank;

    @SerializedName("CashIncomeApproxPercentage")
    private double cashIncomeApproxPercentage;

    @SerializedName("FoodConsumptionRank")
    private int FoodConsumptionRank;

    @SerializedName("FoodConsumptionApproxPercentage")
    private double foodConsumptionApproxPercentage;

    @SerializedName("WgQuestionnaireSessionId")
    private double wgQuestionnaireSessionId;

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public int getCashIncomeRank() {
        return cashIncomeRank;
    }

    public void setCashIncomeRank(int cashIncomeRank) {
        this.cashIncomeRank = cashIncomeRank;
    }

    public double getCashIncomeApproxPercentage() {
        return cashIncomeApproxPercentage;
    }

    public void setCashIncomeApproxPercentage(double cashIncomeApproxPercentage) {
        this.cashIncomeApproxPercentage = cashIncomeApproxPercentage;
    }

    public int getFoodConsumptionRank() {
        return FoodConsumptionRank;
    }

    public void setFoodConsumptionRank(int foodConsumptionRank) {
        FoodConsumptionRank = foodConsumptionRank;
    }

    public double getFoodConsumptionApproxPercentage() {
        return foodConsumptionApproxPercentage;
    }

    public void setFoodConsumptionApproxPercentage(double foodConsumptionApproxPercentage) {
        this.foodConsumptionApproxPercentage = foodConsumptionApproxPercentage;
    }

    public double getWgQuestionnaireSessionId() {
        return wgQuestionnaireSessionId;
    }

    public void setWgQuestionnaireSessionId(double wgQuestionnaireSessionId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
    }
}
