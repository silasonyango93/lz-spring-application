package livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "wg_food_source_response")
public class WgFoodSourcesResponsesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FoodSourceResponseId")
    private int foodSourceResponseId;

    @Column(name = "WgQuestionnaireSessionId")
    private int wgQuestionnaireSessionId;

    @Column(name = "FoodTypeId")
    private int foodTypeId;

    @Column(name = "FoodSourceId")
    private int foodSourceId;

    @Column(name = "SourcePercentage")
    private double sourcePercentage;

    public WgFoodSourcesResponsesEntity() {
    }

    public WgFoodSourcesResponsesEntity(int wgQuestionnaireSessionId, int foodTypeId, int foodSourceId, double sourcePercentage) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
        this.foodTypeId = foodTypeId;
        this.foodSourceId = foodSourceId;
        this.sourcePercentage = sourcePercentage;
    }

    public int getFoodSourceResponseId() {
        return foodSourceResponseId;
    }

    public void setFoodSourceResponseId(int foodSourceResponseId) {
        this.foodSourceResponseId = foodSourceResponseId;
    }

    public int getWgQuestionnaireSessionId() {
        return wgQuestionnaireSessionId;
    }

    public void setWgQuestionnaireSessionId(int wgQuestionnaireSessionId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
    }

    public int getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(int foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public int getFoodSourceId() {
        return foodSourceId;
    }

    public void setFoodSourceId(int foodSourceId) {
        this.foodSourceId = foodSourceId;
    }

    public double getSourcePercentage() {
        return sourcePercentage;
    }

    public void setSourcePercentage(double sourcePercentage) {
        this.sourcePercentage = sourcePercentage;
    }
}
