package livelihoodzone.entity.questionnaire.wealthgroup.migration_patterns;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "migration_patterns")
public class MigrationPatternsEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MigrationPatternId")
    private int migrationPatternId;

    @Column(name = "MigrationPatternDescription")
    private String migrationPatternDescription;

    @Column(name = "MigrationPatternCode")
    private int migrationPatternCode;

    public int getMigrationPatternId() {
        return migrationPatternId;
    }

    public void setMigrationPatternId(int migrationPatternId) {
        this.migrationPatternId = migrationPatternId;
    }

    public String getMigrationPatternDescription() {
        return migrationPatternDescription;
    }

    public void setMigrationPatternDescription(String migrationPatternDescription) {
        this.migrationPatternDescription = migrationPatternDescription;
    }

    public int getMigrationPatternCode() {
        return migrationPatternCode;
    }

    public void setMigrationPatternCode(int migrationPatternCode) {
        this.migrationPatternCode = migrationPatternCode;
    }
}
