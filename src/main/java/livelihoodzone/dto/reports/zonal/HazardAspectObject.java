package livelihoodzone.dto.reports.zonal;

public class HazardAspectObject {
    private int hazardAspectCode;
    private String hazardAspectName;

    public HazardAspectObject() {
    }

    public HazardAspectObject(int hazardAspectCode, String hazardAspectName) {
        this.hazardAspectCode = hazardAspectCode;
        this.hazardAspectName = hazardAspectName;
    }

    public int getHazardAspectCode() {
        return hazardAspectCode;
    }

    public void setHazardAspectCode(int hazardAspectCode) {
        this.hazardAspectCode = hazardAspectCode;
    }

    public String getHazardAspectName() {
        return hazardAspectName;
    }

    public void setHazardAspectName(String hazardAspectName) {
        this.hazardAspectName = hazardAspectName;
    }
}
