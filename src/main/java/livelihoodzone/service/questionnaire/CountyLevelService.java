package livelihoodzone.service.questionnaire;

import com.google.gson.Gson;
import livelihoodzone.common.Constants;
import livelihoodzone.dto.livelihoodzones.SampledSubLocationsRequestDto;
import livelihoodzone.dto.questionnaire.CountyDataCollectionProgressReport;
import livelihoodzone.dto.questionnaire.CountyLevelQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.QuestionnaireResponseDto;
import livelihoodzone.dto.questionnaire.county.LzCropProductionResponses;
import livelihoodzone.dto.questionnaire.county.WaterSourcesResponsesDto;
import livelihoodzone.dto.questionnaire.county.WealthGroupCharectaristicsResponses;
import livelihoodzone.dto.questionnaire.county.WealthGroupPercentageResponse;
import livelihoodzone.dto.questionnaire.county.model.cropproduction.WgCropProductionResponseItem;
import livelihoodzone.dto.questionnaire.county.model.hunger.HungerPatternsResponses;
import livelihoodzone.dto.reports.zonal.charts.LzLivelihoodZoneDataObject;
import livelihoodzone.entity.questionnaire.QuestionnaireResponseStatus;
import livelihoodzone.entity.questionnaire.county.*;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.ZoneLevelSampledSubLocationsEntity;
import livelihoodzone.entity.questionnaire.livelihoodzones.CountyLivelihoodZonesAssignmentEntity;
import livelihoodzone.entity.questionnaire.livelihoodzones.SubLocationsLivelihoodZoneAssignmentEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.entity.user_management.User;
import livelihoodzone.repository.questionnaire.county.*;
import livelihoodzone.repository.questionnaire.livelihoodzones.CountyLivelihoodZonesAssignmentRepository;
import livelihoodzone.repository.questionnaire.livelihoodzones.LivelihoodZonesRepository;
import livelihoodzone.repository.questionnaire.livelihoodzones.SubLocationsLivelihoodZoneAssignmentRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.WealthGroupRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.WgQuestionnaireSessionRepository;
import livelihoodzone.service.questionnaire.zonelevel.LzEthnicGroupsService;
import livelihoodzone.service.questionnaire.zonelevel.LzHazardsService;
import livelihoodzone.service.questionnaire.zonelevel.LzMarketTransactionsService;
import livelihoodzone.service.questionnaire.zonelevel.SeasonalCalendarService;
import livelihoodzone.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
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

    @Autowired
    LzMarketTransactionsService lzMarketTransactionsService;

    @Autowired
    ZoneLevelSampledSubLocationsRepository zoneLevelSampledSubLocationsRepository;

    @Autowired
    SubLocationsLivelihoodZoneAssignmentRepository subLocationsLivelihoodZoneAssignmentRepository;

    @Autowired
    CountyLivelihoodZonesAssignmentRepository countyLivelihoodZonesAssignmentRepository;

    @Autowired
    LivelihoodZonesRepository livelihoodZonesRepository;

    @Autowired
    WgQuestionnaireSessionRepository wgQuestionnaireSessionRepository;

    public QuestionnaireResponseDto submitCountyLevelQuestionnaire(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, User dataCollector) {

        Gson gson = new Gson();
        String questionnaireJsonString = gson.toJson(new CountyLevelQuestionnaireRequestDto());
        System.out.println(gson.toJson(countyLevelQuestionnaireRequestDto));
        List<LzQuestionnaireSessionEntity> existingQuestionnaires = lzQuestionnaireSessionRepository.findByLzQuestionnaireUniqueId(countyLevelQuestionnaireRequestDto.getUniqueId());

        if (existingQuestionnaires.size() > 0) {
            return new QuestionnaireResponseDto(QuestionnaireResponseStatus.DUPLICATE, "Duplicate questionnaire", null, 1);
        }

        LzQuestionnaireSessionEntity savedQuestionnaireSession = lzQuestionnaireSessionRepository.save(new LzQuestionnaireSessionEntity(dataCollector.getUserId(), dataCollector.getCountyId(), countyLevelQuestionnaireRequestDto.getSelectedLivelihoodZone().getLivelihoodZoneId(), countyLevelQuestionnaireRequestDto.getQuestionnaireName(), countyLevelQuestionnaireRequestDto.getLatitude(), countyLevelQuestionnaireRequestDto.getLongitude(), countyLevelQuestionnaireRequestDto.getQuestionnaireStartDate() != null ? countyLevelQuestionnaireRequestDto.getQuestionnaireStartDate() : Util.getNow(), countyLevelQuestionnaireRequestDto.getQuestionnaireEndDate() != null ? countyLevelQuestionnaireRequestDto.getQuestionnaireEndDate() : Util.getNow(), countyLevelQuestionnaireRequestDto.getUniqueId(), questionnaireJsonString));

        /* QUESTIONNAIRE PROCESSING SECTION ************************************************************************/

        saveSampledSubLocations(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
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
        seasonalCalendarService.saveLandPreparationMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        seasonalCalendarService.saveHarvestingMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        seasonalCalendarService.saveAgricultureCasualLabour(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        seasonalCalendarService.saveNonAgricultureCasualLabourAvailability(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        lzMarketTransactionsService.saveMarketTransactions(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        seasonalCalendarService.saveCasualLabourWages(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        seasonalCalendarService.saveRemittances(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        seasonalCalendarService.saveFishingMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        seasonalCalendarService.saveMarketAccessMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        seasonalCalendarService.saveDiseaseOutBreakMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        seasonalCalendarService.saveWaterStressMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        seasonalCalendarService.saveConflictRisksMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        seasonalCalendarService.saveCeremonyMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        seasonalCalendarService.saveLeanSeasonMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
        seasonalCalendarService.saveFoodSecurityAssessmentMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);

        /***********************************************************************************************************/

        return new QuestionnaireResponseDto(QuestionnaireResponseStatus.ACCEPTED, "Questionnaire submitted successfully", countyLevelQuestionnaireRequestDto.getUniqueId(), 1);
    }


    public void saveSampledSubLocations(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        List<SampledSubLocationsRequestDto> sampledSubLocations = countyLevelQuestionnaireRequestDto.getSampledSubLocations();
        List<ZoneLevelSampledSubLocationsEntity> zoneLevelSampledSubLocationsEntityList = new ArrayList<>();

        for (SampledSubLocationsRequestDto currentSampledSubLocation : sampledSubLocations) {

            SubLocationsLivelihoodZoneAssignmentEntity subLocationsLivelihoodZoneAssignmentEntity = subLocationsLivelihoodZoneAssignmentRepository.findByLzSublocationLivelihoodZoneId(currentSampledSubLocation.getAssignmentId());
            zoneLevelSampledSubLocationsEntityList.add(new ZoneLevelSampledSubLocationsEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), subLocationsLivelihoodZoneAssignmentEntity.getSubLocationId()));
        }
        zoneLevelSampledSubLocationsRepository.saveAll(zoneLevelSampledSubLocationsEntityList);
    }


    public void saveLzWealthGroupcharacteristics(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {

        WealthGroupCharectaristicsResponses wealthGroupCharectaristicsResponses = countyLevelQuestionnaireRequestDto.getWealthGroupCharectariticsResponses();


        /* Save very poor charectaristics*/
        List<LzWealthGroupCharacteristicsEntity> veryPoorCharacteristics = new ArrayList<>();
        for (String currentCharacteristic : wealthGroupCharectaristicsResponses.getVeryPoorCharectaristics()) {
            veryPoorCharacteristics.add(new LzWealthGroupCharacteristicsEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), wealthGroupRepository.findByWealthGroupCode(Constants.VERY_POOR_CODE).getWealthGroupId(), currentCharacteristic));
        }
        lzWealthGroupCharacteristicsRepository.saveAll(veryPoorCharacteristics);

        /* Save poor charectaristics*/
        List<LzWealthGroupCharacteristicsEntity> poorCharacteristics = new ArrayList<>();
        for (String currentCharacteristic : wealthGroupCharectaristicsResponses.getPoorCharectaristics()) {
            poorCharacteristics.add(new LzWealthGroupCharacteristicsEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), wealthGroupRepository.findByWealthGroupCode(Constants.POOR_CODE).getWealthGroupId(), currentCharacteristic));
        }
        lzWealthGroupCharacteristicsRepository.saveAll(poorCharacteristics);

        /* Save medium charectaristics*/
        List<LzWealthGroupCharacteristicsEntity> mediumCharacteristics = new ArrayList<>();
        for (String currentCharacteristic : wealthGroupCharectaristicsResponses.getMediumCharectaristics()) {
            mediumCharacteristics.add(new LzWealthGroupCharacteristicsEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), wealthGroupRepository.findByWealthGroupCode(Constants.MEDIUM_CODE).getWealthGroupId(), currentCharacteristic));
        }
        lzWealthGroupCharacteristicsRepository.saveAll(mediumCharacteristics);

        /* Save better off charectaristics*/
        List<LzWealthGroupCharacteristicsEntity> betterOffCharacteristics = new ArrayList<>();
        for (String currentCharacteristic : wealthGroupCharectaristicsResponses.getBetterOffCharectaristics()) {
            betterOffCharacteristics.add(new LzWealthGroupCharacteristicsEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), wealthGroupRepository.findByWealthGroupCode(Constants.BETTER_OFF_CODE).getWealthGroupId(), currentCharacteristic));
        }
        lzWealthGroupCharacteristicsRepository.saveAll(betterOffCharacteristics);

    }

    private void saveWealthGroupPopulationPercentages(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {

        WealthGroupPercentageResponse wealthGroupPercentageResponse = countyLevelQuestionnaireRequestDto.getWealthGroupResponse();

        lzWealthGroupPopulationPercentageRepository.save(new LzWealthGroupPopulationPercentageEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), wealthGroupRepository.findByWealthGroupCode(Constants.VERY_POOR_CODE).getWealthGroupId(), wealthGroupPercentageResponse.getVerPoorResponse()));

        lzWealthGroupPopulationPercentageRepository.save(new LzWealthGroupPopulationPercentageEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), wealthGroupRepository.findByWealthGroupCode(Constants.POOR_CODE).getWealthGroupId(), wealthGroupPercentageResponse.getPoorResponse()));

        lzWealthGroupPopulationPercentageRepository.save(new LzWealthGroupPopulationPercentageEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), wealthGroupRepository.findByWealthGroupCode(Constants.MEDIUM_CODE).getWealthGroupId(), wealthGroupPercentageResponse.getMediumResponse()));

        lzWealthGroupPopulationPercentageRepository.save(new LzWealthGroupPopulationPercentageEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), wealthGroupRepository.findByWealthGroupCode(Constants.BETTER_OFF_CODE).getWealthGroupId(), wealthGroupPercentageResponse.getBetterOfResponse()));

    }


    private void saveCropProduction(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {

        List<LzCropProductionResponsesEntity> cropProductionResponseItems = new ArrayList<>();

        for (WgCropProductionResponseItem currentItem : countyLevelQuestionnaireRequestDto.getLzCropProductionResponses().getCropProductionResponses()) {

            //Long rains season - rainfed
            LzCropProductionResponsesEntity longRainsRainfed = new LzCropProductionResponsesEntity(currentItem.getCrop().getCropId(), rainySeasonsRepository.findByRainySeasonCode(Constants.LONG_RAINS_SEASON).getRainySeasonId(), cropWaterAccessTypesRepository.findByCropWaterAccessTypeCode(Constants.RAINFED_CROPS).getCropWaterAccessTypeId(), savedQuestionnaireSession.getLzQuestionnaireSessionId(), currentItem.getLongRainsSeason().getRainfedCultivatedAreaPercentage().getValue(), currentItem.getLongRainsSeason().getRainfedAverageYieldPerHa().getValue());

            cropProductionResponseItems.add(longRainsRainfed);


            //Long rains season - irrigated
            LzCropProductionResponsesEntity longRainsIrrigated = new LzCropProductionResponsesEntity(currentItem.getCrop().getCropId(), rainySeasonsRepository.findByRainySeasonCode(Constants.LONG_RAINS_SEASON).getRainySeasonId(), cropWaterAccessTypesRepository.findByCropWaterAccessTypeCode(Constants.IRRIGATED_CROPS).getCropWaterAccessTypeId(), savedQuestionnaireSession.getLzQuestionnaireSessionId(), currentItem.getLongRainsSeason().getIrrigatedCultivatedArea().getValue(), currentItem.getLongRainsSeason().getIrrigatedAverageYieldPerHa().getValue());

            cropProductionResponseItems.add(longRainsIrrigated);


            //Short rains season - rainfed
            LzCropProductionResponsesEntity shortRainsRainfed = new LzCropProductionResponsesEntity(currentItem.getCrop().getCropId(), rainySeasonsRepository.findByRainySeasonCode(Constants.SHORT_RAINS_SEASON).getRainySeasonId(), cropWaterAccessTypesRepository.findByCropWaterAccessTypeCode(Constants.RAINFED_CROPS).getCropWaterAccessTypeId(), savedQuestionnaireSession.getLzQuestionnaireSessionId(), currentItem.getShortRainsSeason().getRainfedCultivatedAreaPercentage().getValue(), currentItem.getShortRainsSeason().getRainfedAverageYieldPerHa().getValue());

            cropProductionResponseItems.add(shortRainsRainfed);


            //Short rains season - irrigated
            LzCropProductionResponsesEntity shortRainsIrrigated = new LzCropProductionResponsesEntity(currentItem.getCrop().getCropId(), rainySeasonsRepository.findByRainySeasonCode(Constants.SHORT_RAINS_SEASON).getRainySeasonId(), cropWaterAccessTypesRepository.findByCropWaterAccessTypeCode(Constants.IRRIGATED_CROPS).getCropWaterAccessTypeId(), savedQuestionnaireSession.getLzQuestionnaireSessionId(), currentItem.getShortRainsSeason().getIrrigatedCultivatedArea().getValue(), currentItem.getShortRainsSeason().getIrrigatedAverageYieldPerHa().getValue());

            cropProductionResponseItems.add(shortRainsIrrigated);
        }

        lzCropProductionResponsesRepository.saveAll(cropProductionResponseItems);

    }

    private void saveWaterSourceResponses(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        WaterSourcesResponsesDto waterSourceResponses = countyLevelQuestionnaireRequestDto.getWaterSourceResponses();

        //Rivers
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), waterSourceRepository.findByWaterSourceCode(Constants.RIVERS).getWaterSourceId(), waterSourceResponses.getRivers().getWetSeasonPopulation(), waterSourceResponses.getRivers().getDrySeasonPopulationResponse(), ""));

        //Traditional Rivers
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), waterSourceRepository.findByWaterSourceCode(Constants.TRADITIONAL_RIVERS).getWaterSourceId(), waterSourceResponses.getTraditionalRiversWells().getWetSeasonPopulation(), waterSourceResponses.getTraditionalRiversWells().getDrySeasonPopulationResponse(), ""));

        //Natural Ponds
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), waterSourceRepository.findByWaterSourceCode(Constants.NATURAL_PONDS).getWaterSourceId(), waterSourceResponses.getNaturalPonds().getWetSeasonPopulation(), waterSourceResponses.getNaturalPonds().getDrySeasonPopulationResponse(), ""));

        //Pans and Dams
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), waterSourceRepository.findByWaterSourceCode(Constants.PANS_AND_DAMS).getWaterSourceId(), waterSourceResponses.getPansAndDams().getWetSeasonPopulation(), waterSourceResponses.getPansAndDams().getDrySeasonPopulationResponse(), ""));

        //Shallow wells
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), waterSourceRepository.findByWaterSourceCode(Constants.SHALLOW_WELLS).getWaterSourceId(), waterSourceResponses.getShallowWells().getWetSeasonPopulation(), waterSourceResponses.getShallowWells().getDrySeasonPopulationResponse(), ""));

        //Boreholes
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), waterSourceRepository.findByWaterSourceCode(Constants.BOREHOLES).getWaterSourceId(), waterSourceResponses.getBoreholes().getWetSeasonPopulation(), waterSourceResponses.getBoreholes().getDrySeasonPopulationResponse(), ""));

        //Springs
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), waterSourceRepository.findByWaterSourceCode(Constants.SPRINGS).getWaterSourceId(), waterSourceResponses.getSprings().getWetSeasonPopulation(), waterSourceResponses.getSprings().getDrySeasonPopulationResponse(), ""));

        //Lakes
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), waterSourceRepository.findByWaterSourceCode(Constants.LAKES).getWaterSourceId(), waterSourceResponses.getLakes().getWetSeasonPopulation(), waterSourceResponses.getLakes().getDrySeasonPopulationResponse(), ""));

        //Rock Catchment
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), waterSourceRepository.findByWaterSourceCode(Constants.ROCK_CATCHMENT).getWaterSourceId(), waterSourceResponses.getRockCatchments().getWetSeasonPopulation(), waterSourceResponses.getRockCatchments().getDrySeasonPopulationResponse(), ""));

        //Piped Water
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), waterSourceRepository.findByWaterSourceCode(Constants.PIPED_WATER).getWaterSourceId(), waterSourceResponses.getPipedWater().getWetSeasonPopulation(), waterSourceResponses.getPipedWater().getDrySeasonPopulationResponse(), ""));

        //Water Trucking
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), waterSourceRepository.findByWaterSourceCode(Constants.WATER_TRUCKING).getWaterSourceId(), waterSourceResponses.getWaterTrucking().getWetSeasonPopulation(), waterSourceResponses.getWaterTrucking().getDrySeasonPopulationResponse(), ""));

        //Roof catchments
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), waterSourceRepository.findByWaterSourceCode(Constants.ROOF_CATCHMENTS).getWaterSourceId(), waterSourceResponses.getRoofCatchments().getWetSeasonPopulation(), waterSourceResponses.getRoofCatchments().getDrySeasonPopulationResponse(), ""));

        //Roof catchments
        lzWaterSourceResponsesRepository.save(new LzWaterSourceResponsesEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), waterSourceRepository.findByWaterSourceCode(Constants.OTHERS).getWaterSourceId(), waterSourceResponses.getOthers().getWetSeasonPopulation(), waterSourceResponses.getOthers().getDrySeasonPopulationResponse(), waterSourceResponses.getOthers().getExtraDescription()));

    }

    private void saveHungerPatterns(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {

        HungerPatternsResponses hungerPatternsResponses = countyLevelQuestionnaireRequestDto.getHungerPatternsResponses();

        //Long rains
        lzHungerPatternsResponsesRepository.save(new LzHungerPatternsResponsesEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), rainySeasonsRepository.findByRainySeasonCode(Constants.LONG_RAINS_SEASON).getRainySeasonId(), hungerPatternsResponses.getLongRainsPeriod()));

        //Short rains
        lzHungerPatternsResponsesRepository.save(new LzHungerPatternsResponsesEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), rainySeasonsRepository.findByRainySeasonCode(Constants.SHORT_RAINS_SEASON).getRainySeasonId(), hungerPatternsResponses.getShortRainsPeriod()));

        //Between end long and begin short
        lzHungerPatternsResponsesRepository.save(new LzHungerPatternsResponsesEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), rainySeasonsRepository.findByRainySeasonCode(Constants.BETWEEN_END_LONG_AND_BEGIN_SHORT).getRainySeasonId(), hungerPatternsResponses.getEndLongBeginShort()));

        //Between end short and begin long
        lzHungerPatternsResponsesRepository.save(new LzHungerPatternsResponsesEntity(savedQuestionnaireSession.getLzQuestionnaireSessionId(), rainySeasonsRepository.findByRainySeasonCode(Constants.BETWEEN_END_SHORT_AND_BEGIN_LONG).getRainySeasonId(), hungerPatternsResponses.getEndShortBeginLong()));
    }


    public CountyDataCollectionProgressReport countyDataCollectionProgressReport(int countyId) {
        CountyDataCollectionProgressReport countyDataCollectionProgressReport = new CountyDataCollectionProgressReport();
        List<CountyLivelihoodZonesAssignmentEntity> alreadyFilledOutZoneLevelLivelihoodZones = new ArrayList<>();
        List<CountyLivelihoodZonesAssignmentEntity> unfilledZoneLevelLivelihoodZones = new ArrayList<>();
        List<CountyLivelihoodZonesAssignmentEntity> alreadyFilledOutWealthGroupLivelihoodZones = new ArrayList<>();
        List<CountyLivelihoodZonesAssignmentEntity> unfilledWealthGroupLivelihoodZones = new ArrayList<>();
        List<CountyLivelihoodZonesAssignmentEntity> countyLivelihoodZonesAssignmentEntityList = countyLivelihoodZonesAssignmentRepository.findByCountyId(countyId);
        unfilledZoneLevelLivelihoodZones.addAll(countyLivelihoodZonesAssignmentEntityList);
        unfilledWealthGroupLivelihoodZones.addAll(countyLivelihoodZonesAssignmentEntityList);

        List<LzQuestionnaireSessionEntity> lzQuestionnaireSessionEntityList = lzQuestionnaireSessionRepository.findByCountyId(countyId);
        List<WgQuestionnaireSessionEntity> wgQuestionnaireSessionEntityList = wgQuestionnaireSessionRepository.findByCountyIdAndWgQuestionnaireTypeId(countyId, 1);


        //Prepare Zone-level report
        for (CountyLivelihoodZonesAssignmentEntity currentLivelihoodZone : countyLivelihoodZonesAssignmentEntityList) {
            for (LzQuestionnaireSessionEntity currentQuestionnaire : lzQuestionnaireSessionEntityList) {
                if (currentQuestionnaire.getLivelihoodZoneId() == currentLivelihoodZone.getLivelihoodZoneId()) {
                    alreadyFilledOutZoneLevelLivelihoodZones.add(currentLivelihoodZone);
                }
            }
        }
        unfilledZoneLevelLivelihoodZones.removeAll(alreadyFilledOutZoneLevelLivelihoodZones);
        countyDataCollectionProgressReport.setZoneLevelDataCollectionCompleted(unfilledZoneLevelLivelihoodZones.isEmpty());
        countyDataCollectionProgressReport.setCompletedZoneLevelQuestionnaires(returnStringList(alreadyFilledOutZoneLevelLivelihoodZones));
        countyDataCollectionProgressReport.setPendingZoneLevelQuestionnaires(returnStringList(unfilledZoneLevelLivelihoodZones));


        //Prepare Wealth Group Report
        List<String> pendingWealthGroupQuestionnaires = new ArrayList<>();
        List<String> completedWealthGroupQuestionnaires = new ArrayList<>();
        for (CountyLivelihoodZonesAssignmentEntity currentLivelihoodZone : countyLivelihoodZonesAssignmentEntityList) {

            List<WgQuestionnaireSessionEntity> veryPoor = wgQuestionnaireSessionEntityList.stream().filter(c -> c.getLivelihoodZoneId() == currentLivelihoodZone.getLivelihoodZoneId() && c.getWealthGroupId() == 1).collect(Collectors.toList());

            List<WgQuestionnaireSessionEntity> poor = wgQuestionnaireSessionEntityList.stream().filter(c -> c.getLivelihoodZoneId() == currentLivelihoodZone.getLivelihoodZoneId() && c.getWealthGroupId() == 2).collect(Collectors.toList());

            List<WgQuestionnaireSessionEntity> medium = wgQuestionnaireSessionEntityList.stream().filter(c -> c.getLivelihoodZoneId() == currentLivelihoodZone.getLivelihoodZoneId() && c.getWealthGroupId() == 3).collect(Collectors.toList());


            List<WgQuestionnaireSessionEntity> betterOff = wgQuestionnaireSessionEntityList.stream().filter(c -> c.getLivelihoodZoneId() == currentLivelihoodZone.getLivelihoodZoneId() && c.getWealthGroupId() == 4).collect(Collectors.toList());

            if (!veryPoor.isEmpty() && !poor.isEmpty() && !medium.isEmpty() && !betterOff.isEmpty()) {
                alreadyFilledOutWealthGroupLivelihoodZones.add(currentLivelihoodZone);
            }

            if (veryPoor.isEmpty()) {
                pendingWealthGroupQuestionnaires.add(livelihoodZonesRepository.findByLivelihoodZoneId(currentLivelihoodZone.getLivelihoodZoneId()).getLivelihoodZoneName() + " Very poor Questionnaire");
            } else {
                completedWealthGroupQuestionnaires.add(livelihoodZonesRepository.findByLivelihoodZoneId(currentLivelihoodZone.getLivelihoodZoneId()).getLivelihoodZoneName() + " Very poor Questionnaire");
            }
            if (poor.isEmpty()) {
                pendingWealthGroupQuestionnaires.add(livelihoodZonesRepository.findByLivelihoodZoneId(currentLivelihoodZone.getLivelihoodZoneId()).getLivelihoodZoneName() + " Poor Questionnaire");
            } else {
                completedWealthGroupQuestionnaires.add(livelihoodZonesRepository.findByLivelihoodZoneId(currentLivelihoodZone.getLivelihoodZoneId()).getLivelihoodZoneName() + " Poor Questionnaire");
            }
            if (medium.isEmpty()) {
                pendingWealthGroupQuestionnaires.add(livelihoodZonesRepository.findByLivelihoodZoneId(currentLivelihoodZone.getLivelihoodZoneId()).getLivelihoodZoneName() + " Medium Questionnaire");
            } else {
                completedWealthGroupQuestionnaires.add(livelihoodZonesRepository.findByLivelihoodZoneId(currentLivelihoodZone.getLivelihoodZoneId()).getLivelihoodZoneName() + " Medium Questionnaire");
            }
            if (betterOff.isEmpty()) {
                pendingWealthGroupQuestionnaires.add(livelihoodZonesRepository.findByLivelihoodZoneId(currentLivelihoodZone.getLivelihoodZoneId()).getLivelihoodZoneName() + " Better Off Questionnaire");
            } else {
                completedWealthGroupQuestionnaires.add(livelihoodZonesRepository.findByLivelihoodZoneId(currentLivelihoodZone.getLivelihoodZoneId()).getLivelihoodZoneName() + " Better Off Questionnaire");
            }
        }

        unfilledWealthGroupLivelihoodZones.removeAll(alreadyFilledOutWealthGroupLivelihoodZones);
        countyDataCollectionProgressReport.setWealthGroupDataCollectionCompleted(unfilledWealthGroupLivelihoodZones.isEmpty());
        countyDataCollectionProgressReport.setCompletedWealthGroupQuestionnaires(completedWealthGroupQuestionnaires);
        countyDataCollectionProgressReport.setPendingWealthGroupQuestionnaires(pendingWealthGroupQuestionnaires);
        return countyDataCollectionProgressReport;
    }

    public List<String> returnStringList(List<CountyLivelihoodZonesAssignmentEntity> list) {
        List<String> stringList = new ArrayList<>();
        for (CountyLivelihoodZonesAssignmentEntity currentAssignment : list) {
            stringList.add(livelihoodZonesRepository.findByLivelihoodZoneId(currentAssignment.getLivelihoodZoneId()).getLivelihoodZoneName());
        }
        return stringList;
    }

    public WealthGroupCharectaristicsResponses fetchWealthGroupCharacteristicsResponses(int countyId, int livelihoodZoneId) {
        List<LzQuestionnaireSessionEntity> lzQuestionnaireSessionEntityList = lzQuestionnaireSessionRepository.findByCountyIdAndLivelihoodZoneId(countyId, livelihoodZoneId);
        if (lzQuestionnaireSessionEntityList.isEmpty()) {
            return null;
        }
        WealthGroupCharectaristicsResponses wealthGroupCharectaristicsResponses = new WealthGroupCharectaristicsResponses();
        LzQuestionnaireSessionEntity lzQuestionnaireSessionEntity = lzQuestionnaireSessionEntityList.get(0);
        wealthGroupCharectaristicsResponses.setLzQuestionnaireSessionEntity(lzQuestionnaireSessionEntity);
        List<LzWealthGroupCharacteristicsEntity> lzWealthGroupCharacteristicsEntityList = lzWealthGroupCharacteristicsRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionEntity.getLzQuestionnaireSessionId());
        List<String> veryPoor = new ArrayList<>();
        List<String> poor = new ArrayList<>();
        List<String> medium = new ArrayList<>();
        List<String> betterOff = new ArrayList<>();
        for (LzWealthGroupCharacteristicsEntity lzWealthGroupCharacteristicsEntity : lzWealthGroupCharacteristicsEntityList) {
            if (lzWealthGroupCharacteristicsEntity.getWealthGroupId() == Constants.VERY_POOR_CODE) {
                veryPoor.add(lzWealthGroupCharacteristicsEntity.getCharectaristicDescription());
            }
            if (lzWealthGroupCharacteristicsEntity.getWealthGroupId() == Constants.POOR_CODE) {
                poor.add(lzWealthGroupCharacteristicsEntity.getCharectaristicDescription());
            }
            if (lzWealthGroupCharacteristicsEntity.getWealthGroupId() == Constants.MEDIUM_CODE) {
                medium.add(lzWealthGroupCharacteristicsEntity.getCharectaristicDescription());
            }
            if (lzWealthGroupCharacteristicsEntity.getWealthGroupId() == Constants.BETTER_OFF_CODE) {
                betterOff.add(lzWealthGroupCharacteristicsEntity.getCharectaristicDescription());
            }
        }
        wealthGroupCharectaristicsResponses.setVeryPoorCharectaristics(veryPoor);
        wealthGroupCharectaristicsResponses.setPoorCharectaristics(poor);
        wealthGroupCharectaristicsResponses.setMediumCharectaristics(medium);
        wealthGroupCharectaristicsResponses.setBetterOffCharectaristics(betterOff);
        return wealthGroupCharectaristicsResponses;
    }

    public void updateWealthGroupCharacteristics(int lzQuestionnaireSessionId, WealthGroupCharectaristicsResponses wealthGroupCharectaristicsResponses) {
        if (wealthGroupCharectaristicsResponses == null) {
            return;
        }
        lzWealthGroupCharacteristicsRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setWealthGroupCharectariticsResponses(wealthGroupCharectaristicsResponses);
        saveLzWealthGroupcharacteristics(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
    }

    public void updateWealthGroupPopulation(int lzQuestionnaireSessionId, WealthGroupPercentageResponse wealthGroupPercentageResponse) {
        lzWealthGroupPopulationPercentageRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setWealthGroupResponse(wealthGroupPercentageResponse);
        saveWealthGroupPopulationPercentages(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
    }

    public void updateCropProduction(int lzQuestionnaireSessionId, LzCropProductionResponses lzCropProductionResponses) {
        lzCropProductionResponsesRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setLzCropProductionResponses(lzCropProductionResponses);
        saveCropProduction(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
    }

    public void updateSourcesOfWater(int lzQuestionnaireSessionId, WaterSourcesResponsesDto waterSourcesResponsesDto) {
        lzWaterSourceResponsesRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setWaterSourceResponses(waterSourcesResponsesDto);
        saveWaterSourceResponses(countyLevelQuestionnaireRequestDto,savedQuestionnaireSession);
    }

    public void updateZoneLevelQuestionnaireSections(List<Number> lzQuestionnaireSectionCodes, LzLivelihoodZoneDataObject lzLivelihoodZoneDataObject) {
        for (Number currentSectionCode : lzQuestionnaireSectionCodes) {
            if (currentSectionCode.intValue() == Constants.WEALTH_GROUP_CHARACTERISTICS) {
                updateWealthGroupCharacteristics(lzLivelihoodZoneDataObject.getLzQuestionnaireSessionEntity().getLzQuestionnaireSessionId(), lzLivelihoodZoneDataObject.getWealthGroupCharectariticsResponses());
            }
            if (currentSectionCode.intValue() == Constants.WEALTH_GROUP_POPULATION_PERCENTAGE) {
                updateWealthGroupPopulation(lzLivelihoodZoneDataObject.getLzQuestionnaireSessionEntity().getLzQuestionnaireSessionId(), lzLivelihoodZoneDataObject.getWealthGroupResponse());
            }
            if (currentSectionCode.intValue() == Constants.LZ_CROP_PRODUCTION) {
                updateCropProduction(lzLivelihoodZoneDataObject.getLzQuestionnaireSessionEntity().getLzQuestionnaireSessionId(), lzLivelihoodZoneDataObject.getLzCropProductionResponses());
            }
            if (currentSectionCode.intValue() == Constants.MAIN_SOURCES_OF_WATER) {
                updateSourcesOfWater(lzLivelihoodZoneDataObject.getLzQuestionnaireSessionEntity().getLzQuestionnaireSessionId(),lzLivelihoodZoneDataObject.getWaterSourceResponses());
            }
        }
    }
}
