package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LivestockMigrationTypesEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzCalvingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LzCalvingRepository extends JpaRepository<LzCalvingEntity, Long> {
}
