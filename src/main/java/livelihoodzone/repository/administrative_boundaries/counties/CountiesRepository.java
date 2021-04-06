package livelihoodzone.repository.administrative_boundaries.counties;

import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.entity.user_management.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountiesRepository extends JpaRepository<CountiesEntity, Long> {
    public CountiesEntity findByCountyId(@Param("CountyId") int countyId);

    @Query(nativeQuery = true)
    public List<CountiesEntity> fetchCountyComprehensively(int countyId);
}
