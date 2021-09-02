package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzCasualLabourWagesEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzCeremonyMonthsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LzCeremonyMonthsRepository extends JpaRepository<LzCeremonyMonthsEntity, Long> {
}
