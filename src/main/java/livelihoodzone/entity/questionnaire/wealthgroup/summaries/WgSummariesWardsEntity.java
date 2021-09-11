package livelihoodzone.entity.questionnaire.wealthgroup.summaries;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "wg_summaries_wards")
public class WgSummariesWardsEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WgSummariesWardId")
    private int wgSummariesWardId;

    @Column(name = "WgQuestionnaireSessionId")
    private int wgQuestionnaireSessionId;

    @Column(name = "WardId")
    private int wardId;

    public WgSummariesWardsEntity() {
    }

    public WgSummariesWardsEntity(int wgQuestionnaireSessionId, int wardId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
        this.wardId = wardId;
    }

    public int getWgSummariesWardId() {
        return wgSummariesWardId;
    }

    public void setWgSummariesWardId(int wgSummariesWardId) {
        this.wgSummariesWardId = wgSummariesWardId;
    }

    public int getWgQuestionnaireSessionId() {
        return wgQuestionnaireSessionId;
    }

    public void setWgQuestionnaireSessionId(int wgQuestionnaireSessionId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
    }

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
    }
}
