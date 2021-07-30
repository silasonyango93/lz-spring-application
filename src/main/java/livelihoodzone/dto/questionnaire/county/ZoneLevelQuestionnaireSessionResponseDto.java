package livelihoodzone.dto.questionnaire.county;

import livelihoodzone.dto.user_management.UserResponseDTO;

public class ZoneLevelQuestionnaireSessionResponseDto {
    private int questionnaireSessionId;
    private int userId;
    private int countyId;
    private int livelihoodZoneId;
    private String questionnaireSessionDescription;
    private double latitude;
    private double longitude;
    private String sessionStartDate;
    private String sessionEndDate;
    private String questionnaireUniqueId;
    private String countyName;
    private String livelihoodzoneName;
    private UserResponseDTO userDetails;

    public ZoneLevelQuestionnaireSessionResponseDto() {
    }

    public ZoneLevelQuestionnaireSessionResponseDto(int questionnaireSessionId, int userId, int countyId, int livelihoodZoneId, String questionnaireSessionDescription, double latitude, double longitude, String sessionStartDate, String sessionEndDate, String questionnaireUniqueId, String countyName, String livelihoodzoneName, UserResponseDTO userDetails) {
        this.questionnaireSessionId = questionnaireSessionId;
        this.userId = userId;
        this.countyId = countyId;
        this.livelihoodZoneId = livelihoodZoneId;
        this.questionnaireSessionDescription = questionnaireSessionDescription;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sessionStartDate = sessionStartDate;
        this.sessionEndDate = sessionEndDate;
        this.questionnaireUniqueId = questionnaireUniqueId;
        this.countyName = countyName;
        this.livelihoodzoneName = livelihoodzoneName;
        this.userDetails = userDetails;
    }

    public int getQuestionnaireSessionId() {
        return questionnaireSessionId;
    }

    public void setQuestionnaireSessionId(int questionnaireSessionId) {
        this.questionnaireSessionId = questionnaireSessionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
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

    public String getQuestionnaireUniqueId() {
        return questionnaireUniqueId;
    }

    public void setQuestionnaireUniqueId(String questionnaireUniqueId) {
        this.questionnaireUniqueId = questionnaireUniqueId;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getLivelihoodzoneName() {
        return livelihoodzoneName;
    }

    public void setLivelihoodzoneName(String livelihoodzoneName) {
        this.livelihoodzoneName = livelihoodzoneName;
    }

    public UserResponseDTO getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserResponseDTO userDetails) {
        this.userDetails = userDetails;
    }
}
