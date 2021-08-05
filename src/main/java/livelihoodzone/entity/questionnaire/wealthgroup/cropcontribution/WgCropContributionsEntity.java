package livelihoodzone.entity.questionnaire.wealthgroup.cropcontribution;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "wg_crop_contributions")
public class WgCropContributionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WgCropContributionId")
    private int wgCropContributionId;

    @Column(name = "WgQuestionnaireSessionId")
    private int wgQuestionnaireSessionId;

    @Column(name = "CropId")
    private int cropId;

    @Column(name = "CashIncomeRank")
    private int cashIncomeRank;

    @Column(name = "CashIncomeApproxPercentage")
    private double cashIncomeApproxPercentage;

    @Column(name = "FoodConsumptionRank")
    private int foodConsumptionRank;

    @Column(name = "FoodConsumptionApproxPercentage")
    private double foodConsumptionApproxPercentage;

    public WgCropContributionsEntity() {
    }

    public WgCropContributionsEntity(int wgQuestionnaireSessionId, int cropId, int cashIncomeRank, double cashIncomeApproxPercentage, int foodConsumptionRank, double foodConsumptionApproxPercentage) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
        this.cropId = cropId;
        this.cashIncomeRank = cashIncomeRank;
        this.cashIncomeApproxPercentage = cashIncomeApproxPercentage;
        this.foodConsumptionRank = foodConsumptionRank;
        this.foodConsumptionApproxPercentage = foodConsumptionApproxPercentage;
    }

    public int getWgCropContributionId() {
        return wgCropContributionId;
    }

    public void setWgCropContributionId(int wgCropContributionId) {
        this.wgCropContributionId = wgCropContributionId;
    }

    public int getWgQuestionnaireSessionId() {
        return wgQuestionnaireSessionId;
    }

    public void setWgQuestionnaireSessionId(int wgQuestionnaireSessionId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
    }

    public int getCropId() {
        return cropId;
    }

    public void setCropId(int cropId) {
        this.cropId = cropId;
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
        return foodConsumptionRank;
    }

    public void setFoodConsumptionRank(int foodConsumptionRank) {
        this.foodConsumptionRank = foodConsumptionRank;
    }

    public double getFoodConsumptionApproxPercentage() {
        return foodConsumptionApproxPercentage;
    }

    public void setFoodConsumptionApproxPercentage(double foodConsumptionApproxPercentage) {
        this.foodConsumptionApproxPercentage = foodConsumptionApproxPercentage;
    }
}
