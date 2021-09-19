package livelihoodzone.repository.questionnaire.county;

import livelihoodzone.entity.questionnaire.county.CropWaterAccessTypesEntity;
import livelihoodzone.entity.questionnaire.county.seasonal_calendar.ZoneLevelSampledSubLocationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneLevelSampledSubLocationsRepository extends JpaRepository<ZoneLevelSampledSubLocationsEntity, Long> {
}
