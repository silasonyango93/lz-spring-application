package livelihoodzone.entity.administrative_boundaries.sublocation;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "sublocations")
public class SubLocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SubLocationId")
    private int subLocationId;

    @Column(name = "WardId")
    private int wardId;

    @Column(name = "SubLocationName")
    private String subLocationName;

    @Column(name = "SubLocationCode")
    private int subLocationCode;

    public SubLocationEntity() {
    }

    public SubLocationEntity(int wardId, String subLocationName, int subLocationCode) {
        this.wardId = wardId;
        this.subLocationName = subLocationName;
        this.subLocationCode = subLocationCode;
    }

    public int getSubLocationId() {
        return subLocationId;
    }

    public void setSubLocationId(int subLocationId) {
        this.subLocationId = subLocationId;
    }

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
    }

    public String getSubLocationName() {
        return subLocationName;
    }

    public void setSubLocationName(String subLocationName) {
        this.subLocationName = subLocationName;
    }

    public int getSubLocationCode() {
        return subLocationCode;
    }

    public void setSubLocationCode(int subLocationCode) {
        this.subLocationCode = subLocationCode;
    }
}
