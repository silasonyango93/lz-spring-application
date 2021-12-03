package livelihoodzone.dto.reports.wealthgroup.charts;

public class WgIncomeSourcesMapRequestDto {
    private int countyId;
    private int wealthGroupId;
    private int incomeSourceCode;

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

    public int getIncomeSourceCode() {
        return incomeSourceCode;
    }

    public void setIncomeSourceCode(int incomeSourceCode) {
        this.incomeSourceCode = incomeSourceCode;
    }
}
