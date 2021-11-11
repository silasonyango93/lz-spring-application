package livelihoodzone.repository.questionnaire.wealthgroup.animal_contribution;

import livelihoodzone.entity.questionnaire.wealthgroup.WealthGroupEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.animal_contribution.WgAveAnimalNoPerHouseholdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WgAveAnimalNoPerHouseholdRepository extends JpaRepository<WgAveAnimalNoPerHouseholdEntity, Long> {
    public List<WgAveAnimalNoPerHouseholdEntity> findByWgQuestionnaireSessionId(@Param("WgQuestionnaireSessionId") int wgQuestionnaireSessionId);
}
