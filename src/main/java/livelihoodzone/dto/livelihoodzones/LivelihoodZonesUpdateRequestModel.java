package livelihoodzone.dto.livelihoodzones;

public class LivelihoodZonesUpdateRequestModel {
    private int livelihoodZoneId;

    public LivelihoodZonesUpdateRequestModel() {
    }

    public LivelihoodZonesUpdateRequestModel(int livelihoodZoneId) {
        this.livelihoodZoneId = livelihoodZoneId;
    }

    public int getLivelihoodZoneId() {
        return livelihoodZoneId;
    }

    public void setLivelihoodZoneId(int livelihoodZoneId) {
        this.livelihoodZoneId = livelihoodZoneId;
    }
}
