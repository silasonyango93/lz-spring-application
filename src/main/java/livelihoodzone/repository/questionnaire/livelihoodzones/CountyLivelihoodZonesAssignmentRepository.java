package livelihoodzone.repository.questionnaire.livelihoodzones;

import livelihoodzone.entity.questionnaire.livelihoodzones.CountyLivelihoodZonesAssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountyLivelihoodZonesAssignmentRepository extends JpaRepository<CountyLivelihoodZonesAssignmentEntity, Long> {
    public List<CountyLivelihoodZonesAssignmentEntity> findByCountyId(@Param("CountyId") int countyId);
}
