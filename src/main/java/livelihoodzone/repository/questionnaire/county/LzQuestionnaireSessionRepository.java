package livelihoodzone.repository.questionnaire.county;

import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzQuestionnaireSessionRepository extends JpaRepository<LzQuestionnaireSessionEntity, Long> {
    public List<LzQuestionnaireSessionEntity> findByLzQuestionnaireUniqueId(@Param("LzQuestionnaireUniqueId") String lzQuestionnaireUniqueId);
}
