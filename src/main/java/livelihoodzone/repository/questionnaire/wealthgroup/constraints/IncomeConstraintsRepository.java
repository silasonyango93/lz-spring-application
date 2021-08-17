package livelihoodzone.repository.questionnaire.wealthgroup.constraints;

import livelihoodzone.entity.questionnaire.wealthgroup.WealthGroupEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.constraints.IncomeConstraintsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface IncomeConstraintsRepository extends JpaRepository<IncomeConstraintsEntity, Long> {
    public IncomeConstraintsEntity findByIncomeConstraintCode(@Param("IncomeConstraintCode") int incomeConstraintCode);
}
