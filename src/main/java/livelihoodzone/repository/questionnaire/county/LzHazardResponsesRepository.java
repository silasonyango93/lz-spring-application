package livelihoodzone.repository.questionnaire.county;

import livelihoodzone.entity.questionnaire.county.LzHazardResponsesEntity;
import livelihoodzone.entity.questionnaire.county.LzHazardsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzHazardResponsesRepository extends JpaRepository<LzHazardResponsesEntity, Long> {
    public List<LzHazardResponsesEntity> findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);
}
