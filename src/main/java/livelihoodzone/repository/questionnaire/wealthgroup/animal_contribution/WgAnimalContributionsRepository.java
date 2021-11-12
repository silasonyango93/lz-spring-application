package livelihoodzone.repository.questionnaire.wealthgroup.animal_contribution;

import livelihoodzone.entity.questionnaire.wealthgroup.animal_contribution.WgAnimalContributionsEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.animal_contribution.WgAveAnimalNoPerHouseholdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WgAnimalContributionsRepository extends JpaRepository<WgAnimalContributionsEntity, Long> {
    public List<WgAnimalContributionsEntity> findByWgQuestionnaireSessionId(@Param("WgQuestionnaireSessionId") int wgQuestionnaireSessionId);
}
