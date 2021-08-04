package livelihoodzone.repository.questionnaire.wealthgroup.income_food_sources;

import livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources.FoodSourcesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface FoodSourcesRepository extends JpaRepository<FoodSourcesEntity, Long> {
    public FoodSourcesEntity findByFoodSourceCode(@Param("FoodSourceCode") int foodSourceCode);
}
