package livelihoodzone.dto.reports;

public class HazardsMapRequestDto {
    private int countyId;
    private int hazardCode;
    private int hazardAspectCode;

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public int getHazardCode() {
        return hazardCode;
    }

    public void setHazardCode(int hazardCode) {
        this.hazardCode = hazardCode;
    }

    public int getHazardAspectCode() {
        return hazardAspectCode;
    }

    public void setHazardAspectCode(int hazardAspectCode) {
        this.hazardAspectCode = hazardAspectCode;
    }
}
