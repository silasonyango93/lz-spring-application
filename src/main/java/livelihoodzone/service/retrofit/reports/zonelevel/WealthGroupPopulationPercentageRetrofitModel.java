package livelihoodzone.service.retrofit.reports.zonelevel;

import com.google.gson.annotations.SerializedName;

public class WealthGroupPopulationPercentageRetrofitModel {
    @SerializedName("LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @SerializedName("WealthGroupCode")
    private int wealthGroupCode;

    @SerializedName("WealthGroupDescription")
    private String wealthGroupDescription;

    @SerializedName("PopulationPercentage")
    private double populationPercentage;

    public int getLzQuestionnaireSessionId() {
        return lzQuestionnaireSessionId;
    }

    public void setLzQuestionnaireSessionId(int lzQuestionnaireSessionId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
    }

    public int getWealthGroupCode() {
        return wealthGroupCode;
    }

    public void setWealthGroupCode(int wealthGroupCode) {
        this.wealthGroupCode = wealthGroupCode;
    }

    public String getWealthGroupDescription() {
        return wealthGroupDescription;
    }

    public void setWealthGroupDescription(String wealthGroupDescription) {
        this.wealthGroupDescription = wealthGroupDescription;
    }

    public double getPopulationPercentage() {
        return populationPercentage;
    }

    public void setPopulationPercentage(double populationPercentage) {
        this.populationPercentage = populationPercentage;
    }
}
