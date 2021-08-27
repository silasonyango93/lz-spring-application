package livelihoodzone.service.retrofit.reports.wealthgroup;

import com.google.gson.annotations.SerializedName;

public class WgExpenditurePatternsDataSetRetrofitModel {
    @SerializedName("WgQuestionnaireSessionId")
    private int wgQuestionnaireSessionId;

    @SerializedName("ExpenditureItemDescription")
    private String expenditureItemDescription;

    @SerializedName("ExpenditureItemCode")
    private int expenditureItemCode;

    @SerializedName("ExpenditurePercentage")
    private double expenditurePercentage;

    public int getWgQuestionnaireSessionId() {
        return wgQuestionnaireSessionId;
    }

    public void setWgQuestionnaireSessionId(int wgQuestionnaireSessionId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
    }

    public String getExpenditureItemDescription() {
        return expenditureItemDescription;
    }

    public void setExpenditureItemDescription(String expenditureItemDescription) {
        this.expenditureItemDescription = expenditureItemDescription;
    }

    public int getExpenditureItemCode() {
        return expenditureItemCode;
    }

    public void setExpenditureItemCode(int expenditureItemCode) {
        this.expenditureItemCode = expenditureItemCode;
    }

    public double getExpenditurePercentage() {
        return expenditurePercentage;
    }

    public void setExpenditurePercentage(double expenditurePercentage) {
        this.expenditurePercentage = expenditurePercentage;
    }
}
