package livelihoodzone.repository.questionnaire.tribe;

import livelihoodzone.entity.questionnaire.county.LzHazardResponsesEntity;
import livelihoodzone.entity.questionnaire.tribe.EthnicGroupsPercentagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LzEthnicGroupsPercentagesRepository extends JpaRepository<EthnicGroupsPercentagesEntity, Long> {
    public List<EthnicGroupsPercentagesEntity> findByLzQuestionnaireSessionId(@Param("LzQuestionnaireSessionId") int lzQuestionnaireSessionId);
}
