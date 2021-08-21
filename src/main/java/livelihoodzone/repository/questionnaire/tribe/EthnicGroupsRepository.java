package livelihoodzone.repository.questionnaire.tribe;

import livelihoodzone.entity.questionnaire.county.LzHazardsEntity;
import livelihoodzone.entity.questionnaire.crops.CropsEntity;
import livelihoodzone.entity.questionnaire.tribe.EthnicGroupsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface EthnicGroupsRepository extends JpaRepository<EthnicGroupsEntity, Long> {
    public EthnicGroupsEntity findByEthnicGroupId(@Param("EthnicGroupId") int ethnicGroupId);
}
