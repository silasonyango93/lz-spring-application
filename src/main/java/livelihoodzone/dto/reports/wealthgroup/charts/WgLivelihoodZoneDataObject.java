package livelihoodzone.dto.reports.wealthgroup.charts;

import livelihoodzone.dto.questionnaire.wealthgroup.incomefoodsources.IncomeAndFoodSourcesResponses;

public class WgLivelihoodZoneDataObject {
    private int livelihoodZoneId;
    private String livelihoodZoneName;
    private IncomeAndFoodSourcesResponses incomeAndFoodSourcesResponses;

    public int getLivelihoodZoneId() {
        return livelihoodZoneId;
    }

    public void setLivelihoodZoneId(int livelihoodZoneId) {
        this.livelihoodZoneId = livelihoodZoneId;
    }

    public String getLivelihoodZoneName() {
        return livelihoodZoneName;
    }

    public void setLivelihoodZoneName(String livelihoodZoneName) {
        this.livelihoodZoneName = livelihoodZoneName;
    }

    public IncomeAndFoodSourcesResponses getIncomeAndFoodSourcesResponses() {
        return incomeAndFoodSourcesResponses;
    }

    public void setIncomeAndFoodSourcesResponses(IncomeAndFoodSourcesResponses incomeAndFoodSourcesResponses) {
        this.incomeAndFoodSourcesResponses = incomeAndFoodSourcesResponses;
    }
}
