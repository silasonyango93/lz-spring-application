package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzFoodPricesEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzKiddingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzFoodPricesRepository extends JpaRepository<LzFoodPricesEntity, Long> {
    public List<LzFoodPricesEntity> findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);

    public long deleteByLzQuestionnaireSessionId(int lzQuestionnaireSessionId);
}
