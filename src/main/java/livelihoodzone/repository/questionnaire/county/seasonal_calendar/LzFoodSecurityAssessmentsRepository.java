package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzFoodSecurityAssessmentsEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzKiddingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LzFoodSecurityAssessmentsRepository extends JpaRepository<LzFoodSecurityAssessmentsEntity, Long> {
}
