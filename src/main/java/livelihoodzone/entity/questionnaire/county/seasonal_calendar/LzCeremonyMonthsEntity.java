package livelihoodzone.entity.questionnaire.county.seasonal_calendar;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "lz_ceremony_months")
public class LzCeremonyMonthsEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LzCeremonyMonthId")
    private int lzCeremonyMonthId;

    @Column(name = "LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @Column(name = "MonthId")
    private int monthId;

    public LzCeremonyMonthsEntity() {
    }

    public LzCeremonyMonthsEntity(int lzQuestionnaireSessionId, int monthId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
        this.monthId = monthId;
    }

    public int getLzCeremonyMonthId() {
        return lzCeremonyMonthId;
    }

    public void setLzCeremonyMonthId(int lzCeremonyMonthId) {
        this.lzCeremonyMonthId = lzCeremonyMonthId;
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
