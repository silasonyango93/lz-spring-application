package livelihoodzone.dto.questionnaire;

import livelihoodzone.dto.questionnaire.wealthgroup.FgdParticipantModel;
import livelihoodzone.dto.questionnaire.wealthgroup.constraints.ConstraintsResponses;
import livelihoodzone.dto.questionnaire.wealthgroup.copingstrategies.CopingStrategiesResponses;
import livelihoodzone.dto.questionnaire.wealthgroup.cropcontribution.WgCropContributionResponseItem;
import livelihoodzone.dto.questionnaire.wealthgroup.expenditurepatterns.ExpenditurePatternsResponses;
import livelihoodzone.dto.questionnaire.wealthgroup.foodconsumption.FoodConsumptionResponsesDto;
import livelihoodzone.dto.questionnaire.wealthgroup.incomefoodsources.IncomeAndFoodSourcesResponses;
import livelihoodzone.dto.questionnaire.wealthgroup.labourpatterns.LabourPatternResponses;
import livelihoodzone.dto.questionnaire.wealthgroup.livestockownership.LivestockPoultryOwnershipResponses;
import livelihoodzone.dto.questionnaire.wealthgroup.livestockpoultrycontributions.LivestockContributionResponses;
import livelihoodzone.dto.questionnaire.wealthgroup.migrationpatterns.MigrationPatternResponses;

import java.util.List;

public class WealthGroupQuestionnaireRequestDto {
    private String uniqueId;
    private String questionnaireName;
    private String questionnaireStartDate;
    private String questionnaireEndDate;
    private IncomeAndFoodSourcesResponses incomeAndFoodSourceResponses;
    private QuestionnaireGeography questionnaireGeography;
    private List<WgCropContributionResponseItem> cropContributionResponseItems;
    private FoodConsumptionResponsesDto foodConsumptionResponses;
    private LivestockPoultryOwnershipResponses livestockPoultryOwnershipResponses;
    private LivestockContributionResponses livestockContributionResponses;
    private LabourPatternResponses labourPatternResponses;
    private ExpenditurePatternsResponses expenditurePatternsResponses;
    private MigrationPatternResponses migrationPatternResponses;
    private ConstraintsResponses constraintsResponses;
    private CopingStrategiesResponses copingStrategiesResponses;
    private List<FgdParticipantModel> fdgParticipants;

    public WealthGroupQuestionnaireRequestDto() {
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getQuestionnaireName() {
        return questionnaireName;
    }

    public void setQuestionnaireName(String questionnaireName) {
        this.questionnaireName = questionnaireName;
    }

    public IncomeAndFoodSourcesResponses getIncomeAndFoodSourceResponses() {
        return incomeAndFoodSourceResponses;
    }

    public void setIncomeAndFoodSourceResponses(IncomeAndFoodSourcesResponses incomeAndFoodSourceResponses) {
        this.incomeAndFoodSourceResponses = incomeAndFoodSourceResponses;
    }

    public QuestionnaireGeography getQuestionnaireGeography() {
        return questionnaireGeography;
    }

    public void setQuestionnaireGeography(QuestionnaireGeography questionnaireGeography) {
        this.questionnaireGeography = questionnaireGeography;
    }

    public String getQuestionnaireStartDate() {
        return questionnaireStartDate;
    }

    public void setQuestionnaireStartDate(String questionnaireStartDate) {
        this.questionnaireStartDate = questionnaireStartDate;
    }

    public String getQuestionnaireEndDate() {
        return questionnaireEndDate;
    }

    public void setQuestionnaireEndDate(String questionnaireEndDate) {
        this.questionnaireEndDate = questionnaireEndDate;
    }

    public FoodConsumptionResponsesDto getFoodConsumptionResponses() {
        return foodConsumptionResponses;
    }

    public void setFoodConsumptionResponses(FoodConsumptionResponsesDto foodConsumptionResponses) {
        this.foodConsumptionResponses = foodConsumptionResponses;
    }

    public LivestockPoultryOwnershipResponses getLivestockPoultryOwnershipResponses() {
        return livestockPoultryOwnershipResponses;
    }

    public void setLivestockPoultryOwnershipResponses(LivestockPoultryOwnershipResponses livestockPoultryOwnershipResponses) {
        this.livestockPoultryOwnershipResponses = livestockPoultryOwnershipResponses;
    }

    public LivestockContributionResponses getLivestockContributionResponses() {
        return livestockContributionResponses;
    }

    public void setLivestockContributionResponses(LivestockContributionResponses livestockContributionResponses) {
        this.livestockContributionResponses = livestockContributionResponses;
    }

    public LabourPatternResponses getLabourPatternResponses() {
        return labourPatternResponses;
    }

    public void setLabourPatternResponses(LabourPatternResponses labourPatternResponses) {
        this.labourPatternResponses = labourPatternResponses;
    }

    public ExpenditurePatternsResponses getExpenditurePatternsResponses() {
        return expenditurePatternsResponses;
    }

    public void setExpenditurePatternsResponses(ExpenditurePatternsResponses expenditurePatternsResponses) {
        this.expenditurePatternsResponses = expenditurePatternsResponses;
    }

    public MigrationPatternResponses getMigrationPatternResponses() {
        return migrationPatternResponses;
    }

    public void setMigrationPatternResponses(MigrationPatternResponses migrationPatternResponses) {
        this.migrationPatternResponses = migrationPatternResponses;
    }

    public ConstraintsResponses getConstraintsResponses() {
        return constraintsResponses;
    }

    public void setConstraintsResponses(ConstraintsResponses constraintsResponses) {
        this.constraintsResponses = constraintsResponses;
    }

    public CopingStrategiesResponses getCopingStrategiesResponses() {
        return copingStrategiesResponses;
    }

    public void setCopingStrategiesResponses(CopingStrategiesResponses copingStrategiesResponses) {
        this.copingStrategiesResponses = copingStrategiesResponses;
    }

    public List<FgdParticipantModel> getFdgParticipants() {
        return fdgParticipants;
    }

    public void setFdgParticipants(List<FgdParticipantModel> fdgParticipants) {
        this.fdgParticipants = fdgParticipants;
    }

    public List<WgCropContributionResponseItem> getCropContributionResponseItems() {
        return cropContributionResponseItems;
    }

    public void setCropContributionResponseItems(List<WgCropContributionResponseItem> cropContributionResponseItems) {
        this.cropContributionResponseItems = cropContributionResponseItems;
    }
}
