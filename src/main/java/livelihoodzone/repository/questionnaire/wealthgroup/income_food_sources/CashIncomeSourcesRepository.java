package livelihoodzone.repository.questionnaire.wealthgroup.income_food_sources;

import livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources.CashIncomeSourcesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CashIncomeSourcesRepository extends JpaRepository<CashIncomeSourcesEntity, Long> {
    public CashIncomeSourcesEntity findByCashIncomeSourceCode(@Param("CashIncomeSourceCode") int cashIncomeSourceCode);
}
