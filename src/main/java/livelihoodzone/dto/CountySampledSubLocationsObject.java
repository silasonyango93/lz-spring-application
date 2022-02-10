package livelihoodzone.dto;

import java.util.List;

public class CountySampledSubLocationsObject {
    private int countyId;
    private String countyName;
    private List<LivelihoodZoneSampledSubLocationsObject> livelihoodZoneSampledSubLocationsObjectList;

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

    public List<LivelihoodZoneSampledSubLocationsObject> getLivelihoodZoneSampledSubLocationsObjectList() {
        return livelihoodZoneSampledSubLocationsObjectList;
    }

    public void setLivelihoodZoneSampledSubLocationsObjectList(List<LivelihoodZoneSampledSubLocationsObject> livelihoodZoneSampledSubLocationsObjectList) {
        this.livelihoodZoneSampledSubLocationsObjectList = livelihoodZoneSampledSubLocationsObjectList;
    }
}
