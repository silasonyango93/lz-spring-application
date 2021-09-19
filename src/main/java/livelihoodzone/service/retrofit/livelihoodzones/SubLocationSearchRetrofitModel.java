package livelihoodzone.service.retrofit.livelihoodzones;

import com.google.gson.annotations.SerializedName;

public class SubLocationSearchRetrofitModel {
    @SerializedName("SubLocationId")
    private int subLocationId;

    @SerializedName("SubLocationName")
    private String subLocationName;

    @SerializedName("WardName")
    private String wardName;

    @SerializedName("SubCountyName")
    private String subCountyName;

    @SerializedName("CountyId")
    private int countyId;

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

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getSubCountyName() {
        return subCountyName;
    }

    public void setSubCountyName(String subCountyName) {
        this.subCountyName = subCountyName;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }
}
