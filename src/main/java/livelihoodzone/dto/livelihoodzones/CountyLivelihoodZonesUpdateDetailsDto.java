package livelihoodzone.dto.livelihoodzones;

import java.util.List;

public class CountyLivelihoodZonesUpdateDetailsDto {
    private boolean active;
    private int countyId;
    private List<LivelihoodZonesUpdateRequestModel> livelihoodZoneIds;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public List<LivelihoodZonesUpdateRequestModel> getLivelihoodZoneIds() {
        return livelihoodZoneIds;
    }

    public void setLivelihoodZoneIds(List<LivelihoodZonesUpdateRequestModel> livelihoodZoneIds) {
        this.livelihoodZoneIds = livelihoodZoneIds;
    }
}
