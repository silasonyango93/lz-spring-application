package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzCasualLabourWagesEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzCeremonyMonthsEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzConflictRisksMonthsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzCeremonyMonthsRepository extends JpaRepository<LzCeremonyMonthsEntity, Long> {
    public List<LzCeremonyMonthsEntity> findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);

    public long deleteByLzQuestionnaireSessionId(int lzQuestionnaireSessionId);
}
