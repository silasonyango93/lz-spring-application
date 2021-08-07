package livelihoodzone.dto.reports.wealthgroup;

import java.util.HashMap;

public class WealthGroupReportResponseHashMapObject {
    private HashMap<String, Object> reportHashMapObject = new HashMap<>();

    public HashMap<String, Object> getReportHashMapObject() {
        return reportHashMapObject;
    }

    public void setReportHashMapObject(String objectKey, Object dataObject) {
        reportHashMapObject.put(objectKey,dataObject);
    }
}
