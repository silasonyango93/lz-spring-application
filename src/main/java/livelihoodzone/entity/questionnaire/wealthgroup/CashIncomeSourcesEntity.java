package livelihoodzone.entity.questionnaire.wealthgroup;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "cash_income_sources")
public class CashIncomeSourcesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CashIncomeSourceId")
    private int cashIncomeSourceId;

    @Column(name = "CashIncomeSourceDescription")
    private String cashIncomeSourceDescription;

    @Column(name = "CashIncomeSourceCode")
    private int cashIncomeSourceCode;

    public int getCashIncomeSourceId() {
        return cashIncomeSourceId;
    }

    public void setCashIncomeSourceId(int cashIncomeSourceId) {
        this.cashIncomeSourceId = cashIncomeSourceId;
    }

    public String getCashIncomeSourceDescription() {
        return cashIncomeSourceDescription;
    }

    public void setCashIncomeSourceDescription(String cashIncomeSourceDescription) {
        this.cashIncomeSourceDescription = cashIncomeSourceDescription;
    }

    public int getCashIncomeSourceCode() {
        return cashIncomeSourceCode;
    }

    public void setCashIncomeSourceCode(int cashIncomeSourceCode) {
        this.cashIncomeSourceCode = cashIncomeSourceCode;
    }
}
