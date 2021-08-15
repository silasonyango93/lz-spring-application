package livelihoodzone.entity.questionnaire.wealthgroup.expenditure_patterns;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "wg_expenditure_percentages")
public class WgExpenditurePercentagesEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WgExpenditurePercentageId")
    private int wgExpenditurePercentageId;

    @Column(name = "WgQuestionnaireSessionId")
    private int wgQuestionnaireSessionId;

    @Column(name = "ExpenditureItemId")
    private int expenditureItemId;

    @Column(name = "ExpenditurePercentage")
    private double expenditurePercentage;

    public WgExpenditurePercentagesEntity() {
    }

    public WgExpenditurePercentagesEntity(int wgQuestionnaireSessionId, int expenditureItemId, double expenditurePercentage) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
        this.expenditureItemId = expenditureItemId;
        this.expenditurePercentage = expenditurePercentage;
    }

    public int getWgExpenditurePercentageId() {
        return wgExpenditurePercentageId;
    }

    public void setWgExpenditurePercentageId(int wgExpenditurePercentageId) {
        this.wgExpenditurePercentageId = wgExpenditurePercentageId;
    }

    public int getWgQuestionnaireSessionId() {
        return wgQuestionnaireSessionId;
    }

    public void setWgQuestionnaireSessionId(int wgQuestionnaireSessionId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
    }

    public int getExpenditureItemId() {
        return expenditureItemId;
    }

    public void setExpenditureItemId(int expenditureItemId) {
        this.expenditureItemId = expenditureItemId;
    }

    public double getExpenditurePercentage() {
        return expenditurePercentage;
    }

    public void setExpenditurePercentage(double expenditurePercentage) {
        this.expenditurePercentage = expenditurePercentage;
    }
}
