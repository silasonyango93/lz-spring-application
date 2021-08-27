package livelihoodzone.repository.questionnaire.county;

import livelihoodzone.entity.questionnaire.county.LzHazardsEntity;
import livelihoodzone.entity.questionnaire.county.LzMarketsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LzMarketsRepository extends JpaRepository<LzMarketsEntity, Long> {
}
