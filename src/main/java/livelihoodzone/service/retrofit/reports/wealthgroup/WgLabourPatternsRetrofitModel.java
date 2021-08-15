package livelihoodzone.service.retrofit.reports.wealthgroup;

import com.google.gson.annotations.SerializedName;

public class WgLabourPatternsRetrofitModel {
    @SerializedName("WgQuestionnaireSessionId")
    private int wgQuestionnaireSessionId;

    @SerializedName("LivelihoodActivityDescription")
    private String livelihoodActivityDescription;

    @SerializedName("MenPercentage")
    private double menPercentage;

    @SerializedName("WomenPercentage")
    private double womenPercentage;

    @SerializedName("LivelihoodActivityCode")
    private int livelihoodActivityCode;

    public int getWgQuestionnaireSessionId() {
        return wgQuestionnaireSessionId;
    }

    public void setWgQuestionnaireSessionId(int wgQuestionnaireSessionId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
    }

    public String getLivelihoodActivityDescription() {
        return livelihoodActivityDescription;
    }

    public void setLivelihoodActivityDescription(String livelihoodActivityDescription) {
        this.livelihoodActivityDescription = livelihoodActivityDescription;
    }

    public double getMenPercentage() {
        return menPercentage;
    }

    public void setMenPercentage(double menPercentage) {
        this.menPercentage = menPercentage;
    }

    public double getWomenPercentage() {
        return womenPercentage;
    }

    public void setWomenPercentage(double womenPercentage) {
        this.womenPercentage = womenPercentage;
    }

    public int getLivelihoodActivityCode() {
        return livelihoodActivityCode;
    }

    public void setLivelihoodActivityCode(int livelihoodActivityCode) {
        this.livelihoodActivityCode = livelihoodActivityCode;
    }
}
