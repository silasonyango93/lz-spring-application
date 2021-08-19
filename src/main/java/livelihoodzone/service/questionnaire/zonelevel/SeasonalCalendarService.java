package livelihoodzone.service.questionnaire.zonelevel;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.CountyLevelQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.county.model.seasons.LzSeasonsResponses;
import livelihoodzone.entity.questionnaire.calendar.MonthsEntity;
import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LivestockMigrationMonthsEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzMilkProductionEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzSeasonMonthsEntity;
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
}
