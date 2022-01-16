package livelihoodzone.dto.questionnaire.wealthgroup.cropcontribution;

import livelihoodzone.entity.questionnaire.crops.CropsEntity;

public class WgCropContributionResponseItem {
    private CropsEntity cropModel;
    private CropContributionResponseValue cashIncomeRank;
    private CropContributionResponseValue cashIncomeApproxPercentage;
    private CropContributionResponseValue foodConsumptionRank;
    private CropContributionResponseValue foodConsumptionApproxPercentage;
    private Number parameterValue;

    public WgCropContributionResponseItem() {
    }

    public WgCropContributionResponseItem(CropsEntity cropModel, boolean instantiate) {
        if (instantiate) {
            this.cropModel = cropModel;
            cashIncomeRank = new CropContributionResponseValue();
            cashIncomeApproxPercentage = new CropContributionResponseValue();
            foodConsumptionRank = new CropContributionResponseValue();
            foodConsumptionApproxPercentage = new CropContributionResponseValue();
        }
    }

    public CropsEntity getCropModel() {
        return cropModel;
    }

    public void setCropModel(CropsEntity cropModel) {
        this.cropModel = cropModel;
    }

    public CropContributionResponseValue getCashIncomeRank() {
        return cashIncomeRank;
    }

    public void setCashIncomeRank(CropContributionResponseValue cashIncomeRank) {
        this.cashIncomeRank = cashIncomeRank;
    }

    public CropContributionResponseValue getCashIncomeApproxPercentage() {
        return cashIncomeApproxPercentage;
    }

    public void setCashIncomeApproxPercentage(CropContributionResponseValue cashIncomeApproxPercentage) {
        this.cashIncomeApproxPercentage = cashIncomeApproxPercentage;
    }

    public CropContributionResponseValue getFoodConsumptionRank() {
        return foodConsumptionRank;
    }

    public void setFoodConsumptionRank(CropContributionResponseValue foodConsumptionRank) {
        this.foodConsumptionRank = foodConsumptionRank;
    }

    public CropContributionResponseValue getFoodConsumptionApproxPercentage() {
        return foodConsumptionApproxPercentage;
    }

    public void setFoodConsumptionApproxPercentage(CropContributionResponseValue foodConsumptionApproxPercentage) {
        this.foodConsumptionApproxPercentage = foodConsumptionApproxPercentage;
    }

    public Number getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(Number parameterValue) {
        this.parameterValue = parameterValue;
    }
}
