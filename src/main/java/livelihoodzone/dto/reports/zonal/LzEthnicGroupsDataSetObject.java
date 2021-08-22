package livelihoodzone.dto.reports.zonal;

import java.util.List;

public class LzEthnicGroupsDataSetObject {
    private List<String> ethnicGroupPercentageReport;

    public LzEthnicGroupsDataSetObject() {
    }

    public LzEthnicGroupsDataSetObject(List<String> ethnicGroupPercentageReport) {
        this.ethnicGroupPercentageReport = ethnicGroupPercentageReport;
    }

    public List<String> getEthnicGroupPercentageReport() {
        return ethnicGroupPercentageReport;
    }

    public void setEthnicGroupPercentageReport(List<String> ethnicGroupPercentageReport) {
        this.ethnicGroupPercentageReport = ethnicGroupPercentageReport;
    }
}
