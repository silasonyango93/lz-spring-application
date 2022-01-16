package livelihoodzone.dto.reports.zonal;

public class WealthGroupPopulationRequestDto {
    int countyId;
    int wealthGroupCode;

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public int getWealthGroupCode() {
        return wealthGroupCode;
    }

    public void setWealthGroupCode(int wealthGroupCode) {
        this.wealthGroupCode = wealthGroupCode;
    }
}
