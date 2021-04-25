package livelihoodzone.service.retrofit.livelihoodzones;

import com.google.gson.annotations.SerializedName;

public class CountyLivelihoodZonesRetrofitModel {
    @SerializedName("CountyLivelihoodZoneAssignmentId")
    private int countyLivelihoodZoneAssignmentId;

    @SerializedName("CountyId")
    private int countyId;

    @SerializedName("CountyName")
    private String countyName;

    @SerializedName("LivelihoodZoneId")
    private int livelihoodZoneId;

    @SerializedName("LivelihoodZoneName")
    private String livelihoodZoneName;

    @SerializedName("LivelihoodZoneCode")
    private int livelihoodZoneCode;

    public int getCountyLivelihoodZoneAssignmentId() {
        return countyLivelihoodZoneAssignmentId;
    }

    public void setCountyLivelihoodZoneAssignmentId(int countyLivelihoodZoneAssignmentId) {
        this.countyLivelihoodZoneAssignmentId = countyLivelihoodZoneAssignmentId;
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

    public int getLivelihoodZoneCode() {
        return livelihoodZoneCode;
    }

    public void setLivelihoodZoneCode(int livelihoodZoneCode) {
        this.livelihoodZoneCode = livelihoodZoneCode;
    }
}
