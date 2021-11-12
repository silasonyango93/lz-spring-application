package livelihoodzone.repository.questionnaire.wealthgroup.labour_patterns;

import livelihoodzone.entity.questionnaire.wealthgroup.labour_patterns.LivelihoodActivitiesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.labour_patterns.WgGenderLivelihoodActivitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WgGenderLivelihoodActivitiesRepository extends JpaRepository<WgGenderLivelihoodActivitiesEntity, Long> {
    public List<WgGenderLivelihoodActivitiesEntity> findByWgQuestionnaireSessionId(@Param("WgQuestionnaireSessionId") int wgQuestionnaireSessionId);
}
