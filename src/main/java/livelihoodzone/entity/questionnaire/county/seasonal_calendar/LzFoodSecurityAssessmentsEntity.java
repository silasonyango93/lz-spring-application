package livelihoodzone.entity.questionnaire.county.seasonal_calendar;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "lz_food_security_assessment_months")
public class LzFoodSecurityAssessmentsEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LzFoodSecurityAssessmentMonthId")
    private int lzFoodSecurityAssessmentMonthId;

    @Column(name = "LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @Column(name = "MonthId")
    private int monthId;

    public LzFoodSecurityAssessmentsEntity() {
    }

    public LzFoodSecurityAssessmentsEntity(int lzQuestionnaireSessionId, int monthId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
        this.monthId = monthId;
    }

    public int getLzFoodSecurityAssessmentMonthId() {
        return lzFoodSecurityAssessmentMonthId;
    }

    public void setLzFoodSecurityAssessmentMonthId(int lzFoodSecurityAssessmentMonthId) {
        this.lzFoodSecurityAssessmentMonthId = lzFoodSecurityAssessmentMonthId;
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
