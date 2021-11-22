package livelihoodzone.repository.questionnaire.county;

import livelihoodzone.entity.questionnaire.county.LzHazardsEntity;
import livelihoodzone.entity.questionnaire.county.LzMarketsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface LzMarketsRepository extends JpaRepository<LzMarketsEntity, Long> {
    public LzMarketsEntity findByMarketId(@Param("MarketId") int marketId);
}
