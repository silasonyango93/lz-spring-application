package livelihoodzone.repository.questionnaire.county;

import livelihoodzone.entity.questionnaire.county.LzWaterSourceResponsesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzWaterSourceResponsesRepository extends JpaRepository<LzWaterSourceResponsesEntity, Long> {
    public List<LzWaterSourceResponsesEntity> findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);

    public long deleteByLzQuestionnaireSessionId(int lzQuestionnaireSessionId);
}
