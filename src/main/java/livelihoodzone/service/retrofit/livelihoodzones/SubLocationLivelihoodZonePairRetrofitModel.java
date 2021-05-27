package livelihoodzone.service.retrofit.livelihoodzones;

import com.google.gson.annotations.SerializedName;

public class SubLocationLivelihoodZonePairRetrofitModel {

    @SerializedName("LzSublocationLivelihoodZoneId")
    private int lzSublocationLivelihoodZoneId;

    @SerializedName("SubLocationId")
    private int subLocationId;

    @SerializedName("LivelihoodZoneId")
    private int livelihoodZoneId;

    public int getLzSublocationLivelihoodZoneId() {
        return lzSublocationLivelihoodZoneId;
    }

    public void setLzSublocationLivelihoodZoneId(int lzSublocationLivelihoodZoneId) {
        this.lzSublocationLivelihoodZoneId = lzSublocationLivelihoodZoneId;
    }

    public int getSubLocationId() {
        return subLocationId;
    }

    public void setSubLocationId(int subLocationId) {
        this.subLocationId = subLocationId;
    }

    public int getLivelihoodZoneId() {
        return livelihoodZoneId;
    }

    public void setLivelihoodZoneId(int livelihoodZoneId) {
        this.livelihoodZoneId = livelihoodZoneId;
    }
}
