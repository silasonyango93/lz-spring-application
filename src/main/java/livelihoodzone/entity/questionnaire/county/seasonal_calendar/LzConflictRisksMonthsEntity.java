package livelihoodzone.entity.questionnaire.county.seasonal_calendar;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "lz_conflict_risk_months")
public class LzConflictRisksMonthsEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LzConflictRiskMonthId")
    private int lzConflictRiskMonthId;

    @Column(name = "LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @Column(name = "MonthId")
    private int monthId;

    public LzConflictRisksMonthsEntity() {
    }

    public LzConflictRisksMonthsEntity(int lzQuestionnaireSessionId, int monthId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
        this.monthId = monthId;
    }

    public int getLzConflictRiskMonthId() {
        return lzConflictRiskMonthId;
    }

    public void setLzConflictRiskMonthId(int lzConflictRiskMonthId) {
        this.lzConflictRiskMonthId = lzConflictRiskMonthId;
    }

    public int getLzQuestionnaireSessionId() {
        return lzQuestionnaireSessionId;
    }

    public void setLzQuestionnaireSessionId(int lzQuestionnaireSessionId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
    }

    public int getMonthId() {
        return monthId;
    }

    public void setMonthId(int monthId) {
        this.monthId = monthId;
    }
}
