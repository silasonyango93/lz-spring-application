package livelihoodzone.entity.questionnaire.livelihoodzones;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "county_livelihoodzone_assignment")
public class CountyLivelihoodZonesAssignmentEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CountyLivelihoodZoneAssignmentId")
    private int countyLivelihoodZoneAssignmentId;

    @Column(name = "CountyId")
    private int countyId;

    @Column(name = "LivelihoodZoneId")
    private int livelihoodZoneId;

    @Column(name = "IsActive")
    private int isActive;

    public CountyLivelihoodZonesAssignmentEntity() {
    }

    public CountyLivelihoodZonesAssignmentEntity(int countyLivelihoodZoneAssignmentId, int countyId, int livelihoodZoneId, int isActive) {
        this.countyLivelihoodZoneAssignmentId = countyLivelihoodZoneAssignmentId;
        this.countyId = countyId;
        this.livelihoodZoneId = livelihoodZoneId;
        this.isActive = isActive;
    }

    public CountyLivelihoodZonesAssignmentEntity(int countyId, int livelihoodZoneId, int isActive) {
        this.countyId = countyId;
        this.livelihoodZoneId = livelihoodZoneId;
        this.isActive = isActive;
    }

    public int getCountyLivelihoodZoneAssignmentId() {
        return countyLivelihoodZoneAssignmentId;
    }

    public void setCountyLivelihoodZoneAssignmentId(int countyLivelihoodZoneAssignmentId) {
        this.countyLivelihoodZoneAssignmentId = countyLivelihoodZoneAssignmentId;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public int getLivelihoodZoneId() {
        return livelihoodZoneId;
    }

    public void setLivelihoodZoneId(int livelihoodZoneId) {
        this.livelihoodZoneId = livelihoodZoneId;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
}
