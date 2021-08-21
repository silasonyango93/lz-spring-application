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
}
