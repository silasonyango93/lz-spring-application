package livelihoodzone.service.retrofit.reports.wealthgroup;

import com.google.gson.annotations.SerializedName;

public class WgConstraintsDataSetRetrofitModel {
    @SerializedName("WgQuestionnaireSessionId")
    private int wgQuestionnaireSessionId;

    @SerializedName("IncomeConstraintDescription")
    private String incomeConstraintDescription;

    @SerializedName("IncomeConstraintCode")
    private int incomeConstraintCode;

    @SerializedName("Rank")
    private int rank;

    public int getWgQuestionnaireSessionId() {
        return wgQuestionnaireSessionId;
    }

    public void setWgQuestionnaireSessionId(int wgQuestionnaireSessionId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
    }

    public String getIncomeConstraintDescription() {
        return incomeConstraintDescription;
    }

    public void setIncomeConstraintDescription(String incomeConstraintDescription) {
        this.incomeConstraintDescription = incomeConstraintDescription;
    }

    public int getIncomeConstraintCode() {
        return incomeConstraintCode;
    }

    public void setIncomeConstraintCode(int incomeConstraintCode) {
        this.incomeConstraintCode = incomeConstraintCode;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
