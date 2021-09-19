package livelihoodzone.entity.questionnaire.county.seasonal_calendar;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "zone_level_sampled_sublocations")
public class ZoneLevelSampledSubLocationsEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ZoneLevelSampledSubLocationId")
    private int zoneLevelSampledSubLocationId;

    @Column(name = "LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @Column(name = "SubLocationId")
    private int subLocationId;

    public ZoneLevelSampledSubLocationsEntity() {
    }

    public ZoneLevelSampledSubLocationsEntity(int lzQuestionnaireSessionId, int subLocationId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
        this.subLocationId = subLocationId;
    }

    public int getZoneLevelSampledSubLocationId() {
        return zoneLevelSampledSubLocationId;
    }

    public void setZoneLevelSampledSubLocationId(int zoneLevelSampledSubLocationId) {
        this.zoneLevelSampledSubLocationId = zoneLevelSampledSubLocationId;
    }

    public int getLzQuestionnaireSessionId() {
        return lzQuestionnaireSessionId;
    }

    public void setLzQuestionnaireSessionId(int lzQuestionnaireSessionId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
    }

    public int getSubLocationId() {
        return subLocationId;
    }

    public void setSubLocationId(int subLocationId) {
        this.subLocationId = subLocationId;
    }
}
