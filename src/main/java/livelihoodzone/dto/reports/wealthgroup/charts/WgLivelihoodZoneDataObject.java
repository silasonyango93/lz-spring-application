package livelihoodzone.dto.reports.wealthgroup.charts;

import livelihoodzone.dto.questionnaire.wealthgroup.constraints.ConstraintsResponses;
import livelihoodzone.dto.questionnaire.wealthgroup.cropcontribution.WgCropContributionResponseItem;
import livelihoodzone.dto.questionnaire.wealthgroup.expenditurepatterns.ExpenditurePatternsResponses;
import livelihoodzone.dto.questionnaire.wealthgroup.foodconsumption.FoodConsumptionResponsesDto;
import livelihoodzone.dto.questionnaire.wealthgroup.incomefoodsources.IncomeAndFoodSourcesResponses;
import livelihoodzone.dto.questionnaire.wealthgroup.labourpatterns.LabourPatternResponses;
import livelihoodzone.dto.questionnaire.wealthgroup.livestockownership.LivestockPoultryOwnershipResponses;
import livelihoodzone.dto.questionnaire.wealthgroup.livestockpoultrycontributions.LivestockContributionResponses;
import livelihoodzone.dto.questionnaire.wealthgroup.migrationpatterns.MigrationPatternResponses;
import livelihoodzone.entity.administrative_boundaries.sublocation.SubLocationEntity;

import java.util.List;

public class WgLivelihoodZoneDataObject {
    private int livelihoodZoneId;
    private String livelihoodZoneName;
    private int wealthGroupId;
    private String wealthGroupName;
    private String countyName;
    private IncomeAndFoodSourcesResponses incomeAndFoodSourcesResponses;
    private FoodConsumptionResponsesDto foodConsumptionPercentages;
    private WgCropContributionChartObject cropProduction;
    private LivestockPoultryOwnershipResponses livestockAndPoultryOwnership;
    private LivestockContributionResponses livestockContributions;
    private LabourPatternResponses labourPatterns;
    private ExpenditurePatternsResponses expenditurePatterns;
    private MigrationPatternResponses settlementAndmigrationPatterns;
    private ConstraintsResponses constraintsToMainEconomicActivities;
    private List<SubLocationEntity> subLocationsUnderTheLivelihoodZone;

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

    public FoodConsumptionResponsesDto getFoodConsumptionPercentages() {
        return foodConsumptionPercentages;
    }

    public void setFoodConsumptionPercentages(FoodConsumptionResponsesDto foodConsumptionPercentages) {
        this.foodConsumptionPercentages = foodConsumptionPercentages;
    }

    public WgCropContributionChartObject getCropProduction() {
        return cropProduction;
    }

    public void setCropProduction(WgCropContributionChartObject cropProduction) {
        this.cropProduction = cropProduction;
    }

    public LivestockPoultryOwnershipResponses getLivestockAndPoultryOwnership() {
        return livestockAndPoultryOwnership;
    }

    public void setLivestockAndPoultryOwnership(LivestockPoultryOwnershipResponses livestockAndPoultryOwnership) {
        this.livestockAndPoultryOwnership = livestockAndPoultryOwnership;
    }

    public LivestockContributionResponses getLivestockContributions() {
        return livestockContributions;
    }

    public void setLivestockContributions(LivestockContributionResponses livestockContributions) {
        this.livestockContributions = livestockContributions;
    }

    public LabourPatternResponses getLabourPatterns() {
        return labourPatterns;
    }

    public void setLabourPatterns(LabourPatternResponses labourPatterns) {
        this.labourPatterns = labourPatterns;
    }

    public ExpenditurePatternsResponses getExpenditurePatterns() {
        return expenditurePatterns;
    }

    public void setExpenditurePatterns(ExpenditurePatternsResponses expenditurePatterns) {
        this.expenditurePatterns = expenditurePatterns;
    }

    public MigrationPatternResponses getSettlementAndmigrationPatterns() {
        return settlementAndmigrationPatterns;
    }

    public void setSettlementAndmigrationPatterns(MigrationPatternResponses settlementAndmigrationPatterns) {
        this.settlementAndmigrationPatterns = settlementAndmigrationPatterns;
    }

    public ConstraintsResponses getConstraintsToMainEconomicActivities() {
        return constraintsToMainEconomicActivities;
    }

    public void setConstraintsToMainEconomicActivities(ConstraintsResponses constraintsToMainEconomicActivities) {
        this.constraintsToMainEconomicActivities = constraintsToMainEconomicActivities;
    }

    public List<SubLocationEntity> getSubLocationsUnderTheLivelihoodZone() {
        return subLocationsUnderTheLivelihoodZone;
    }

    public void setSubLocationsUnderTheLivelihoodZone(List<SubLocationEntity> subLocationsUnderTheLivelihoodZone) {
        this.subLocationsUnderTheLivelihoodZone = subLocationsUnderTheLivelihoodZone;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public int getWealthGroupId() {
        return wealthGroupId;
    }

    public void setWealthGroupId(int wealthGroupId) {
        this.wealthGroupId = wealthGroupId;
    }

    public String getWealthGroupName() {
        return wealthGroupName;
    }

    public void setWealthGroupName(String wealthGroupName) {
        this.wealthGroupName = wealthGroupName;
    }
}
