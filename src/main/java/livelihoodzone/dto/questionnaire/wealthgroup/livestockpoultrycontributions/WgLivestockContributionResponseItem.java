package livelihoodzone.dto.questionnaire.wealthgroup.livestockpoultrycontributions;

public class WgLivestockContributionResponseItem {
    private LivestockContributionResponseValue incomeRank;
    private LivestockContributionResponseValue incomePercentage;
    private LivestockContributionResponseValue consumptionRank;
    private LivestockContributionResponseValue consumptionPercentage;

    public LivestockContributionResponseValue getIncomeRank() {
        return incomeRank;
    }

    public void setIncomeRank(LivestockContributionResponseValue incomeRank) {
        this.incomeRank = incomeRank;
    }

    public LivestockContributionResponseValue getIncomePercentage() {
        return incomePercentage;
    }

    public void setIncomePercentage(LivestockContributionResponseValue incomePercentage) {
        this.incomePercentage = incomePercentage;
    }

    public LivestockContributionResponseValue getConsumptionRank() {
        return consumptionRank;
    }

    public void setConsumptionRank(LivestockContributionResponseValue consumptionRank) {
        this.consumptionRank = consumptionRank;
    }

    public LivestockContributionResponseValue getConsumptionPercentage() {
        return consumptionPercentage;
    }

    public void setConsumptionPercentage(LivestockContributionResponseValue consumptionPercentage) {
        this.consumptionPercentage = consumptionPercentage;
    }
}
