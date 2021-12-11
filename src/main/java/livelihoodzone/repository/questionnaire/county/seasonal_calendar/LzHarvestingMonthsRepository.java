package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzCalvingEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzHarvestingMonthsEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzPlantingMonthsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzHarvestingMonthsRepository extends JpaRepository<LzHarvestingMonthsEntity, Long> {
    public List<LzHarvestingMonthsEntity> findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);
    public List<LzHarvestingMonthsEntity> findByLzQuestionnaireSessionIdAndCropId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId, @Param("CropId") int cropId);
}
