package livelihoodzone.repository.questionnaire.county.seasonal_calendar;

import livelihoodzone.entity.questionnaire.county.CropWaterAccessTypesEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.LzSeasonsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface LzSeasonsRepository extends JpaRepository<LzSeasonsEntity, Long> {
    public LzSeasonsEntity findByLzSeasonCode(@Param("LzSeasonCode") int lzSeasonCode);
}
