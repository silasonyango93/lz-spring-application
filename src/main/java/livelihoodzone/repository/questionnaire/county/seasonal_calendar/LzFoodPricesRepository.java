package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzFoodPricesEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzKiddingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LzFoodPricesRepository extends JpaRepository<LzFoodPricesEntity, Long> {
}
