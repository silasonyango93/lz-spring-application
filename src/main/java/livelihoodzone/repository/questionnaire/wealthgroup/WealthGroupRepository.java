package livelihoodzone.repository.questionnaire.wealthgroup;

import livelihoodzone.entity.questionnaire.wealthgroup.WealthGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface WealthGroupRepository extends JpaRepository<WealthGroupEntity, Long> {
    public WealthGroupEntity findByWealthGroupCode(@Param("WealthGroupCode") int wealthGroupCode);

    public WealthGroupEntity findByWealthGroupId(@Param("WealthGroupId") int wealthGroupId);
}
