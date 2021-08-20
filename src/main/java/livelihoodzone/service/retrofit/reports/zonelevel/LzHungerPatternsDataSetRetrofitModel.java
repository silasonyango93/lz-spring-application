package livelihoodzone.service.retrofit.reports.zonelevel;

import com.google.gson.annotations.SerializedName;

public class LzHungerPatternsDataSetRetrofitModel {
    @SerializedName("LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @SerializedName("RainySeasonName")
    private String rainySeasonName;

    @SerializedName("RainySeasonCode")
    private int rainySeasonCode;

    @SerializedName("YearsOfWidespreadHunger")
    private double yearsOfWidespreadHunger;

    public int getLzQuestionnaireSessionId() {
        return lzQuestionnaireSessionId;
    }

    public void setLzQuestionnaireSessionId(int lzQuestionnaireSessionId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
    }

    public String getRainySeasonName() {
        return rainySeasonName;
    }

    public void setRainySeasonName(String rainySeasonName) {
        this.rainySeasonName = rainySeasonName;
    }

    public int getRainySeasonCode() {
        return rainySeasonCode;
    }

    public void setRainySeasonCode(int rainySeasonCode) {
        this.rainySeasonCode = rainySeasonCode;
    }

    public double getYearsOfWidespreadHunger() {
        return yearsOfWidespreadHunger;
    }

    public void setYearsOfWidespreadHunger(double yearsOfWidespreadHunger) {
        this.yearsOfWidespreadHunger = yearsOfWidespreadHunger;
    }
}
