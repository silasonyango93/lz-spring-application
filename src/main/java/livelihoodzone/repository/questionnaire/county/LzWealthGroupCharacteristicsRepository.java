package livelihoodzone.repository.questionnaire.county;

import livelihoodzone.entity.questionnaire.county.LzWealthGroupCharacteristicsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzWealthGroupCharacteristicsRepository extends JpaRepository<LzWealthGroupCharacteristicsEntity, Long> {
    public List<LzWealthGroupCharacteristicsEntity> findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);
}
