package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzKiddingEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzLivestockPricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LzLivestockPricesRepository extends JpaRepository<LzLivestockPricesEntity, Long> {
}
