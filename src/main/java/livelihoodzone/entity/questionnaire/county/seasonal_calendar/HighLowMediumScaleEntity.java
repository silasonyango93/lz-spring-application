package livelihoodzone.entity.questionnaire.county.seasonal_calendar;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "high_low_medium_scale")
public class HighLowMediumScaleEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ScaleMetricId")
    private int scaleMetricId;

    @Column(name = "ScaleMetricName")
    private String scaleMetricName;

    @Column(name = "ScaleMetricCode")
    private int scaleMetricCode;

    public int getScaleMetricId() {
        return scaleMetricId;
    }

    public void setScaleMetricId(int scaleMetricId) {
        this.scaleMetricId = scaleMetricId;
    }

    public String getScaleMetricName() {
        return scaleMetricName;
    }

    public void setScaleMetricName(String scaleMetricName) {
        this.scaleMetricName = scaleMetricName;
    }

    public int getScaleMetricCode() {
        return scaleMetricCode;
    }

    public void setScaleMetricCode(int scaleMetricCode) {
        this.scaleMetricCode = scaleMetricCode;
    }
}
