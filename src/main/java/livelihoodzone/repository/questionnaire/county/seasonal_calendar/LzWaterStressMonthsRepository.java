package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzRemittancesEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzWaterStressMonthsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LzWaterStressMonthsRepository extends JpaRepository<LzWaterStressMonthsEntity, Long> {
}
