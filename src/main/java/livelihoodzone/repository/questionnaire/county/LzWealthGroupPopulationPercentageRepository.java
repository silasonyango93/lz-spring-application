package livelihoodzone.repository.questionnaire.county;

import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.entity.questionnaire.county.LzWealthGroupPopulationPercentageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzWealthGroupPopulationPercentageRepository extends JpaRepository<LzWealthGroupPopulationPercentageEntity, Long> {
    public List<LzWealthGroupPopulationPercentageEntity> findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);
}
