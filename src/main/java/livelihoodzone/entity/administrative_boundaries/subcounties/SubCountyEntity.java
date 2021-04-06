package livelihoodzone.entity.administrative_boundaries.subcounties;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "subcounties")
public class SubCountyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SubCountyId")
    private int subCountyId;

    @Column(name = "CountyId")
    private int countyId;

    @Column(name = "SubCountyName")
    private String subCountyName;

    @Column(name = "SubCountyCode")
    private int subCountyCode;

    public SubCountyEntity() {
    }

    public SubCountyEntity(int countyId, String subCountyName, int subCountyCode) {
        this.countyId = countyId;
        this.subCountyName = subCountyName;
        this.subCountyCode = subCountyCode;
    }

    public int getSubCountyId() {
        return subCountyId;
    }

    public void setSubCountyId(int subCountyId) {
        this.subCountyId = subCountyId;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public String getSubCountyName() {
        return subCountyName;
    }

    public void setSubCountyName(String subCountyName) {
        this.subCountyName = subCountyName;
    }

    public int getSubCountyCode() {
        return subCountyCode;
    }

    public void setSubCountyCode(int subCountyCode) {
        this.subCountyCode = subCountyCode;
    }
}
