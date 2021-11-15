package livelihoodzone.repository.questionnaire.wealthgroup.cropcontribution;

import livelihoodzone.entity.questionnaire.wealthgroup.cropcontribution.WgCropContributionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WgCropContributionsRepository extends JpaRepository<WgCropContributionsEntity, Long> {
    public List<WgCropContributionsEntity> findByWgQuestionnaireSessionId(@Param("WgQuestionnaireSessionId") int wgQuestionnaireSessionId);
}
