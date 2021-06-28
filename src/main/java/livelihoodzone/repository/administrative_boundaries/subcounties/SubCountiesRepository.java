package livelihoodzone.repository.administrative_boundaries.subcounties;

import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.entity.administrative_boundaries.subcounties.SubCountyEntity;
import livelihoodzone.entity.administrative_boundaries.sublocation.SubLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubCountiesRepository extends JpaRepository<SubCountyEntity, Long> {
    public List<SubCountyEntity> findByCountyId(@Param("CountyId") int countyId);

    public SubCountyEntity findBySubCountyId(@Param("SubCountyId") int subCountyId);
}
