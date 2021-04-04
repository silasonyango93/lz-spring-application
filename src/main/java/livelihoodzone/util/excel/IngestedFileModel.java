package livelihoodzone.util.excel;

public class IngestedFileModel {
    private double code;
    private String county;
    private String oldDivision;
    private String oldLocation;
    private String oldSubLocation;
    private String currentConstituency;
    private String currentWard;

    public double getCode() {
        return code;
    }

    public void setCode(double code) {
        this.code = code;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getOldDivision() {
        return oldDivision;
    }

    public void setOldDivision(String oldDivision) {
        this.oldDivision = oldDivision;
    }

    public String getOldLocation() {
        return oldLocation;
    }

    public void setOldLocation(String oldLocation) {
        this.oldLocation = oldLocation;
    }

    public String getOldSubLocation() {
        return oldSubLocation;
    }

    public void setOldSubLocation(String oldSubLocation) {
        this.oldSubLocation = oldSubLocation;
    }

    public String getCurrentConstituency() {
        return currentConstituency;
    }

    public void setCurrentConstituency(String currentConstituency) {
        this.currentConstituency = currentConstituency;
    }

    public String getCurrentWard() {
        return currentWard;
    }

    public void setCurrentWard(String currentWard) {
        this.currentWard = currentWard;
    }
}
