package livelihoodzone.entity.questionnaire.wealthgroup.expenditure_patterns;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "expenditure_items")
public class WgExpenditureItemsEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ExpenditureItemId")
    private int expenditureItemId;

    @Column(name = "ExpenditureItemDescription")
    private String expenditureItemDescription;

    @Column(name = "ExpenditureItemCode")
    private int expenditureItemCode;

    public int getExpenditureItemId() {
        return expenditureItemId;
    }

    public void setExpenditureItemId(int expenditureItemId) {
        this.expenditureItemId = expenditureItemId;
    }

    public String getExpenditureItemDescription() {
        return expenditureItemDescription;
    }

    public void setExpenditureItemDescription(String expenditureItemDescription) {
        this.expenditureItemDescription = expenditureItemDescription;
    }

    public int getExpenditureItemCode() {
        return expenditureItemCode;
    }

    public void setExpenditureItemCode(int expenditureItemCode) {
        this.expenditureItemCode = expenditureItemCode;
    }
}
