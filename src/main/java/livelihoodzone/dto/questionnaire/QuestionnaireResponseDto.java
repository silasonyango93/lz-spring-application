package livelihoodzone.dto.questionnaire;

import livelihoodzone.entity.questionnaire.QuestionnaireResponseStatus;

public class QuestionnaireResponseDto {
    private QuestionnaireResponseStatus questionnaireResponseStatus;
    private String responseMessage;

    public QuestionnaireResponseDto() {
    }



    public QuestionnaireResponseDto(QuestionnaireResponseStatus questionnaireResponseStatus, String responseMessage) {
        this.questionnaireResponseStatus = questionnaireResponseStatus;
        this.responseMessage = responseMessage;
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
}
