package livelihoodzone.dto.reports.wealthgroup;

public class WealthGroupReportRequestDto {
    private int countyId;
    private int questionnaireTypeId;
    private boolean questionnaireDetails;
    private boolean sourcesOfIncome;
    private boolean sourcesOfFood;
    private boolean cropProduction;
    private boolean livestockAndPoultryOwnership;
    private boolean livestockAndPoultryContributions;
    private boolean labourPatterns;
    private boolean expenditurePatterns;
    private boolean settlementAndMigration;
    private boolean constraintsToEconomicActivities;
    private boolean fgdParticipants;

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public int getQuestionnaireTypeId() {
        return questionnaireTypeId;
    }

    public void setQuestionnaireTypeId(int questionnaireTypeId) {
        this.questionnaireTypeId = questionnaireTypeId;
    }

    public boolean isQuestionnaireDetails() {
        return questionnaireDetails;
    }

    public void setQuestionnaireDetails(boolean questionnaireDetails) {
        this.questionnaireDetails = questionnaireDetails;
    }

    public boolean isSourcesOfIncome() {
        return sourcesOfIncome;
    }

    public void setSourcesOfIncome(boolean sourcesOfIncome) {
        this.sourcesOfIncome = sourcesOfIncome;
    }

    public boolean isSourcesOfFood() {
        return sourcesOfFood;
    }

    public void setSourcesOfFood(boolean sourcesOfFood) {
        this.sourcesOfFood = sourcesOfFood;
    }

    public boolean isCropProduction() {
        return cropProduction;
    }

    public void setCropProduction(boolean cropProduction) {
        this.cropProduction = cropProduction;
    }

    public boolean isLivestockAndPoultryOwnership() {
        return livestockAndPoultryOwnership;
    }

    public void setLivestockAndPoultryOwnership(boolean livestockAndPoultryOwnership) {
        this.livestockAndPoultryOwnership = livestockAndPoultryOwnership;
    }

    public boolean isLivestockAndPoultryContributions() {
        return livestockAndPoultryContributions;
    }

    public void setLivestockAndPoultryContributions(boolean livestockAndPoultryContributions) {
        this.livestockAndPoultryContributions = livestockAndPoultryContributions;
    }

    public boolean isLabourPatterns() {
        return labourPatterns;
    }

    public void setLabourPatterns(boolean labourPatterns) {
        this.labourPatterns = labourPatterns;
    }

    public boolean isExpenditurePatterns() {
        return expenditurePatterns;
    }

    public void setExpenditurePatterns(boolean expenditurePatterns) {
        this.expenditurePatterns = expenditurePatterns;
    }

    public boolean isSettlementAndMigration() {
        return settlementAndMigration;
    }

    public void setSettlementAndMigration(boolean settlementAndMigration) {
        this.settlementAndMigration = settlementAndMigration;
    }

    public boolean isConstraintsToEconomicActivities() {
        return constraintsToEconomicActivities;
    }

    public void setConstraintsToEconomicActivities(boolean constraintsToEconomicActivities) {
        this.constraintsToEconomicActivities = constraintsToEconomicActivities;
    }

    public boolean isFgdParticipants() {
        return fgdParticipants;
    }

    public void setFgdParticipants(boolean fgdParticipants) {
        this.fgdParticipants = fgdParticipants;
    }
}
