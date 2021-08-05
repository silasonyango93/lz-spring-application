package livelihoodzone.repository.questionnaire.wealthgroup.cropcontribution;

import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireSessionEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.cropcontribution.CropContributionTypesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CropContributionTypesRepository extends JpaRepository<CropContributionTypesEntity, Long> {
    public List<CropContributionTypesEntity> findByCropContributionTypeCode(@Param("CropContributionTypeCode") int cropContributionTypeCode);
}
