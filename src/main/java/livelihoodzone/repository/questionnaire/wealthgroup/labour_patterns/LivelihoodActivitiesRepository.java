package livelihoodzone.repository.questionnaire.wealthgroup.labour_patterns;

import livelihoodzone.entity.questionnaire.wealthgroup.WealthGroupEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.labour_patterns.LivelihoodActivitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface LivelihoodActivitiesRepository extends JpaRepository<LivelihoodActivitiesEntity, Long> {
    public LivelihoodActivitiesEntity findByLivelihoodActivityCode(@Param("LivelihoodActivityCode") int livelihoodActivityCode);
}
