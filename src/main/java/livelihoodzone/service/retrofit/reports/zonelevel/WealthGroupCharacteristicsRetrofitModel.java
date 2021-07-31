package livelihoodzone.service.retrofit.reports.zonelevel;

import com.google.gson.annotations.SerializedName;

public class WealthGroupCharacteristicsRetrofitModel {
    @SerializedName("WealthGroupId")
    private int wealthGroupId;

    @SerializedName("WealthGroupDescription")
    private String wealthGroupDescription;

    @SerializedName("WealthGroupCode")
    private int wealthGroupCode;

    @SerializedName("LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @SerializedName("CharectaristicDescription")
    private String charectaristicDescription;

    @SerializedName("CountyName")
    private String countyName;

    @SerializedName("LivelihoodZoneName")
    private String livelihoodZoneName;

    public int getWealthGroupId() {
        return wealthGroupId;
    }

    public void setWealthGroupId(int wealthGroupId) {
        this.wealthGroupId = wealthGroupId;
    }

    public String getWealthGroupDescription() {
        return wealthGroupDescription;
    }

    public void setWealthGroupDescription(String wealthGroupDescription) {
        this.wealthGroupDescription = wealthGroupDescription;
    }

    public int getWealthGroupCode() {
        return wealthGroupCode;
    }

    public void setWealthGroupCode(int wealthGroupCode) {
        this.wealthGroupCode = wealthGroupCode;
    }

    public int getLzQuestionnaireSessionId() {
        return lzQuestionnaireSessionId;
    }

    public void setLzQuestionnaireSessionId(int lzQuestionnaireSessionId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
    }

    public String getCharectaristicDescription() {
        return charectaristicDescription;
    }

    public void setCharectaristicDescription(String charectaristicDescription) {
        this.charectaristicDescription = charectaristicDescription;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getLivelihoodZoneName() {
        return livelihoodZoneName;
    }

    public void setLivelihoodZoneName(String livelihoodZoneName) {
        this.livelihoodZoneName = livelihoodZoneName;
    }
}
