package livelihoodzone.dto;

import livelihoodzone.dto.questionnaire.QuestionnaireDataObject;

import java.util.List;

public class QuestionnaireUpdateRequestDto {
    private List<Number> questionnaireSectionCodes;
    private QuestionnaireDataObject questionnaireDataObject;

    public QuestionnaireUpdateRequestDto() {
    }

    public List<Number> getQuestionnaireSectionCodes() {
        return questionnaireSectionCodes;
    }

    public void setQuestionnaireSectionCodes(List<Number> questionnaireSectionCodes) {
        this.questionnaireSectionCodes = questionnaireSectionCodes;
    }

    public QuestionnaireDataObject getQuestionnaireDataObject() {
        return questionnaireDataObject;
    }

    public void setQuestionnaireDataObject(QuestionnaireDataObject questionnaireDataObject) {
        this.questionnaireDataObject = questionnaireDataObject;
    }
}
