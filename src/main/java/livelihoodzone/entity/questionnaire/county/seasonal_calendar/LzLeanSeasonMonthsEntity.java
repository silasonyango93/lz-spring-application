package livelihoodzone.entity.questionnaire.county.seasonal_calendar;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "lz_lean_season_months")
public class LzLeanSeasonMonthsEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LzLeanSeasonMonthId")
    private int lzLeanSeasonMonthId;

    @Column(name = "LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @Column(name = "MonthId")
    private int monthId;

    public LzLeanSeasonMonthsEntity() {
    }

    public LzLeanSeasonMonthsEntity(int lzQuestionnaireSessionId, int monthId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
        this.monthId = monthId;
    }

    public int getLzLeanSeasonMonthId() {
        return lzLeanSeasonMonthId;
    }

    public void setLzLeanSeasonMonthId(int lzLeanSeasonMonthId) {
        this.lzLeanSeasonMonthId = lzLeanSeasonMonthId;
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
