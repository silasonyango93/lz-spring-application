package livelihoodzone.repository.questionnaire.wealthgroup;

import livelihoodzone.entity.questionnaire.wealthgroup.CashIncomeSourcesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.WgIncomeSourcesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WgIncomeSourcesRepository extends JpaRepository<WgIncomeSourcesEntity, Long> {
}
