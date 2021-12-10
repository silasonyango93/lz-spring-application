package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzMilkProductionEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzSeasonMonthsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzMilkProductionRepository extends JpaRepository<LzMilkProductionEntity, Long> {
    public List<LzMilkProductionEntity> findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);
}
