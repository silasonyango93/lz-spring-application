package livelihoodzone.dto.reports.wealthgroup;

public class IncompleteQuestionnairesRequestDto {
    int countyId;
    int questionnaireTypeId;

    public IncompleteQuestionnairesRequestDto() {
    }

    public IncompleteQuestionnairesRequestDto(int countyId, int questionnaireTypeId) {
        this.countyId = countyId;
        this.questionnaireTypeId = questionnaireTypeId;
    }

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
}
