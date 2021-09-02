package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzCalvingEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzDiseaseOutBreakMonthsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LzDiseaseOutBreakMonthsRepository extends JpaRepository<LzDiseaseOutBreakMonthsEntity, Long> {
}
