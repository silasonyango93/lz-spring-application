package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzKiddingEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzMarketAccessMonthsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LzMarketAccessMonthsRepository extends JpaRepository<LzMarketAccessMonthsEntity, Long> {
}
