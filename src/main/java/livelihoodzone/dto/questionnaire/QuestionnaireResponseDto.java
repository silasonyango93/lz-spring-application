package livelihoodzone.dto.questionnaire;

import livelihoodzone.entity.questionnaire.QuestionnaireResponseStatus;

public class QuestionnaireResponseDto {
    private QuestionnaireResponseStatus questionnaireResponseStatus;
    private String responseMessage;
    private String questionnaireUniqueId;
    private int questionnaireType;

    public QuestionnaireResponseDto() {
    }


    public QuestionnaireResponseDto(QuestionnaireResponseStatus questionnaireResponseStatus, String responseMessage, String questionnaireUniqueId, int questionnaireType) {
        this.questionnaireResponseStatus = questionnaireResponseStatus;
        this.responseMessage = responseMessage;
        this.questionnaireUniqueId = questionnaireUniqueId;
        this.questionnaireType = questionnaireType;
    }

    public QuestionnaireResponseStatus getQuestionnaireResponseStatus() {
        return questionnaireResponseStatus;
    }

    public void setQuestionnaireResponseStatus(QuestionnaireResponseStatus questionnaireResponseStatus) {
        this.questionnaireResponseStatus = questionnaireResponseStatus;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getQuestionnaireUniqueId() {
        return questionnaireUniqueId;
    }

    public void setQuestionnaireUniqueId(String questionnaireUniqueId) {
        this.questionnaireUniqueId = questionnaireUniqueId;
    }

    public int getQuestionnaireType() {
        return questionnaireType;
    }

    public void setQuestionnaireType(int questionnaireType) {
        this.questionnaireType = questionnaireType;
    }
}
