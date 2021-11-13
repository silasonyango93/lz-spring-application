package livelihoodzone.repository.questionnaire.wealthgroup.migration_patterns;

import livelihoodzone.entity.questionnaire.wealthgroup.WealthGroupEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.migration_patterns.MigrationPatternsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface MigrationPatternsRepository extends JpaRepository<MigrationPatternsEntity, Long> {
    public MigrationPatternsEntity findByMigrationPatternCode(@Param("MigrationPatternCode") int migrationPatternCode);

    public MigrationPatternsEntity findByMigrationPatternId(@Param("MigrationPatternId") int migrationPatternId);
}
