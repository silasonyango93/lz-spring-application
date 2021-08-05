package livelihoodzone.service.questionnaire;

import com.google.gson.Gson;
import livelihoodzone.dto.questionnaire.QuestionnaireResponseDto;
import livelihoodzone.dto.questionnaire.WealthGroupQuestionnaireRequestDto;
import livelihoodzone.entity.questionnaire.QuestionnaireResponseStatus;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.entity.user_management.User;
import livelihoodzone.repository.questionnaire.wealthgroup.WealthGroupRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.WgQuestionnaireSessionRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.WgQuestionnaireTypesRepository;
import livelihoodzone.service.questionnaire.wealthgroup.cropcontribution.CropContributionService;
import livelihoodzone.service.questionnaire.wealthgroup.income_food_sources.IncomeFoodSourcesService;
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

    @Autowired
    WgQuestionnaireTypesRepository wgQuestionnaireTypesRepository;

    @Autowired
    IncomeFoodSourcesService incomeFoodSourcesService;

    @Autowired
    CropContributionService cropContributionService;

    public QuestionnaireResponseDto processQuestionnaire(WealthGroupQuestionnaireRequestDto wealthGroupQuestionnaireRequestDto, User dataCollector) {

        Gson gson = new Gson();
        String questionnaireJsonString = gson.toJson(wealthGroupQuestionnaireRequestDto);

        List<WgQuestionnaireSessionEntity> existingQuestionnaires = wgQuestionnaireSessionRepository.findByQuestionnaireUniqueId(wealthGroupQuestionnaireRequestDto.getUniqueId());

        if (existingQuestionnaires.size() > 0) {
            return new QuestionnaireResponseDto(
                    QuestionnaireResponseStatus.DUPLICATE,
                    "Duplicate questionnaire",
                    null,
                    2
            );
        }

        WgQuestionnaireSessionEntity questionnaireSession = new WgQuestionnaireSessionEntity();
        questionnaireSession.setUserId(dataCollector.getUserId());
        questionnaireSession.setWealthGroupId(wealthGroupRepository.findByWealthGroupCode(wealthGroupQuestionnaireRequestDto.getQuestionnaireGeography().getSelectedWealthGroup().getWealthGroupCode()).getWealthGroupId());
        questionnaireSession.setCountyId(dataCollector.getCountyId());
        questionnaireSession.setSubCountyId(wealthGroupQuestionnaireRequestDto.getQuestionnaireGeography().getSelectedSubCounty().getSubCountyId());
        questionnaireSession.setWardId(wealthGroupQuestionnaireRequestDto.getQuestionnaireGeography().getSelectedWard().getWardId());
        questionnaireSession.setSubLocationId(wealthGroupQuestionnaireRequestDto.getQuestionnaireGeography().getSelectedSubLocation().getSubLocationId());
        questionnaireSession.setLivelihoodZoneId(wealthGroupQuestionnaireRequestDto.getQuestionnaireGeography().getSelectedLivelihoodZone().getLivelihoodZoneId());
        questionnaireSession.setQuestionnaireSessionDescription(wealthGroupQuestionnaireRequestDto.getQuestionnaireName());
        questionnaireSession.setLatitude(wealthGroupQuestionnaireRequestDto.getQuestionnaireGeography().getLatitude());
        questionnaireSession.setLongitude(wealthGroupQuestionnaireRequestDto.getQuestionnaireGeography().getLongitude());
        questionnaireSession.setSessionStartDate(wealthGroupQuestionnaireRequestDto.getQuestionnaireStartDate());
        questionnaireSession.setSessionEndDate(wealthGroupQuestionnaireRequestDto.getQuestionnaireEndDate());
        questionnaireSession.setHasBeenSubmitted(1);
        questionnaireSession.setQuestionnaireUniqueId(wealthGroupQuestionnaireRequestDto.getUniqueId());
        questionnaireSession.setWgQuestionnaireTypeId(wgQuestionnaireTypesRepository.findByWgQuestionnaireTypeCode(wealthGroupQuestionnaireRequestDto.getQuestionnaireGeography().getSelectedWgQuestionnaireType().getWgQuestionnaireTypeCode()).getWgQuestionnaireTypeId());
        questionnaireSession.setQuestionnaireJsonString(questionnaireJsonString);

        WgQuestionnaireSessionEntity savedQuestionnaireSessionEntity = wgQuestionnaireSessionRepository.save(questionnaireSession);

        /*Process Questionnaire Sections and Commit to db ***********************************************************************************************/


        incomeFoodSourcesService.saveIncomeSources(wealthGroupQuestionnaireRequestDto,savedQuestionnaireSessionEntity);
        incomeFoodSourcesService.saveFoodSources(wealthGroupQuestionnaireRequestDto,savedQuestionnaireSessionEntity);
        cropContributionService.saveCropContribution(wealthGroupQuestionnaireRequestDto,savedQuestionnaireSessionEntity);

        /* ***********************************************************************************************************************************************/

        return new QuestionnaireResponseDto(
                QuestionnaireResponseStatus.ACCEPTED,
                "Questionnaire submitted successfully",
                wealthGroupQuestionnaireRequestDto.getUniqueId(),
                2
        );
    }
}
