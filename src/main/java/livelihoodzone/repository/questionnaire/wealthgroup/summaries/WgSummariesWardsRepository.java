package livelihoodzone.repository.questionnaire.wealthgroup.summaries;

import livelihoodzone.entity.questionnaire.wealthgroup.summaries.WgSummariesSubCountiesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.summaries.WgSummariesWardsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WgSummariesWardsRepository extends JpaRepository<WgSummariesWardsEntity, Long> {
    public List<WgSummariesWardsEntity> findByWgQuestionnaireSessionId(@Param("WgQuestionnaireSessionId") int wgQuestionnaireSessionId);
}
