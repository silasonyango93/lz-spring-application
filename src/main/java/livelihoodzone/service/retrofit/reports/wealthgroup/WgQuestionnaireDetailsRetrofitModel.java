package livelihoodzone.service.retrofit.reports.wealthgroup;

import com.google.gson.annotations.SerializedName;

public class WgQuestionnaireDetailsRetrofitModel {
    @SerializedName("WgQuestionnaireSessionId")
    private int wgQuestionnaireSessionId;

    @SerializedName("QuestionnaireSessionDescription")
    private String questionnaireSessionDescription;

    @SerializedName("Latitude")
    private double latitude;

    @SerializedName("Longitude")
    private double longitude;

    @SerializedName("SessionStartDate")
    private String SessionStartDate;

    @SerializedName("SessionEndDate")
    private String sessionEndDate;

    @SerializedName("QuestionnaireUniqueId")
    private String questionnaireUniqueId;

    @SerializedName("WgQuestionnaireTypeDescription")
    private String wgQuestionnaireTypeDescription;

    @SerializedName("UserFirstName")
    private String dataCollectorFirstName;

    @SerializedName("UserMiddleName")
    private String dataCollectorMiddleName;

    @SerializedName("UserSurname")
    private String dataCollectorSurname;

    @SerializedName("OrganizationName")
    private String organizationName;

    @SerializedName("WealthGroupDescription")
    private String wealthGroupDescription;

    @SerializedName("CountyName")
    private String countyName;

    @SerializedName("SubCountyName")
    private String subCountyName;

    @SerializedName("WardName")
    private String wardName;

    @SerializedName("SubLocationName")
    private String subLocationName;

    @SerializedName("LivelihoodZoneName")
    private String livelihoodZoneName;

    public int getWgQuestionnaireSessionId() {
        return wgQuestionnaireSessionId;
    }

    public void setWgQuestionnaireSessionId(int wgQuestionnaireSessionId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
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
        return SessionStartDate;
    }

    public void setSessionStartDate(String sessionStartDate) {
        SessionStartDate = sessionStartDate;
    }

    public String getSessionEndDate() {
        return sessionEndDate;
    }

    public void setSessionEndDate(String sessionEndDate) {
        this.sessionEndDate = sessionEndDate;
    }

    public String getQuestionnaireUniqueId() {
        return questionnaireUniqueId;
    }

    public void setQuestionnaireUniqueId(String questionnaireUniqueId) {
        this.questionnaireUniqueId = questionnaireUniqueId;
    }

    public String getWgQuestionnaireTypeDescription() {
        return wgQuestionnaireTypeDescription;
    }

    public void setWgQuestionnaireTypeDescription(String wgQuestionnaireTypeDescription) {
        this.wgQuestionnaireTypeDescription = wgQuestionnaireTypeDescription;
    }

    public String getDataCollectorFirstName() {
        return dataCollectorFirstName;
    }

    public void setDataCollectorFirstName(String dataCollectorFirstName) {
        this.dataCollectorFirstName = dataCollectorFirstName;
    }

    public String getDataCollectorMiddleName() {
        return dataCollectorMiddleName;
    }

    public void setDataCollectorMiddleName(String dataCollectorMiddleName) {
        this.dataCollectorMiddleName = dataCollectorMiddleName;
    }

    public String getDataCollectorSurname() {
        return dataCollectorSurname;
    }

    public void setDataCollectorSurname(String dataCollectorSurname) {
        this.dataCollectorSurname = dataCollectorSurname;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getWealthGroupDescription() {
        return wealthGroupDescription;
    }

    public void setWealthGroupDescription(String wealthGroupDescription) {
        this.wealthGroupDescription = wealthGroupDescription;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getSubCountyName() {
        return subCountyName;
    }

    public void setSubCountyName(String subCountyName) {
        this.subCountyName = subCountyName;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getSubLocationName() {
        return subLocationName;
    }

    public void setSubLocationName(String subLocationName) {
        this.subLocationName = subLocationName;
    }

    public String getLivelihoodZoneName() {
        return livelihoodZoneName;
    }

    public void setLivelihoodZoneName(String livelihoodZoneName) {
        this.livelihoodZoneName = livelihoodZoneName;
    }
}
