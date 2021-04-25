package livelihoodzone.repository.questionnaire.livelihoodzones;

import livelihoodzone.entity.questionnaire.livelihoodzones.LivelihoodZonesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface LivelihoodZonesRepository extends JpaRepository<LivelihoodZonesEntity, Long> {
    public LivelihoodZonesEntity findByLivelihoodZoneId(@Param("LivelihoodZoneId") int livelihoodZoneId);
}
