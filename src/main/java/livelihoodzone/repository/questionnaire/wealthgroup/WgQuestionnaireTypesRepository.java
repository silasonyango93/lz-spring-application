package livelihoodzone.repository.questionnaire.wealthgroup;

import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireTypesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface WgQuestionnaireTypesRepository extends JpaRepository<WgQuestionnaireTypesEntity, Long> {
    public WgQuestionnaireTypesEntity findByWgQuestionnaireTypeCode(@Param("WgQuestionnaireTypeCode") int wgQuestionnaireTypeCode);
}
