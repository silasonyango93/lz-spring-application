package livelihoodzone.repository.questionnaire.county;

import livelihoodzone.entity.questionnaire.county.LzCropProductionResponsesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzCropProductionResponsesRepository extends JpaRepository<LzCropProductionResponsesEntity, Long> {
    public List<LzCropProductionResponsesEntity> findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);
}
