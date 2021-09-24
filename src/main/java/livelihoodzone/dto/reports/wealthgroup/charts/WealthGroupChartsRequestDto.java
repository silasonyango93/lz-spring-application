package livelihoodzone.dto.reports.wealthgroup.charts;

public class WealthGroupChartsRequestDto {
    private int countyId;
    private int wealthGroupId;
    private int questionnaireSectionCode;

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

    public int getQuestionnaireSectionCode() {
        return questionnaireSectionCode;
    }

    public void setQuestionnaireSectionCode(int questionnaireSectionCode) {
        this.questionnaireSectionCode = questionnaireSectionCode;
    }
}
