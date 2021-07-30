package livelihoodzone.repository.questionnaire.county;

import livelihoodzone.entity.questionnaire.county.CropWaterAccessTypesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CropWaterAccessTypesRepository extends JpaRepository<CropWaterAccessTypesEntity, Long> {
    public CropWaterAccessTypesEntity findByCropWaterAccessTypeCode(@Param("CropWaterAccessTypeCode") int cropWaterAccessTypeCode);
}
