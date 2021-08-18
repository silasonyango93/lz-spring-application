package livelihoodzone.repository.questionnaire.county;

import livelihoodzone.entity.questionnaire.county.CropWaterAccessTypesEntity;
import livelihoodzone.entity.questionnaire.county.LzHazardResponsesEntity;
import livelihoodzone.entity.questionnaire.county.LzHazardsEntity;
import livelihoodzone.entity.questionnaire.county.LzHungerPatternsResponsesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface LzHazardsRepository extends JpaRepository<LzHazardsEntity, Long> {
    public LzHazardsEntity findByLzHazardCode(@Param("LzHazardCode") int lzHazardCode);
}
