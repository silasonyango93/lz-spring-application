package livelihoodzone.dto;

import livelihoodzone.entity.administrative_boundaries.sublocation.SubLocationEntity;

import java.util.List;

public class LivelihoodZoneSampledSubLocationsObject {
    private int livelihoodZoneId;
    private String livelihoodZoneName;
    private List<SubLocationEntity> sampledSubLocations;

    public LivelihoodZoneSampledSubLocationsObject() {
    }

    public LivelihoodZoneSampledSubLocationsObject(int livelihoodZoneId, String livelihoodZoneName, List<SubLocationEntity> sampledSubLocations) {
        this.livelihoodZoneId = livelihoodZoneId;
        this.livelihoodZoneName = livelihoodZoneName;
        this.sampledSubLocations = sampledSubLocations;
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

    public List<SubLocationEntity> getSampledSubLocations() {
        return sampledSubLocations;
    }

    public void setSampledSubLocations(List<SubLocationEntity> sampledSubLocations) {
        this.sampledSubLocations = sampledSubLocations;
    }
}
