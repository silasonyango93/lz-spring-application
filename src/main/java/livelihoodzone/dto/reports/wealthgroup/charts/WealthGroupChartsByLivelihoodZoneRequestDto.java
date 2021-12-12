package livelihoodzone.dto.reports.wealthgroup.charts;

public class WealthGroupChartsByLivelihoodZoneRequestDto {
    private int countyId;
    private int livelihoodZoneId;
    private int questionnaireSectionCode;

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public int getLivelihoodZoneId() {
        return livelihoodZoneId;
    }

    public void setLivelihoodZoneId(int livelihoodZoneId) {
        this.livelihoodZoneId = livelihoodZoneId;
    }

    public int getQuestionnaireSectionCode() {
        return questionnaireSectionCode;
    }

    public void setQuestionnaireSectionCode(int questionnaireSectionCode) {
        this.questionnaireSectionCode = questionnaireSectionCode;
    }
}
