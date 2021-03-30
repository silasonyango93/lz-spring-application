package livelihoodzone.entity.questionnaire.wealthgroup;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "wealth_groups")
public class WealthGroupEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WealthGroupId")
    private int wealthGroupId;

    @Column(name = "WealthGroupDescription")
    private String wealthGroupDescription;

    @Column(name = "WealthGroupCode")
    private int wealthGroupCode;

    public int getWealthGroupId() {
        return wealthGroupId;
    }

    public void setWealthGroupId(int wealthGroupId) {
        this.wealthGroupId = wealthGroupId;
    }

    public String getWealthGroupDescription() {
        return wealthGroupDescription;
    }

    public void setWealthGroupDescription(String wealthGroupDescription) {
        this.wealthGroupDescription = wealthGroupDescription;
    }

    public int getWealthGroupCode() {
        return wealthGroupCode;
    }

    public void setWealthGroupCode(int wealthGroupCode) {
        this.wealthGroupCode = wealthGroupCode;
    }
}
