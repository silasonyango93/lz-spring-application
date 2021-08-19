package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.seasonal_calendar.HighLowMediumScaleEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LivestockMigrationMonthsEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzSeasonsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface HighLowMediumScaleRepository extends JpaRepository<HighLowMediumScaleEntity, Long> {
    public HighLowMediumScaleEntity findByScaleMetricCode(@Param("ScaleMetricCode") int scaleMetricCode);
}
