package livelihoodzone.dto.reports.zonal.charts;

public class ZoneLevelChartsRequestDto {
    private int countyId;
    private int questionnaireSectionCode;

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public int getQuestionnaireSectionCode() {
        return questionnaireSectionCode;
    }

    public void setQuestionnaireSectionCode(int questionnaireSectionCode) {
        this.questionnaireSectionCode = questionnaireSectionCode;
    }
}
