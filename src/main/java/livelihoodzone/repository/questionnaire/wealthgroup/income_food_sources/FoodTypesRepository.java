package livelihoodzone.repository.questionnaire.wealthgroup.income_food_sources;

import livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources.FoodTypesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface FoodTypesRepository extends JpaRepository<FoodTypesEntity, Long> {
    public FoodTypesEntity findByFoodTypeCode(@Param("FoodTypeCode") int foodTypeCode);
}
