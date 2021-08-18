package livelihoodzone.entity.questionnaire.county.seasonal_calendar;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "lz_livestock_migration_months")
public class LivestockMigrationMonthsEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LzLivestockMigrationMonthId")
    private int lzLivestockMigrationMonthId;

    @Column(name = "LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @Column(name = "LivestockMigrationTypeId")
    private int livestockMigrationTypeId;

    @Column(name = "MonthId")
    private int monthId;

    public LivestockMigrationMonthsEntity() {
    }

    public LivestockMigrationMonthsEntity(int lzQuestionnaireSessionId, int livestockMigrationTypeId, int monthId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
        this.livestockMigrationTypeId = livestockMigrationTypeId;
        this.monthId = monthId;
    }

    public int getLzLivestockMigrationMonthId() {
        return lzLivestockMigrationMonthId;
    }

    public void setLzLivestockMigrationMonthId(int lzLivestockMigrationMonthId) {
        this.lzLivestockMigrationMonthId = lzLivestockMigrationMonthId;
    }

    public int getLzQuestionnaireSessionId() {
        return lzQuestionnaireSessionId;
    }

    public void setLzQuestionnaireSessionId(int lzQuestionnaireSessionId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
    }

    public int getLivestockMigrationTypeId() {
        return livestockMigrationTypeId;
    }

    public void setLivestockMigrationTypeId(int livestockMigrationTypeId) {
        this.livestockMigrationTypeId = livestockMigrationTypeId;
    }

    public int getMonthId() {
        return monthId;
    }

    public void setMonthId(int monthId) {
        this.monthId = monthId;
    }
}
