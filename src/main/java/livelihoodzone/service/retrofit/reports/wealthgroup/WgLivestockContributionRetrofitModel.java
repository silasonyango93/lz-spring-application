package livelihoodzone.service.retrofit.reports.wealthgroup;

import com.google.gson.annotations.SerializedName;

public class WgLivestockContributionRetrofitModel {
    @SerializedName("WgQuestionnaireSessionId")
    private int wgQuestionnaireSessionId;

    @SerializedName("AnimalName")
    private String animalName;

    @SerializedName("AnimalCode")
    private int animalCode;

    @SerializedName("IncomeContributionRank")
    private int incomeContributionRank;

    @SerializedName("IncomeContributionApproxPercentage")
    private double incomeContributionApproxPercentage;

    @SerializedName("ConsumptionContributionRank")
    private int consumptionContributionRank;

    @SerializedName("ConsumptionContributionApproxPercentage")
    private double consumptionContributionApproxPercentage;

    public int getWgQuestionnaireSessionId() {
        return wgQuestionnaireSessionId;
    }

    public void setWgQuestionnaireSessionId(int wgQuestionnaireSessionId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public int getAnimalCode() {
        return animalCode;
    }

    public void setAnimalCode(int animalCode) {
        this.animalCode = animalCode;
    }

    public int getIncomeContributionRank() {
        return incomeContributionRank;
    }

    public void setIncomeContributionRank(int incomeContributionRank) {
        this.incomeContributionRank = incomeContributionRank;
    }

    public double getIncomeContributionApproxPercentage() {
        return incomeContributionApproxPercentage;
    }

    public void setIncomeContributionApproxPercentage(double incomeContributionApproxPercentage) {
        this.incomeContributionApproxPercentage = incomeContributionApproxPercentage;
    }

    public int getConsumptionContributionRank() {
        return consumptionContributionRank;
    }

    public void setConsumptionContributionRank(int consumptionContributionRank) {
        this.consumptionContributionRank = consumptionContributionRank;
    }

    public double getConsumptionContributionApproxPercentage() {
        return consumptionContributionApproxPercentage;
    }

    public void setConsumptionContributionApproxPercentage(double consumptionContributionApproxPercentage) {
        this.consumptionContributionApproxPercentage = consumptionContributionApproxPercentage;
    }
}
