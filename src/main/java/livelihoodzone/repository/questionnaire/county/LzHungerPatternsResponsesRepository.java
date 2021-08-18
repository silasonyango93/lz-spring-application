package livelihoodzone.repository.questionnaire.county;

import livelihoodzone.entity.questionnaire.county.LzHungerPatternsResponsesEntity;
import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LzHungerPatternsResponsesRepository extends JpaRepository<LzHungerPatternsResponsesEntity, Long> {
}
