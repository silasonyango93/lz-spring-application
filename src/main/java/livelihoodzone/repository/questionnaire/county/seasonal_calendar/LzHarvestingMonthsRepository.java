package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzCalvingEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzHarvestingMonthsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LzHarvestingMonthsRepository extends JpaRepository<LzHarvestingMonthsEntity, Long> {
}
