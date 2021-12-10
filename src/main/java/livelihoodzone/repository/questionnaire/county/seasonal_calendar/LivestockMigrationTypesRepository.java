package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LivestockMigrationTypesEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzSeasonMonthsEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzSeasonsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface LivestockMigrationTypesRepository extends JpaRepository<LivestockMigrationTypesEntity, Long> {
    public LivestockMigrationTypesEntity findByLivestockMigrationTypeCode(@Param("LivestockMigrationTypeCode") int livestockMigrationTypeCode);
    public LivestockMigrationTypesEntity findByLivestockMigrationTypeId(@Param("LivestockMigrationTypeId") int livestockMigrationTypeId);
}
