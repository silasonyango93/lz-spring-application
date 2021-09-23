package livelihoodzone.repository.questionnaire.wealthgroup;

import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WgQuestionnaireSessionRepository extends JpaRepository<WgQuestionnaireSessionEntity, Long> {
    public List<WgQuestionnaireSessionEntity> findByQuestionnaireUniqueId(@Param("QuestionnaireUniqueId") String questionnaireUniqueId);

    public List<WgQuestionnaireSessionEntity> findByCountyId(@Param("CountyId") int countyId);

    @Query(nativeQuery = true)
    public List<WgQuestionnaireSessionEntity> fetchQuestionnaireSessionsByCountyAndWealthGroup(int countyId, int wealthGroupId);
}
