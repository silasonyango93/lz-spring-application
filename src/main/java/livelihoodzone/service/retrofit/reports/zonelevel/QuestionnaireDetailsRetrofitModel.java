package livelihoodzone.service.retrofit.reports.zonelevel;

import com.google.gson.annotations.SerializedName;

public class QuestionnaireDetailsRetrofitModel {
    @SerializedName("QuestionnaireSessionDescription")
    private String questionnaireSessionDescription;

    @SerializedName("Latitude")
    private double Latitude;

    @SerializedName("Longitude")
    private double longitude;

    @SerializedName("SessionStartDate")
    private String sessionStartDate;

    @SerializedName("SessionEndDate")
    private String sessionEndDate;

    @SerializedName("LzQuestionnaireUniqueId")
    private String lzQuestionnaireUniqueId;

    @SerializedName("UserFirstName")
    private String userFirstName;

    @SerializedName("UserMiddleName")
    private String userMiddleName;

    @SerializedName("UserSurname")
    private String userSurname;

    @SerializedName("OrganizationName")
    private String organizationName;

    @SerializedName("CountyName")
    private String countyName;

    @SerializedName("LivelihoodZoneName")
    private String livelihoodZoneName;

    public String getQuestionnaireSessionDescription() {
        return questionnaireSessionDescription;
    }

    public void setQuestionnaireSessionDescription(String questionnaireSessionDescription) {
        this.questionnaireSessionDescription = questionnaireSessionDescription;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
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

    public String getLzQuestionnaireUniqueId() {
        return lzQuestionnaireUniqueId;
    }

    public void setLzQuestionnaireUniqueId(String lzQuestionnaireUniqueId) {
        this.lzQuestionnaireUniqueId = lzQuestionnaireUniqueId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserMiddleName() {
        return userMiddleName;
    }

    public void setUserMiddleName(String userMiddleName) {
        this.userMiddleName = userMiddleName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
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
