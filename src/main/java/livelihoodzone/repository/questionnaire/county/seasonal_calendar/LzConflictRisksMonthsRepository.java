package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzCasualLabourWagesEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzConflictRisksMonthsEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzWaterStressMonthsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzConflictRisksMonthsRepository extends JpaRepository<LzConflictRisksMonthsEntity, Long> {
    public List<LzConflictRisksMonthsEntity> findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);
}
