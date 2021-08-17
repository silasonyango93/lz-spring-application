package livelihoodzone.entity.questionnaire.wealthgroup.constraints;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "con_income_sources")
public class ConIncomeSourcesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ConIncomeSourceId")
    private int conIncomeSourceId;

    @Column(name = "ConIncomeSourceDescription")
    private String conIncomeSourceDescription;

    @Column(name = "ConIncomeSourceCode")
    private int conIncomeSourceCode;

    public int getConIncomeSourceId() {
        return conIncomeSourceId;
    }

    public void setConIncomeSourceId(int conIncomeSourceId) {
        this.conIncomeSourceId = conIncomeSourceId;
    }

    public String getConIncomeSourceDescription() {
        return conIncomeSourceDescription;
    }

    public void setConIncomeSourceDescription(String conIncomeSourceDescription) {
        this.conIncomeSourceDescription = conIncomeSourceDescription;
    }

    public int getConIncomeSourceCode() {
        return conIncomeSourceCode;
    }

    public void setConIncomeSourceCode(int conIncomeSourceCode) {
        this.conIncomeSourceCode = conIncomeSourceCode;
    }
}
