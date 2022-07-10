package livelihoodzone.repository.questionnaire.county;

import livelihoodzone.entity.questionnaire.county.LzHungerPatternsResponsesEntity;
import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzHungerPatternsResponsesRepository extends JpaRepository<LzHungerPatternsResponsesEntity, Long> {
    public List<LzHungerPatternsResponsesEntity> findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);

    public long deleteByLzQuestionnaireSessionId(int lzQuestionnaireSessionId);
}
