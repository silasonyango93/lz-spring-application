package livelihoodzone.repository.questionnaire;

import livelihoodzone.entity.questionnaire.WgQuestionnaireSectionsEntity;
import livelihoodzone.entity.questionnaire.county.CropWaterAccessTypesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WgQuestionnaireSectionsRepository extends JpaRepository<WgQuestionnaireSectionsEntity, Long> {
}
