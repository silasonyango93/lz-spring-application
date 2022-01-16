package livelihoodzone.dto.reports.wealthgroup;

public class CropContributionAspectDto {
    private int contributionAspectCode;
    private String contributionAspectName;

    public CropContributionAspectDto() {
    }

    public CropContributionAspectDto(int contributionAspectCode, String contributionAspectName) {
        this.contributionAspectCode = contributionAspectCode;
        this.contributionAspectName = contributionAspectName;
    }

    public int getContributionAspectCode() {
        return contributionAspectCode;
    }

    public void setContributionAspectCode(int contributionAspectCode) {
        this.contributionAspectCode = contributionAspectCode;
    }

    public String getContributionAspectName() {
        return contributionAspectName;
    }

    public void setContributionAspectName(String contributionAspectName) {
        this.contributionAspectName = contributionAspectName;
    }
}
