package livelihoodzone.service.retrofit.reports.wealthgroup;

import com.google.gson.annotations.SerializedName;

public class WgIncomeSourcesRetrofitModel {
    @SerializedName("WgQuestionnaireSessionId")
    private int wgQuestionnaireSessionId;

    @SerializedName("CashIncomeSourceId")
    private int cashIncomeSourceId;

    @SerializedName("IncomeSourcePercentage")
    private double incomeSourcePercentage;

    @SerializedName("ExtraDescription")
    private String extraDescription;

    @SerializedName("CashIncomeSourceDescription")
    private String cashIncomeSourceDescription;

    public int getWgQuestionnaireSessionId() {
        return wgQuestionnaireSessionId;
    }

    public void setWgQuestionnaireSessionId(int wgQuestionnaireSessionId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
    }

    public int getCashIncomeSourceId() {
        return cashIncomeSourceId;
    }

    public void setCashIncomeSourceId(int cashIncomeSourceId) {
        this.cashIncomeSourceId = cashIncomeSourceId;
    }

    public double getIncomeSourcePercentage() {
        return incomeSourcePercentage;
    }

    public void setIncomeSourcePercentage(double incomeSourcePercentage) {
        this.incomeSourcePercentage = incomeSourcePercentage;
    }

    public String getExtraDescription() {
        return extraDescription;
    }

    public void setExtraDescription(String extraDescription) {
        this.extraDescription = extraDescription;
    }

    public String getCashIncomeSourceDescription() {
        return cashIncomeSourceDescription;
    }

    public void setCashIncomeSourceDescription(String cashIncomeSourceDescription) {
        this.cashIncomeSourceDescription = cashIncomeSourceDescription;
    }
}
