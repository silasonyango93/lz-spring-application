package livelihoodzone.entity.questionnaire;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "livelihood_zones")

public class LivelihoodZonesEntity implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LivelihoodZoneId")
    private int livelihoodZoneId;

    @Column(name = "LivelihoodZoneName")
    private String livelihoodZoneName;

    @Column(name = "LivelihoodZoneCode")
    private int livelihoodZoneCode;

    public int getLivelihoodZoneId() {
        return livelihoodZoneId;
    }

    public void setLivelihoodZoneId(int livelihoodZoneId) {
        this.livelihoodZoneId = livelihoodZoneId;
    }

    public String getLivelihoodZoneName() {
        return livelihoodZoneName;
    }

    public void setLivelihoodZoneName(String livelihoodZoneName) {
        this.livelihoodZoneName = livelihoodZoneName;
    }

    public int getLivelihoodZoneCode() {
        return livelihoodZoneCode;
    }

    public void setLivelihoodZoneCode(int livelihoodZoneCode) {
        this.livelihoodZoneCode = livelihoodZoneCode;
    }
}
