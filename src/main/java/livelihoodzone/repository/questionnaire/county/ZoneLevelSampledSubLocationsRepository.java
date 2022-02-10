package livelihoodzone.repository.questionnaire.county;

import livelihoodzone.entity.questionnaire.county.CropWaterAccessTypesEntity;
import livelihoodzone.entity.questionnaire.county.LzQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.ZoneLevelSampledSubLocationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ZoneLevelSampledSubLocationsRepository extends JpaRepository<ZoneLevelSampledSubLocationsEntity, Long> {
    public List<ZoneLevelSampledSubLocationsEntity> findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);
}
