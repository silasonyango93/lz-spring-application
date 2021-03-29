package livelihoodzone.entity.administrative_boundaries.counties;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "counties")
public class CountiesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CountyId")
    private int countyId;

    @Column(name = "CountyName")
    private String countyName;

    @Column(name = "CountyCode")
    private String countyCode;

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
}
