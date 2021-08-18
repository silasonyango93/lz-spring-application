package livelihoodzone.entity.questionnaire.county;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "lz_hazard_responses")
public class LzHazardResponsesEntity implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LzHazardResponseId")
    private int lzHazardResponseId;

    @Column(name = "LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @Column(name = "LzHazardId")
    private int lzHazardId;

    @Column(name = "Rank")
    private int rank;

    @Column(name = "YearsExperienced")
    private double yearsExperienced;

    public LzHazardResponsesEntity() {
    }

    public LzHazardResponsesEntity(int lzQuestionnaireSessionId, int lzHazardId, int rank, double yearsExperienced) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
        this.lzHazardId = lzHazardId;
        this.rank = rank;
        this.yearsExperienced = yearsExperienced;
    }

    public int getLzHazardResponseId() {
        return lzHazardResponseId;
    }

    public void setLzHazardResponseId(int lzHazardResponseId) {
        this.lzHazardResponseId = lzHazardResponseId;
    }

    public int getLzQuestionnaireSessionId() {
        return lzQuestionnaireSessionId;
    }

    public void setLzQuestionnaireSessionId(int lzQuestionnaireSessionId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
    }

    public int getLzHazardId() {
        return lzHazardId;
    }

    public void setLzHazardId(int lzHazardId) {
        this.lzHazardId = lzHazardId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getYearsExperienced() {
        return yearsExperienced;
    }

    public void setYearsExperienced(double yearsExperienced) {
        this.yearsExperienced = yearsExperienced;
    }
}
