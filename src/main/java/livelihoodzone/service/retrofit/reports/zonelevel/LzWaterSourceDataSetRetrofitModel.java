package livelihoodzone.service.retrofit.reports.zonelevel;

import com.google.gson.annotations.SerializedName;

public class LzWaterSourceDataSetRetrofitModel {
    @SerializedName("LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @SerializedName("WaterSourceDescription")
    private String waterSourceDescription;

    @SerializedName("WaterSourceCode")
    private int waterSourceCode;

    @SerializedName("WetSeasonPercentage")
    private int wetSeasonPercentage;

    @SerializedName("DrySeasonPercentage")
    private int drySeasonPercentage;

    @SerializedName("ExtraDescription")
    private String extraDescription;

    public int getLzQuestionnaireSessionId() {
        return lzQuestionnaireSessionId;
    }

    public void setLzQuestionnaireSessionId(int lzQuestionnaireSessionId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
    }

    public String getWaterSourceDescription() {
        return waterSourceDescription;
    }

    public void setWaterSourceDescription(String waterSourceDescription) {
        this.waterSourceDescription = waterSourceDescription;
    }

    public int getWaterSourceCode() {
        return waterSourceCode;
    }

    public void setWaterSourceCode(int waterSourceCode) {
        this.waterSourceCode = waterSourceCode;
    }

    public int getWetSeasonPercentage() {
        return wetSeasonPercentage;
    }

    public void setWetSeasonPercentage(int wetSeasonPercentage) {
        this.wetSeasonPercentage = wetSeasonPercentage;
    }

    public int getDrySeasonPercentage() {
        return drySeasonPercentage;
    }

    public void setDrySeasonPercentage(int drySeasonPercentage) {
        this.drySeasonPercentage = drySeasonPercentage;
    }

    public String getExtraDescription() {
        return extraDescription;
    }

    public void setExtraDescription(String extraDescription) {
        this.extraDescription = extraDescription;
    }
}
