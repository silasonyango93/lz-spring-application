package livelihoodzone.service.retrofit.reports.wealthgroup;

import com.google.gson.annotations.SerializedName;

public class WgWealthGroupSummaryAssociatedRawDataRetrofitModel {
    @SerializedName("WgQuestionnaireSessionId")
    private int wgQuestionnaireSessionId;

    @SerializedName("UserId")
    private int userId;

    @SerializedName("WgQuestionnaireTypeId")
    private int wgQuestionnaireTypeId;

    @SerializedName("WealthGroupId")
    private int wealthGroupId;

    @SerializedName("CountyId")
    private int countyId;

    @SerializedName("SubCountyId")
    private int subCountyId;

    @SerializedName("WardId")
    private int wardId;

    @SerializedName("SubLocationId")
    private int subLocationId;

    @SerializedName("LivelihoodZoneId")
    private int livelihoodZoneId;

    @SerializedName("QuestionnaireSessionDescription")
    private String questionnaireSessionDescription;

    @SerializedName("Latitude")
    private double latitude;

    @SerializedName("Longitude")
    private double longitude;

    @SerializedName("SessionStartDate")
    private String sessionStartDate;

    @SerializedName("SessionEndDate")
    private String sessionEndDate;

    @SerializedName("HasBeenSubmitted")
    private int hasBeenSubmitted;

    @SerializedName("QuestionnaireUniqueId")
    private String questionnaireUniqueId;

    @SerializedName("QuestionnaireJsonString")
    private String questionnaireJsonString;


    public int getWgQuestionnaireSessionId() {
        return wgQuestionnaireSessionId;
    }

    public void setWgQuestionnaireSessionId(int wgQuestionnaireSessionId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWgQuestionnaireTypeId() {
        return wgQuestionnaireTypeId;
    }

    public void setWgQuestionnaireTypeId(int wgQuestionnaireTypeId) {
        this.wgQuestionnaireTypeId = wgQuestionnaireTypeId;
    }

    public int getWealthGroupId() {
        return wealthGroupId;
    }

    public void setWealthGroupId(int wealthGroupId) {
        this.wealthGroupId = wealthGroupId;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public int getSubCountyId() {
        return subCountyId;
    }

    public void setSubCountyId(int subCountyId) {
        this.subCountyId = subCountyId;
    }

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
    }

    public int getSubLocationId() {
        return subLocationId;
    }

    public void setSubLocationId(int subLocationId) {
        this.subLocationId = subLocationId;
    }

    public int getLivelihoodZoneId() {
        return livelihoodZoneId;
    }

    public void setLivelihoodZoneId(int livelihoodZoneId) {
        this.livelihoodZoneId = livelihoodZoneId;
    }

    public String getQuestionnaireSessionDescription() {
        return questionnaireSessionDescription;
    }

    public void setQuestionnaireSessionDescription(String questionnaireSessionDescription) {
        this.questionnaireSessionDescription = questionnaireSessionDescription;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getSessionStartDate() {
        return sessionStartDate;
    }

    public void setSessionStartDate(String sessionStartDate) {
        this.sessionStartDate = sessionStartDate;
    }

    public String getSessionEndDate() {
        return sessionEndDate;
    }

    public void setSessionEndDate(String sessionEndDate) {
        this.sessionEndDate = sessionEndDate;
    }

    public int getHasBeenSubmitted() {
        return hasBeenSubmitted;
    }

    public void setHasBeenSubmitted(int hasBeenSubmitted) {
        this.hasBeenSubmitted = hasBeenSubmitted;
    }

    public String getQuestionnaireUniqueId() {
        return questionnaireUniqueId;
    }

    public void setQuestionnaireUniqueId(String questionnaireUniqueId) {
        this.questionnaireUniqueId = questionnaireUniqueId;
    }

    public String getQuestionnaireJsonString() {
        return questionnaireJsonString;
    }

    public void setQuestionnaireJsonString(String questionnaireJsonString) {
        this.questionnaireJsonString = questionnaireJsonString;
    }
}
