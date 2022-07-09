package livelihoodzone.repository.questionnaire.county;

import livelihoodzone.entity.questionnaire.county.LzCropProductionResponsesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzCropProductionResponsesRepository extends JpaRepository<LzCropProductionResponsesEntity, Long> {
    public List<LzCropProductionResponsesEntity> findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);

    public LzCropProductionResponsesEntity findByLzQuestionnaireSessionIdAndCropIdAndRainySeasonIdAndCropWaterAccessTypeId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId, @Param("CropId") int cropId, @Param("RainySeasonId") int rainySeasonId, @Param("CropWaterAccessTypeId") int cropWaterAccessTypeId);

    public long deleteByLzQuestionnaireSessionId(int lzQuestionnaireSessionId);
}
