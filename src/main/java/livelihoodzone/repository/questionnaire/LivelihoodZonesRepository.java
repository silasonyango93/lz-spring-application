package livelihoodzone.repository.questionnaire;

import livelihoodzone.entity.questionnaire.LivelihoodZonesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.WealthGroupEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface LivelihoodZonesRepository extends JpaRepository<LivelihoodZonesEntity, Long> {

}
