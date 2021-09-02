package livelihoodzone.entity.questionnaire.county.seasonal_calendar;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "lz_remittances")
public class LzRemittancesEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LzRemittanceId")
    private int lzRemittanceId;

    @Column(name = "LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @Column(name = "ScaleMetricId")
    private int scaleMetricId;

    @Column(name = "MonthId")
    private int monthId;

    public LzRemittancesEntity() {
    }

    public LzRemittancesEntity(int lzQuestionnaireSessionId, int scaleMetricId, int monthId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
        this.scaleMetricId = scaleMetricId;
        this.monthId = monthId;
    }

    public int getLzRemittanceId() {
        return lzRemittanceId;
    }

    public void setLzRemittanceId(int lzRemittanceId) {
        this.lzRemittanceId = lzRemittanceId;
    }

    public int getLzQuestionnaireSessionId() {
        return lzQuestionnaireSessionId;
    }

    public void setLzQuestionnaireSessionId(int lzQuestionnaireSessionId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
    }

    public int getScaleMetricId() {
        return scaleMetricId;
    }

    public void setScaleMetricId(int scaleMetricId) {
        this.scaleMetricId = scaleMetricId;
    }

    public int getMonthId() {
        return monthId;
    }

    public void setMonthId(int monthId) {
        this.monthId = monthId;
    }
}
