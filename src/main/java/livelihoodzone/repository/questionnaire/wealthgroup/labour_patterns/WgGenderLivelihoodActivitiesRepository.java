package livelihoodzone.repository.questionnaire.wealthgroup.labour_patterns;

import livelihoodzone.entity.questionnaire.wealthgroup.labour_patterns.LivelihoodActivitiesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.labour_patterns.WgGenderLivelihoodActivitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WgGenderLivelihoodActivitiesRepository extends JpaRepository<WgGenderLivelihoodActivitiesEntity, Long> {
}