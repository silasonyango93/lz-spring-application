package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzCasualLabourWagesEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzConflictRisksMonthsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LzConflictRisksMonthsRepository extends JpaRepository<LzConflictRisksMonthsEntity, Long> {
}
