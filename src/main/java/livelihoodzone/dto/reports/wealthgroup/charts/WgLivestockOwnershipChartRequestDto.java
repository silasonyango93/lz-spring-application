package livelihoodzone.dto.reports.wealthgroup.charts;

public class WgLivestockOwnershipChartRequestDto {
    private int countyId;
    private int wealthGroupId;
    private int livestockCode;

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

    public int getLivestockCode() {
        return livestockCode;
    }

    public void setLivestockCode(int livestockCode) {
        this.livestockCode = livestockCode;
    }
}
