package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzMilkProductionEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzPlantingMonthsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LzPlantingMonthsRepository extends JpaRepository<LzPlantingMonthsEntity, Long> {
}
