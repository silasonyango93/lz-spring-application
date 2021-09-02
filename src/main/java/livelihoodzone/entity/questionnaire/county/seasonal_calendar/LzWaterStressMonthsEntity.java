package livelihoodzone.entity.questionnaire.county.seasonal_calendar;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "lz_water_stress_months")
public class LzWaterStressMonthsEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LzWaterStressMonthId")
    private int lzWaterStressMonthId;

    @Column(name = "LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @Column(name = "MonthId")
    private int monthId;

    public LzWaterStressMonthsEntity() {
    }

    public LzWaterStressMonthsEntity(int lzQuestionnaireSessionId, int monthId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
        this.monthId = monthId;
    }

    public int getLzWaterStressMonthId() {
        return lzWaterStressMonthId;
    }

    public void setLzWaterStressMonthId(int lzWaterStressMonthId) {
        this.lzWaterStressMonthId = lzWaterStressMonthId;
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
