package livelihoodzone.repository.questionnaire.county;

import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzQuestionnaireSessionRepository extends JpaRepository<LzQuestionnaireSessionEntity, Long> {
    public List<LzQuestionnaireSessionEntity> findByLzQuestionnaireUniqueId(@Param("LzQuestionnaireUniqueId") String lzQuestionnaireUniqueId);

    public List<LzQuestionnaireSessionEntity> findByCountyId(@Param("CountyId") int countyId);

    public LzQuestionnaireSessionEntity findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);

    public List<LzQuestionnaireSessionEntity> findByCountyIdAndLivelihoodZoneId(@Param("CountyId") int countyId, @Param("LivelihoodZoneId") int livelihoodZoneId);

    @Query(nativeQuery = true)
    public List<LzQuestionnaireSessionEntity> fetchQuestionnaireSessionByCountyAndLivelihoodZone(int countyId, int livelihoodzoneId);
}
