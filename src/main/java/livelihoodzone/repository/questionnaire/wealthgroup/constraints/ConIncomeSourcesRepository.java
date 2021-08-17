package livelihoodzone.repository.questionnaire.wealthgroup.constraints;

import livelihoodzone.entity.questionnaire.wealthgroup.constraints.ConIncomeSourcesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.constraints.IncomeConstraintsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ConIncomeSourcesRepository extends JpaRepository<ConIncomeSourcesEntity, Long> {
    public ConIncomeSourcesEntity findByConIncomeSourceCode(@Param("ConIncomeSourceCode") int conIncomeSourceCode);
}
