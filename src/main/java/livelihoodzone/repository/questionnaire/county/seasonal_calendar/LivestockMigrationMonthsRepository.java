package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LivestockMigrationMonthsEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LivestockMigrationTypesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivestockMigrationMonthsRepository extends JpaRepository<LivestockMigrationMonthsEntity, Long> {
}
