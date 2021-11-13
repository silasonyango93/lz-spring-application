package livelihoodzone.repository.questionnaire.wealthgroup.constraints;

import livelihoodzone.entity.questionnaire.wealthgroup.constraints.IncomeConstraintsEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.constraints.WgIncomeConstraintRankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WgIncomeConstraintRankRepository extends JpaRepository<WgIncomeConstraintRankEntity, Long> {
    public List<WgIncomeConstraintRankEntity> findByWgQuestionnaireSessionId(@Param("WgQuestionnaireSessionId") int wgQuestionnaireSessionId);
}
