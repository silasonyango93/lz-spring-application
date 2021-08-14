package livelihoodzone.dto.livelihoodzones;

public class SampledSubLocationsRequestDto {
    private String subLocationName;
    private int livelihoodZoneId;
    private String zoneName;
    private boolean hasBeenSelected;
    private int assignmentId;

    public String getSubLocationName() {
        return subLocationName;
    }

    public void setSubLocationName(String subLocationName) {
        this.subLocationName = subLocationName;
    }

    public int getLivelihoodZoneId() {
        return livelihoodZoneId;
    }

    public void setLivelihoodZoneId(int livelihoodZoneId) {
        this.livelihoodZoneId = livelihoodZoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public boolean isHasBeenSelected() {
        return hasBeenSelected;
    }

    public void setHasBeenSelected(boolean hasBeenSelected) {
        this.hasBeenSelected = hasBeenSelected;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }
}
