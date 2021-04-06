package livelihoodzone.entity.administrative_boundaries.ward;

import livelihoodzone.entity.administrative_boundaries.sublocation.SubLocationEntity;

import javax.persistence.*;
import java.util.List;

@javax.persistence.Entity
@Table(name = "wards")
public class WardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WardId")
    private int wardId;

    @Column(name = "SubCountyId")
    private int subCountyId;

    @Column(name = "WardName")
    private String wardName;

    @Column(name = "WardCode")
    private int wardCode;

    @OneToMany(mappedBy = "wardId",fetch = FetchType.EAGER)
    private List<SubLocationEntity> subLocations;

    public WardEntity() {
    }

    public WardEntity(int subCountyId, String wardName, int wardCode) {
        this.subCountyId = subCountyId;
        this.wardName = wardName;
        this.wardCode = wardCode;
    }

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
    }

    public int getSubCountyId() {
        return subCountyId;
    }

    public void setSubCountyId(int subCountyId) {
        this.subCountyId = subCountyId;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public int getWardCode() {
        return wardCode;
    }

    public void setWardCode(int wardCode) {
        this.wardCode = wardCode;
    }

    public List<SubLocationEntity> getSubLocations() {
        return subLocations;
    }

    public void setSubLocations(List<SubLocationEntity> subLocations) {
        this.subLocations = subLocations;
    }
}
