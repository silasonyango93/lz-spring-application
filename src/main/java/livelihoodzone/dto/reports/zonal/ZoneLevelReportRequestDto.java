package livelihoodzone.dto.reports.zonal;

public class ZoneLevelReportRequestDto {
    private boolean sampleDataCollectionSubLocations;
    private boolean wealthGroupCharacteristics;
    private boolean wealthGroupPopulationDistribution;
    private boolean cropProduction;
    private boolean mainSourcesOfWater;
    private boolean markets;
    private boolean ethnicGroups;
    private boolean patternsOfHunger;
    private boolean hazards;
    private boolean seasonalCalendar;

    public boolean isSampleDataCollectionSubLocations() {
        return sampleDataCollectionSubLocations;
    }

    public void setSampleDataCollectionSubLocations(boolean sampleDataCollectionSubLocations) {
        this.sampleDataCollectionSubLocations = sampleDataCollectionSubLocations;
    }

    public boolean isWealthGroupCharacteristics() {
        return wealthGroupCharacteristics;
    }

    public void setWealthGroupCharacteristics(boolean wealthGroupCharacteristics) {
        this.wealthGroupCharacteristics = wealthGroupCharacteristics;
    }

    public boolean isWealthGroupPopulationDistribution() {
        return wealthGroupPopulationDistribution;
    }

    public void setWealthGroupPopulationDistribution(boolean wealthGroupPopulationDistribution) {
        this.wealthGroupPopulationDistribution = wealthGroupPopulationDistribution;
    }

    public boolean isCropProduction() {
        return cropProduction;
    }

    public void setCropProduction(boolean cropProduction) {
        this.cropProduction = cropProduction;
    }

    public boolean isMainSourcesOfWater() {
        return mainSourcesOfWater;
    }

    public void setMainSourcesOfWater(boolean mainSourcesOfWater) {
        this.mainSourcesOfWater = mainSourcesOfWater;
    }

    public boolean isMarkets() {
        return markets;
    }

    public void setMarkets(boolean markets) {
        this.markets = markets;
    }

    public boolean isEthnicGroups() {
        return ethnicGroups;
    }

    public void setEthnicGroups(boolean ethnicGroups) {
        this.ethnicGroups = ethnicGroups;
    }

    public boolean isPatternsOfHunger() {
        return patternsOfHunger;
    }

    public void setPatternsOfHunger(boolean patternsOfHunger) {
        this.patternsOfHunger = patternsOfHunger;
    }

    public boolean isHazards() {
        return hazards;
    }

    public void setHazards(boolean hazards) {
        this.hazards = hazards;
    }

    public boolean isSeasonalCalendar() {
        return seasonalCalendar;
    }

    public void setSeasonalCalendar(boolean seasonalCalendar) {
        this.seasonalCalendar = seasonalCalendar;
    }
}
