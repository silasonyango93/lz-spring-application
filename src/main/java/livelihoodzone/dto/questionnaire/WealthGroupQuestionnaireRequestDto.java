package livelihoodzone.dto.questionnaire;

import livelihoodzone.entity.questionnaire.LivelihoodZonesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireTypesEntity;

public class WealthGroupQuestionnaireRequestDto {
    private String uniqueId;
    private String questionnaireName;
    private String questionnaireStartDate;
    private String questionnaireEndDate;
    private IncomeAndFoodSourcesDto incomeAndFoodSourceResponses;
    private QuestionnaireGeography questionnaireGeography;

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

    public IncomeAndFoodSourcesDto getIncomeAndFoodSourceResponses() {
        return incomeAndFoodSourceResponses;
    }

    public void setIncomeAndFoodSourceResponses(IncomeAndFoodSourcesDto incomeAndFoodSourceResponses) {
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
}
