package livelihoodzone.dto.reports.zonal;

import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;

import java.util.ArrayList;
import java.util.List;

public class LzIncompleteQuestionnaireResponseDto {
    private LzQuestionnaireSessionEntity lzQuestionnaireSessionEntity;
    private List<String> missingQuestionnaireSections;

    public LzIncompleteQuestionnaireResponseDto() {
    }

    public LzIncompleteQuestionnaireResponseDto(LzQuestionnaireSessionEntity lzQuestionnaireSessionEntity) {
        this.lzQuestionnaireSessionEntity = lzQuestionnaireSessionEntity;
        this.missingQuestionnaireSections = new ArrayList<>();
    }

    public LzQuestionnaireSessionEntity getLzQuestionnaireSessionEntity() {
        return lzQuestionnaireSessionEntity;
    }

    public void setLzQuestionnaireSessionEntity(LzQuestionnaireSessionEntity lzQuestionnaireSessionEntity) {
        this.lzQuestionnaireSessionEntity = lzQuestionnaireSessionEntity;
    }

    public List<String> getMissingQuestionnaireSections() {
        return missingQuestionnaireSections;
    }

    public void setMissingQuestionnaireSections(List<String> missingQuestionnaireSections) {
        this.missingQuestionnaireSections = missingQuestionnaireSections;
    }
}
