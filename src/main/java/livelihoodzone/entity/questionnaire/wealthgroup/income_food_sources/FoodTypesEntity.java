package livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "food_types")
public class FoodTypesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FoodTypeId")
    private int foodTypeId;

    @Column(name = "FoodTypeDescription")
    private String foodTypeDescription;

    @Column(name = "FoodTypeCode")
    private int foodTypeCode;

    public int getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(int foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public String getFoodTypeDescription() {
        return foodTypeDescription;
    }

    public void setFoodTypeDescription(String foodTypeDescription) {
        this.foodTypeDescription = foodTypeDescription;
    }

    public int getFoodTypeCode() {
        return foodTypeCode;
    }

    public void setFoodTypeCode(int foodTypeCode) {
        this.foodTypeCode = foodTypeCode;
    }
}
