package livelihoodzone.dto.questionnaire;

public class WealthGroupQuestionnaireRequestDto {
    private String uniqueId;
    private String questionnaireName;
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
}
