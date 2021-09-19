package livelihoodzone.dto.reports.wealthgroup.charts;

public class WgCountyChartObject {
    private int countyId;
    private String countyName;
    private int livelihoodZoneId;
    private String livelihoodZoneName;
    private int wealthGroupId;
    private String wealthGroupName;
    private int questionnaireSectionId;

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public int getLivelihoodZoneId() {
        return livelihoodZoneId;
    }

    public void setLivelihoodZoneId(int livelihoodZoneId) {
        this.livelihoodZoneId = livelihoodZoneId;
    }

    public String getLivelihoodZoneName() {
        return livelihoodZoneName;
    }

    public void setLivelihoodZoneName(String livelihoodZoneName) {
        this.livelihoodZoneName = livelihoodZoneName;
    }

    public int getWealthGroupId() {
        return wealthGroupId;
    }

    public void setWealthGroupId(int wealthGroupId) {
        this.wealthGroupId = wealthGroupId;
    }

    public String getWealthGroupName() {
        return wealthGroupName;
    }

    public void setWealthGroupName(String wealthGroupName) {
        this.wealthGroupName = wealthGroupName;
    }

    public int getQuestionnaireSectionId() {
        return questionnaireSectionId;
    }

    public void setQuestionnaireSectionId(int questionnaireSectionId) {
        this.questionnaireSectionId = questionnaireSectionId;
    }
}
