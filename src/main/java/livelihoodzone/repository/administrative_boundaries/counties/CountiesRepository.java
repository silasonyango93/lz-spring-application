package livelihoodzone.repository.administrative_boundaries.counties;

import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.entity.user_management.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CountiesRepository extends JpaRepository<CountiesEntity, Long> {
    public CountiesEntity findByCountyId(@Param("CountyId") int countyId);
}
