package livelihoodzone.service.reports.zonal.quality_checks;

import livelihoodzone.dto.reports.zonal.LzIncompleteQuestionnaireResponseDto;
import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import livelihoodzone.repository.administrative_boundaries.counties.CountiesRepository;
import livelihoodzone.repository.questionnaire.county.*;
import livelihoodzone.repository.questionnaire.county.seasonal_calendar.LzSeasonMonthsRepository;
import livelihoodzone.repository.questionnaire.tribe.LzEthnicGroupsPercentagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZonalQualityChecksService {

    @Autowired
    LzWealthGroupCharacteristicsRepository lzWealthGroupCharacteristicsRepository;

    @Autowired
    LzWealthGroupPopulationPercentageRepository lzWealthGroupPopulationPercentageRepository;

    @Autowired
    LzCropProductionResponsesRepository lzCropProductionResponsesRepository;

    @Autowired
    LzWaterSourceResponsesRepository lzWaterSourceResponsesRepository;

    @Autowired
    LzMarketTransactionsRepository lzMarketTransactionsRepository;

    @Autowired
    LzEthnicGroupsPercentagesRepository lzEthnicGroupsPercentagesRepository;

    @Autowired
    LzHungerPatternsResponsesRepository lzHungerPatternsResponsesRepository;

    @Autowired
    LzHazardResponsesRepository lzHazardResponsesRepository;

    @Autowired
    LzSeasonMonthsRepository lzSeasonMonthsRepository;

    @Autowired
    LzQuestionnaireSessionRepository lzQuestionnaireSessionRepository;

    @Autowired
    CountiesRepository countiesRepository;



    public List<Number> extractCountryWideIncompleteZoneLevelQuestionnaires() {
        List<Number> countryWideIncompleteQuestionnaireIds = new ArrayList<>();
        List<CountiesEntity> countiesEntityList = countiesRepository.findAll();

        for (CountiesEntity countiesEntity : countiesEntityList) {
            List<Number> countyIncompleteQuestionnaireIds = extractIncompleteQuestionnaires(countiesEntity.getCountyId());
            countryWideIncompleteQuestionnaireIds.addAll(countyIncompleteQuestionnaireIds);
        }
        return countryWideIncompleteQuestionnaireIds;
    }


    public List<Number> extractIncompleteQuestionnaires(int countyId) {
        List<Number> incompleteQuestionnaireIds = new ArrayList<>();
        List<LzQuestionnaireSessionEntity> lzQuestionnaireSessionEntityList = lzQuestionnaireSessionRepository.findByCountyId(countyId);

        for (LzQuestionnaireSessionEntity lzQuestionnaireSessionEntity : lzQuestionnaireSessionEntityList) {
            if (!hasWealthGroupCharacteristics(lzQuestionnaireSessionEntity.getLzQuestionnaireSessionId())
                    || !hasWealthGroupPopulationPercentage(lzQuestionnaireSessionEntity.getLzQuestionnaireSessionId())
                    || !hasCropProduction(lzQuestionnaireSessionEntity.getLzQuestionnaireSessionId())
                    || !hasMainSourcesOfWater(lzQuestionnaireSessionEntity.getLzQuestionnaireSessionId())
                    || !hasMarkets(lzQuestionnaireSessionEntity.getLzQuestionnaireSessionId())
                    || !hasSocietyAndEthnicity(lzQuestionnaireSessionEntity.getLzQuestionnaireSessionId())
                    || !hasHungerPatterns(lzQuestionnaireSessionEntity.getLzQuestionnaireSessionId())
                    || !hasHazards(lzQuestionnaireSessionEntity.getLzQuestionnaireSessionId())
                    || !hasSeasonalCalendar(lzQuestionnaireSessionEntity.getLzQuestionnaireSessionId())) {

                incompleteQuestionnaireIds.add(lzQuestionnaireSessionEntity.getLzQuestionnaireSessionId());

            }
        }
        return incompleteQuestionnaireIds;
    }


    public boolean hasWealthGroupCharacteristics(int lzQuestionnaireSessionId) {
        return !lzWealthGroupCharacteristicsRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId).isEmpty();
    }

    public boolean hasWealthGroupPopulationPercentage(int lzQuestionnaireSessionId) {
        return !lzWealthGroupPopulationPercentageRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId).isEmpty();
    }

    public boolean hasCropProduction(int lzQuestionnaireSessionId) {
        boolean hasNoCropProduction = lzCropProductionResponsesRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId).isEmpty();

        if (hasNoCropProduction) {
            LzQuestionnaireSessionEntity lzQuestionnaireSessionEntity = lzQuestionnaireSessionRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId);

            if (lzQuestionnaireSessionEntity.getLivelihoodZoneId() == 15 || lzQuestionnaireSessionEntity.getLivelihoodZoneId() == 20) {
                return true;
            }
        }
        return !hasNoCropProduction;
    }

    public boolean hasMainSourcesOfWater(int lzQuestionnaireSessionId) {
        return !lzWaterSourceResponsesRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId).isEmpty();
    }

    public boolean hasMarkets(int lzQuestionnaireSessionId) {
        return !lzMarketTransactionsRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId).isEmpty();
    }

    public boolean hasSocietyAndEthnicity(int lzQuestionnaireSessionId) {
        return !lzEthnicGroupsPercentagesRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId).isEmpty();
    }

    public boolean hasHungerPatterns(int lzQuestionnaireSessionId) {
        return !lzHungerPatternsResponsesRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId).isEmpty();
    }

    public boolean hasHazards(int lzQuestionnaireSessionId) {
        return !lzHazardResponsesRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId).isEmpty();
    }

    public boolean hasSeasonalCalendar(int lzQuestionnaireSessionId) {
        return !lzSeasonMonthsRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId).isEmpty();
    }


    public LzIncompleteQuestionnaireResponseDto returnMissingQuestionnaireSections(int lzQuestionnaireSessionId) {
        LzIncompleteQuestionnaireResponseDto lzIncompleteQuestionnaireResponseDto = new LzIncompleteQuestionnaireResponseDto(lzQuestionnaireSessionRepository.findByLzQuestionnaireSessionId(lzQuestionnaireSessionId));

        if (!hasWealthGroupCharacteristics(lzQuestionnaireSessionId)) {
            lzIncompleteQuestionnaireResponseDto.getMissingQuestionnaireSections().add("Wealth Group Characteristics");
        }
        if (!hasWealthGroupPopulationPercentage(lzQuestionnaireSessionId)) {
            lzIncompleteQuestionnaireResponseDto.getMissingQuestionnaireSections().add("Wealth Groups Population Percentage");
        }
        if (!hasCropProduction(lzQuestionnaireSessionId)) {
            lzIncompleteQuestionnaireResponseDto.getMissingQuestionnaireSections().add("Crop Production");
        }
        if (!hasMainSourcesOfWater(lzQuestionnaireSessionId)) {
            lzIncompleteQuestionnaireResponseDto.getMissingQuestionnaireSections().add("Main Sources Of Water");
        }
        if (!hasMarkets(lzQuestionnaireSessionId)) {
            lzIncompleteQuestionnaireResponseDto.getMissingQuestionnaireSections().add("Markets Serving The Livelihood Zone");
        }
        if (!hasSocietyAndEthnicity(lzQuestionnaireSessionId)) {
            lzIncompleteQuestionnaireResponseDto.getMissingQuestionnaireSections().add("Society And Ethnicity");
        }
        if (!hasHungerPatterns(lzQuestionnaireSessionId)) {
            lzIncompleteQuestionnaireResponseDto.getMissingQuestionnaireSections().add("Patterns Of Hunger");
        }
        if (!hasHazards(lzQuestionnaireSessionId)) {
            lzIncompleteQuestionnaireResponseDto.getMissingQuestionnaireSections().add("Hazards");
        }
        if (!hasSeasonalCalendar(lzQuestionnaireSessionId)) {
            lzIncompleteQuestionnaireResponseDto.getMissingQuestionnaireSections().add("Seasonal Calendar");
        }
        return lzIncompleteQuestionnaireResponseDto;
    }
}
