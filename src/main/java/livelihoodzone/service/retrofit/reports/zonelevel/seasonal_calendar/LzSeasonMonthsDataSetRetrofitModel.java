package livelihoodzone.service.retrofit.reports.zonelevel.seasonal_calendar;

import com.google.gson.annotations.SerializedName;

public class LzSeasonMonthsDataSetRetrofitModel {
    @SerializedName("LzSeasonName")
    private String lzSeasonName;

    @SerializedName("LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @SerializedName("MonthCode")
    private int monthCode;

    @SerializedName("MonthName")
    private String monthName;

    @SerializedName("LzSeasonCode")
    private int lzSeasonCode;

    public String getLzSeasonName() {
        return lzSeasonName;
    }

    public void setLzSeasonName(String lzSeasonName) {
        this.lzSeasonName = lzSeasonName;
    }

    public int getLzQuestionnaireSessionId() {
        return lzQuestionnaireSessionId;
    }

    public void setLzQuestionnaireSessionId(int lzQuestionnaireSessionId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
    }

    public int getMonthCode() {
        return monthCode;
    }

    public void setMonthCode(int monthCode) {
        this.monthCode = monthCode;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public int getLzSeasonCode() {
        return lzSeasonCode;
    }

    public void setLzSeasonCode(int lzSeasonCode) {
        this.lzSeasonCode = lzSeasonCode;
    }
}
