package livelihoodzone.dto.reports.zonal;

public class HungerPatternsMapDataRequestDto {
    private int countyId;
    private int rainySeasonCode;

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public int getRainySeasonCode() {
        return rainySeasonCode;
    }

    public void setRainySeasonCode(int rainySeasonCode) {
        this.rainySeasonCode = rainySeasonCode;
    }
}
