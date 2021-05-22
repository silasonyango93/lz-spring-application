package livelihoodzone.entity.questionnaire.county;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "lz_wealth_group_characteristics")
public class LzWealthGroupCharacteristicsEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WealthGroupCharectaristicId")
    private int wealthGroupCharectaristicId;

    @Column(name = "LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @Column(name = "WealthGroupId")
    private int wealthGroupId;

    @Column(name = "CharectaristicDescription")
    private String charectaristicDescription;

    public LzWealthGroupCharacteristicsEntity() {
    }

    public LzWealthGroupCharacteristicsEntity(int lzQuestionnaireSessionId, int wealthGroupId, String charectaristicDescription) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
        this.wealthGroupId = wealthGroupId;
        this.charectaristicDescription = charectaristicDescription;
    }

    public int getWealthGroupCharectaristicId() {
        return wealthGroupCharectaristicId;
    }

    public void setWealthGroupCharectaristicId(int wealthGroupCharectaristicId) {
        this.wealthGroupCharectaristicId = wealthGroupCharectaristicId;
    }

    public int getLzQuestionnaireSessionId() {
        return lzQuestionnaireSessionId;
    }

    public void setLzQuestionnaireSessionId(int lzQuestionnaireSessionId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
    }

    public int getWealthGroupId() {
        return wealthGroupId;
    }

    public void setWealthGroupId(int wealthGroupId) {
        this.wealthGroupId = wealthGroupId;
    }

    public String getCharectaristicDescription() {
        return charectaristicDescription;
    }

    public void setCharectaristicDescription(String charectaristicDescription) {
        this.charectaristicDescription = charectaristicDescription;
    }
}
