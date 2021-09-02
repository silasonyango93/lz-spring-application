package livelihoodzone.entity.questionnaire.county.seasonal_calendar;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "lz_non_agriculture_casual_labour_availability")
public class LzNonAgricCasualLabourAvailabilityEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NonAgricCasualLabourAvailabilityId")
    private int nonAgricCasualLabourAvailabilityId;

    @Column(name = "LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @Column(name = "ScaleMetricId")
    private int scaleMetricId;

    @Column(name = "MonthId")
    private int monthId;

    public LzNonAgricCasualLabourAvailabilityEntity() {
    }

    public LzNonAgricCasualLabourAvailabilityEntity(int lzQuestionnaireSessionId, int scaleMetricId, int monthId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
        this.scaleMetricId = scaleMetricId;
        this.monthId = monthId;
    }

    public int getNonAgricCasualLabourAvailabilityId() {
        return nonAgricCasualLabourAvailabilityId;
    }

    public void setNonAgricCasualLabourAvailabilityId(int nonAgricCasualLabourAvailabilityId) {
        this.nonAgricCasualLabourAvailabilityId = nonAgricCasualLabourAvailabilityId;
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
