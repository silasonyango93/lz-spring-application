package livelihoodzone.service.retrofit.reports.zonelevel;

import com.google.gson.annotations.SerializedName;

public class LzCropProductionReportRetrofitModel {
    @SerializedName("LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @SerializedName("CropName")
    private String cropName;

    @SerializedName("RainySeasonCode")
    private int rainySeasonCode;

    @SerializedName("CropWaterAccessTypeCode")
    private int cropWaterAccessTypeCode;

    @SerializedName("CultivatedAreaPercentage")
    private double cultivatedAreaPercentage;

    @SerializedName("AverageYieldKgPerHectare")
    private double averageYieldKgPerHectare;

    @SerializedName("RainySeasonName")
    private String rainySeasonName;

    @SerializedName("CropWaterAccessTypeDescription")
    private String cropWaterAccessTypeDescription;

    public int getLzQuestionnaireSessionId() {
        return lzQuestionnaireSessionId;
    }

    public void setLzQuestionnaireSessionId(int lzQuestionnaireSessionId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public int getRainySeasonCode() {
        return rainySeasonCode;
    }

    public void setRainySeasonCode(int rainySeasonCode) {
        this.rainySeasonCode = rainySeasonCode;
    }

    public int getCropWaterAccessTypeCode() {
        return cropWaterAccessTypeCode;
    }

    public void setCropWaterAccessTypeCode(int cropWaterAccessTypeCode) {
        this.cropWaterAccessTypeCode = cropWaterAccessTypeCode;
    }

    public double getCultivatedAreaPercentage() {
        return cultivatedAreaPercentage;
    }

    public void setCultivatedAreaPercentage(double cultivatedAreaPercentage) {
        this.cultivatedAreaPercentage = cultivatedAreaPercentage;
    }

    public double getAverageYieldKgPerHectare() {
        return averageYieldKgPerHectare;
    }

    public void setAverageYieldKgPerHectare(double averageYieldKgPerHectare) {
        this.averageYieldKgPerHectare = averageYieldKgPerHectare;
    }

    public String getRainySeasonName() {
        return rainySeasonName;
    }

    public void setRainySeasonName(String rainySeasonName) {
        this.rainySeasonName = rainySeasonName;
    }

    public String getCropWaterAccessTypeDescription() {
        return cropWaterAccessTypeDescription;
    }

    public void setCropWaterAccessTypeDescription(String cropWaterAccessTypeDescription) {
        this.cropWaterAccessTypeDescription = cropWaterAccessTypeDescription;
    }
}
