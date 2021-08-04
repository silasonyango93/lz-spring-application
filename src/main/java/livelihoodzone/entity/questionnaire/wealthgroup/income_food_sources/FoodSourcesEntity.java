package livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "food_sources")
public class FoodSourcesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FoodSourceId")
    private int foodSourceId;

    @Column(name = "FoodSourceDescription")
    private String foodSourceDescription;

    @Column(name = "FoodSourceCode")
    private int foodSourceCode;

    public int getFoodSourceId() {
        return foodSourceId;
    }

    public void setFoodSourceId(int foodSourceId) {
        this.foodSourceId = foodSourceId;
    }

    public String getFoodSourceDescription() {
        return foodSourceDescription;
    }

    public void setFoodSourceDescription(String foodSourceDescription) {
        this.foodSourceDescription = foodSourceDescription;
    }

    public int getFoodSourceCode() {
        return foodSourceCode;
    }

    public void setFoodSourceCode(int foodSourceCode) {
        this.foodSourceCode = foodSourceCode;
    }
}
