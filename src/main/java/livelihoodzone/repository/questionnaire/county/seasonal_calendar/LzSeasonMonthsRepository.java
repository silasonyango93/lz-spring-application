package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzSeasonMonthsEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzSeasonsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LzSeasonMonthsRepository extends JpaRepository<LzSeasonMonthsEntity, Long> {
}
