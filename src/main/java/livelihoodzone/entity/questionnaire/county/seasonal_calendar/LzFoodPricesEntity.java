package livelihoodzone.entity.questionnaire.county.seasonal_calendar;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "lz_food_prices")
public class LzFoodPricesEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LzFoodPriceId")
    private int lzFoodPriceId;

    @Column(name = "LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @Column(name = "ScaleMetricId")
    private int scaleMetricId;

    @Column(name = "MonthId")
    private int monthId;

    public LzFoodPricesEntity() {
    }

    public LzFoodPricesEntity(int lzQuestionnaireSessionId, int scaleMetricId, int monthId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
        this.scaleMetricId = scaleMetricId;
        this.monthId = monthId;
    }

    public int getLzFoodPriceId() {
        return lzFoodPriceId;
    }

    public void setLzFoodPriceId(int lzFoodPriceId) {
        this.lzFoodPriceId = lzFoodPriceId;
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
