package livelihoodzone.repository.questionnaire.wealthgroup.expenditure_patterns;

import livelihoodzone.entity.questionnaire.wealthgroup.expenditure_patterns.WgExpenditureItemsEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.expenditure_patterns.WgExpenditurePercentagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WgExpenditurePercentagesRepository extends JpaRepository<WgExpenditurePercentagesEntity, Long> {
    public List<WgExpenditurePercentagesEntity> findByWgQuestionnaireSessionId(@Param("WgQuestionnaireSessionId") int wgQuestionnaireSessionId);
}
