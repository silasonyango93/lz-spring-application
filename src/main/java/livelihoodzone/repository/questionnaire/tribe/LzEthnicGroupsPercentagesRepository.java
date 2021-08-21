package livelihoodzone.repository.questionnaire.tribe;

import livelihoodzone.entity.questionnaire.county.LzHazardResponsesEntity;
import livelihoodzone.entity.questionnaire.tribe.EthnicGroupsPercentagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LzEthnicGroupsPercentagesRepository extends JpaRepository<EthnicGroupsPercentagesEntity, Long> {
}
