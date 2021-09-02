package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzKiddingEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzRemittancesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LzRemittancesRepository extends JpaRepository<LzRemittancesEntity, Long> {
}
