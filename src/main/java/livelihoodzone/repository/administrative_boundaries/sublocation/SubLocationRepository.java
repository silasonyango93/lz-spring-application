package livelihoodzone.repository.administrative_boundaries.sublocation;

import livelihoodzone.entity.administrative_boundaries.subcounties.SubCountyEntity;
import livelihoodzone.entity.administrative_boundaries.sublocation.SubLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubLocationRepository extends JpaRepository<SubLocationEntity, Long> {
}
