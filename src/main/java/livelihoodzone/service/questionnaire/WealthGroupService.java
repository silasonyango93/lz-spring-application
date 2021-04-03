package livelihoodzone.service.questionnaire;

import livelihoodzone.dto.questionnaire.QuestionnaireResponseDto;
import livelihoodzone.dto.questionnaire.WealthGroupQuestionnaireRequestDto;
import livelihoodzone.entity.questionnaire.QuestionnaireResponseStatus;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.entity.user_management.User;
import livelihoodzone.repository.questionnaire.wealthgroup.WealthGroupRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.WgQuestionnaireSessionRepository;
import livelihoodzone.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WealthGroupService {

    @Autowired
    WgQuestionnaireSessionRepository wgQuestionnaireSessionRepository;

    @Autowired
    WealthGroupRepository wealthGroupRepository;

    public QuestionnaireResponseDto processQuestionnaire(WealthGroupQuestionnaireRequestDto wealthGroupQuestionnaireRequestDto, User dataCollector) {

        List<WgQuestionnaireSessionEntity> existingQuestionnaires = wgQuestionnaireSessionRepository.findByQuestionnaireUniqueId(wealthGroupQuestionnaireRequestDto.getUniqueId());

        if (existingQuestionnaires.size() > 0) {
            return new QuestionnaireResponseDto(
                    QuestionnaireResponseStatus.DUPLICATE,
                    "Duplicate questionnaire"
            );
        }

        WgQuestionnaireSessionEntity questionnaireSession = new WgQuestionnaireSessionEntity();
        questionnaireSession.setUserId(dataCollector.getUserId());
        questionnaireSession.setWealthGroupId(wealthGroupRepository.findByWealthGroupCode(wealthGroupQuestionnaireRequestDto.getQuestionnaireGeography().getSelectedWealthGroup().getWealthGroupCode()).getWealthGroupId());
        questionnaireSession.setCountyId(dataCollector.getCountyId());
        questionnaireSession.setSubCountyId(wealthGroupQuestionnaireRequestDto.getQuestionnaireGeography().getSelectedSubCounty().getSubCountyId());
        questionnaireSession.setWardId(wealthGroupQuestionnaireRequestDto.getQuestionnaireGeography().getSelectedWard().getWardId());
        questionnaireSession.setSubLocationId(wealthGroupQuestionnaireRequestDto.getQuestionnaireGeography().getSelectedSubLocation().getSubLocationId());
        questionnaireSession.setLivelihoodZoneId(1);
        questionnaireSession.setQuestionnaireSessionDescription(wealthGroupQuestionnaireRequestDto.getQuestionnaireName());
        questionnaireSession.setLatitude(0.0);
        questionnaireSession.setLongitude(0.0);
        questionnaireSession.setSessionStartDate(Util.getToday());
        questionnaireSession.setSessionEndDate(Util.getToday());
        questionnaireSession.setHasBeenSubmitted(1);
        questionnaireSession.setQuestionnaireUniqueId(wealthGroupQuestionnaireRequestDto.getUniqueId());

        wgQuestionnaireSessionRepository.save(questionnaireSession);

        return new QuestionnaireResponseDto(
                QuestionnaireResponseStatus.ACCEPTED,
                "Questionnaire submitted successfully"
        );
    }
}
