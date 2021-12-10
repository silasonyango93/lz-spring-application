package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzSeasonMonthsEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzSeasonsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzSeasonMonthsRepository extends JpaRepository<LzSeasonMonthsEntity, Long> {
    public List<LzSeasonMonthsEntity> findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);
}
