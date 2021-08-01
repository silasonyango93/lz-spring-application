package livelihoodzone.entity.questionnaire.county;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "water_sources")
public class WaterSourcesEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WaterSourceId")
    private int waterSourceId;

    @Column(name = "WaterSourceDescription")
    private String waterSourceDescription;

    @Column(name = "WaterSourceCode")
    private int waterSourceCode;

    public int getWaterSourceId() {
        return waterSourceId;
    }

    public void setWaterSourceId(int waterSourceId) {
        this.waterSourceId = waterSourceId;
    }

    public String getWaterSourceDescription() {
        return waterSourceDescription;
    }

    public void setWaterSourceDescription(String waterSourceDescription) {
        this.waterSourceDescription = waterSourceDescription;
    }

    public int getWaterSourceCode() {
        return waterSourceCode;
    }

    public void setWaterSourceCode(int waterSourceCode) {
        this.waterSourceCode = waterSourceCode;
    }
}
