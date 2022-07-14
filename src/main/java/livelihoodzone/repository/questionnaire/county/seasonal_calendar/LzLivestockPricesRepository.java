package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzKiddingEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzLivestockPricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzLivestockPricesRepository extends JpaRepository<LzLivestockPricesEntity, Long> {
    public List<LzLivestockPricesEntity> findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);

    public long deleteByLzQuestionnaireSessionId(int lzQuestionnaireSessionId);
}
