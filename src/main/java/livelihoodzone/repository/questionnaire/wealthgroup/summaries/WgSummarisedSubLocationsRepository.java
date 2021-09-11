package livelihoodzone.repository.questionnaire.wealthgroup.summaries;

import livelihoodzone.entity.questionnaire.wealthgroup.summaries.WgSummariesSubLocationsEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.summaries.WgSummariesWardsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WgSummarisedSubLocationsRepository extends JpaRepository<WgSummariesSubLocationsEntity, Long> {
    public List<WgSummariesSubLocationsEntity> findByWgQuestionnaireSessionId(@Param("WgQuestionnaireSessionId") int wgQuestionnaireSessionId);
}
