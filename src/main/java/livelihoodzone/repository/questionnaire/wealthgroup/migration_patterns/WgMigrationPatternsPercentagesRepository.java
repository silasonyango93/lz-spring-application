package livelihoodzone.repository.questionnaire.wealthgroup.migration_patterns;

import livelihoodzone.entity.questionnaire.wealthgroup.migration_patterns.MigrationPatternsEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.migration_patterns.WgMigrationPatternPercentagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WgMigrationPatternsPercentagesRepository extends JpaRepository<WgMigrationPatternPercentagesEntity, Long> {
    public List<WgMigrationPatternPercentagesEntity> findByWgQuestionnaireSessionId(@Param("WgQuestionnaireSessionId") int wgQuestionnaireSessionId);
}
