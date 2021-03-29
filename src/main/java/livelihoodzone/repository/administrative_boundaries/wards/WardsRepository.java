package livelihoodzone.repository.administrative_boundaries.wards;

import livelihoodzone.entity.administrative_boundaries.subcounties.SubCountyEntity;
import livelihoodzone.entity.administrative_boundaries.ward.WardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WardsRepository extends JpaRepository<WardEntity, Long> {
}
