package livelihoodzone.repository.questionnaire.county;

import livelihoodzone.entity.questionnaire.county.LzHazardResponsesEntity;
import livelihoodzone.entity.questionnaire.county.LzHazardsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LzHazardResponsesRepository extends JpaRepository<LzHazardResponsesEntity, Long> {
}
