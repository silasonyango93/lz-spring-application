package livelihoodzone.repository.questionnaire.wealthgroup.income_food_sources;

import livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources.FoodTypesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources.WgFoodSourcesResponsesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WgFoodSourcesResponsesRepository extends JpaRepository<WgFoodSourcesResponsesEntity, Long> {
}
