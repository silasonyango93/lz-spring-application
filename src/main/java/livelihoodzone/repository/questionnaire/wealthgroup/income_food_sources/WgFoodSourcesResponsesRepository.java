package livelihoodzone.repository.questionnaire.wealthgroup.income_food_sources;

import livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources.FoodTypesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources.WgFoodSourcesResponsesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources.WgIncomeSourcesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WgFoodSourcesResponsesRepository extends JpaRepository<WgFoodSourcesResponsesEntity, Long> {
    public List<WgFoodSourcesResponsesEntity> findByWgQuestionnaireSessionId(@Param("WgQuestionnaireSessionId") int wgQuestionnaireSessionId);
}
