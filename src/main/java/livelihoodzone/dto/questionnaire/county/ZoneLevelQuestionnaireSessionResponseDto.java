package livelihoodzone.dto.questionnaire.county;

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

    public ZoneLevelQuestionnaireSessionResponseDto() {
    }

    public ZoneLevelQuestionnaireSessionResponseDto(int questionnaireSessionId, int userId, int countyId, int livelihoodZoneId, String questionnaireSessionDescription, double latitude, double longitude, String sessionStartDate, String sessionEndDate, String questionnaireUniqueId) {
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
}
