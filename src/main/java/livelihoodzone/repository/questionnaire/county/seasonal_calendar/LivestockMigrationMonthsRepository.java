package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LivestockMigrationMonthsEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LivestockMigrationTypesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivestockMigrationMonthsRepository extends JpaRepository<LivestockMigrationMonthsEntity, Long> {
    public List<LivestockMigrationMonthsEntity> findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);

    public long deleteByLzQuestionnaireSessionId(int lzQuestionnaireSessionId);
}
