package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzFoodPricesEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzLeanSeasonMonthsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzLeanSeasonMonthsRepository extends JpaRepository<LzLeanSeasonMonthsEntity, Long> {
    public List<LzLeanSeasonMonthsEntity> findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);
}
