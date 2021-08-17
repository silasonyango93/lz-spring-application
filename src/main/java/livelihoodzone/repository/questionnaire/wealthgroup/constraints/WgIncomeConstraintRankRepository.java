package livelihoodzone.repository.questionnaire.wealthgroup.constraints;

import livelihoodzone.entity.questionnaire.wealthgroup.constraints.IncomeConstraintsEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.constraints.WgIncomeConstraintRankEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WgIncomeConstraintRankRepository extends JpaRepository<WgIncomeConstraintRankEntity, Long> {
}
