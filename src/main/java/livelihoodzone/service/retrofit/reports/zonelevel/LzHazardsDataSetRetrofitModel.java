package livelihoodzone.service.retrofit.reports.zonelevel;

import com.google.gson.annotations.SerializedName;

public class LzHazardsDataSetRetrofitModel {
    @SerializedName("LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @SerializedName("LzHazardDescription")
    private String lzHazardDescription;

    @SerializedName("LzHazardCode")
    private int LzHazardCode;

    @SerializedName("Rank")
    private int rank;

    @SerializedName("YearsExperienced")
    private double yearsExperienced;

    public int getLzQuestionnaireSessionId() {
        return lzQuestionnaireSessionId;
    }

    public void setLzQuestionnaireSessionId(int lzQuestionnaireSessionId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
    }

    public String getLzHazardDescription() {
        return lzHazardDescription;
    }

    public void setLzHazardDescription(String lzHazardDescription) {
        this.lzHazardDescription = lzHazardDescription;
    }

    public int getLzHazardCode() {
        return LzHazardCode;
    }

    public void setLzHazardCode(int lzHazardCode) {
        LzHazardCode = lzHazardCode;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getYearsExperienced() {
        return yearsExperienced;
    }

    public void setYearsExperienced(double yearsExperienced) {
        this.yearsExperienced = yearsExperienced;
    }
}
