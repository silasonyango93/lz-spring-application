package livelihoodzone.entity.questionnaire.county;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "lz_wealth_group_population_percentage")
public class LzWealthGroupPopulationPercentageEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LzWealthGroupPopulationPercentageId")
    private int lzWealthGroupPopulationPercentageId;

    @Column(name = "LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @Column(name = "WealthGroupId")
    private int wealthGroupId;

    @Column(name = "PopulationPercentage")
    private double populationPercentage;

    public LzWealthGroupPopulationPercentageEntity() {
    }

    public LzWealthGroupPopulationPercentageEntity(int lzQuestionnaireSessionId, int wealthGroupId, double populationPercentage) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
        this.wealthGroupId = wealthGroupId;
        this.populationPercentage = populationPercentage;
    }

    public int getLzWealthGroupPopulationPercentageId() {
        return lzWealthGroupPopulationPercentageId;
    }

    public void setLzWealthGroupPopulationPercentageId(int lzWealthGroupPopulationPercentageId) {
        this.lzWealthGroupPopulationPercentageId = lzWealthGroupPopulationPercentageId;
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

    public double getPopulationPercentage() {
        return populationPercentage;
    }

    public void setPopulationPercentage(double populationPercentage) {
        this.populationPercentage = populationPercentage;
    }
}
