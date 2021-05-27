package livelihoodzone.dto.questionnaire.county.model.watersources;

public class WaterDependenceResponseItem {
    private double wetSeasonPopulation;
    private double drySeasonPopulationResponse;

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
}
