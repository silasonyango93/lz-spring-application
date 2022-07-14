package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzFoodSecurityAssessmentsEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzKiddingEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzLeanSeasonMonthsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzFoodSecurityAssessmentsRepository extends JpaRepository<LzFoodSecurityAssessmentsEntity, Long> {
    public List<LzFoodSecurityAssessmentsEntity> findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);

    public long deleteByLzQuestionnaireSessionId(int lzQuestionnaireSessionId);
}
