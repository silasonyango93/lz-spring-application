package livelihoodzone.dto.reports.wealthgroup.charts;

public class WgMigrationPatternsMapDataRequestDto {
    private int countyId;
    private int wealthGroupId;
    private int migrationPatternsCode;

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public int getWealthGroupId() {
        return wealthGroupId;
    }

    public void setWealthGroupId(int wealthGroupId) {
        this.wealthGroupId = wealthGroupId;
    }

    public int getMigrationPatternsCode() {
        return migrationPatternsCode;
    }

    public void setMigrationPatternsCode(int migrationPatternsCode) {
        this.migrationPatternsCode = migrationPatternsCode;
    }
}
