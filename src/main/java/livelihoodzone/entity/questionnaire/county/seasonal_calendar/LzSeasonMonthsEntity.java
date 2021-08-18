package livelihoodzone.entity.questionnaire.county.seasonal_calendar;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "lz_season_months")
public class LzSeasonMonthsEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LzSeasonMonthId")
    private int lzSeasonMonthId;

    @Column(name = "LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @Column(name = "LzSeasonId")
    private int lzSeasonId;

    @Column(name = "MonthId")
    private int monthId;

    public LzSeasonMonthsEntity() {
    }

    public LzSeasonMonthsEntity(int lzQuestionnaireSessionId, int lzSeasonId, int monthId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
        this.lzSeasonId = lzSeasonId;
        this.monthId = monthId;
    }

    public int getLzSeasonMonthId() {
        return lzSeasonMonthId;
    }

    public void setLzSeasonMonthId(int lzSeasonMonthId) {
        this.lzSeasonMonthId = lzSeasonMonthId;
    }

    public int getLzQuestionnaireSessionId() {
        return lzQuestionnaireSessionId;
    }

    public void setLzQuestionnaireSessionId(int lzQuestionnaireSessionId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
    }

    public int getLzSeasonId() {
        return lzSeasonId;
    }

    public void setLzSeasonId(int lzSeasonId) {
        this.lzSeasonId = lzSeasonId;
    }

    public int getMonthId() {
        return monthId;
    }

    public void setMonthId(int monthId) {
        this.monthId = monthId;
    }
}
