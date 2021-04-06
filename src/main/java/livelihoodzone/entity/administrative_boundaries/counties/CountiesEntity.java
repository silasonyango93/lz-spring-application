package livelihoodzone.entity.administrative_boundaries.counties;

import livelihoodzone.entity.administrative_boundaries.subcounties.SubCountyEntity;

import javax.persistence.*;
import java.util.List;

@javax.persistence.Entity
@Table(name = "counties")

@javax.persistence.SqlResultSetMapping(
        name = "counties", entities =
@javax.persistence.EntityResult(entityClass = CountiesEntity.class)
)


@NamedNativeQueries({
        @NamedNativeQuery(
                name="CountiesEntity.fetchCountyComprehensively",
                query="SELECT * FROM counties INNER JOIN subcounties ON counties.CountyId = subcounties.CountyId INNER JOIN wards ON subcounties.SubCountyId = wards.SubCountyId INNER JOIN sublocations ON wards.WardId = sublocations.WardId WHERE counties.CountyId = ?",
                resultSetMapping = "counties")
})

public class CountiesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CountyId")
    private int countyId;

    @Column(name = "CountyName")
    private String countyName;

    @Column(name = "CountyCode")
    private String countyCode;

    @OneToMany(mappedBy = "countyId",fetch = FetchType.EAGER)
    private List<SubCountyEntity> subCounties;

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public List<SubCountyEntity> getSubCounties() {
        return subCounties;
    }

    public void setSubCounties(List<SubCountyEntity> subCounties) {
        this.subCounties = subCounties;
    }
}
