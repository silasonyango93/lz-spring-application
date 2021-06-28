package livelihoodzone.dto.questionnaire.county.model.watersources;

public class WaterDependenceResponseItem {
    private double wetSeasonPopulation;
    private double drySeasonPopulationResponse;
    private String extraDescription;

    public double getWetSeasonPopulation() {
        return wetSeasonPopulation;
    }

    public void setWetSeasonPopulation(double wetSeasonPopulation) {
        this.wetSeasonPopulation = wetSeasonPopulation;
    }

    public double getDrySeasonPopulationResponse() {
        return drySeasonPopulationResponse;
    }

    public void setDrySeasonPopulationResponse(double drySeasonPopulationResponse) {
        this.drySeasonPopulationResponse = drySeasonPopulationResponse;
    }

    public String getExtraDescription() {
        return extraDescription;
    }

    public void setExtraDescription(String extraDescription) {
        this.extraDescription = extraDescription;
    }
}
