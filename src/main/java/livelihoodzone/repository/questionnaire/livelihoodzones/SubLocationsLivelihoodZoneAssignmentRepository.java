package livelihoodzone.repository.questionnaire.livelihoodzones;

import livelihoodzone.entity.questionnaire.county.RainySeasonsEntity;
import livelihoodzone.entity.questionnaire.livelihoodzones.SubLocationsLivelihoodZoneAssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface SubLocationsLivelihoodZoneAssignmentRepository extends JpaRepository<SubLocationsLivelihoodZoneAssignmentEntity, Long> {
    public SubLocationsLivelihoodZoneAssignmentEntity findByLzSublocationLivelihoodZoneId(@Param("LzSublocationLivelihoodZoneId") int lzSublocationLivelihoodZoneId);

    public SubLocationsLivelihoodZoneAssignmentEntity findBySubLocationId(@Param("SubLocationId") int subLocationId);
}
