package livelihoodzone.repository.questionnaire.wealthgroup;

import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WgQuestionnaireSessionRepository extends JpaRepository<WgQuestionnaireSessionEntity, Long> {
    public List<WgQuestionnaireSessionEntity> findByQuestionnaireUniqueId(@Param("QuestionnaireUniqueId") String questionnaireUniqueId);
}
