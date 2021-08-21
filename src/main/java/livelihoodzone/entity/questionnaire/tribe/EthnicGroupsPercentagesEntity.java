package livelihoodzone.entity.questionnaire.tribe;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "lz_ethnic_groups_percentage")
public class EthnicGroupsPercentagesEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LzEthnicGroupPercentageId")
    private int lzEthnicGroupPercentageId;

    @Column(name = "LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @Column(name = "EthnicGroupId")
    private int ethnicGroupId;

    @Column(name = "PopulationPercentage")
    private double populationPercentage;

    public EthnicGroupsPercentagesEntity() {
    }

    public EthnicGroupsPercentagesEntity(int lzQuestionnaireSessionId, int ethnicGroupId, double populationPercentage) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
        this.ethnicGroupId = ethnicGroupId;
        this.populationPercentage = populationPercentage;
    }

    public int getLzEthnicGroupPercentageId() {
        return lzEthnicGroupPercentageId;
    }

    public void setLzEthnicGroupPercentageId(int lzEthnicGroupPercentageId) {
        this.lzEthnicGroupPercentageId = lzEthnicGroupPercentageId;
    }

    public int getLzQuestionnaireSessionId() {
        return lzQuestionnaireSessionId;
    }

    public void setLzQuestionnaireSessionId(int lzQuestionnaireSessionId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
    }

    public int getEthnicGroupId() {
        return ethnicGroupId;
    }

    public void setEthnicGroupId(int ethnicGroupId) {
        this.ethnicGroupId = ethnicGroupId;
    }

    public double getPopulationPercentage() {
        return populationPercentage;
    }

    public void setPopulationPercentage(double populationPercentage) {
        this.populationPercentage = populationPercentage;
    }
}
