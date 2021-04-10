package livelihoodzone.entity.questionnaire.calendar;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "months")
public class MonthsEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MonthId")
    private int monthId;

    @Column(name = "MonthName")
    private String monthName;

    @Column(name = "MonthCode")
    private int monthCode;


    public int getMonthId() {
        return monthId;
    }

    public void setMonthId(int monthId) {
        this.monthId = monthId;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public int getMonthCode() {
        return monthCode;
    }

    public void setMonthCode(int monthCode) {
        this.monthCode = monthCode;
    }
}
