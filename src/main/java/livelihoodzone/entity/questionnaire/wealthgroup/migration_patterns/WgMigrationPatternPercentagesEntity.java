package livelihoodzone.entity.questionnaire.wealthgroup.migration_patterns;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "wg_migration_pattern_percentages")
public class WgMigrationPatternPercentagesEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WgMigrationPatternPercentageId")
    private int wgMigrationPatternPercentageId;

    @Column(name = "WgQuestionnaireSessionId")
    private int wgQuestionnaireSessionId;

    @Column(name = "MigrationPatternId")
    private int migrationPatternId;

    @Column(name = "Percentage")
    private double percentage;

    public WgMigrationPatternPercentagesEntity() {
    }

    public WgMigrationPatternPercentagesEntity(int wgQuestionnaireSessionId, int migrationPatternId, double percentage) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
        this.migrationPatternId = migrationPatternId;
        this.percentage = percentage;
    }

    public int getWgMigrationPatternPercentageId() {
        return wgMigrationPatternPercentageId;
    }

    public void setWgMigrationPatternPercentageId(int wgMigrationPatternPercentageId) {
        this.wgMigrationPatternPercentageId = wgMigrationPatternPercentageId;
    }

    public int getWgQuestionnaireSessionId() {
        return wgQuestionnaireSessionId;
    }

    public void setWgQuestionnaireSessionId(int wgQuestionnaireSessionId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
    }

    public int getMigrationPatternId() {
        return migrationPatternId;
    }

    public void setMigrationPatternId(int migrationPatternId) {
        this.migrationPatternId = migrationPatternId;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
