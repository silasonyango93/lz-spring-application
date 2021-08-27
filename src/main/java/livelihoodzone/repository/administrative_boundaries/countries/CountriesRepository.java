package livelihoodzone.repository.administrative_boundaries.countries;

import livelihoodzone.entity.administrative_boundaries.countries.CountriesEntity;
import livelihoodzone.entity.administrative_boundaries.subcounties.SubCountyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountriesRepository extends JpaRepository<CountriesEntity, Long> {
}
