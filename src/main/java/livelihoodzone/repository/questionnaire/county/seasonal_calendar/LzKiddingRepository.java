package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzKiddingEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzMilkProductionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LzKiddingRepository extends JpaRepository<LzKiddingEntity, Long> {
}
