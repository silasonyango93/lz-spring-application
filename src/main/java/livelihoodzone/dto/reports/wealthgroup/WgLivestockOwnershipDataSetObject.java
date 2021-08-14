package livelihoodzone.dto.reports.wealthgroup;

import java.util.List;

public class WgLivestockOwnershipDataSetObject {
    private List<String> livestockOwnershipStringReportList;

    public WgLivestockOwnershipDataSetObject() {
    }

    public WgLivestockOwnershipDataSetObject(List<String> livestockOwnershipStringReportList) {
        this.livestockOwnershipStringReportList = livestockOwnershipStringReportList;
    }

    public List<String> getLivestockOwnershipStringReportList() {
        return livestockOwnershipStringReportList;
    }

    public void setLivestockOwnershipStringReportList(List<String> livestockOwnershipStringReportList) {
        this.livestockOwnershipStringReportList = livestockOwnershipStringReportList;
    }
}
