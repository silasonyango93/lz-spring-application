package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzMilkProductionEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzSeasonMonthsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LzMilkProductionRepository extends JpaRepository<LzMilkProductionEntity, Long> {
}
