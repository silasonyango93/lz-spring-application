package livelihoodzone.service.questionnaire.zonelevel;

import livelihoodzone.common.Constants;
import livelihoodzone.dto.questionnaire.CountyLevelQuestionnaireRequestDto;
import livelihoodzone.dto.questionnaire.county.model.seasons.LzSeasonsResponses;
import livelihoodzone.entity.questionnaire.calendar.MonthsEntity;
import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzSeasonMonthsEntity;
import livelihoodzone.repository.questionnaire.county.seasonal_calendar.LzSeasonMonthsRepository;
import livelihoodzone.repository.questionnaire.county.seasonal_calendar.LzSeasonsRepository;
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
}
