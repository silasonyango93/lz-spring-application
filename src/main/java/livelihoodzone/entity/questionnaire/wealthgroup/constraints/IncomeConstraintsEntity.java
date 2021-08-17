package livelihoodzone.entity.questionnaire.wealthgroup.constraints;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "income_constraints")
public class IncomeConstraintsEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IncomeConstraintId")
    private int incomeConstraintId;

    @Column(name = "IncomeConstraintDescription")
    private String incomeConstraintDescription;

    @Column(name = "IncomeConstraintCode")
    private int incomeConstraintCode;

    public int getIncomeConstraintId() {
        return incomeConstraintId;
    }

    public void setIncomeConstraintId(int incomeConstraintId) {
        this.incomeConstraintId = incomeConstraintId;
    }

    public String getIncomeConstraintDescription() {
        return incomeConstraintDescription;
    }

    public void setIncomeConstraintDescription(String incomeConstraintDescription) {
        this.incomeConstraintDescription = incomeConstraintDescription;
    }

    public int getIncomeConstraintCode() {
        return incomeConstraintCode;
    }

    public void setIncomeConstraintCode(int incomeConstraintCode) {
        this.incomeConstraintCode = incomeConstraintCode;
    }
}
