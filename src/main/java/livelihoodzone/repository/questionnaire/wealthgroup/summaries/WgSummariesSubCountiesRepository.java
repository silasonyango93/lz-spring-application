package livelihoodzone.repository.questionnaire.wealthgroup.summaries;

import livelihoodzone.entity.questionnaire.wealthgroup.WealthGroupEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.summaries.WgSummariesSubCountiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WgSummariesSubCountiesRepository extends JpaRepository<WgSummariesSubCountiesEntity, Long> {
    public List<WgSummariesSubCountiesEntity> findByWgQuestionnaireSessionId(@Param("WgQuestionnaireSessionId") int wgQuestionnaireSessionId);
}
