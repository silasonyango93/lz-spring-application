package livelihoodzone.repository.questionnaire.county;

import livelihoodzone.entity.questionnaire.county.RainySeasonsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface RainySeasonsRepository extends JpaRepository<RainySeasonsEntity, Long> {
    public RainySeasonsEntity findByRainySeasonCode(@Param("RainySeasonCode") int rainySeasonCode);
    public RainySeasonsEntity findByRainySeasonId(@Param("RainySeasonId") int rainySeasonId);
}
