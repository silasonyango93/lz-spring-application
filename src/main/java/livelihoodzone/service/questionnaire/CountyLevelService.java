package livelihoodzone.service.questionnaire;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.CountyLevelQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.QuestionnaireResponseDto;
import livelihoodzone.dto.questionnaire.county.WealthGroupCharectaristicsResponses;
import livelihoodzone.dto.questionnaire.county.WealthGroupPercentageResponse;
import livelihoodzone.dto.questionnaire.county.model.WgCropProductionResponseItem;
import livelihoodzone.entity.questionnaire.QuestionnaireResponseStatus;
import livelihoodzone.entity.questionnaire.county.LzCropProductionResponsesEntity;
import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.county.LzWealthGroupCharacteristicsEntity;
import livelihoodzone.entity.questionnaire.county.LzWealthGroupPopulationPercentageEntity;
import livelihoodzone.entity.user_management.User;
import livelihoodzone.repository.questionnaire.county.LzCropProductionResponsesRepository;
import livelihoodzone.repository.questionnaire.county.LzQuestionnaireSessionRepository;
import livelihoodzone.repository.questionnaire.county.LzWealthGroupCharacteristicsRepository;
import livelihoodzone.repository.questionnaire.county.LzWealthGroupPopulationPercentageRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.WealthGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountyLevelService {

    @Autowired
    LzQuestionnaireSessionRepository lzQuestionnaireSessionRepository;

    @Autowired
    LzWealthGroupCharacteristicsRepository lzWealthGroupCharacteristicsRepository;

    @Autowired
    WealthGroupRepository wealthGroupRepository;

    @Autowired
    LzWealthGroupPopulationPercentageRepository lzWealthGroupPopulationPercentageRepository;

    @Autowired
    LzCropProductionResponsesRepository lzCropProductionResponsesRepository;

    public QuestionnaireResponseDto submitCountyLevelQuestionnaire(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, User dataCollector) {

        List<LzQuestionnaireSessionEntity> existingQuestionnaires = lzQuestionnaireSessionRepository.findByLzQuestionnaireUniqueId(countyLevelQuestionnaireRequestDto.getUniqueId());

        if (existingQuestionnaires.size() > 0) {
            return new QuestionnaireResponseDto(
                    QuestionnaireResponseStatus.DUPLICATE,
                    "Duplicate questionnaire",
                    null,
                    1
            );
        }

        LzQuestionnaireSessionEntity savedQuestionnaireSession = lzQuestionnaireSessionRepository.save(new LzQuestionnaireSessionEntity(
                dataCollector.getUserId(),
                dataCollector.getCountyId(),
                countyLevelQuestionnaireRequestDto.getSelectedLivelihoodZone().getLivelihoodZoneId(),
                "none",
                countyLevelQuestionnaireRequestDto.getLatitude(),
                countyLevelQuestionnaireRequestDto.getLongitude(),
                countyLevelQuestionnaireRequestDto.getQuestionnaireStartDate(),
                countyLevelQuestionnaireRequestDto.getQuestionnaireEndDate(),
                countyLevelQuestionnaireRequestDto.getUniqueId()
        ));

        /* QUESTIONNAIRE PROCESSING SECTION ************************************************************************/

        saveLzWealthGroupcharacteristics(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        saveWealthGroupPopulationPercentages(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);

        /***********************************************************************************************************/

        return new QuestionnaireResponseDto(
                QuestionnaireResponseStatus.ACCEPTED,
                "Questionnaire submitted successfully",
                countyLevelQuestionnaireRequestDto.getUniqueId(),
                1
        );
    }

    public void saveLzWealthGroupcharacteristics(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {

        WealthGroupCharectaristicsResponses wealthGroupCharectaristicsResponses = countyLevelQuestionnaireRequestDto.getWealthGroupCharectariticsResponses();


        /* Save very poor charectaristics*/
        List<LzWealthGroupCharacteristicsEntity> veryPoorCharacteristics = new ArrayList<>();
        for (String currentCharacteristic : wealthGroupCharectaristicsResponses.getVeryPoorCharectaristics()) {
            veryPoorCharacteristics.add(new LzWealthGroupCharacteristicsEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    wealthGroupRepository.findByWealthGroupCode(Constants.VERY_POOR_CODE).getWealthGroupId(),
                    currentCharacteristic
            ));
        }
        lzWealthGroupCharacteristicsRepository.saveAll(veryPoorCharacteristics);

        /* Save poor charectaristics*/
        List<LzWealthGroupCharacteristicsEntity> poorCharacteristics = new ArrayList<>();
        for (String currentCharacteristic : wealthGroupCharectaristicsResponses.getPoorCharectaristics()) {
            poorCharacteristics.add(new LzWealthGroupCharacteristicsEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    wealthGroupRepository.findByWealthGroupCode(Constants.POOR_CODE).getWealthGroupId(),
                    currentCharacteristic
            ));
        }
        lzWealthGroupCharacteristicsRepository.saveAll(poorCharacteristics);

        /* Save medium charectaristics*/
        List<LzWealthGroupCharacteristicsEntity> mediumCharacteristics = new ArrayList<>();
        for (String currentCharacteristic : wealthGroupCharectaristicsResponses.getMediumCharectaristics()) {
            mediumCharacteristics.add(new LzWealthGroupCharacteristicsEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    wealthGroupRepository.findByWealthGroupCode(Constants.MEDIUM_CODE).getWealthGroupId(),
                    currentCharacteristic
            ));
        }
        lzWealthGroupCharacteristicsRepository.saveAll(mediumCharacteristics);

        /* Save better off charectaristics*/
        List<LzWealthGroupCharacteristicsEntity> betterOffCharacteristics = new ArrayList<>();
        for (String currentCharacteristic : wealthGroupCharectaristicsResponses.getBetterOffCharectaristics()) {
            betterOffCharacteristics.add(new LzWealthGroupCharacteristicsEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    wealthGroupRepository.findByWealthGroupCode(Constants.BETTER_OFF_CODE).getWealthGroupId(),
                    currentCharacteristic
            ));
        }
        lzWealthGroupCharacteristicsRepository.saveAll(betterOffCharacteristics);

    }

    private void saveWealthGroupPopulationPercentages(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {

        WealthGroupPercentageResponse wealthGroupPercentageResponse = countyLevelQuestionnaireRequestDto.getWealthGroupResponse();

        lzWealthGroupPopulationPercentageRepository.save(new LzWealthGroupPopulationPercentageEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                wealthGroupRepository.findByWealthGroupCode(Constants.VERY_POOR_CODE).getWealthGroupId(),
                wealthGroupPercentageResponse.getVerPoorResponse()
        ));

        lzWealthGroupPopulationPercentageRepository.save(new LzWealthGroupPopulationPercentageEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                wealthGroupRepository.findByWealthGroupCode(Constants.POOR_CODE).getWealthGroupId(),
                wealthGroupPercentageResponse.getPoorResponse()
        ));

        lzWealthGroupPopulationPercentageRepository.save(new LzWealthGroupPopulationPercentageEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                wealthGroupRepository.findByWealthGroupCode(Constants.MEDIUM_CODE).getWealthGroupId(),
                wealthGroupPercentageResponse.getMediumResponse()
        ));

        lzWealthGroupPopulationPercentageRepository.save(new LzWealthGroupPopulationPercentageEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                wealthGroupRepository.findByWealthGroupCode(Constants.BETTER_OFF_CODE).getWealthGroupId(),
                wealthGroupPercentageResponse.getBetterOfResponse()
        ));

    }


    private void saveCropProduction(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {

//        for (WgCropProductionResponseItem currentItem : countyLevelQuestionnaireRequestDto.getLzCropProductionResponses().getCropProductionResponses()) {
//            lzCropProductionResponsesRepository.save(new LzCropProductionResponsesEntity(
//                    currentItem.getCrop().getCropId(),
//            ));
//        }

    }
}
