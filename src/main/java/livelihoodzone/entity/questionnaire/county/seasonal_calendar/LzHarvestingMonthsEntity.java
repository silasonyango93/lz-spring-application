package livelihoodzone.entity.questionnaire.county.seasonal_calendar;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "lz_harvesting_months")
public class LzHarvestingMonthsEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LzHarvestingMonthId")
    private int lzHarvestingMonthId;

    @Column(name = "LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @Column(name = "CropId")
    private int cropId;

    @Column(name = "MonthId")
    private int monthId;

    public LzHarvestingMonthsEntity() {
    }

    public LzHarvestingMonthsEntity(int lzQuestionnaireSessionId, int cropId, int monthId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
        this.cropId = cropId;
        this.monthId = monthId;
    }

    public int getLzHarvestingMonthId() {
        return lzHarvestingMonthId;
    }

    public void setLzHarvestingMonthId(int lzHarvestingMonthId) {
        this.lzHarvestingMonthId = lzHarvestingMonthId;
    }

    public int getLzQuestionnaireSessionId() {
        return lzQuestionnaireSessionId;
    }

    public void setLzQuestionnaireSessionId(int lzQuestionnaireSessionId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
    }

    public int getCropId() {
        return cropId;
    }

    public void setCropId(int cropId) {
        this.cropId = cropId;
    }

    public int getMonthId() {
        return monthId;
    }

    public void setMonthId(int monthId) {
        this.monthId = monthId;
    }
}
