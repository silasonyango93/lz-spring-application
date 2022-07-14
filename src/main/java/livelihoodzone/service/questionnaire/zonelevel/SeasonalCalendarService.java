package livelihoodzone.service.questionnaire.zonelevel;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.CountyLevelQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.county.LzCropProductionResponses;
import livelihoodzone.dto.questionnaire.county.model.cropproduction.WgCropProductionResponseItem;
import livelihoodzone.dto.questionnaire.county.model.seasons.LzSeasonsResponses;
import livelihoodzone.entity.questionnaire.calendar.MonthsEntity;
import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.*;
import livelihoodzone.repository.questionnaire.county.seasonal_calendar.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeasonalCalendarService {

    @Autowired
    LzSeasonsRepository lzSeasonsRepository;

    @Autowired
    LzSeasonMonthsRepository lzSeasonMonthsRepository;

    @Autowired
    LzMilkProductionRepository lzMilkProductionRepository;

    @Autowired
    HighLowMediumScaleRepository highLowMediumScaleRepository;

    @Autowired
    LivestockMigrationTypesRepository livestockMigrationTypesRepository;

    @Autowired
    LivestockMigrationMonthsRepository livestockMigrationMonthsRepository;

    @Autowired
    LzCalvingRepository lzCalvingRepository;

    @Autowired
    LzKiddingRepository lzKiddingRepository;

    @Autowired
    LzFoodPricesRepository lzFoodPricesRepository;

    @Autowired
    LzLivestockPricesRepository lzLivestockPricesRepository;

    @Autowired
    LzPlantingMonthsRepository lzPlantingMonthsRepository;

    @Autowired
    LzAgricultureCasualLabourAvailabilityRepository lzAgricultureCasualLabourAvailabilityRepository;

    @Autowired
    LzLandPreparationMonthsRepository lzLandPreparationMonthsRepository;

    @Autowired
    LzHarvestingMonthsRepository lzHarvestingMonthsRepository;

    @Autowired
    LzNonAgricCasualLabourAvailabilityRepository lzNonAgricCasualLabourAvailabilityRepository;

    @Autowired
    LzCasualLabourWagesRepository lzCasualLabourWagesRepository;

    @Autowired
    LzRemittancesRepository lzRemittancesRepository;

    @Autowired
    LzFishingMonthsRepository lzFishingMonthsRepository;

    @Autowired
    LzMarketAccessMonthsRepository lzMarketAccessMonthsRepository;

    @Autowired
    LzDiseaseOutBreakMonthsRepository lzDiseaseOutBreakMonthsRepository;

    @Autowired
    LzWaterStressMonthsRepository lzWaterStressMonthsRepository;

    @Autowired
    LzConflictRisksMonthsRepository lzConflictRisksMonthsRepository;

    @Autowired
    LzCeremonyMonthsRepository lzCeremonyMonthsRepository;

    @Autowired
    LzLeanSeasonMonthsRepository lzLeanSeasonMonthsRepository;

    @Autowired
    LzFoodSecurityAssessmentsRepository lzFoodSecurityAssessmentsRepository;

    public void saveSeasonMonths(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        LzSeasonsResponses livelihoodZoneSeasonsResponses = countyLevelQuestionnaireRequestDto.getLivelihoodZoneSeasonsResponses();

        //Dry season
        List<LzSeasonMonthsEntity> drySeasonResponses = new ArrayList<>();

        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getDry()) {
            drySeasonResponses.add(new LzSeasonMonthsEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    lzSeasonsRepository.findByLzSeasonCode(Constants.SC_DRY_SEASON).getLzSeasonId(),
                    currentMonth.getMonthId()
            ));
        }

        lzSeasonMonthsRepository.saveAll(drySeasonResponses);


        //Long rains
        List<LzSeasonMonthsEntity> longRainsResponses = new ArrayList<>();

        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getLongRains()) {
            longRainsResponses.add(new LzSeasonMonthsEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    lzSeasonsRepository.findByLzSeasonCode(Constants.SC_LONG_RAINS).getLzSeasonId(),
                    currentMonth.getMonthId()
            ));
        }

        lzSeasonMonthsRepository.saveAll(longRainsResponses);


        //Short rains
        List<LzSeasonMonthsEntity> shortRainsResponses = new ArrayList<>();

        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getShortRains()) {
            shortRainsResponses.add(new LzSeasonMonthsEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    lzSeasonsRepository.findByLzSeasonCode(Constants.SC_SHORT_RAINS).getLzSeasonId(),
                    currentMonth.getMonthId()
            ));
        }

        lzSeasonMonthsRepository.saveAll(shortRainsResponses);
    }


    public void saveLivestockMigration(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        LzSeasonsResponses livelihoodZoneSeasonsResponses = countyLevelQuestionnaireRequestDto.getLivelihoodZoneSeasonsResponses();

        //In migration
        List<LivestockMigrationMonthsEntity> inMigrationResponses = new ArrayList<>();

        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getLivestockInMigration()) {
            inMigrationResponses.add(new LivestockMigrationMonthsEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    livestockMigrationTypesRepository.findByLivestockMigrationTypeCode(Constants.LM_IN_MIGRATION).getLivestockMigrationTypeId(),
                    currentMonth.getMonthId()
            ));
        }

        livestockMigrationMonthsRepository.saveAll(inMigrationResponses);


        //Out migration
        List<LivestockMigrationMonthsEntity> outMigrationResponses = new ArrayList<>();

        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getLivestockInMigration()) {
            inMigrationResponses.add(new LivestockMigrationMonthsEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    livestockMigrationTypesRepository.findByLivestockMigrationTypeCode(Constants.LM_OUT_MIGRATION).getLivestockMigrationTypeId(),
                    currentMonth.getMonthId()
            ));
        }

        livestockMigrationMonthsRepository.saveAll(outMigrationResponses);
    }


    public void saveMilkProduction(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {

        LzSeasonsResponses livelihoodZoneSeasonsResponses = countyLevelQuestionnaireRequestDto.getLivelihoodZoneSeasonsResponses();

        //High milk production
        List<LzMilkProductionEntity> highMilkProductionResponses = new ArrayList<>();

        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getHighMilkProduction()) {
            highMilkProductionResponses.add(new LzMilkProductionEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.HIGH).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }

        lzMilkProductionRepository.saveAll(highMilkProductionResponses);

        //Low milk production
        List<LzMilkProductionEntity> lowMilkProductionResponses = new ArrayList<>();

        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getHighMilkProduction()) {
            lowMilkProductionResponses.add(new LzMilkProductionEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.LOW).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }

        lzMilkProductionRepository.saveAll(lowMilkProductionResponses);
    }

    public void saveCalvingMonths(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        LzSeasonsResponses livelihoodZoneSeasonsResponses = countyLevelQuestionnaireRequestDto.getLivelihoodZoneSeasonsResponses();

        //High calving
        List<LzCalvingEntity> highCalvingResponses = new ArrayList<>();

        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getHighCalving()) {
            highCalvingResponses.add(new LzCalvingEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.HIGH).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }
        lzCalvingRepository.saveAll(highCalvingResponses);


        //Low calving
        List<LzCalvingEntity> lowCalvingResponses = new ArrayList<>();

        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getLowCalving()) {
            lowCalvingResponses.add(new LzCalvingEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.LOW).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }
        lzCalvingRepository.saveAll(lowCalvingResponses);
    }


    public void saveKiddingMonths(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        LzSeasonsResponses livelihoodZoneSeasonsResponses = countyLevelQuestionnaireRequestDto.getLivelihoodZoneSeasonsResponses();

        //High kidding
        List<LzKiddingEntity> highKiddingResponses = new ArrayList<>();
        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getHighKidding()) {
            highKiddingResponses.add(new LzKiddingEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.HIGH).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }
        lzKiddingRepository.saveAll(highKiddingResponses);

        //Low kidding
        List<LzKiddingEntity> lowKiddingResponses = new ArrayList<>();
        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getLowKidding()) {
            lowKiddingResponses.add(new LzKiddingEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.LOW).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }
        lzKiddingRepository.saveAll(lowKiddingResponses);
    }

    public void saveFoodPricesMonths(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        LzSeasonsResponses livelihoodZoneSeasonsResponses = countyLevelQuestionnaireRequestDto.getLivelihoodZoneSeasonsResponses();

        //High food prices
        List<LzFoodPricesEntity> highFoodPriceResponses = new ArrayList<>();
        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getHighFoodPrices()) {
            highFoodPriceResponses.add(new LzFoodPricesEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.HIGH).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }
        lzFoodPricesRepository.saveAll(highFoodPriceResponses);

        //Medium food prices
        List<LzFoodPricesEntity> mediumFoodPriceResponses = new ArrayList<>();
        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getMediumFoodPrices()) {
            mediumFoodPriceResponses.add(new LzFoodPricesEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.MEDIUM).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }
        lzFoodPricesRepository.saveAll(mediumFoodPriceResponses);

        //Low food prices
        List<LzFoodPricesEntity> lowFoodPriceResponses = new ArrayList<>();
        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getLowFoodPrices()) {
            lowFoodPriceResponses.add(new LzFoodPricesEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.LOW).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }
        lzFoodPricesRepository.saveAll(lowFoodPriceResponses);
    }


    public void saveLivestockPricesMonths(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        LzSeasonsResponses livelihoodZoneSeasonsResponses = countyLevelQuestionnaireRequestDto.getLivelihoodZoneSeasonsResponses();

        //High livestock prices
        List<LzLivestockPricesEntity> highLivestockPriceResponses = new ArrayList<>();
        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getHighLivestockPrices()) {
            highLivestockPriceResponses.add(new LzLivestockPricesEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.HIGH).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }
        lzLivestockPricesRepository.saveAll(highLivestockPriceResponses);

        //Medium livestock prices
        List<LzLivestockPricesEntity> mediumLivestockPriceResponses = new ArrayList<>();
        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getMediumLivestockPrices()) {
            mediumLivestockPriceResponses.add(new LzLivestockPricesEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.MEDIUM).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }
        lzLivestockPricesRepository.saveAll(mediumLivestockPriceResponses);

        //Low livestock prices
        List<LzLivestockPricesEntity> lowLivestockPriceResponses = new ArrayList<>();
        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getLowLivestockPrices()) {
            lowLivestockPriceResponses.add(new LzLivestockPricesEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.LOW).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }
        lzLivestockPricesRepository.saveAll(lowLivestockPriceResponses);
    }


    public void savePlantingMonths(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        LzCropProductionResponses lzCropProductionResponses = countyLevelQuestionnaireRequestDto.getLzCropProductionResponses();
        List<WgCropProductionResponseItem> cropProductionResponses = lzCropProductionResponses.getCropProductionResponses();

        for (WgCropProductionResponseItem currentResponseItem : cropProductionResponses) {
            List<MonthsEntity> plantingPeriod = currentResponseItem.getPlantingPeriod();
            List<LzPlantingMonthsEntity> lzPlantingMonthsEntityList = new ArrayList<>();
            for (MonthsEntity currentMonth : plantingPeriod) {
                lzPlantingMonthsEntityList.add(new LzPlantingMonthsEntity(
                        savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                        currentResponseItem.getCrop().getCropId(),
                        currentMonth.getMonthId()
                ));
            }
            lzPlantingMonthsRepository.saveAll(lzPlantingMonthsEntityList);
        }
    }


    public void saveLandPreparationMonths(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        LzCropProductionResponses lzCropProductionResponses = countyLevelQuestionnaireRequestDto.getLzCropProductionResponses();
        List<WgCropProductionResponseItem> cropProductionResponses = lzCropProductionResponses.getCropProductionResponses();

        for (WgCropProductionResponseItem currentResponseItem : cropProductionResponses) {
            List<MonthsEntity> landPreparationPeriod = currentResponseItem.getLandPreparationPeriod();
            List<LzLandPreparationMonthsEntity> lzLandPreparationMonthsEntityList = new ArrayList<>();
            for (MonthsEntity currentMonth : landPreparationPeriod) {
                lzLandPreparationMonthsEntityList.add(new LzLandPreparationMonthsEntity(
                        savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                        currentResponseItem.getCrop().getCropId(),
                        currentMonth.getMonthId()
                ));
            }
            lzLandPreparationMonthsRepository.saveAll(lzLandPreparationMonthsEntityList);
        }
    }


    public void saveHarvestingMonths(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        LzCropProductionResponses lzCropProductionResponses = countyLevelQuestionnaireRequestDto.getLzCropProductionResponses();
        List<WgCropProductionResponseItem> cropProductionResponses = lzCropProductionResponses.getCropProductionResponses();

        for (WgCropProductionResponseItem currentResponseItem : cropProductionResponses) {
            List<MonthsEntity> harvestingPeriod = currentResponseItem.getHarvestingPeriod();
            List<LzHarvestingMonthsEntity> lzHarvestingMonthsEntityList = new ArrayList<>();
            for (MonthsEntity currentMonth : harvestingPeriod) {
                lzHarvestingMonthsEntityList.add(new LzHarvestingMonthsEntity(
                        savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                        currentResponseItem.getCrop().getCropId(),
                        currentMonth.getMonthId()
                ));
            }
            lzHarvestingMonthsRepository.saveAll(lzHarvestingMonthsEntityList);
        }
    }


    public void saveAgricultureCasualLabour(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        LzSeasonsResponses livelihoodZoneSeasonsResponses = countyLevelQuestionnaireRequestDto.getLivelihoodZoneSeasonsResponses();

        //High
        List<LzAgricultureCasualLabourAvailabilityEntity> highAgricultureWagedLabour = new ArrayList<>();

        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getHighCasualLabourAvailability()) {
            highAgricultureWagedLabour.add(new LzAgricultureCasualLabourAvailabilityEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.HIGH).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }

        lzAgricultureCasualLabourAvailabilityRepository.saveAll(highAgricultureWagedLabour);


        //Low
        List<LzAgricultureCasualLabourAvailabilityEntity> lowAgricultureWagedLabour = new ArrayList<>();

        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getLowCasualLabourAvailability()) {
            lowAgricultureWagedLabour.add(new LzAgricultureCasualLabourAvailabilityEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.LOW).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }

        lzAgricultureCasualLabourAvailabilityRepository.saveAll(lowAgricultureWagedLabour);
    }


    public void saveNonAgricultureCasualLabourAvailability(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        LzSeasonsResponses livelihoodZoneSeasonsResponses = countyLevelQuestionnaireRequestDto.getLivelihoodZoneSeasonsResponses();

        //High
        List<LzNonAgricCasualLabourAvailabilityEntity> highNonAgricultureCasualLabour = new ArrayList<>();
        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getNonAgricHighCasualLabourAvailability()) {
            highNonAgricultureCasualLabour.add(new LzNonAgricCasualLabourAvailabilityEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.HIGH).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }
        lzNonAgricCasualLabourAvailabilityRepository.saveAll(highNonAgricultureCasualLabour);

        //Low
        List<LzNonAgricCasualLabourAvailabilityEntity> lowNonAgricultureCasualLabour = new ArrayList<>();
        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getNonAgricLowCasualLabourAvailability()) {
            lowNonAgricultureCasualLabour.add(new LzNonAgricCasualLabourAvailabilityEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.LOW).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }
        lzNonAgricCasualLabourAvailabilityRepository.saveAll(lowNonAgricultureCasualLabour);
    }


    public void saveCasualLabourWages(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        LzSeasonsResponses livelihoodZoneSeasonsResponses = countyLevelQuestionnaireRequestDto.getLivelihoodZoneSeasonsResponses();

        //High
        List<LzCasualLabourWagesEntity> highCasualLabourWages = new ArrayList<>();
        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getHighCasualLabourWages()) {
            highCasualLabourWages.add(new LzCasualLabourWagesEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.HIGH).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }
        lzCasualLabourWagesRepository.saveAll(highCasualLabourWages);

        //Low
        List<LzCasualLabourWagesEntity> lowCasualLabourWages = new ArrayList<>();
        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getLowCasualLabourWages()) {
            lowCasualLabourWages.add(new LzCasualLabourWagesEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.LOW).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }
        lzCasualLabourWagesRepository.saveAll(lowCasualLabourWages);
    }


    public void saveRemittances(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        LzSeasonsResponses livelihoodZoneSeasonsResponses = countyLevelQuestionnaireRequestDto.getLivelihoodZoneSeasonsResponses();

        //High
        List<LzRemittancesEntity> highRemittance = new ArrayList<>();
        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getHighRemittances()) {
            highRemittance.add(new LzRemittancesEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.HIGH).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }
        lzRemittancesRepository.saveAll(highRemittance);


        //Low
        List<LzRemittancesEntity> lowRemittance = new ArrayList<>();
        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getLowRemittances()) {
            lowRemittance.add(new LzRemittancesEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.LOW).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }
        lzRemittancesRepository.saveAll(lowRemittance);

    }


    public void saveFishingMonths(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        LzSeasonsResponses livelihoodZoneSeasonsResponses = countyLevelQuestionnaireRequestDto.getLivelihoodZoneSeasonsResponses();

        //High
        List<LzFishingMonthsEntity> highRemittance = new ArrayList<>();
        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getHighFish()) {
            highRemittance.add(new LzFishingMonthsEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.HIGH).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }
        lzFishingMonthsRepository.saveAll(highRemittance);

        //Low
        List<LzFishingMonthsEntity> lowRemittance = new ArrayList<>();
        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getLowFish()) {
            lowRemittance.add(new LzFishingMonthsEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.LOW).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }
        lzFishingMonthsRepository.saveAll(lowRemittance);
    }


    public void saveMarketAccessMonths(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        LzSeasonsResponses livelihoodZoneSeasonsResponses = countyLevelQuestionnaireRequestDto.getLivelihoodZoneSeasonsResponses();

        //High
        List<LzMarketAccessMonthsEntity> highMarketAccess = new ArrayList<>();
        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getHighMarketAccess()) {
            highMarketAccess.add(new LzMarketAccessMonthsEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.HIGH).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }
        lzMarketAccessMonthsRepository.saveAll(highMarketAccess);

        //High
        List<LzMarketAccessMonthsEntity> lowMarketAccess = new ArrayList<>();
        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getLowMarketAccess()) {
            lowMarketAccess.add(new LzMarketAccessMonthsEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.LOW).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }
        lzMarketAccessMonthsRepository.saveAll(lowMarketAccess);
    }


    public void saveDiseaseOutBreakMonths(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        LzSeasonsResponses livelihoodZoneSeasonsResponses = countyLevelQuestionnaireRequestDto.getLivelihoodZoneSeasonsResponses();

        //High
        List<LzDiseaseOutBreakMonthsEntity> highDiseaseOutBreak = new ArrayList<>();
        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getHighDiseaseOutbreak()) {
            highDiseaseOutBreak.add(new LzDiseaseOutBreakMonthsEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.HIGH).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }
        lzDiseaseOutBreakMonthsRepository.saveAll(highDiseaseOutBreak);


        //Low
        List<LzDiseaseOutBreakMonthsEntity> lowDiseaseOutBreak = new ArrayList<>();
        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getLowDiseaseOutbreak()) {
            lowDiseaseOutBreak.add(new LzDiseaseOutBreakMonthsEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    highLowMediumScaleRepository.findByScaleMetricCode(Constants.LOW).getScaleMetricId(),
                    currentMonth.getMonthId()
            ));
        }
        lzDiseaseOutBreakMonthsRepository.saveAll(lowDiseaseOutBreak);
    }


    public void saveWaterStressMonths(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        LzSeasonsResponses livelihoodZoneSeasonsResponses = countyLevelQuestionnaireRequestDto.getLivelihoodZoneSeasonsResponses();

        List<LzWaterStressMonthsEntity> waterStressMonths = new ArrayList<>();
        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getWaterStress()) {
            waterStressMonths.add(new LzWaterStressMonthsEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    currentMonth.getMonthId()
            ));
        }
        lzWaterStressMonthsRepository.saveAll(waterStressMonths);
    }


    public void saveConflictRisksMonths(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        LzSeasonsResponses livelihoodZoneSeasonsResponses = countyLevelQuestionnaireRequestDto.getLivelihoodZoneSeasonsResponses();

        List<LzConflictRisksMonthsEntity> waterStressMonths = new ArrayList<>();
        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getConflictRisks()) {
            waterStressMonths.add(new LzConflictRisksMonthsEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    currentMonth.getMonthId()
            ));
        }
        lzConflictRisksMonthsRepository.saveAll(waterStressMonths);
    }

    public void saveCeremonyMonths(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        LzSeasonsResponses livelihoodZoneSeasonsResponses = countyLevelQuestionnaireRequestDto.getLivelihoodZoneSeasonsResponses();

        List<LzCeremonyMonthsEntity> ceremoniesMonths = new ArrayList<>();
        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getCeremonies()) {
            ceremoniesMonths.add(new LzCeremonyMonthsEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    currentMonth.getMonthId()
            ));
        }
        lzCeremonyMonthsRepository.saveAll(ceremoniesMonths);
    }


    public void saveLeanSeasonMonths(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        LzSeasonsResponses livelihoodZoneSeasonsResponses = countyLevelQuestionnaireRequestDto.getLivelihoodZoneSeasonsResponses();

        List<LzLeanSeasonMonthsEntity> leanSeasonMonths = new ArrayList<>();
        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getLeanSeasons()) {
            leanSeasonMonths.add(new LzLeanSeasonMonthsEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    currentMonth.getMonthId()
            ));
        }
        lzLeanSeasonMonthsRepository.saveAll(leanSeasonMonths);
    }


    public void saveFoodSecurityAssessmentMonths(CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto, LzQuestionnaireSessionEntity savedQuestionnaireSession) {
        LzSeasonsResponses livelihoodZoneSeasonsResponses = countyLevelQuestionnaireRequestDto.getLivelihoodZoneSeasonsResponses();

        List<LzFoodSecurityAssessmentsEntity> foodSecurityAssessmentsEntityList = new ArrayList<>();
        for (MonthsEntity currentMonth : livelihoodZoneSeasonsResponses.getFoodSecurityAssessments()) {
            foodSecurityAssessmentsEntityList.add(new LzFoodSecurityAssessmentsEntity(
                    savedQuestionnaireSession.getLzQuestionnaireSessionId(),
                    currentMonth.getMonthId()
            ));
        }
        lzFoodSecurityAssessmentsRepository.saveAll(foodSecurityAssessmentsEntityList);
    }


    /*
     * UPDATES
     * */

    public void updateSeasonMonths(int lzQuestionnaireSessionId, LzSeasonsResponses lzSeasonsResponses) {
        lzSeasonMonthsRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setLivelihoodZoneSeasonsResponses(lzSeasonsResponses);
        saveSeasonMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
    }

    public void updateLandPreparation(int lzQuestionnaireSessionId, LzCropProductionResponses lzCropProductionResponses) {
        lzLandPreparationMonthsRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setLzCropProductionResponses(lzCropProductionResponses);
        saveLandPreparationMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
    }

    public void updatePlanting(int lzQuestionnaireSessionId, LzCropProductionResponses lzCropProductionResponses) {
        lzPlantingMonthsRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setLzCropProductionResponses(lzCropProductionResponses);
        savePlantingMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
    }

    public void updateHarvesting(int lzQuestionnaireSessionId, LzCropProductionResponses lzCropProductionResponses) {
        lzHarvestingMonthsRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setLzCropProductionResponses(lzCropProductionResponses);
        saveHarvestingMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
    }

    public void updateLivestockMigration(int lzQuestionnaireSessionId, LzSeasonsResponses lzSeasonsResponses) {
        livestockMigrationMonthsRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setLivelihoodZoneSeasonsResponses(lzSeasonsResponses);
        saveLivestockMigration(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
    }

    public void updateMilkProduction(int lzQuestionnaireSessionId, LzSeasonsResponses lzSeasonsResponses) {
        lzMilkProductionRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setLivelihoodZoneSeasonsResponses(lzSeasonsResponses);
        saveMilkProduction(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
    }

    public void updateCalving(int lzQuestionnaireSessionId, LzSeasonsResponses lzSeasonsResponses) {
        lzCalvingRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setLivelihoodZoneSeasonsResponses(lzSeasonsResponses);
        saveCalvingMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
    }

    public void updateKidding(int lzQuestionnaireSessionId, LzSeasonsResponses lzSeasonsResponses) {
        lzKiddingRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setLivelihoodZoneSeasonsResponses(lzSeasonsResponses);
        saveKiddingMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
    }

    public void updateFoodPrices(int lzQuestionnaireSessionId, LzSeasonsResponses lzSeasonsResponses) {
        lzFoodPricesRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setLivelihoodZoneSeasonsResponses(lzSeasonsResponses);
        saveFoodPricesMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
    }

    public void updateLivestockPrices(int lzQuestionnaireSessionId, LzSeasonsResponses lzSeasonsResponses) {
        lzLivestockPricesRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setLivelihoodZoneSeasonsResponses(lzSeasonsResponses);
        saveLivestockPricesMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
    }

    public void updateAgricultureCasualLabour(int lzQuestionnaireSessionId, LzSeasonsResponses lzSeasonsResponses) {
        lzAgricultureCasualLabourAvailabilityRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setLivelihoodZoneSeasonsResponses(lzSeasonsResponses);
        saveAgricultureCasualLabour(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
    }

    public void updateNonAgricultureCasualLabour(int lzQuestionnaireSessionId, LzSeasonsResponses lzSeasonsResponses) {
        lzNonAgricCasualLabourAvailabilityRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setLivelihoodZoneSeasonsResponses(lzSeasonsResponses);
        saveNonAgricultureCasualLabourAvailability(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
    }

    public void updateCasualLabourWages(int lzQuestionnaireSessionId, LzSeasonsResponses lzSeasonsResponses) {
        lzCasualLabourWagesRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setLivelihoodZoneSeasonsResponses(lzSeasonsResponses);
        saveCasualLabourWages(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
    }

    public void updateRemittances(int lzQuestionnaireSessionId, LzSeasonsResponses lzSeasonsResponses) {
        lzRemittancesRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setLivelihoodZoneSeasonsResponses(lzSeasonsResponses);
        saveRemittances(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
    }

    public void updateFishing(int lzQuestionnaireSessionId, LzSeasonsResponses lzSeasonsResponses) {
        lzFishingMonthsRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setLivelihoodZoneSeasonsResponses(lzSeasonsResponses);
        saveFishingMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
    }

    public void updateMarketAccess(int lzQuestionnaireSessionId, LzSeasonsResponses lzSeasonsResponses) {
        lzMarketAccessMonthsRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setLivelihoodZoneSeasonsResponses(lzSeasonsResponses);
        saveMarketAccessMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
    }

    public void updateDiseaseOutBreak(int lzQuestionnaireSessionId, LzSeasonsResponses lzSeasonsResponses) {
        lzDiseaseOutBreakMonthsRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setLivelihoodZoneSeasonsResponses(lzSeasonsResponses);
        saveDiseaseOutBreakMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
    }

    public void updateWaterStress(int lzQuestionnaireSessionId, LzSeasonsResponses lzSeasonsResponses) {
        lzWaterStressMonthsRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setLivelihoodZoneSeasonsResponses(lzSeasonsResponses);
        saveWaterStressMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
    }

    public void updateConflictRisks(int lzQuestionnaireSessionId, LzSeasonsResponses lzSeasonsResponses) {
        lzConflictRisksMonthsRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setLivelihoodZoneSeasonsResponses(lzSeasonsResponses);
        saveConflictRisksMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
    }

    public void updateCeremonies(int lzQuestionnaireSessionId, LzSeasonsResponses lzSeasonsResponses) {
        lzCeremonyMonthsRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setLivelihoodZoneSeasonsResponses(lzSeasonsResponses);
        saveCeremonyMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
    }

    public void updateLeanSeasons(int lzQuestionnaireSessionId, LzSeasonsResponses lzSeasonsResponses) {
        lzLeanSeasonMonthsRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setLivelihoodZoneSeasonsResponses(lzSeasonsResponses);
        saveLeanSeasonMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
    }

    public void updateFoodSecurityAssessment(int lzQuestionnaireSessionId, LzSeasonsResponses lzSeasonsResponses) {
        lzFoodSecurityAssessmentsRepository.deleteByLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        CountyLevelQuestionnaireRequestDto countyLevelQuestionnaireRequestDto = new CountyLevelQuestionnaireRequestDto();
        LzQuestionnaireSessionEntity savedQuestionnaireSession = new LzQuestionnaireSessionEntity();
        savedQuestionnaireSession.setLzQuestionnaireSessionId(lzQuestionnaireSessionId);
        countyLevelQuestionnaireRequestDto.setLivelihoodZoneSeasonsResponses(lzSeasonsResponses);
        saveFoodSecurityAssessmentMonths(countyLevelQuestionnaireRequestDto, savedQuestionnaireSession);
    }
}
