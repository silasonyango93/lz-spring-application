package livelihoodzone.repository.questionnaire.county;

import livelihoodzone.entity.questionnaire.county.LzMarketTransactionsEntity;
import livelihoodzone.entity.questionnaire.county.LzMarketsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LzMarketTransactionsRepository extends JpaRepository<LzMarketTransactionsEntity, Long> {
}
