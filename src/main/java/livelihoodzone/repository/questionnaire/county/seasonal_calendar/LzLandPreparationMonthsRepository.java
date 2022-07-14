package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzKiddingEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzLandPreparationMonthsEntity;
import livelihoodzone.entity.questionnaire.crops.CropsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzLandPreparationMonthsRepository extends JpaRepository<LzLandPreparationMonthsEntity, Long> {
    public List<LzLandPreparationMonthsEntity> findByLzQuestionnaireSessionIdAndCropId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId, @Param("CropId") int cropId);

    public long deleteByLzQuestionnaireSessionId(int lzQuestionnaireSessionId);
}
