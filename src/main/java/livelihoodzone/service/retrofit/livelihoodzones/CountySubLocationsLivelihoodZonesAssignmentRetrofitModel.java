package livelihoodzone.service.retrofit.livelihoodzones;

import com.google.gson.annotations.SerializedName;

public class CountySubLocationsLivelihoodZonesAssignmentRetrofitModel {
    @SerializedName("LzSublocationLivelihoodZoneId")
    private int lzSublocationLivelihoodZoneId;

    @SerializedName("CountyId")
    private int countyId;

    @SerializedName("CountyName")
    private String countyName;

    @SerializedName("SubLocationId")
    private int subLocationId;

    @SerializedName("SubLocationName")
    private String subLocationName;

    @SerializedName("LivelihoodZoneId")
    private int livelihoodZoneId;

    @SerializedName("LivelihoodZoneName")
    private String livelihoodZoneName;

    public int getLzSublocationLivelihoodZoneId() {
        return lzSublocationLivelihoodZoneId;
    }

    public void setLzSublocationLivelihoodZoneId(int lzSublocationLivelihoodZoneId) {
        this.lzSublocationLivelihoodZoneId = lzSublocationLivelihoodZoneId;
    }

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

    public int getSubLocationId() {
        return subLocationId;
    }

    public void setSubLocationId(int subLocationId) {
        this.subLocationId = subLocationId;
    }

    public String getSubLocationName() {
        return subLocationName;
    }

    public void setSubLocationName(String subLocationName) {
        this.subLocationName = subLocationName;
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
}
