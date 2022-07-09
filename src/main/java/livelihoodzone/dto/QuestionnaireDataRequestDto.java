package livelihoodzone.dto;

public class QuestionnaireDataRequestDto {
    private int questionnaireTypeId;
    private int countyId;
    private int livelihoodZoneId;
    private int wealthGroupId;

    public QuestionnaireDataRequestDto() {
    }

    public int getQuestionnaireTypeId() {
        return questionnaireTypeId;
    }

    public void setQuestionnaireTypeId(int questionnaireTypeId) {
        this.questionnaireTypeId = questionnaireTypeId;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public int getLivelihoodZoneId() {
        return livelihoodZoneId;
    }

    public void setLivelihoodZoneId(int livelihoodZoneId) {
        this.livelihoodZoneId = livelihoodZoneId;
    }

    public int getWealthGroupId() {
        return wealthGroupId;
    }

    public void setWealthGroupId(int wealthGroupId) {
        this.wealthGroupId = wealthGroupId;
    }
}
