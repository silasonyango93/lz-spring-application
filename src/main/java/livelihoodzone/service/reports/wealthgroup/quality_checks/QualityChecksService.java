package livelihoodzone.service.reports.wealthgroup.quality_checks;

import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
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
        return wgIncomeSourcesRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId) != null;
    }

    public boolean hasFoodConsumptionSection(int wgQuestionnaireSessionId) {
        return wgFoodSourcesResponsesRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId) != null;
    }

    public boolean hasCropProductionSection(int wgQuestionnaireSessionId) {
        return wgCropContributionsRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId) != null;
    }

    public boolean hasLivestockOwnershipSection(int wgQuestionnaireSessionId) {
        return wgAveAnimalNoPerHouseholdRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId) != null;
    }

    public boolean hasLabourPatternsSection(int wgQuestionnaireSessionId) {
        return wgGenderLivelihoodActivitiesRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId) != null;
    }

    public boolean hasExpenditurePatternsSection(int wgQuestionnaireSessionId) {
        return wgExpenditurePercentagesRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId) != null;
    }

    public boolean hasMigrationPatternsSection(int wgQuestionnaireSessionId) {
        return wgMigrationPatternsPercentagesRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId) != null;
    }

    public boolean hasConstraintsSection(int wgQuestionnaireSessionId) {
        return wgIncomeConstraintRankRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId) != null;
    }

    public boolean hasFgdParticipantsSection(int wgQuestionnaireSessionId) {
        return fgdParticipantsRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId) != null;
    }

    public boolean hasLivestockContributionSection(int wgQuestionnaireSessionId) {
        return wgAnimalContributionsRepository.findByWgQuestionnaireSessionId(wgQuestionnaireSessionId) != null;
    }
}
