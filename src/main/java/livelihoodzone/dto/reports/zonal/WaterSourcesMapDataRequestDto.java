package livelihoodzone.dto.reports.zonal;

public class WaterSourcesMapDataRequestDto {
    int countyId;
    int waterSourceCode;
    int seasonCode;

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public int getWaterSourceCode() {
        return waterSourceCode;
    }

    public void setWaterSourceCode(int waterSourceCode) {
        this.waterSourceCode = waterSourceCode;
    }

    public int getSeasonCode() {
        return seasonCode;
    }

    public void setSeasonCode(int seasonCode) {
        this.seasonCode = seasonCode;
    }
}
