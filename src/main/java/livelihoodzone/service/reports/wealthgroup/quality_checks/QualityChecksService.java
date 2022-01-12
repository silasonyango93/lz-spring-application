package livelihoodzone.service.reports.wealthgroup.quality_checks;

import livelihoodzone.dto.reports.wealthgroup.WgIncompleteQuestionnaireResponseDto;
import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.repository.administrative_boundaries.counties.CountiesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.WgQuestionnaireSessionRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.animal_contribution.WgAnimalContributionsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.animal_contribution.WgAveAnimalNoPerHouseholdRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.constraints.WgIncomeConstraintRankRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.cropcontribution.WgCropContributionsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.expenditure_patterns.WgExpenditurePercentagesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.fgd_participants.FgdParticipantsRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.income_food_sources.WgFoodSourcesResponsesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.income_food_sources.WgIncomeSourcesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.labour_patterns.WgGenderLivelihoodActivitiesRepository;
import livelihoodzone.repository.questionnaire.wealthgroup.migration_patterns.WgMigrationPatternsPercentagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QualityChecksService {

    @Autowired
    WgQuestionnaireSessionRepository wgQuestionnaireSessionRepository;

    @Autowired
    WgIncomeSourcesRepository wgIncomeSourcesRepository;

    @Autowired
    WgFoodSourcesResponsesRepository wgFoodSourcesResponsesRepository;

    @Autowired
    WgCropContributionsRepository wgCropContributionsRepository;

    @Autowired
    WgAveAnimalNoPerHouseholdRepository wgAveAnimalNoPerHouseholdRepository;

    @Autowired
    WgGenderLivelihoodActivitiesRepository wgGenderLivelihoodActivitiesRepository;

    @Autowired
    WgExpenditurePercentagesRepository wgExpenditurePercentagesRepository;

    @Autowired
    WgMigrationPatternsPercentagesRepository wgMigrationPatternsPercentagesRepository;

    @Autowired
    WgIncomeConstraintRankRepository wgIncomeConstraintRankRepository;

    @Autowired
    FgdParticipantsRepository fgdParticipantsRepository;

    @Autowired
    WgAnimalContributionsRepository wgAnimalContributionsRepository;

    @Autowired
    CountiesRepository countiesRepository;


    public List<Number> extractCountryWideIncompleteWealthGroupQuestionnaires(int questionnaireTypeId) {
        List<Number> countryWideIncompleteQuestionnaireIds = new ArrayList<>();

        List<CountiesEntity> countiesEntityList = countiesRepository.findAll();

        for (CountiesEntity countiesEntity : countiesEntityList) {
            List<Number> countyIncompleteQuestionnaireIds = extractIncompleteWealthGroupQuestionnaires(countiesEntity.getCountyId(), questionnaireTypeId);
            countryWideIncompleteQuestionnaireIds.addAll(countyIncompleteQuestionnaireIds);
        }
        return countryWideIncompleteQuestionnaireIds;
    }

    public List<Number> extractIncompleteWealthGroupQuestionnaires(int countyId, int questionnaireTypeId) {
        List<Number> incompleteQuestionnaireIds = new ArrayList<>();
        List<WgQuestionnaireSessionEntity> wgQuestionnaireSessionEntityList = wgQuestionnaireSessionRepository.findByCountyIdAndWgQuestionnaireTypeId(countyId, questionnaireTypeId);

        for (WgQuestionnaireSessionEntity wgQuestionnaireSessionEntity : wgQuestionnaireSessionEntityList) {
            if (!hasIncomeSourcesSection(wgQuestionnaireSessionEntity.getWgQuestionnaireSessionId())
                    || !hasFoodConsumptionSection(wgQuestionnaireSessionEntity.getWgQuestionnaireSessionId())
                    || !hasCropProductionSection(wgQuestionnaireSessionEntity.getWgQuestionnaireSessionId())
                    || !hasLivestockOwnershipSection(wgQuestionnaireSessionEntity.getWgQuestionnaireSessionId())
                    || !hasLabourPatternsSection(wgQuestionnaireSessionEntity.getWgQuestionnaireSessionId())
                    || !hasExpenditurePatternsSection(wgQuestionnaireSessionEntity.getWgQuestionnaireSessionId())
                    || !hasMigrationPatternsSection(wgQuestionnaireSessionEntity.getWgQuestionnaireSessionId())
                    || !hasConstraintsSection(wgQuestionnaireSessionEntity.getWgQuestionnaireSessionId())
                    || !hasFgdParticipantsSection(wgQuestionnaireSessionEntity.getWgQuestionnaireSessionId())
                    || !hasLivestockContributionSection(wgQuestionnaireSessionEntity.getWgQuestionnaireSessionId())) {

                incompleteQuestionnaireIds.add(wgQuestionnaireSessionEntity.getWgQuestionnaireSessionId());

            }
        }
        return incompleteQuestionnaireIds;
    }


    public boolean hasIncomeSourcesSection(int wgQuestionnaireSessionId) {
        return !wgIncomeSourcesRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId).isEmpty();
    }

    public boolean hasFoodConsumptionSection(int wgQuestionnaireSessionId) {
        return !wgFoodSourcesResponsesRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId).isEmpty();
    }

    public boolean hasCropProductionSection(int wgQuestionnaireSessionId) {
        boolean hasNoCropProduction = wgCropContributionsRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId).isEmpty();

        if (hasNoCropProduction) {
            WgQuestionnaireSessionEntity wgQuestionnaireSessionEntity = wgQuestionnaireSessionRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId);

            if (wgQuestionnaireSessionEntity.getLivelihoodZoneId() == 15 || wgQuestionnaireSessionEntity.getLivelihoodZoneId() == 20 || wgQuestionnaireSessionEntity.getWealthGroupId() == 1) {
                return true;
            }
        }
        return !hasNoCropProduction;
    }

    public boolean hasLivestockOwnershipSection(int wgQuestionnaireSessionId) {
        return !wgAveAnimalNoPerHouseholdRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId).isEmpty();
    }

    public boolean hasLabourPatternsSection(int wgQuestionnaireSessionId) {
        return !wgGenderLivelihoodActivitiesRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId).isEmpty();
    }

    public boolean hasExpenditurePatternsSection(int wgQuestionnaireSessionId) {
        return !wgExpenditurePercentagesRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId).isEmpty();
    }

    public boolean hasMigrationPatternsSection(int wgQuestionnaireSessionId) {
        return !wgMigrationPatternsPercentagesRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId).isEmpty();
    }

    public boolean hasConstraintsSection(int wgQuestionnaireSessionId) {
        return !wgIncomeConstraintRankRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId).isEmpty();
    }

    public boolean hasFgdParticipantsSection(int wgQuestionnaireSessionId) {
        return !fgdParticipantsRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId).isEmpty();
    }

    public boolean hasLivestockContributionSection(int wgQuestionnaireSessionId) {
        return !wgAnimalContributionsRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId).isEmpty();
    }

    public WgIncompleteQuestionnaireResponseDto returnMissingQuestionnaireSections(int wgQuestionnaireSessionId) {
        WgIncompleteQuestionnaireResponseDto wgIncompleteQuestionnaireResponseDto = new WgIncompleteQuestionnaireResponseDto(wgQuestionnaireSessionRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId));
        if (!hasIncomeSourcesSection(wgQuestionnaireSessionId)) {
            wgIncompleteQuestionnaireResponseDto.getMissingQuestionnaireSections().add("Main Sources of Income and Food");
        }

        if (!hasFoodConsumptionSection(wgQuestionnaireSessionId)) {
            wgIncompleteQuestionnaireResponseDto.getMissingQuestionnaireSections().add("Percent of total food consumption");
        }

        if (!hasCropProductionSection(wgQuestionnaireSessionId)) {
            wgIncompleteQuestionnaireResponseDto.getMissingQuestionnaireSections().add("Crop Production");
        }

        if (!hasLivestockOwnershipSection(wgQuestionnaireSessionId)) {
            wgIncompleteQuestionnaireResponseDto.getMissingQuestionnaireSections().add("Livestock and Poultry Ownership");
        }

        if (!hasLabourPatternsSection(wgQuestionnaireSessionId)) {
            wgIncompleteQuestionnaireResponseDto.getMissingQuestionnaireSections().add("Labour Patterns");
        }

        if (!hasExpenditurePatternsSection(wgQuestionnaireSessionId)) {
            wgIncompleteQuestionnaireResponseDto.getMissingQuestionnaireSections().add("Expenditure Patterns");
        }

        if (!hasMigrationPatternsSection(wgQuestionnaireSessionId)) {
            wgIncompleteQuestionnaireResponseDto.getMissingQuestionnaireSections().add("Settlement and Migration Patterns");
        }

        if (!hasConstraintsSection(wgQuestionnaireSessionId)) {
            wgIncompleteQuestionnaireResponseDto.getMissingQuestionnaireSections().add("Constraints to Main Economic Activities");
        }

        if (!hasFgdParticipantsSection(wgQuestionnaireSessionId)) {
            wgIncompleteQuestionnaireResponseDto.getMissingQuestionnaireSections().add("FGD participants information");
        }

        if (!hasLivestockContributionSection(wgQuestionnaireSessionId)) {
            wgIncompleteQuestionnaireResponseDto.getMissingQuestionnaireSections().add("Livestock contributions");
        }
        return wgIncompleteQuestionnaireResponseDto;
    }
}
