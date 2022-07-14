package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzCasualLabourWagesEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzKiddingEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzRemittancesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzRemittancesRepository extends JpaRepository<LzRemittancesEntity, Long> {
    public List<LzRemittancesEntity> findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);

    public long deleteByLzQuestionnaireSessionId(int lzQuestionnaireSessionId);
}
