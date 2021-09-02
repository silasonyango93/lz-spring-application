package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzCalvingEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzCasualLabourWagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LzCasualLabourWagesRepository extends JpaRepository<LzCasualLabourWagesEntity, Long> {
}
