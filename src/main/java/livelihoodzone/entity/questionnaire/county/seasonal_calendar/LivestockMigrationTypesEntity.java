package livelihoodzone.entity.questionnaire.county.seasonal_calendar;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "livestock_migration_types")
public class LivestockMigrationTypesEntity implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LivestockMigrationTypeId")
    private int livestockMigrationTypeId;

    @Column(name = "LivestockMigrationTypeDescription")
    private String livestockMigrationTypeDescription;

    @Column(name = "LivestockMigrationTypeCode")
    private int livestockMigrationTypeCode;

    public int getLivestockMigrationTypeId() {
        return livestockMigrationTypeId;
    }

    public void setLivestockMigrationTypeId(int livestockMigrationTypeId) {
        this.livestockMigrationTypeId = livestockMigrationTypeId;
    }

    public String getLivestockMigrationTypeDescription() {
        return livestockMigrationTypeDescription;
    }

    public void setLivestockMigrationTypeDescription(String livestockMigrationTypeDescription) {
        this.livestockMigrationTypeDescription = livestockMigrationTypeDescription;
    }

    public int getLivestockMigrationTypeCode() {
        return livestockMigrationTypeCode;
    }

    public void setLivestockMigrationTypeCode(int livestockMigrationTypeCode) {
        this.livestockMigrationTypeCode = livestockMigrationTypeCode;
    }
}
