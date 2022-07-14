package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzFoodPricesEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzLandPreparationMonthsEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzNonAgricCasualLabourAvailabilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzNonAgricCasualLabourAvailabilityRepository extends JpaRepository<LzNonAgricCasualLabourAvailabilityEntity, Long> {
    public List<LzNonAgricCasualLabourAvailabilityEntity> findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);

    public long deleteByLzQuestionnaireSessionId(int lzQuestionnaireSessionId);
}
