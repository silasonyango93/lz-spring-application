package livelihoodzone.entity.questionnaire.wealthgroup.constraints;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "wg_income_constraint_rank")
public class WgIncomeConstraintRankEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IncomeConstraintRankId")
    private int incomeConstraintRankId;

    @Column(name = "WgQuestionnaireSessionId")
    private int wgQuestionnaireSessionId;

    @Column(name = "ConIncomeSourceId")
    private int conIncomeSourceId;

    @Column(name = "IncomeConstraintId")
    private int incomeConstraintId;

    @Column(name = "Rank")
    private int rank;

    public WgIncomeConstraintRankEntity() {
    }

    public WgIncomeConstraintRankEntity(int wgQuestionnaireSessionId, int conIncomeSourceId, int incomeConstraintId, int rank) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
        this.conIncomeSourceId = conIncomeSourceId;
        this.incomeConstraintId = incomeConstraintId;
        this.rank = rank;
    }

    public int getIncomeConstraintRankId() {
        return incomeConstraintRankId;
    }

    public void setIncomeConstraintRankId(int incomeConstraintRankId) {
        this.incomeConstraintRankId = incomeConstraintRankId;
    }

    public int getWgQuestionnaireSessionId() {
        return wgQuestionnaireSessionId;
    }

    public void setWgQuestionnaireSessionId(int wgQuestionnaireSessionId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
    }

    public int getConIncomeSourceId() {
        return conIncomeSourceId;
    }

    public void setConIncomeSourceId(int conIncomeSourceId) {
        this.conIncomeSourceId = conIncomeSourceId;
    }

    public int getIncomeConstraintId() {
        return incomeConstraintId;
    }

    public void setIncomeConstraintId(int incomeConstraintId) {
        this.incomeConstraintId = incomeConstraintId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
