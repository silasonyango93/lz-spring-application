package livelihoodzone.repository.questionnaire.county;

import livelihoodzone.entity.questionnaire.county.LzMarketTransactionsEntity;
import livelihoodzone.entity.questionnaire.county.LzMarketsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzMarketTransactionsRepository extends JpaRepository<LzMarketTransactionsEntity, Long> {
    public List<LzMarketTransactionsEntity> findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);
}
