package livelihoodzone.dto.reports.wealthgroup;

public class CropContributionMapDataRequestDto {
    private int countyId;
    private int wealthGroupId;
    private int cropId;
    private int contributionAspectCode;

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

    public int getCropId() {
        return cropId;
    }

    public void setCropId(int cropId) {
        this.cropId = cropId;
    }

    public int getContributionAspectCode() {
        return contributionAspectCode;
    }

    public void setContributionAspectCode(int contributionAspectCode) {
        this.contributionAspectCode = contributionAspectCode;
    }
}
