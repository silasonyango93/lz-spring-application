package livelihoodzone.repository.questionnaire.county;

import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSectionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface LzQuestionnaireSectionsRepository extends JpaRepository<LzQuestionnaireSectionsEntity, Long> {
    public LzQuestionnaireSectionsEntity findByLzQuestionnaireSectionCode(@Param("LzQuestionnaireSectionCode") int lzQuestionnaireSectionCode);
}
