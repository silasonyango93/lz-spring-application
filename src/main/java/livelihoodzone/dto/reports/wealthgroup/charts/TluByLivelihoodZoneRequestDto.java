package livelihoodzone.dto.reports.wealthgroup.charts;

public class TluByLivelihoodZoneRequestDto {
    private int countyId;
    private int livelihoodZoneId;

    public TluByLivelihoodZoneRequestDto() {
    }

    public TluByLivelihoodZoneRequestDto(int countyId, int livelihoodZoneId) {
        this.countyId = countyId;
        this.livelihoodZoneId = livelihoodZoneId;
    }

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
}
