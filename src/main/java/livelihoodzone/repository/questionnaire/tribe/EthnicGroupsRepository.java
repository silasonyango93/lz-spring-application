package livelihoodzone.repository.questionnaire.tribe;

import livelihoodzone.entity.questionnaire.crops.CropsEntity;
import livelihoodzone.entity.questionnaire.tribe.EthnicGroupsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EthnicGroupsRepository extends JpaRepository<EthnicGroupsEntity, Long> {
}
