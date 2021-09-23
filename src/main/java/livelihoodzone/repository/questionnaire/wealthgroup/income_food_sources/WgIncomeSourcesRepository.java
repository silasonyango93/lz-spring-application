package livelihoodzone.repository.questionnaire.wealthgroup.income_food_sources;

import livelihoodzone.entity.questionnaire.county.LzHazardsEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.income_food_sources.WgIncomeSourcesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WgIncomeSourcesRepository extends JpaRepository<WgIncomeSourcesEntity, Long> {
    public List<WgIncomeSourcesEntity> findByWgQuestionnaireSessionId(@Param("WgQuestionnaireSessionId") int wgQuestionnaireSessionId);

    public WgIncomeSourcesEntity findByWgIncomeSourceId(@Param("WgIncomeSourceId") int wgIncomeSourceId);
}
