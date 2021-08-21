package livelihoodzone.service.questionnaire;

import com.google.gson.Gson;
import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.CountyLevelQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.QuestionnaireResponseDto;
import livelihoodzone.dto.questionnaire.county.WaterSourcesResponsesDto;
import livelihoodzone.dto.questionnaire.county.WealthGroupCharectaristicsResponses;
import livelihoodzone.dto.questionnaire.county.WealthGroupPercentageResponse;
import livelihoodzone.dto.questionnaire.county.model.cropproduction.WgCropProductionResponseItem;
import livelihoodzone.dto.questionnaire.county.model.hunger.HungerPatternsResponses;
import livelihoodzone.entity.questionnaire.QuestionnaireResponseStatus;
import livelihoodzone.entity.questionnaire.county.*;
import livelihoodzone.entity.user_management.User;
import livelihoodzone.repository.questionnaire.county.*;
import livelihoodzone.repository.questionnaire.wealthgroup.WealthGroupRepository;
import livelihoodzone.service.questionnaire.zonelevel.LzEthnicGroupsService;
import livelihoodzone.service.questionnaire.zonelevel.LzHazardsService;
import livelihoodzone.service.questionnaire.zonelevel.SeasonalCalendarService;
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

    @Autowired
    LzWaterSourceResponsesRepository lzWaterSourceResponsesRepository;

    @Autowired
    WaterSourceRepository waterSourceRepository;

    @Autowired
    LzHungerPatternsResponsesRepository lzHungerPatternsResponsesRepository;

    @Autowired
    LzHazardsService lzHazardsService;

    @Autowired
    SeasonalCalendarService seasonalCalendarService;

    @Autowired
    LzEthnicGroupsService lzEthnicGroupsService;

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
        saveWaterSourceResponses(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        saveHungerPatterns(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        lzEthnicGroupsService.saveEthnicGroups(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        lzHazardsService.saveHazards(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        seasonalCalendarService.saveSeasonMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        seasonalCalendarService.saveLivestockMigration(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        seasonalCalendarService.saveMilkProduction(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        seasonalCalendarService.saveCalvingMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        seasonalCalendarService.saveKiddingMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        seasonalCalendarService.saveFoodPricesMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        seasonalCalendarService.saveLivestockPricesMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        seasonalCalendarService.savePlantingMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);

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

    private void saveWaterSourceResponses(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        WaterSourcesResponsesDto waterSourceResponses = countyLevelQuestionnaireRequestDto.getWaterSourceResponses();

        //Rivers
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                waterSourceRepository.findByWaterSourceCode(Constants.RIVERS).getWaterSourceId(),
                waterSourceResponses.getRivers().getWetSeasonPopulation(),
                waterSourceResponses.getRivers().getDrySeasonPopulationResponse(),
                ""
        ));

        //Traditional Rivers
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                waterSourceRepository.findByWaterSourceCode(Constants.TRADITIONAL_RIVERS).getWaterSourceId(),
                waterSourceResponses.getTraditionalRiversWells().getWetSeasonPopulation(),
                waterSourceResponses.getTraditionalRiversWells().getDrySeasonPopulationResponse(),
                ""
        ));

        //Natural Ponds
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                waterSourceRepository.findByWaterSourceCode(Constants.NATURAL_PONDS).getWaterSourceId(),
                waterSourceResponses.getNaturalPonds().getWetSeasonPopulation(),
                waterSourceResponses.getNaturalPonds().getDrySeasonPopulationResponse(),
                ""
        ));

        //Pans and Dams
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                waterSourceRepository.findByWaterSourceCode(Constants.PANS_AND_DAMS).getWaterSourceId(),
                waterSourceResponses.getPansAndDams().getWetSeasonPopulation(),
                waterSourceResponses.getPansAndDams().getDrySeasonPopulationResponse(),
                ""
        ));

        //Shallow wells
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                waterSourceRepository.findByWaterSourceCode(Constants.SHALLOW_WELLS).getWaterSourceId(),
                waterSourceResponses.getShallowWells().getWetSeasonPopulation(),
                waterSourceResponses.getShallowWells().getDrySeasonPopulationResponse(),
                ""
        ));

        //Boreholes
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                waterSourceRepository.findByWaterSourceCode(Constants.BOREHOLES).getWaterSourceId(),
                waterSourceResponses.getBoreholes().getWetSeasonPopulation(),
                waterSourceResponses.getBoreholes().getDrySeasonPopulationResponse(),
                ""
        ));

        //Springs
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                waterSourceRepository.findByWaterSourceCode(Constants.SPRINGS).getWaterSourceId(),
                waterSourceResponses.getSprings().getWetSeasonPopulation(),
                waterSourceResponses.getSprings().getDrySeasonPopulationResponse(),
                ""
        ));

        //Lakes
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                waterSourceRepository.findByWaterSourceCode(Constants.LAKES).getWaterSourceId(),
                waterSourceResponses.getLakes().getWetSeasonPopulation(),
                waterSourceResponses.getLakes().getDrySeasonPopulationResponse(),
                ""
        ));

        //Rock Catchment
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                waterSourceRepository.findByWaterSourceCode(Constants.ROCK_CATCHMENT).getWaterSourceId(),
                waterSourceResponses.getRockCatchments().getWetSeasonPopulation(),
                waterSourceResponses.getRockCatchments().getDrySeasonPopulationResponse(),
                ""
        ));

        //Piped Water
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                waterSourceRepository.findByWaterSourceCode(Constants.PIPED_WATER).getWaterSourceId(),
                waterSourceResponses.getPipedWater().getWetSeasonPopulation(),
                waterSourceResponses.getPipedWater().getDrySeasonPopulationResponse(),
                ""
        ));

        //Water Trucking
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                waterSourceRepository.findByWaterSourceCode(Constants.WATER_TRUCKING).getWaterSourceId(),
                waterSourceResponses.getWaterTrucking().getWetSeasonPopulation(),
                waterSourceResponses.getWaterTrucking().getDrySeasonPopulationResponse(),
                ""
        ));

        //Roof catchments
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                waterSourceRepository.findByWaterSourceCode(Constants.ROOF_CATCHMENTS).getWaterSourceId(),
                waterSourceResponses.getRoofCatchments().getWetSeasonPopulation(),
                waterSourceResponses.getRoofCatchments().getDrySeasonPopulationResponse(),
                ""
        ));

        //Roof catchments
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                waterSourceRepository.findByWaterSourceCode(Constants.OTHERS).getWaterSourceId(),
                waterSourceResponses.getOthers().getWetSeasonPopulation(),
                waterSourceResponses.getOthers().getDrySeasonPopulationResponse(),
                waterSourceResponses.getOthers().getExtraDescription()
        ));

    }

    private void saveHungerPatterns(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {

        HungerPatternsResponses hungerPatternsResponses = countyLevelQuestionnaireRequestDto.getHungerPatternsResponses();

        //Long rains
        lzHungerPatternsResponsesRepository.save(new LzHungerPatternsResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                rainySeasonsRepository.findByRainySeasonCode(Constants.LONG_RAINS_SEASON).getRainySeasonId(),
                hungerPatternsResponses.getLongRainsPeriod()
        ));

        //Short rains
        lzHungerPatternsResponsesRepository.save(new LzHungerPatternsResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                rainySeasonsRepository.findByRainySeasonCode(Constants.SHORT_RAINS_SEASON).getRainySeasonId(),
                hungerPatternsResponses.getShortRainsPeriod()
        ));

        //Between end long and begin short
        lzHungerPatternsResponsesRepository.save(new LzHungerPatternsResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                rainySeasonsRepository.findByRainySeasonCode(Constants.BETWEEN_END_LONG_AND_BEGIN_SHORT).getRainySeasonId(),
                hungerPatternsResponses.getEndLongBeginShort()
        ));

        //Between end short and begin long
        lzHungerPatternsResponsesRepository.save(new LzHungerPatternsResponsesEntity(
                savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                rainySeasonsRepository.findByRainySeasonCode(Constants.BETWEEN_END_SHORT_AND_BEGIN_LONG).getRainySeasonId(),
                hungerPatternsResponses.getEndShortBeginLong()
        ));
    }
}
