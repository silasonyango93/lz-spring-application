package livelihoodzone.entity.questionnaire.county;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "hunger_patterns_responses")
public class LzHungerPatternsResponsesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HungerPatternsResponseId")
    private int hungerPatternsResponseId;

    @Column(name = "LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @Column(name = "RainySeasonId")
    private int rainySeasonId;

    @Column(name = "YearsOfWidespreadHunger")
    private double yearsOfWidespreadHunger;

    public LzHungerPatternsResponsesEntity() {
    }

    public LzHungerPatternsResponsesEntity(int lzQuestionnaireSessionId, int rainySeasonId, double yearsOfWidespreadHunger) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
        this.rainySeasonId = rainySeasonId;
        this.yearsOfWidespreadHunger = yearsOfWidespreadHunger;
    }

    public int getHungerPatternsResponseId() {
        return hungerPatternsResponseId;
    }

    public void setHungerPatternsResponseId(int hungerPatternsResponseId) {
        this.hungerPatternsResponseId = hungerPatternsResponseId;
    }

    public int getLzQuestionnaireSessionId() {
        return lzQuestionnaireSessionId;
    }

    public void setLzQuestionnaireSessionId(int lzQuestionnaireSessionId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
    }

    public int getRainySeasonId() {
        return rainySeasonId;
    }

    public void setRainySeasonId(int rainySeasonId) {
        this.rainySeasonId = rainySeasonId;
    }

    public double getYearsOfWidespreadHunger() {
        return yearsOfWidespreadHunger;
    }

    public void setYearsOfWidespreadHunger(double yearsOfWidespreadHunger) {
        this.yearsOfWidespreadHunger = yearsOfWidespreadHunger;
    }
}
