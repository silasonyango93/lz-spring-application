package livelihoodzone.repository.administrative_boundaries.countries;

import livelihoodzone.entity.administrative_boundaries.countries.CountriesEntity;
import livelihoodzone.entity.administrative_boundaries.subcounties.SubCountyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CountriesRepository extends JpaRepository<CountriesEntity, Long> {
    public CountriesEntity findByCountryId(@Param("CountryId") int countryId);
}
