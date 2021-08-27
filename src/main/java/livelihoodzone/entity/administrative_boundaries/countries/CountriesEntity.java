package livelihoodzone.entity.administrative_boundaries.countries;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "countries")
public class CountriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CountryId")
    private int countryId;

    @Column(name = "CountryName")
    private String countryName;

    @Column(name = "CountryCode")
    private int countryCode;

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }
}
