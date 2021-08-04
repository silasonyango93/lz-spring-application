package livelihoodzone.repository.questionnaire.wealthgroup;

import livelihoodzone.entity.questionnaire.wealthgroup.CashIncomeSourcesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CashIncomeSourcesRepository extends JpaRepository<CashIncomeSourcesEntity, Long> {
    public CashIncomeSourcesEntity findByCashIncomeSourceCode(@Param("CashIncomeSourceCode") int cashIncomeSourceCode);
}
