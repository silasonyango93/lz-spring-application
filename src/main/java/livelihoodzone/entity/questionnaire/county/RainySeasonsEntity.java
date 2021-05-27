package livelihoodzone.entity.questionnaire.county;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "rainy_seasons")
public class RainySeasonsEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RainySeasonId")
    private int rainySeasonId;

    @Column(name = "RainySeasonName")
    private String rainySeasonName;

    @Column(name = "RainySeasonCode")
    private int rainySeasonCode;

    public int getRainySeasonId() {
        return rainySeasonId;
    }

    public void setRainySeasonId(int rainySeasonId) {
        this.rainySeasonId = rainySeasonId;
    }

    public String getRainySeasonName() {
        return rainySeasonName;
    }

    public void setRainySeasonName(String rainySeasonName) {
        this.rainySeasonName = rainySeasonName;
    }

    public int getRainySeasonCode() {
        return rainySeasonCode;
    }

    public void setRainySeasonCode(int rainySeasonCode) {
        this.rainySeasonCode = rainySeasonCode;
    }
}
