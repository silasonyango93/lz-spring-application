package livelihoodzone.dto.reports.wealthgroup;

import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;

import java.util.ArrayList;
import java.util.List;

public class WgIncompleteQuestionnaireResponseDto {
    private WgQuestionnaireSessionEntity wgQuestionnaireSessionEntity;
    private List<String> missingQuestionnaireSections;

    public WgIncompleteQuestionnaireResponseDto() {
    }

    public WgIncompleteQuestionnaireResponseDto(WgQuestionnaireSessionEntity wgQuestionnaireSessionEntity) {
        this.wgQuestionnaireSessionEntity = wgQuestionnaireSessionEntity;
        this.missingQuestionnaireSections = new ArrayList<>();
    }

    public WgQuestionnaireSessionEntity getWgQuestionnaireSessionEntity() {
        return wgQuestionnaireSessionEntity;
    }

    public void setWgQuestionnaireSessionEntity(WgQuestionnaireSessionEntity wgQuestionnaireSessionEntity) {
        this.wgQuestionnaireSessionEntity = wgQuestionnaireSessionEntity;
    }

    public List<String> getMissingQuestionnaireSections() {
        return missingQuestionnaireSections;
    }

    public void setMissingQuestionnaireSections(List<String> missingQuestionnaireSections) {
        this.missingQuestionnaireSections = missingQuestionnaireSections;
    }
}
