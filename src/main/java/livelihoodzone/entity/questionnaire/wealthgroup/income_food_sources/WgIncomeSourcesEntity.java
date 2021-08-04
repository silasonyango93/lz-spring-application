package livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "wg_income_sources")
public class WgIncomeSourcesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WgIncomeSourceId")
    private int wgIncomeSourceId;

    @Column(name = "WgQuestionnaireSessionId")
    private int wgQuestionnaireSessionId;

    @Column(name = "CashIncomeSourceId")
    private int cashIncomeSourceId;

    @Column(name = "IncomeSourcePercentage")
    private double incomeSourcePercentage;

    @Column(name = "ExtraDescription")
    private String extraDescription;

    public WgIncomeSourcesEntity() {
    }

    public WgIncomeSourcesEntity(int wgQuestionnaireSessionId, int cashIncomeSourceId, double incomeSourcePercentage) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
        this.cashIncomeSourceId = cashIncomeSourceId;
        this.incomeSourcePercentage = incomeSourcePercentage;
    }

    public int getWgIncomeSourceId() {
        return wgIncomeSourceId;
    }

    public void setWgIncomeSourceId(int wgIncomeSourceId) {
        this.wgIncomeSourceId = wgIncomeSourceId;
    }

    public int getWgQuestionnaireSessionId() {
        return wgQuestionnaireSessionId;
    }

    public void setWgQuestionnaireSessionId(int wgQuestionnaireSessionId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
    }

    public int getCashIncomeSourceId() {
        return cashIncomeSourceId;
    }

    public void setCashIncomeSourceId(int cashIncomeSourceId) {
        this.cashIncomeSourceId = cashIncomeSourceId;
    }

    public double getIncomeSourcePercentage() {
        return incomeSourcePercentage;
    }

    public void setIncomeSourcePercentage(double incomeSourcePercentage) {
        this.incomeSourcePercentage = incomeSourcePercentage;
    }

    public String getExtraDescription() {
        return extraDescription;
    }

    public void setExtraDescription(String extraDescription) {
        this.extraDescription = extraDescription;
    }
}
