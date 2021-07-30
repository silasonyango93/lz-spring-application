package livelihoodzone.service.questionnaire;

import com.google.gson.Gson;
import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.CountyLevelQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.QuestionnaireResponseDto;
import livelihoodzone.dto.questionnaire.county.WealthGroupCharectaristicsResponses;
import livelihoodzone.dto.questionnaire.county.WealthGroupPercentageResponse;
import livelihoodzone.dto.questionnaire.county.model.cropproduction.WgCropProductionResponseItem;
import livelihoodzone.entity.questionnaire.QuestionnaireResponseStatus;
import livelihoodzone.entity.questionnaire.county.LzCropProductionResponsesEntity;
import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.county.LzWealthGroupCharacteristicsEntity;
import livelihoodzone.entity.questionnaire.county.LzWealthGroupPopulationPercentageEntity;
import livelihoodzone.entity.user_management.User;
import livelihoodzone.repository.questionnaire.county.*;
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

    @Autowired
    RainySeasonsRepository rainySeasonsRepository;

    @Autowired
    CropWaterAccessTypesRepository cropWaterAccessTypesRepository;

    public QuestionnaireResponseDto submitCountyLevelQuestionnaire(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, User dataCollector) {

        Gson gson = new Gson();
        String questionnaireJsonString = gson.toJson(countyLevelQuestionnaireRequestDto);
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
                countyLevelQuestionnaireRequestDto.getQuestionnaireName(),
                countyLevelQuestionnaireRequestDto.getLatitude(),
                countyLevelQuestionnaireRequestDto.getLongitude(),
                countyLevelQuestionnaireRequestDto.getQuestionnaireStartDate(),
                countyLevelQuestionnaireRequestDto.getQuestionnaireEndDate(),
                countyLevelQuestionnaireRequestDto.getUniqueId(),
                questionnaireJsonString
        ));

        /* QUESTIONNAIRE PROCESSING SECTION ************************************************************************/

        saveLzWealthGroupcharacteristics(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        saveWealthGroupPopulationPercentages(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        saveCropProduction(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);

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

        List<LzCropProductionResponsesEntity> cropProductionResponseItems = new ArrayList<>();

        for (WgCropProductionResponseItem currentItem : countyLevelQuestionnaireRequestDto.getLzCropProductionResponses().getCropProductionResponses()) {

            //Long rains season - rainfed
            LzCropProductionResponsesEntity longRainsRainfed = new LzCropProductionResponsesEntity(
                    currentItem.getCrop().getCropId(),
                    rainySeasonsRepository.findByRainySeasonCode(Constants.LONG_RAINS_SEASON).getRainySeasonId(),
                    cropWaterAccessTypesRepository.findByCropWaterAccessTypeCode(Constants.RAINFED_CROPS).getCropWaterAccessTypeId(),
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    currentItem.getLongRainsSeason().getRainfedCultivatedAreaPercentage().getValue(),
                    currentItem.getLongRainsSeason().getRainfedAverageYieldPerHa().getValue()
            );

            cropProductionResponseItems.add(longRainsRainfed);


            //Long rains season - irrigated
            LzCropProductionResponsesEntity longRainsIrrigated = new LzCropProductionResponsesEntity(
                    currentItem.getCrop().getCropId(),
                    rainySeasonsRepository.findByRainySeasonCode(Constants.LONG_RAINS_SEASON).getRainySeasonId(),
                    cropWaterAccessTypesRepository.findByCropWaterAccessTypeCode(Constants.IRRIGATED_CROPS).getCropWaterAccessTypeId(),
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    currentItem.getLongRainsSeason().getIrrigatedCultivatedArea().getValue(),
                    currentItem.getLongRainsSeason().getIrrigatedAverageYieldPerHa().getValue()
            );

            cropProductionResponseItems.add(longRainsIrrigated);


            //Short rains season - rainfed
            LzCropProductionResponsesEntity shortRainsRainfed = new LzCropProductionResponsesEntity(
                    currentItem.getCrop().getCropId(),
                    rainySeasonsRepository.findByRainySeasonCode(Constants.SHORT_RAINS_SEASON).getRainySeasonId(),
                    cropWaterAccessTypesRepository.findByCropWaterAccessTypeCode(Constants.RAINFED_CROPS).getCropWaterAccessTypeId(),
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    currentItem.getShortRainsSeason().getRainfedCultivatedAreaPercentage().getValue(),
                    currentItem.getShortRainsSeason().getRainfedAverageYieldPerHa().getValue()
            );

            cropProductionResponseItems.add(shortRainsRainfed);


            //Short rains season - irrigated
            LzCropProductionResponsesEntity shortRainsIrrigated = new LzCropProductionResponsesEntity(
                    currentItem.getCrop().getCropId(),
                    rainySeasonsRepository.findByRainySeasonCode(Constants.SHORT_RAINS_SEASON).getRainySeasonId(),
                    cropWaterAccessTypesRepository.findByCropWaterAccessTypeCode(Constants.IRRIGATED_CROPS).getCropWaterAccessTypeId(),
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    currentItem.getShortRainsSeason().getIrrigatedCultivatedArea().getValue(),
                    currentItem.getShortRainsSeason().getIrrigatedAverageYieldPerHa().getValue()
            );

            cropProductionResponseItems.add(shortRainsIrrigated);
        }

        lzCropProductionResponsesRepository.saveAll(cropProductionResponseItems);

    }
}
