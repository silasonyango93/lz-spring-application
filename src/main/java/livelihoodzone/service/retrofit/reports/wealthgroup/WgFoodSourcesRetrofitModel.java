package livelihoodzone.service.retrofit.reports.wealthgroup;

import com.google.gson.annotations.SerializedName;

public class WgFoodSourcesRetrofitModel {
    @SerializedName("WgQuestionnaireSessionId")
    private int wgQuestionnaireSessionId;

    @SerializedName("SourcePercentage")
    private double sourcePercentage;

    @SerializedName("FoodTypeDescription")
    private String foodTypeDescription;

    @SerializedName("FoodTypeCode")
    private int foodTypeCode;

    @SerializedName("FoodSourceDescription")
    private String foodSourceDescription;

    @SerializedName("FoodSourceCode")
    private int foodSourceCode;

    public int getWgQuestionnaireSessionId() {
        return wgQuestionnaireSessionId;
    }

    public void setWgQuestionnaireSessionId(int wgQuestionnaireSessionId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
    }

    public double getSourcePercentage() {
        return sourcePercentage;
    }

    public void setSourcePercentage(double sourcePercentage) {
        this.sourcePercentage = sourcePercentage;
    }

    public String getFoodTypeDescription() {
        return foodTypeDescription;
    }

    public void setFoodTypeDescription(String foodTypeDescription) {
        this.foodTypeDescription = foodTypeDescription;
    }

    public int getFoodTypeCode() {
        return foodTypeCode;
    }

    public void setFoodTypeCode(int foodTypeCode) {
        this.foodTypeCode = foodTypeCode;
    }

    public String getFoodSourceDescription() {
        return foodSourceDescription;
    }

    public void setFoodSourceDescription(String foodSourceDescription) {
        this.foodSourceDescription = foodSourceDescription;
    }

    public int getFoodSourceCode() {
        return foodSourceCode;
    }

    public void setFoodSourceCode(int foodSourceCode) {
        this.foodSourceCode = foodSourceCode;
    }
}
