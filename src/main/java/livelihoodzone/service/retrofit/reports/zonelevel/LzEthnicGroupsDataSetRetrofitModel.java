package livelihoodzone.service.retrofit.reports.zonelevel;

import com.google.gson.annotations.SerializedName;

public class LzEthnicGroupsDataSetRetrofitModel {
    @SerializedName("LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @SerializedName("EthnicGroupName")
    private String ethnicGroupName;

    @SerializedName("PopulationPercentage")
    private double populationPercentage;

    public int getLzQuestionnaireSessionId() {
        return lzQuestionnaireSessionId;
    }

    public void setLzQuestionnaireSessionId(int lzQuestionnaireSessionId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
    }

    public String getEthnicGroupName() {
        return ethnicGroupName;
    }

    public void setEthnicGroupName(String ethnicGroupName) {
        this.ethnicGroupName = ethnicGroupName;
    }

    public double getPopulationPercentage() {
        return populationPercentage;
    }

    public void setPopulationPercentage(double populationPercentage) {
        this.populationPercentage = populationPercentage;
    }
}
