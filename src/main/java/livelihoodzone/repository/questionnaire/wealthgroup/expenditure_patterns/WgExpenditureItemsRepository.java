package livelihoodzone.repository.questionnaire.wealthgroup.expenditure_patterns;

import livelihoodzone.entity.questionnaire.wealthgroup.WealthGroupEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.expenditure_patterns.WgExpenditureItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface WgExpenditureItemsRepository extends JpaRepository<WgExpenditureItemsEntity, Long> {
    public WgExpenditureItemsEntity findByExpenditureItemCode(@Param("ExpenditureItemCode") int expenditureItemCode);
}
