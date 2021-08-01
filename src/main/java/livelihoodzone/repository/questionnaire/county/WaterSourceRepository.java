package livelihoodzone.repository.questionnaire.county;

import livelihoodzone.entity.questionnaire.county.WaterSourcesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface WaterSourceRepository extends JpaRepository<WaterSourcesEntity, Long> {
    public WaterSourcesEntity findByWaterSourceCode(@Param("WaterSourceCode") int waterSourceCode);
}
