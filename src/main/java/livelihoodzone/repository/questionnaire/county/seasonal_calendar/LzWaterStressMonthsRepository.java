package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzRemittancesEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzWaterStressMonthsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzWaterStressMonthsRepository extends JpaRepository<LzWaterStressMonthsEntity, Long> {
    public List<LzWaterStressMonthsEntity> findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);
}
