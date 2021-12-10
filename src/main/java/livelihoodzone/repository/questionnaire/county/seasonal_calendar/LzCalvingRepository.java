package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LivestockMigrationTypesEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzCalvingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzCalvingRepository extends JpaRepository<LzCalvingEntity, Long> {
    public List<LzCalvingEntity> findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);
}
