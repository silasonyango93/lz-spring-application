package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzCalvingEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzFishingMonthsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LzFishingMonthsRepository extends JpaRepository<LzFishingMonthsEntity, Long> {
}
