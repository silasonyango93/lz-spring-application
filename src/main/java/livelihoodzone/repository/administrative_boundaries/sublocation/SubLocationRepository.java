package livelihoodzone.repository.administrative_boundaries.sublocation;

import livelihoodzone.entity.administrative_boundaries.subcounties.SubCountyEntity;
import livelihoodzone.entity.administrative_boundaries.sublocation.SubLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface SubLocationRepository extends JpaRepository<SubLocationEntity, Long> {
    public SubLocationEntity findBySubLocationId(@Param("SubLocationId") int subLocationId);
}
