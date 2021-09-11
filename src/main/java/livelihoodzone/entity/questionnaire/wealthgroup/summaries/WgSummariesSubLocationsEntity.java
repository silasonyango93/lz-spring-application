package livelihoodzone.entity.questionnaire.wealthgroup.summaries;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "wg_summaries_sublocations")
public class WgSummariesSubLocationsEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WgSummariesSubLocationId")
    private int wgSummariesSubLocationId;

    @Column(name = "WgQuestionnaireSessionId")
    private int wgQuestionnaireSessionId;

    @Column(name = "SubLocationId")
    private int subLocationId;

    public WgSummariesSubLocationsEntity() {
    }

    public WgSummariesSubLocationsEntity(int wgQuestionnaireSessionId, int subLocationId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
        this.subLocationId = subLocationId;
    }

    public int getWgSummariesSubLocationId() {
        return wgSummariesSubLocationId;
    }

    public void setWgSummariesSubLocationId(int wgSummariesSubLocationId) {
        this.wgSummariesSubLocationId = wgSummariesSubLocationId;
    }

    public int getWgQuestionnaireSessionId() {
        return wgQuestionnaireSessionId;
    }

    public void setWgQuestionnaireSessionId(int wgQuestionnaireSessionId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
    }

    public int getSubLocationId() {
        return subLocationId;
    }

    public void setSubLocationId(int subLocationId) {
        this.subLocationId = subLocationId;
    }
}
