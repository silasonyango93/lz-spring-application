package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzLandPreparationMonthsEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzMilkProductionEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzPlantingMonthsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzPlantingMonthsRepository extends JpaRepository<LzPlantingMonthsEntity, Long> {
    public List<LzPlantingMonthsEntity> findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);
    public List<LzPlantingMonthsEntity> findByLzQuestionnaireSessionIdAndCropId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId, @Param("CropId") int cropId);

    public long deleteByLzQuestionnaireSessionId(int lzQuestionnaireSessionId);
}
