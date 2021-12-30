package livelihoodzone.repository.questionnaire;

import livelihoodzone.entity.questionnaire.WgQuestionnaireSectionsEntity;
import livelihoodzone.entity.questionnaire.county.CropWaterAccessTypesEntity;
import livelihoodzone.entity.questionnaire.crops.CropsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface WgQuestionnaireSectionsRepository extends JpaRepository<WgQuestionnaireSectionsEntity, Long> {
    public WgQuestionnaireSectionsEntity findByWgQuestionnaireSectionCode(@Param("WgQuestionnaireSectionCode") int wgQuestionnaireSectionCode);
}
