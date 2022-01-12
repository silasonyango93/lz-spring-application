package livelihoodzone.repository.questionnaire.wealthgroup;

import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WgQuestionnaireSessionRepository extends JpaRepository<WgQuestionnaireSessionEntity, Long> {
    public List<WgQuestionnaireSessionEntity> findByQuestionnaireUniqueId(@Param("QuestionnaireUniqueId") String questionnaireUniqueId);

    WgQuestionnaireSessionEntity findByWgQuestionnaireSessionId(@Param("WgQuestionnaireSessionId") int wgQuestionnaireSessionId);

    public List<WgQuestionnaireSessionEntity> findByCountyIdAndWgQuestionnaireTypeId(@Param("CountyId") int countyId, @Param("WgQuestionnaireTypeId") int wgQuestionnaireTypeId);

    public List<WgQuestionnaireSessionEntity> findByCountyIdAndLivelihoodZoneIdAndWgQuestionnaireTypeId(@Param("CountyId") int countyId, @Param("LivelihoodZoneId") int livelihoodZoneId, @Param("WgQuestionnaireTypeId") int wgQuestionnaireTypeId);

    public List<WgQuestionnaireSessionEntity> findByCountyIdAndWealthGroupIdAndWgQuestionnaireTypeId(@Param("CountyId") int countyId, @Param("WealthGroupId") int wealthGroupId, @Param("WgQuestionnaireTypeId") int wgQuestionnaireTypeId);
}
