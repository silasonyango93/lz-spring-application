package livelihoodzone.entity.questionnaire.livelihoodzones;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "sublocations_livelihoodzone_assignment")
public class SubLocationsLivelihoodZoneAssignmentEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LzSublocationLivelihoodZoneId")
    private int lzSublocationLivelihoodZoneId;

    @Column(name = "SubLocationId")
    private int subLocationId;

    @Column(name = "LivelihoodZoneId")
    private int livelihoodZoneId;

    public int getLzSublocationLivelihoodZoneId() {
        return lzSublocationLivelihoodZoneId;
    }

    public void setLzSublocationLivelihoodZoneId(int lzSublocationLivelihoodZoneId) {
        this.lzSublocationLivelihoodZoneId = lzSublocationLivelihoodZoneId;
    }

    public int getSubLocationId() {
        return subLocationId;
    }

    public void setSubLocationId(int subLocationId) {
        this.subLocationId = subLocationId;
    }

    public int getLivelihoodZoneId() {
        return livelihoodZoneId;
    }

    public void setLivelihoodZoneId(int livelihoodZoneId) {
        this.livelihoodZoneId = livelihoodZoneId;
    }
}
