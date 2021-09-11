package livelihoodzone.entity.questionnaire.wealthgroup.summaries;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "wg_summaries_subcounties")
public class WgSummariesSubCountiesEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WgSummariesSubCountyId")
    private int wgSummariesSubCountyId;

    @Column(name = "WgQuestionnaireSessionId")
    private int wgQuestionnaireSessionId;

    @Column(name = "SubCountyId")
    private int subCountyId;

    public WgSummariesSubCountiesEntity() {
    }

    public WgSummariesSubCountiesEntity(int wgQuestionnaireSessionId, int subCountyId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
        this.subCountyId = subCountyId;
    }

    public int getWgSummariesSubCountyId() {
        return wgSummariesSubCountyId;
    }

    public void setWgSummariesSubCountyId(int wgSummariesSubCountyId) {
        this.wgSummariesSubCountyId = wgSummariesSubCountyId;
    }

    public int getWgQuestionnaireSessionId() {
        return wgQuestionnaireSessionId;
    }

    public void setWgQuestionnaireSessionId(int wgQuestionnaireSessionId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
    }

    public int getSubCountyId() {
        return subCountyId;
    }

    public void setSubCountyId(int subCountyId) {
        this.subCountyId = subCountyId;
    }
}
